import { Injectable } from '@angular/core';
import { ChatNotificationMsg } from '../models/notification.model';
import { Subject } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class MessageService {
  private messages: ChatNotificationMsg[] = [];
  private messagesSubject: Subject<ChatNotificationMsg[]> = new Subject<ChatNotificationMsg[]>();

  addMessage(message: ChatNotificationMsg) {
    this.messages.push(message);
    this.messagesSubject.next([...this.messages]); // Notify subscribers about the change
  }

  getMessages(): ChatNotificationMsg[] {
    return this.messages;
  }

  clearMessages() {
    this.messages = [];
  }

  // Observable to subscribe for changes
  messagesChanged$() {
    return this.messagesSubject.asObservable();
  }
}
