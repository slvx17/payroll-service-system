import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import {MenubarModule} from 'primeng/menubar'


@Component({
  selector: 'app-sa-dashboard',
  standalone: true,
  imports: [
    CommonModule,
    MenubarModule,
  ],
  templateUrl: './sa-dashboard.component.html',
  styleUrl: './sa-dashboard.component.css'
})
export class SaDashboardComponent {

}
