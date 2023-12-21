import { Component, OnInit } from "@angular/core";
import { WebSocketSrvice } from "../services/websockets.service";
import { MessageService } from "../services/message.service";
import { AccountService } from "../services/account.service";
import { LoginUserReply } from "../models/loginuser.model";

@Component({
  selector: 'app-chat',
  templateUrl: './chat.component.html',
  styleUrls: ['./chat.component.css']
})
export class ChatComponent implements OnInit {
  messages: string[] = [];
  newMessage: string = '';
  clients: LoginUserReply[];
  selectedClientId: number;

  constructor(private webSocketService: WebSocketSrvice, private messageService: MessageService, private accountService: AccountService) {
    this.messages = this.messageService.getMessages();
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
        this.selectedClientId = this.clients.length > 0 ? this.clients[0].id : null;
      }
    });
  }
  

  ngOnInit(): void {
    this.webSocketService.connect_chat();
  }

  sendMessage() {
    if (this.newMessage.trim() !== '') {
      this.messageService.addMessage(this.newMessage);
      this.webSocketService.sendMessage(this.newMessage, this.selectedClientId);
      this.newMessage = '';
    }
  }
}
