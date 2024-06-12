import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';

interface Message {
  author: string;
  content: string;
}

@Component({
  selector: 'app-chat',
  templateUrl: './chat.component.html',
  styleUrls: ['./chat.component.css'],
  imports:[
    FormsModule,
    CommonModule
  ],
  standalone: true
})
export class ChatComponent {
  messages: Message[] = [];
  newMessage: string = '';

  sendMessage() {
    if (this.newMessage.trim().length === 0) {
      return;
    }

    this.messages.push({ author: 'me', content: this.newMessage });
    this.newMessage = '';
    this.simulateReply();
  }

  simulateReply() {
    setTimeout(() => {
      this.messages.push({ author: 'them', content: 'This is a simulated reply.' });
    }, 1000);
  }
}