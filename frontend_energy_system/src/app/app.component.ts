import { Component } from '@angular/core';
import { WebSocketAPI } from './websockets/websocket';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'frontend_energy_system';

  webSocketAPI: WebSocketAPI;
  notification: Notification;
  name: string;
  ngOnInit() {
    this.webSocketAPI = new WebSocketAPI(new AppComponent());
    this.connect();
  }

  connect(){
    this.webSocketAPI._connect();
  }

  disconnect(){
    this.webSocketAPI._disconnect();
  }

  sendMessage(){
    this.webSocketAPI._send(this.name);
  }

  handleMessage(message){
    this.notification = message;
  }
}
