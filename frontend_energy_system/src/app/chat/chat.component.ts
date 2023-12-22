import { Component, OnInit } from "@angular/core";
import { WebSocketSrvice } from "../services/websockets.service";
import { MessageService } from "../services/message.service";
import { AccountService } from "../services/account.service";
import { LoginUserReply } from "../models/loginuser.model";
import { ChatNotificationMsg } from "../models/notification.model";

@Component({
  selector: 'app-chat',
  templateUrl: './chat.component.html',
  styleUrls: ['./chat.component.css']
})
export class ChatComponent implements OnInit {
  newMessage: ChatNotificationMsg = new ChatNotificationMsg('', 0, Number(localStorage.getItem("eshop-username")));
  clients: LoginUserReply[];
  selectedClientId: number;

  constructor(private webSocketService: WebSocketSrvice, public messageService: MessageService, private accountService: AccountService) {
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
  }
  

  ngOnInit(): void {
    this.webSocketService.connect_chat();
    this.getStoredMessages();
  }

  onPersonCahanged() {
    this.messageService.clearMessages();
    this.getStoredMessages();
  }

  sendMessage() {
    this.messageService.addMessage(this.newMessage);
    this.webSocketService.sendMessage(this.newMessage.message, this.selectedClientId);
  }

  private getStoredMessages() {
    // Retrieve stored messages from the server for a specific conversation
    this.webSocketService.getStoredMessages(Number(localStorage.getItem("eshop-userid")), Number(this.selectedClientId));
  }
}
