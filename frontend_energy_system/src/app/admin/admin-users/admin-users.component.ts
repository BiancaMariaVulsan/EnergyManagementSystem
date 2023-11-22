import { Component, OnInit } from '@angular/core';
import { Client, LoginUserReply } from 'src/app/models/loginuser.model';
import { AccountService } from 'src/app/services/account.service';

@Component({
  selector: 'app-admin-users',
  templateUrl: './admin-users.component.html',
  styleUrls: ['./admin-users.component.css']
})
export class AdminUsersComponent implements OnInit {
  users: LoginUserReply[];
  activateAddEditUser = false;
  userOperation = 'Add';
  user = new Client();

  constructor(private accountService: AccountService) { }

  ngOnInit(): void {
    this.getAllUsers();
  }

  addClick(): void {
    this.user = new Client();
    this.userOperation = 'Add';
    this.activateAddEditUser = true;
  }

  closeClick(): void {
    this.activateAddEditUser = false;
    this.users = [];
    this.getAllUsers();
  }

  editClick(client: Client): void {
    this.user = client;
    this.userOperation = 'Edit';
    this.activateAddEditUser = true;
  }

  deleteClick(client: Client): void {
    this.accountService.deleteUser(client.id).subscribe(result => {
      if (result === true) {
        this.users.forEach( (item, index) => {
          if (item.id === client.id) {
            this.users.splice(index, 1);
        }});
      } else {
        alert('Error, could not delete the client!');
      }
    });
  }

  getAllUsers(): void {
    this.accountService.getUsers().subscribe(p => {
      this.users = p;
      console.log(this.users)
    });
  }
}
