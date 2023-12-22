import { Injectable } from '@angular/core';
import { ChatNotificationMsg } from '../models/notification.model';

@Injectable({
  providedIn: 'root',
})
export class MessageService {
  private messages: ChatNotificationMsg[] = [];

  addMessage(message: ChatNotificationMsg) {
    this.messages.push(message);
  }

  getMessages(): ChatNotificationMsg[] {
    return this.messages;
  }

  clearMessages() {
    this.messages = [];
  }
}
