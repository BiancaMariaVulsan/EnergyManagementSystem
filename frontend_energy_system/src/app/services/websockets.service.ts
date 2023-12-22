import { EventEmitter, Injectable, Output } from "@angular/core";
import { ToastrService } from "ngx-toastr";
import { DeviceService } from "./device.service";
import { Device } from "../models/device.model";
import * as SockJS from 'sockjs-client';
import { Stomp } from "@stomp/stompjs";
import { ChatNotificationMsg } from "../models/notification.model";
import { Observable, Subject, of } from "rxjs";

@Injectable ()
export class WebSocketSrvice {
    
    name: string;

    webSocketEndPoint: string = 'http://localhost:8001/ws';
    webSocketChatEndPoint: string = 'http://localhost:8002/ws';
    topic: string = "/topic/notification/" + localStorage.getItem("eshop-userid");
    stompClient: any;
    device: Device;
    chatNotificationMsg: ChatNotificationMsg;
    messageReceived: Subject<ChatNotificationMsg>= new Subject<ChatNotificationMsg>();

    public constructor(private toasterService: ToastrService, private deviceService: DeviceService) {
    }

    public connect(){
        this._connect();
    }

    connect_chat() {
        let socket = new SockJS(this.webSocketChatEndPoint);
        this.stompClient = Stomp.over(socket);
        const _this = this;
        _this.stompClient.connect({}, function (frame) {
          console.log('Connected: ' + frame);
          // Subscribe to the user's conversation topic
          _this.stompClient.subscribe(_this.topic, function (sdkEvent) {
            _this.onChatMessageReceived(sdkEvent);
          });
        });
      }

    public disconnect(){
        this._disconnect();
    }

    sendMessage(massage: string, personId: number) {
        this._send(massage, personId);
    }

    _connect() {
        console.log("Initialize WebSocket Connection");
        let ws = new SockJS(this.webSocketEndPoint);
        this.stompClient = Stomp.over(ws);
        const _this = this;
        _this.stompClient.connect({}, function (frame) {
            _this.stompClient.subscribe(_this.topic, function (sdkEvent) {
                _this.onMessageReceived(sdkEvent);
            });
            _this.stompClient.reconnect_delay = 2000;
        }, this.errorCallBack);
    };

    _disconnect() {
        if (this.stompClient !== null) {
            this.stompClient.disconnect();
        }
        console.log("Disconnected");
    }

    // on error, schedule a reconnection attempt
    errorCallBack(error) {
        console.log("errorCallBack -> " + error)
        setTimeout(() => {
            this._connect();
        }, 5000);
    }

    onMessageReceived(message) {
        const parsedBody = JSON.parse(message.body);

        // Accessing the 'body' and 'id' fields
        const notif = parsedBody.message;
        const deviceId = parsedBody.deviceId;
      
        // Now you can use 'body' and 'id' as needed
        console.log("Parsed Body:", notif);
        console.log("Device Id:", deviceId);

        this.device = this.deviceService.findDevice(deviceId);
        
        this.handleMessage(notif, this.device.description);
    }

    onChatMessageReceived(message){
        const parsedBody = JSON.parse(message.body);

        // Accessing the 'body' and 'id' fields
        const notif = parsedBody.message;
        const toPersonId = parsedBody.toPersonId;
        const fromPersonId = parsedBody.fromPersonId;
          
        // Now you can use 'body' and 'id' as needed
        console.log("Parsed Body:", notif);
        console.log("To Person Id:", toPersonId);
        console.log("From Person Id:", fromPersonId);

        this.chatNotificationMsg = new ChatNotificationMsg(notif, fromPersonId, toPersonId);
        this.messageReceived.next(this.chatNotificationMsg);
    }

    getChatNotification(): Observable<ChatNotificationMsg> {
        return of(this.chatNotificationMsg);
    }

    handleMessage(msg, name){
        this.toasterService.warning(msg + name);
    }

     _send(message, personToSendId: number) {
        console.log("sending message: " + message);
        const chatNotificationMsg = new ChatNotificationMsg(message, personToSendId, Number(localStorage.getItem("eshop-userid")));
        this.stompClient.send("/app/sendNotification", {}, JSON.stringify(chatNotificationMsg));
    }

    getStoredMessages(fromPersonId: number, toPersonId: number) {
        // Request stored messages from the server for a specific conversation
        // Send a JSON payload to the server
        this.stompClient.send("/app/getStoredMessages", {}, JSON.stringify({ fromPersonId, toPersonId }));
    }
}