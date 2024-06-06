import { Component } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { NavbarComponent } from '../../navbar/navbar.component';
import { FloatLabelModule } from 'primeng/floatlabel';
import { ButtonModule } from 'primeng/button';
import { CalendarModule } from 'primeng/calendar';
import { PsService } from '../../../service/ps.service';

@Component({
  selector: 'app-create-schedule',
  standalone: true,
  imports: [
    NavbarComponent,
    FloatLabelModule,
    ReactiveFormsModule,
    ButtonModule,
    CalendarModule
  ],
  templateUrl: './create-schedule.component.html',
  styleUrls: ['./create-schedule.component.css']
})
export class CreateScheduleComponent {
  userForm!: FormGroup;
  date: Date = new Date("2024-06");
  minDate: Date = new Date("2024-06");
  maxDate: Date = new Date("2024-06");

  constructor(private fb: FormBuilder, private psService: PsService) { }

  ngOnInit() {
    this.userForm = this.fb.group({
      userEmail: ['', [Validators.required, Validators.email]],
      monthYear: ['', [Validators.required]],
      sendData: ['', [Validators.required]],
      payrollReport: ['', [Validators.required]],
      bankUpload: ['', [Validators.required]],
      payrollJournal: ['', [Validators.required]],
      paidOut: ['', [Validators.required]]
    });
  }

  onDateSelect(event: any) {
    this.date = new Date(this.userForm.value.monthYear);
    this.minDate = this.date;
    this.date.setDate(1); 
    this.maxDate = new Date(this.minDate.getFullYear(), this.minDate.getMonth() + 1, 0); 
  }

  onSubmit(): void {
    if (this.userForm.valid) {
      const formValue = this.userForm.value;
      const requestData = {
        userEmail: formValue.userEmail,
        monthYear: formValue.monthYear.toISOString().substring(0, 7), 
        sendDataDate: formValue.sendData.toISOString().substring(0, 10),
        payrollReportDate: formValue.payrollReport.toISOString().substring(0, 10),
        bankUploadDate: formValue.bankUpload.toISOString().substring(0, 10),
        payrollJournalDate: formValue.payrollJournal.toISOString().substring(0, 10),
        paidOutDate: formValue.paidOut.toISOString().substring(0, 10)
      };

      this.psService.createScheduleAndDates(requestData).subscribe({
        next: (response: any) => {
          console.log('Schedule created successfully:', response);
          this.userForm.reset();
        },
        error: (error: any) => console.error('Failed to create schedule:', error)
      });
    } else {
      console.error('Form is not valid');
    }
  }
}
