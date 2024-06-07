import { Component } from '@angular/core';
import { FormGroup, NonNullableFormBuilder, ReactiveFormsModule, Validators } from '@angular/forms';
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
import { ClientService } from '../../../service/client.service';
import { DropdownModule } from 'primeng/dropdown';
import { SelectItem } from 'primeng/api';
import { EmailDto } from '../../../dto/assignment/email.dto';

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
  searchTerm!: number;
  searchResult: UserResDto | null = null;
  searchMade: boolean = false;
  selectedEmail: string = "";

  selectedClientUser!: UserResDto;
  selectedSuccessfully: boolean = false;


  calendarReq = this.fb.group({
    email: [this.selectedEmail, [Validators.required]],
  });
  date: string[] = [];
  eventName: string[] = [];
  allEvents: { title: string, date: string }[] = [];


  selectedPayrollServiceUserId: number = 0;
  userlist: SelectItem[] = [];
  cUsers!: FormGroup;
  emailReq = this.fb.group({
    email: [localStorage.getItem('email') || '', [Validators.required]],
  });
  emails: string[] = [];
  ids: number[] = [];

  constructor(private fb:NonNullableFormBuilder, private http: HttpClient, private assignmentService: AssignmentService, private clientService: ClientService, private router: Router) { }

  calendarOptions: CalendarOptions = {
    initialView: 'dayGridMonth',
    plugins:[dayGridPlugin, interactionPlugin],
    // editable: true,
    dateClick: this.handleDateClick.bind(this),
    events: []
  };


  ngOnInit():void {
    var userArray = this.fb.group({
        userEmail: ['', [Validators.required, Validators.email]],
        userName: ['', [Validators.required]],
      });

    const email = localStorage.getItem('email')
    const emailReqRaw = this.emailReq.getRawValue();
    if (email){
        this.assignmentService.getAllCByPs(emailReqRaw).subscribe(cUsers => {
            for(let i = 0; i < cUsers.length; i++){
                this.emails.push(cUsers[i].email)
                this.ids.push(cUsers[i].id)
            }
            this.userlist = cUsers.map(user => ({ label: user.username, value: user }));
        });   
    }
    else { 
        console.log("No email was logged in")
    }
  }

  onUserSelect(userid: number) {
    this.searchTerm = userid;
    console.log(this.searchTerm)
    console.log(this.ids)
    for (let i = 0 ; i < this.ids.length; i++) {
        if (this.searchTerm == this.ids[i]){
            this.selectedEmail = this.emails[i];
            break;
        }
    }
    console.log("search email is " + this.selectedEmail)
    this.searchMade = true;
    this.FillEvents();

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
      email: [this.selectedEmail, [Validators.required]],
    });
    console.log("searchterm is " + this.searchTerm)

    if (this.calendarReq.invalid) {
        console.log("Invalid!")
      return;
  }

  const calendarReqRaw = this.calendarReq.getRawValue();
  this.clientService.calendarGet(calendarReqRaw).subscribe((res: CalendarResDto) => {{
      this.date = res.deadline;
      this.eventName = res.deadlineType;
      this.calendarOptions.events = [];
      this.allEvents = [];

      for (let i = 0; i < this.date.length; i++) {
        let newEvent = {title: this.eventName[i], date: this.date[i]}
        this.allEvents.push(newEvent);
      }
      this.calendarOptions.events = this.allEvents;
  }})
  }
}