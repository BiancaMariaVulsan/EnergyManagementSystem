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
  selectedClient: LoginUserReply;

  constructor(private webSocketService: WebSocketSrvice, private messageService: MessageService, private accountService: AccountService) {
    this.messages = this.messageService.getMessages();
  }

  ngOnInit(): void {
    this.webSocketService.connect_chat();
    this.accountService.getUsers().subscribe(p => {
      this.clients = p;
    });
    // keep just the users (not admin)
    this.clients = this.clients.filter(c => c.role.name === 'User');
  }

  sendMessage() {
    if (this.newMessage.trim() !== '') {
      this.messageService.addMessage(this.newMessage);
      this.webSocketService.sendMessage(this.newMessage, this.selectedClient.id);
      this.newMessage = '';
    }
  }
}
