import { Injectable } from "@angular/core";
import { WebSocketAPI } from "../websockets/websocket";
import { ToastrService } from "ngx-toastr";
import { DeviceService } from "./device.service";

@Injectable ()
export class WebSocketSrvice {
    
    webSocketAPI: WebSocketAPI;
    name: string;

    public constructor(private toasterService: ToastrService, private deviceService: DeviceService) {
        this.webSocketAPI = new WebSocketAPI(this, deviceService);
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

    handleMessage(msg, name){
        this.toasterService.warning(msg + name);
    }
}