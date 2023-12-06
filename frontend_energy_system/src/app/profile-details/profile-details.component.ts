import { Component, OnInit } from '@angular/core';
import { LoginUserReply } from '../models/loginuser.model';
import { WebSocketSrvice } from '../services/websockets.service';

@Component({
  selector: 'app-profile-details',
  templateUrl: './profile-details.component.html',
  styleUrls: ['./profile-details.component.css']
})
export class ProfileDetailsComponent implements OnInit {
  loggedUser: LoginUserReply = new LoginUserReply();
  constructor(private webSocketService: WebSocketSrvice) { }

  ngOnInit(): void {
    this.loggedUser.email = localStorage.getItem("eshop-email");
    this.loggedUser.username = localStorage.getItem("eshop-username");
    this.loggedUser.firstName = localStorage.getItem("eshop-firstname");
    this.loggedUser.lastName = localStorage.getItem("eshop-lastname");
  }

  logout() {
    localStorage.clear();
    this.webSocketService.disconnect();
  }
}
