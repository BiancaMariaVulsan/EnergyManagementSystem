import * as SockJS from 'sockjs-client';
import { Stomp } from '@stomp/stompjs';
import { WebSocketSrvice } from '../services/websockets.service';
import { NotificationMsg } from '../models/notification.model';
import { DeviceService } from '../services/device.service';
import { Device } from '../models/device.model';

export class WebSocketAPI {
    webSocketEndPoint: string = 'http://localhost:8001/ws';
    topic: string = "/topic/notification/" + localStorage.getItem("eshop-userid");
    stompClient: any;
    device: Device;
    constructor(private websocketService: WebSocketSrvice, private deviceService: DeviceService){
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

 /**
  * Send message to sever via web socket
  * @param {*} message 
  */
    _send(message) {
        console.log("calling logout api via web socket");
        this.stompClient.send("/app/sendNotification", {}, JSON.stringify(message));
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
        
        this.websocketService.handleMessage(notif, this.device.description);
    }
}