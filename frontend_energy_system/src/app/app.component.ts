import { Component } from '@angular/core';
import { RxStompService } from './websockets/rxstomp.service';
import { Message } from '@stomp/stompjs';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'frontend_energy_system';
  private topicSubscription: any;

  public constructor(private rxStompService: RxStompService, private toastr: ToastrService) {}

  ngOnInit() {
    this.topicSubscription = this.rxStompService.watch('/topic/notification').subscribe((message: Message) => {
      this.toastr.warning(message.body);
    });
  }

  ngOnDestroy() {
    this.topicSubscription.unsubscribe();
  }
}
