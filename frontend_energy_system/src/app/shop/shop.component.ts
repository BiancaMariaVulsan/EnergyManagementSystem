import { Component, OnInit } from '@angular/core';
import { DeviceService } from '../services/device.service';
import { Device } from '../models/device.model';
import { Router } from '@angular/router';
import { WebSocketSrvice } from '../services/websockets.service';

@Component({
  selector: 'app-shop',
  templateUrl: './shop.component.html',
  styleUrls: ['./shop.component.css']
})
export class ShopComponent implements OnInit {

  devices: Device[];

  constructor(private deviceService: DeviceService, private router: Router, private webSocketService: WebSocketSrvice) { }

  ngOnInit(): void {
    this.deviceService.getDevicesByUser(localStorage.getItem('eshop-userid')).subscribe(p => {
      this.devices = p;
      console.log(this.devices);
    });
    this.webSocketService.connect();
  }

  showDeviceInfo(deviceId: number): void {
    console.log(deviceId);
    this.router.navigate(['/measurements/' + deviceId]);
  }
}
