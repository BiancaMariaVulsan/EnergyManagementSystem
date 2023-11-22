import { Component, Input, OnInit } from '@angular/core';
import { Device } from 'src/app/models/device.model';
import { LoginUserReply } from 'src/app/models/loginuser.model';
import { AccountService } from 'src/app/services/account.service';
import { DeviceService } from 'src/app/services/device.service';
import { ToastrService } from 'ngx-toastr';
import { trigger, state, style, animate, transition } from '@angular/animations';

@Component({
  selector: 'app-device-edit',
  templateUrl: './device-edit.component.html',
  styleUrls: ['./device-edit.component.css']
})
export class DeviceEditComponent implements OnInit {

  constructor(
    private deviceService: DeviceService,
    private accountService: AccountService,
    private toastr: ToastrService
  ) {}

  @Input() device: Device;
  clients: LoginUserReply[] = [];
  clientDropdownText = 'Pick client';

  ngOnInit(): void {
    this.initClients();
    this.clientDropdownText = this.device.personId.toString() !== undefined ? this.device.personId.toString() : 'Pick client';
  }

  addDevice(): void {
    this.deviceService.createDevice(this.device).subscribe(result => {
      if (result !== null) {
        this.toastr.success('Successfully created new device!');
        this.device = new Device();
      } else {
        this.toastr.error('A device with the same name already exists!');
      }
    }, error => {
      const errors = error.error.errors;
      const firstError = errors[Object.keys(errors)[0]];
      this.toastr.error(firstError[0]);
    });
  }

  updateDevice(): void {
    this.deviceService.updateDevice(this.device).subscribe(result => {
      if (result === true) {
        this.toastr.success('Successfully updated the device!');
        this.device = new Device();
      } else {
        this.toastr.error('A device with the same name already exists!');
      }
    }, error => {
      const errors = error.error.errors;
      const firstError = errors[Object.keys(errors)[0]];
      this.toastr.error(firstError[0]);
    });
  }

  initClients(): void {
    this.getClients();
  }

  onPickClient(client: LoginUserReply): void {
    this.device.personId = client.id;
    this.clientDropdownText = client.firstName + ' ' + client.lastName;
  }

  completedFields(): boolean {
    if (this.device.description === '' || this.device.description === undefined ||
        this.device.maxHourlyEnergConsumption <= 0 || this.device.maxHourlyEnergConsumption === undefined ||
        this.device.address === '' || this.device.address === undefined) {
          return false;
        }

    return true;
  }

  getClients(): void {
    this.accountService.getUsers().subscribe(clients => {
      clients.forEach(client => {
        this.clients.push(client);

        if (client.id === this.device.personId) {
          this.clientDropdownText = client.firstName + ' ' + client.lastName;
        }
      });
    });
  }
}
