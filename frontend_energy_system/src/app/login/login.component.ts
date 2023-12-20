import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { LoginUserRequest } from '../models/loginuser.model';
import { AccountService } from '../services/account.service';
import { WebSocketSrvice } from '../services/websockets.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  loginUser: LoginUserRequest;
  constructor(private accountService: AccountService, private router: Router, 
    private webSocketService: WebSocketSrvice) { }

  ngOnInit(): void {
    this.loginUser = new LoginUserRequest();
    this.loginUser.username = "";
    this.loginUser.password = "";
  }

  onLoginClicked(): void {
    this.accountService.loginUser(this.loginUser).subscribe(res => {
      localStorage.setItem("eshop-email", res.email);
      localStorage.setItem("eshop-username", res.username);
      localStorage.setItem("eshop-firstname", res.firstName);
      localStorage.setItem("eshop-lastname", res.lastName);
      localStorage.setItem("eshop-userid", res.id.toString());
      localStorage.setItem("eshop-jwt", res.token);
      localStorage.setItem("eshop-usertypeid", res.role.id.toString());
      localStorage.setItem("eshop-usertype", res.role.name);
      // debugger;
      if (res.role.name == "Admin") {
        this.router.navigate(["/admin"]);
      } else if (res.role.name == "User") {
        this.router.navigate(["/shop"]);
        this.webSocketService.connect();
      }
    }, _ => {
      alert('Bad credentials, please try again.');
    });
  }
}
