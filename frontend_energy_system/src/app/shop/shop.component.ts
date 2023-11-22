import { Component, OnInit } from '@angular/core';
import { DeviceService } from '../services/device.service';
import { Device } from '../models/device.model';

@Component({
  selector: 'app-shop',
  templateUrl: './shop.component.html',
  styleUrls: ['./shop.component.css']
})
export class ShopComponent implements OnInit {

  devices: Device[];

  constructor(private deviceService: DeviceService) { }

  ngOnInit(): void {
    this.deviceService.getDevicesByUser(localStorage.getItem('eshop-userid')).subscribe(p => {
      this.devices = p;
      console.log(this.devices)
    });
  }
}
