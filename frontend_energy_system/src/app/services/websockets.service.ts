import { Injectable } from "@angular/core";
import { ToastrService } from "ngx-toastr";
import { DeviceService } from "./device.service";
import { Device } from "../models/device.model";
import * as SockJS from 'sockjs-client';
import { Stomp } from "@stomp/stompjs";
import { ChatNotificationMsg } from "../models/notification.model";
import { MessageService } from "./message.service";

@Injectable ()
export class WebSocketSrvice {
    
    name: string;

    webSocketEndPoint: string = 'http://localhost:8001/ws';
    webSocketChatEndPoint: string = 'http://localhost:8002/ws';
    topic: string = "/topic/notification/" + localStorage.getItem("eshop-userid");
    stompClient: any;
    device: Device;

    public constructor(private toasterService: ToastrService, private deviceService: DeviceService, private messageService: MessageService) {
    }

    public connect(){
        this._connect();
    }

    public connect_chat(){
        console.log("Initialize WebSocket Connection");
        let ws = new SockJS(this.webSocketChatEndPoint);
        this.stompClient = Stomp.over(ws);
        const _this = this;
        _this.stompClient.connect({}, function (frame) {
            _this.stompClient.subscribe(_this.topic, function (sdkEvent) {
                _this.onChatMessageReceived(sdkEvent);
            });
            _this.stompClient.reconnect_delay = 2000;
        }, this.errorCallBack);
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

    onChatMessageReceived(message) {
        const parsedBody = JSON.parse(message.body);

        // Accessing the 'body' and 'id' fields
        const notif = parsedBody.message;
        const toPersonId = parsedBody.toPersonId;
        const fromPersonId = parsedBody.fromPersonId;
      
        // Now you can use 'body' and 'id' as needed
        console.log("Parsed Body:", notif);
        console.log("To Person Id:", toPersonId);
        console.log("From Person Id:", fromPersonId);

        // Push the message to the shared service
        this.messageService.addMessage(notif);
        console.log("Chat Message Received: " + notif);
    }

    handleMessage(msg, name){
        this.toasterService.warning(msg + name);
    }

     _send(message, personToSendId: number) {
        console.log("sending message: " + message);
        const chatNotificationMsg = new ChatNotificationMsg(message, personToSendId, Number(localStorage.getItem("eshop-userid")));
        this.stompClient.send("/app/sendNotification", {}, JSON.stringify(chatNotificationMsg));
    }
}