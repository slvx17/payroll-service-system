import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import interactionPlugin from '@fullcalendar/interaction';
import { NavbarComponent } from '../../navbar/navbar.component';
import { FullCalendarModule } from '@fullcalendar/angular';
import { CalendarOptions } from '@fullcalendar/core';
import dayGridPlugin from '@fullcalendar/daygrid';
import { NonNullableFormBuilder, Validators } from '@angular/forms';
import { AuthService } from '../../../service/auth.service';
import { ClientCalendarService } from '../../../service/clientcalendar.service';
import { CalendarResDto } from '../../../dto/calendar/calendar-res.dto';

@Component({
  selector: 'app-cl-dashboard',
  standalone: true,
  imports: [
    CommonModule,
    NavbarComponent,
    FullCalendarModule,
    NavbarComponent,
  ],
  templateUrl: './cl-dashboard.component.html',
  styleUrl: './cl-dashboard.component.css'
})
export class ClDashboardComponent {
  date: string[] = [];
  eventName: string[] = [];
  allEvents: { title: string, date: string }[] = [];
  d: string = "2024-06-01"

  calendarReq = this.fb.group({
    email: [localStorage.getItem('email') || '', [Validators.required]],
  });

  constructor(private fb:NonNullableFormBuilder, private authService: AuthService, private clientCalendarService: ClientCalendarService){}

  calendarOptions: CalendarOptions = {
    initialView: 'dayGridMonth',
    plugins:[dayGridPlugin, interactionPlugin],
    // editable: true,
    dateClick: this.handleDateClick.bind(this),
    events: []
  };

  handleDateClick(arg: { dateStr: string; }) {
    alert('Date clicked: ' + arg.dateStr);
    // $("#myModal").modal("show");
    // $(".modal-title, .eventstarttitle").text("");
    // $(".modal-title").text("Add Event at : "+arg.dateStr);
    // $(".eventstarttitle").text(arg.dateStr);
  }

  ngOnInit() {
    if (this.calendarReq.invalid) {
      return;
  }

  const calendarReqRaw = this.calendarReq.getRawValue();
  this.clientCalendarService.calendarGet(calendarReqRaw).subscribe((res: CalendarResDto) => {{
      this.date = res.deadline;
      this.eventName = res.deadlineType;
      console.log(this.date)

      for (let i = 0; i < this.date.length; i++) {
        let newEvent = {title: this.eventName[i], date: this.date[i]}
        this.allEvents.push(newEvent);
        console.log(this.eventName[i])
      }
      this.calendarOptions.events = this.allEvents;
  }})
}
}
