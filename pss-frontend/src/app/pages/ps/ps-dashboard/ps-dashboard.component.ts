import { Component } from '@angular/core';
import { ReactiveFormsModule } from '@angular/forms';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { NavbarComponent } from '../../navbar/navbar.component';
import { ChatComponent } from '../../chat/chat.component';

@Component({
  selector: 'app-ps-dashboard',
  standalone: true,
  imports: [
    CommonModule,
    FormsModule,
    ReactiveFormsModule,
    NavbarComponent,
    ChatComponent
  ],
  templateUrl: './ps-dashboard.component.html',
  styleUrl: './ps-dashboard.component.css'
})
export class PsDashboardComponent {

}
