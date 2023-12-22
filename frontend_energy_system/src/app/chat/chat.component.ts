import { Component, OnInit } from "@angular/core";
import { WebSocketSrvice } from "../services/websockets.service";
import { AccountService } from "../services/account.service";
import { LoginUserReply } from "../models/loginuser.model";
import { ChatNotificationMsg } from "../models/notification.model";

@Component({
  selector: 'app-chat',
  templateUrl: './chat.component.html',
  styleUrls: ['./chat.component.css']
})
export class ChatComponent implements OnInit {
  newMessage: string = "";
  clients: LoginUserReply[];
  selectedClientId: number;
  messages: ChatNotificationMsg[] = [];
  crtUserId: number = Number(localStorage.getItem("eshop-userid"));

  constructor(private webSocketService: WebSocketSrvice, private accountService: AccountService) {
    this.webSocketService.messageReceived.asObservable().subscribe(message => {
      if(message) {
        this.messages.push(message);
      }
    })
  }

  ngOnInit(): void {    
    this.accountService.getUsers().subscribe(p => {
      // Ensure that p is defined and not undefined
      if (p) {
        this.clients = p;
        // Keep just the users (not admin)
        if (localStorage.getItem("eshop-usertype") === 'Admin') {
          this.clients = this.clients.filter(c => c.role.name === 'User');
        } else {
          this.clients = this.clients.filter(c => c.role.name === 'Admin');
        }
      }
    });

    this.webSocketService.connect_notifications();
    this.webSocketService.connect_chat();
  }

  onPersonCahanged() {
    this.messages = [];
    this.getStoredMessages();

    let clientName: string = "";
    for (let i = 0; i < this.clients.length; i++) {
      if (this.clients[i].id === Number(this.selectedClientId)) {
        clientName = this.clients[i].firstName + " " + this.clients[i].lastName;
      }
    }
    
    this.webSocketService.sendNotification(clientName + " is reading your messages!", Number(this.selectedClientId)); // Fix the string concatenation
  }

  sendMessage() {
    this.messages.push(new ChatNotificationMsg(this.newMessage, this.selectedClientId, Number(localStorage.getItem("eshop-username"))));
    this.webSocketService.sendMessage(this.newMessage, this.selectedClientId);
  }

  private getStoredMessages() {
    // Retrieve stored messages from the server for a specific conversation
    this.webSocketService.getStoredMessages(Number(localStorage.getItem("eshop-userid")), Number(this.selectedClientId));
  }

  onFocus() {
    // Send notification when input field gains focus
    if (this.selectedClientId) {
      let clientName: string = "";
      for (let i = 0; i < this.clients.length; i++) {
        if (this.clients[i].id === Number(this.selectedClientId)) {
          clientName = this.clients[i].firstName + " " + this.clients[i].lastName;
        }
      }
      const notificationMessage = clientName + "is typing...";
      this.webSocketService.sendNotification(notificationMessage, Number(this.selectedClientId));
    }
  }
}
