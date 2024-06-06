import { Component, OnInit } from '@angular/core';
import { NavbarComponent } from '../../navbar/navbar.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { FormBuilder, FormGroup, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';
import { CalendarModule } from 'primeng/calendar';
import { DropdownModule } from 'primeng/dropdown';
import { InputTextareaModule } from 'primeng/inputtextarea';

@Component({
  selector: 'app-request-change',
  standalone: true,
  imports: [
    NavbarComponent,
    FormsModule,
    ReactiveFormsModule,
    CalendarModule,
    DropdownModule,
    InputTextareaModule
  ],
  templateUrl: './request-change.component.html',
  styleUrl: './request-change.component.css'
})
export class RequestChangeComponent implements OnInit {
  
  form!: FormGroup;
  cities = [
    { name: 'New York', code: 'NY' },
    { name: 'Rome', code: 'RM' },
    { name: 'London', code: 'LDN' },
    { name: 'Istanbul', code: 'IST' },
    { name: 'Paris', code: 'PRS' }
  ];

  constructor(private fb: FormBuilder) { }
  
  ngOnInit() {
    this.form = this.fb.group({
      monthYear: ['', [Validators.required]],
      selectedEvent: ['', [Validators.required]],
      newDate: ['', [Validators.required]],
      message: ['', [Validators.required]]
    });
  }
}
