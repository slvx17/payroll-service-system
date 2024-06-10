import { Component } from '@angular/core';
import { NavbarComponent } from '../../navbar/navbar.component';
import { ButtonModule } from 'primeng/button';
import { DropdownModule } from 'primeng/dropdown';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-upload-doc',
  standalone: true,
  imports: [
    NavbarComponent,
    ButtonModule,
    DropdownModule,
    FormsModule
  ],
  templateUrl: './upload-doc.component.html',
  styleUrl: './upload-doc.component.css'
})
export class UploadDocComponent {

  downloadDocument() {
    window.location.href = 'https://www.example.com/path/to/document.pdf';
  }

  events = [
    { name: 'Send Data', code: 'send-data' },
    { name: 'Payroll Report', code: 'payroll-report' },
    { name: 'Bank Upload', code: 'bank-upload' },
    { name: 'Payroll Journal', code: 'payroll-journal' },
    { name: 'Paid Out', code: 'paid-out' }
  ];

  selectedEvent: any = null;
}
