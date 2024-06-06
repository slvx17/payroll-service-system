import { Component, OnInit } from '@angular/core';
import { NavbarComponent } from '../../navbar/navbar.component';
import { FormBuilder, FormGroup, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';
import { CalendarModule } from 'primeng/calendar';
import { DropdownModule } from 'primeng/dropdown';
import { InputTextareaModule } from 'primeng/inputtextarea';
import { GetAssignmentDto } from '../../../dto/assignment/get-assignment.dto';
import { Observable, Subscription, map, of, startWith, switchMap } from 'rxjs';
import { DateDetail } from '../../../helper/date-detail.class';
import { EventReqDto } from '../../../dto/requestchange/event-req.dto';
import { CommonModule } from '@angular/common';
import { ReqChangeReqDto } from '../../../dto/requestchange/request-change-req.dto';
import { ClientService } from '../../../service/client.service';
import { Message } from 'primeng/api';
import { MessagesModule } from 'primeng/messages';

@Component({
  selector: 'app-request-change',
  standalone: true,
  imports: [
    NavbarComponent,
    FormsModule,
    ReactiveFormsModule,
    CalendarModule,
    DropdownModule,
    InputTextareaModule,
    CommonModule,
    MessagesModule
  ],
  templateUrl: './request-change.component.html',
  styleUrl: './request-change.component.css'
})



export class RequestChangeComponent implements OnInit {
  form!: FormGroup;
  assignmentId!: number;
  events: DateDetail[] = [];
  private subscriptions = new Subscription();
  minDate!: Date;
  maxDate!: Date;
  messages: Message[] = [];


  constructor(private fb: FormBuilder, private clientService: ClientService) { }

  ngOnInit() {
    this.form = this.fb.group({
      monthYear: ['', [Validators.required]],
      selectedEvent: ['', [Validators.required]],
      newDate: ['', [Validators.required]],
      message: ['', [Validators.required, Validators.maxLength(50)]]
    });

    const userId = localStorage.getItem('id');
    if (userId) {
      this.clientService.getClientAssignmentById(+userId).subscribe({
        next: (assignment: GetAssignmentDto) => {
          this.assignmentId = assignment.assignmentId;  
          console.log('Retrieved assignment ID:', this.assignmentId);
        },
        error: (error) => {
          console.error('Error fetching assignment:', error);
        }
      });
    } else {
      console.error('No user ID found in localStorage');
    }
  }

  ngOnDestroy() {
    this.subscriptions.unsubscribe(); 
  }

  onMonthYearSelect(event: any) {
    const selectedDate = event;
    var monthYear = "";
    if(selectedDate.getMonth>8)monthYear=`${selectedDate.getFullYear()}-${selectedDate.getMonth() + 1}`;  
    else monthYear=`${selectedDate.getFullYear()}-0${selectedDate.getMonth() + 1}`;  
    console.log('Selected Month & Year:', monthYear);

    if (monthYear) {
      const reqDto: EventReqDto = {
        monthYear: monthYear.toString().substring(0, 7), 
        assignmentId: this.assignmentId
      };
      this.clientService.getEvents(reqDto).subscribe({
        next: (res) => {
          console.log(res)
          this.events = res.events;  
        },
        error: (error) => {
          console.error('Error fetching events:', error);
          this.events = [];  
        }
      });
    } else {
      this.events = [];  
    }
  }

  onEventSelect(event: any) {
    let selectedEvent: DateDetail = event.value;
    console.log(event)
    
    this.minDate = new Date(selectedEvent.deadlineDate);
    this.minDate.setDate(1); 
    
    this.maxDate = new Date(selectedEvent.deadlineDate);
    this.maxDate.setDate(this.maxDate.getDate() - 1)

    this.form.get('newDate')?.setValue(this.minDate);
  }

  onSubmit() {
    if (this.form.valid) {
      const reqDto: ReqChangeReqDto = {
        dateId: this.form.value.selectedEvent.id,
        newDate: this.form.value.newDate,
        message: this.form.value.message
      };
      this.clientService.requestChange(reqDto).subscribe({
        next: (response) => {
          this.messages = [{ severity: 'success', summary: 'Success', detail: 'Request Change Successful: ' + response.message }];
          this.form.reset();
        },
        error: (error) => {
          this.messages = [{ severity: 'error', summary: 'Error', detail: 'Failed to submit change request: ' + (error.error.message || error.message) }];
        }
      });
    } else {
      this.messages = [{ severity: 'error', summary: 'Validation Error', detail: 'Form is not valid' }];
    }
  }
}
