import { Component } from '@angular/core';
import { NonNullableFormBuilder, ReactiveFormsModule, Validators } from '@angular/forms';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { NavbarComponent } from '../../navbar/navbar.component';
import { ButtonModule } from 'primeng/button';
import { ToolbarModule } from 'primeng/toolbar';
import { CalendarResDto } from '../../../dto/calendar/calendar-res.dto';
import { AssignmentService } from '../../../service/assignment.service';
import { HttpClient } from '@angular/common/http';
import { UserResDto } from '../../../dto/user/user.dto';
import { Router } from '@angular/router';
import { CalendarOptions } from '@fullcalendar/core';
import dayGridPlugin from '@fullcalendar/daygrid';
import interactionPlugin from '@fullcalendar/interaction';
import { FullCalendarComponent, FullCalendarModule } from '@fullcalendar/angular';
import { ClientCalendarService } from '../../../service/calendar.service';
import { DropdownModule } from 'primeng/dropdown';
import { SelectItem } from 'primeng/api';

@Component({
  selector: 'app-ps-dashboard',
  standalone: true,
  imports: [
    CommonModule,
    FormsModule,
    ReactiveFormsModule,
    NavbarComponent,
    ButtonModule,
    ToolbarModule,
    FullCalendarModule,
    DropdownModule,
  ],
  templateUrl: './ps-calendarview.component.html',
  styleUrl: './ps-calendarview.component.css'
})
export class PSCalendarViewComponent {
  searchTerm: string = '';
  searchResult: UserResDto | null = null;
  searchMade: boolean = false;

  selectedClientUser!: UserResDto;
  selectedSuccessfully: boolean = false;


  calendarReq = this.fb.group({
    email: [this.searchTerm, [Validators.required]],
  });
  date: string[] = [];
  eventName: string[] = [];
  allEvents: { title: string, date: string }[] = [];


  selectedPayrollServiceUserId: number = 0;
  users: SelectItem[] = [];

  constructor(private fb:NonNullableFormBuilder, private http: HttpClient, private assignmentService: AssignmentService, private clientCalendarService: ClientCalendarService, private router: Router) { }

  calendarOptions: CalendarOptions = {
    initialView: 'dayGridMonth',
    plugins:[dayGridPlugin, interactionPlugin],
    // editable: true,
    dateClick: this.handleDateClick.bind(this),
    events: []
  };


  onUserSelect(user:string) {
    this.searchTerm = user;
    this.searchMade = true;
    this.assignmentService.getClientByEmail(this.searchTerm).subscribe({
      next: (clientUser) => {
        this.searchResult = clientUser;
        this.selectedClientUser = clientUser;
        console.log("selected successfully")
        this.FillEvents();
      },
      error: () => {
        console.log("email not found")
        this.FillEvents();
      }
    });
  }

  handleDateClick(arg: { dateStr: string; }) {
    alert('Date clicked: ' + arg.dateStr);
    // $("#myModal").modal("show");
    // $(".modal-title, .eventstarttitle").text("");
    // $(".modal-title").text("Add Event at : "+arg.dateStr);
    // $(".eventstarttitle").text(arg.dateStr);
  }

  FillEvents() {
    this.calendarReq = this.fb.group({
      email: [this.searchTerm, [Validators.required]],
    });

    if (this.calendarReq.invalid) {
      return;
  }

  const calendarReqRaw = this.calendarReq.getRawValue();
  this.clientCalendarService.calendarGet(calendarReqRaw).subscribe((res: CalendarResDto) => {{
      this.date = res.deadline;
      this.eventName = res.deadlineType;
      console.log(this.date)
      this.calendarOptions.events = [];
      this.allEvents = [];

      for (let i = 0; i < this.date.length; i++) {
        let newEvent = {title: this.eventName[i], date: this.date[i]}
        this.allEvents.push(newEvent);
      }
      this.calendarOptions.events = this.allEvents;
  }})
  }

  ngOnInit():void {
    this.assignmentService.getAllC().subscribe(cUsers => {
      console.log(this.users)
      this.users = cUsers.map(user => ({ label: user.username, value: user }));
    });
  }
}
