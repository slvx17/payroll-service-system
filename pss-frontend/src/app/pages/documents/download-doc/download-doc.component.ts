import { Component } from '@angular/core';
import { NavbarComponent } from '../../navbar/navbar.component';
import { FileUploadModule } from 'primeng/fileupload';
import { DropdownModule } from 'primeng/dropdown';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-download-doc',
  standalone: true,
  imports: [NavbarComponent,
    FileUploadModule,
    DropdownModule,
    FormsModule
  ],
  templateUrl: './download-doc.component.html',
  styleUrl: './download-doc.component.css'
})
export class DownloadDocComponent {

  onUpload(event: any) {
    console.log('Upload event:', event);
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
