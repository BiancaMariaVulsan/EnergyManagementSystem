import { Injectable } from "@angular/core";
import { WebSocketAPI } from "../websockets/websocket";
import { ToastrService } from "ngx-toastr";
import { NotificationMsg } from "../models/notification.model";

@Injectable ()
export class WebSocketSrvice {
    
    webSocketAPI: WebSocketAPI;
    notification: NotificationMsg;
    name: string;

    public constructor(private toasterService: ToastrService) {
        this.webSocketAPI = new WebSocketAPI(this);
    }

    public connect(){
        this.webSocketAPI._connect();
    }

    public disconnect(){
        this.webSocketAPI._disconnect();
    }

    sendMessage(){
        this.webSocketAPI._send(this.name);
    }

    handleMessage(notification: NotificationMsg){
        this.notification = notification;
        this.toasterService.warning(this.notification.message + " " + this.notification.deviceId);
    }
}