import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import interactionPlugin from '@fullcalendar/interaction';
import { NavbarComponent } from '../../navbar/navbar.component';
import { FullCalendarModule } from '@fullcalendar/angular';
import { CalendarOptions } from '@fullcalendar/core';
import dayGridPlugin from '@fullcalendar/daygrid';

@Component({
  selector: 'app-cl-dashboard',
  standalone: true,
  imports: [
    CommonModule,
    NavbarComponent,
    FullCalendarModule
  ],
  templateUrl: './cl-dashboard.component.html',
  styleUrl: './cl-dashboard.component.css'
})
export class ClDashboardComponent {
  d: string = "2024-06-01"

  calendarOptions: CalendarOptions = {
    initialView: 'dayGridMonth',
    plugins:[dayGridPlugin, interactionPlugin],
    // editable: true,
    dateClick: this.handleDateClick.bind(this),
    events: [
      {title: "event 1", date: this.d}
    ]
  };

  handleDateClick(arg: { dateStr: string; }) {
    alert('Date clicked: ' + arg.dateStr);
  }
}
