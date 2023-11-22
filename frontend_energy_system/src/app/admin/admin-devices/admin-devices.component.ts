import { Component, OnInit } from '@angular/core';
import { Device } from 'src/app/models/device.model';
import { LoginUserReply } from 'src/app/models/loginuser.model';
import { AccountService } from 'src/app/services/account.service';
import { DeviceService } from 'src/app/services/device.service';

@Component({
  selector: 'app-admin-devices',
  templateUrl: './admin-devices.component.html',
  styleUrls: ['./admin-devices.component.css']
})
export class AdminDevicesComponent implements OnInit {
  devices: Device[];
  activateAddEditDevice = false;
  deviceOperation = 'Add';
  device = new Device();
  clients: LoginUserReply[] = [];

  constructor(private deviceService: DeviceService, private accountService: AccountService) { }

  ngOnInit(): void {
    this.getAllDevices();
    this.initClients();
  }
  initClients() {
    this.accountService.getUsers().subscribe(clients => {
      clients.forEach(client => {
        this.clients.push(client);
      });
    });
  }

  addClick(): void {
    this.device = new Device();
    this.deviceOperation = 'Add';
    this.activateAddEditDevice = true;
  }

  closeClick(): void {
    this.activateAddEditDevice = false;
    this.devices = [];
    this.getAllDevices();
  }

  editClick(device: Device): void {
    this.device = device;
    this.deviceOperation = 'Edit';
    this.activateAddEditDevice = true;
  }

  deleteClick(device: Device): void {
    const subscription = this.deviceService.deleteDevice(device.id).subscribe(result => {
      if (result === true) {
        this.devices.forEach((item, index) => {
          if (item.id === device.id) {
            this.devices.splice(index, 1);
          }
        });
      } else {
        alert('Error, could not delete the device!');
      }
    });
  }

  getAllDevices(): void {
    this.deviceService.getDevices().subscribe(p => {
      this.devices = p;
      console.log(this.devices)
    });
  }

  checkClients(): boolean {
    if (this.clients.length === 0) {
      return true;
    }
  }

  findClientById(id: number): string {
    return this.clients.find(client => client.id === id).username;
  }
}
