import { Component, Input, OnInit } from '@angular/core';
import { ToastrService } from 'ngx-toastr';
import { Client, UserRole } from 'src/app/models/loginuser.model';
import { AccountService } from 'src/app/services/account.service';

@Component({
  selector: 'app-user-edit',
  templateUrl: './user-edit.component.html',
  styleUrls: ['./user-edit.component.css']
})
export class UserEditComponent implements OnInit {
  roleDropdownText = 'Pick role';

  constructor(
    private accountService: AccountService,
    // private encryptionService: EncryptionService,
    private toastr: ToastrService
  ) {}

  @Input() user: Client;
  roles: UserRole[] = [];
  ngOnInit(): void {
    this.initRoles();
    this.roleDropdownText = this.user.role.name !== undefined ? this.user.role.name: 'Pick client';
  }

  getRoles(): void {
    this.accountService.getRoles().subscribe(roles => {
      roles.forEach(role => {
        this.roles.push(role);
      });
    });
  }

  initRoles() {
    this.getRoles();
  }

  onPickRole(role: UserRole): void {
    this.user.role = role;
    this.roleDropdownText = role.name;
  }

  addClient(): void {
    // if (this.client.password !== this.client.repeatedPassword) {
    //   this.toastr.error('Passwords don\'t match!');
    //   this.client.password = '';
    //   this.client.repeatedPassword = '';
    //   return;
    // }
    const clientFullInfoDto = new Client();
    clientFullInfoDto.setFrom(this.user);
    // clientFullInfoDto.password = this.encryptionService.encrypt(clientFullInfoDto.password);
    // clientFullInfoDto.repeatedPassword = clientFullInfoDto.password;

    this.accountService.signupUser(clientFullInfoDto, clientFullInfoDto.password).subscribe(
      (result) => {
        if (result != null) {
          this.toastr.success('Successfully created new client!');
          this.user = new Client();
        } else {
          this.toastr.error('A client with the same username already exists!');
        }
      },
      (error) => {
        const errors = error.error.errors;
        const firstError = errors[Object.keys(errors)[0]];
        this.toastr.error(firstError[0]);
      }
    );
  }

  updateClient(): void {
    // if (this.client.password !== this.client.repeatedPassword) {
    //   this.toastr.error('Passwords don\'t match!');
    //   this.client.password = '';
    //   this.client.repeatedPassword = '';
    //   return;
    // }
    const clientFullInfoDto = new Client();
    clientFullInfoDto.setFrom(this.user);
    // clientFullInfoDto.password = this.encryptionService.encrypt(this.client.password);
    // clientFullInfoDto.repeatedPassword = clientFullInfoDto.password;
    this.accountService.updateUser(clientFullInfoDto).subscribe(
      (result) => {
        if (result === true) {
          this.toastr.success('Successfully updated the client!');
          this.user = new Client();
        } else {
          this.toastr.error('A user with the same username already exists!');
        }
      },
      (error) => {
        const errors = error.error.errors;
        const firstError = errors[Object.keys(errors)[0]];
        this.toastr.error(firstError[0]);
      }
    );
  }

  completedFields(): boolean {
    if (
      this.user.username === '' ||
      this.user.username === undefined ||
      this.user.password === '' ||
      this.user.password === undefined ||
      // this.client.repeatedPassword === '' ||
      // this.client.repeatedPassword === undefined ||
      this.user.firstName === '' ||
      this.user.firstName === undefined ||
      this.user.lastName === '' ||
      this.user.lastName === undefined||
      this.user.role === null ||
      this.user.role === undefined
    ) {
      return false;
    }

    return true;
  }
}
