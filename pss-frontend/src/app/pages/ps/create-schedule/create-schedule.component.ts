import { Component } from '@angular/core';
import { NavbarComponent } from '../../navbar/navbar.component';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { FloatLabelModule } from 'primeng/floatlabel';
import { ButtonModule } from 'primeng/button';
import { CalendarModule } from 'primeng/calendar';

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
  styleUrl: './create-schedule.component.css'
})

export class CreateScheduleComponent {
  userForm!: FormGroup;

  constructor(private fb: FormBuilder) {
  }

  ngOnInit() {
    this.userForm = this.fb.group({
      userEmail: ['', [Validators.required, Validators.email]],
      monthYear: [new Date(), [Validators.required]],
      sendData: ['', [Validators.required]],
      payrollReport: ['', [Validators.required]],
      bankUpload: ['', [Validators.required]],
      payrollJournal: ['', [Validators.required]],
      paidOut: ['', [Validators.required]]
    });
  }

  onSubmit(): void {
    if (this.userForm.valid) {
      console.log('Form Submitted', this.userForm.value);
    } else {
      console.error('Form is not valid');
    }
  }
}

