import { Component, OnInit } from '@angular/core';
import { DeviceService } from '../services/device.service';
import { Device } from '../models/device.model';
import { ToastrService } from 'ngx-toastr';
import { Message } from '@stomp/stompjs';

@Component({
  selector: 'app-shop',
  templateUrl: './shop.component.html',
  styleUrls: ['./shop.component.css']
})
export class ShopComponent implements OnInit {

  devices: Device[];
  private topicSubscription: any;

  constructor(private deviceService: DeviceService,private toastr: ToastrService) { }

  ngOnInit(): void {
    this.deviceService.getDevicesByUser(localStorage.getItem('eshop-userid')).subscribe(p => {
      this.devices = p;
      console.log(this.devices);
    });
    // this.topicSubscription = this.rxStompService.watch('/topic/notification').subscribe((message: Message) => {
    //     this.toastr.warning(message.body);
    // });
    }
  
    ngOnDestroy() {
      // this.topicSubscription.unsubscribe();
    }
}
