import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { SignupUserRequest, UserRole } from '../models/loginuser.model';
import { AccountService } from '../services/account.service';

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.css']
})
export class SignupComponent implements OnInit {
  signUpUser: SignupUserRequest;
  roles: UserRole[];
  confirmationPassword: string = "";

  constructor(private accountService: AccountService, private router: Router) { }

  ngOnInit(): void {
    this.signUpUser = new SignupUserRequest();
    this.signUpUser.email = "";
    this.signUpUser.password = "";
    this.signUpUser.role = new UserRole(1, "Admin");
    this.signUpUser.firstName = "";
    this.signUpUser.lastName = "";
    this.signUpUser.username = "";
    this.accountService.getRoles().subscribe(r => this.roles = r)
  }

  onSignupClicked(): void {
    this.accountService.signupUser(this.signUpUser, this.confirmationPassword).subscribe(res => {
      localStorage.setItem("eshop-email", res.email);
      localStorage.setItem("eshop-username", res.username);
      localStorage.setItem("eshop-firstname", res.firstName);
      localStorage.setItem("eshop-lastname", res.lastName);
      localStorage.setItem("eshop-userid", res.id.toString());
      localStorage.setItem("eshop-jwt", res.token);
      localStorage.setItem("eshop-usertypeid", res.role.id.toString());
      localStorage.setItem("eshop-usertype", res.role.name);

      if (res.role.name == "Admin") {
        this.router.navigate(["/admin"]);
      } else if (res.role.name == "User") {
        this.router.navigate(["/shop"]);
      }
    }, _ => {
      alert('Bad credentials, please try again.');
    });
  }

  setRole(event) {
    this.signUpUser.role.id = event.target.value;
  }

}
