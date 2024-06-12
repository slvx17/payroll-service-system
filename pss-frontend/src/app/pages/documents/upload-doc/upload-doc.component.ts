import { Component, OnInit } from '@angular/core';
import { NavbarComponent } from '../../navbar/navbar.component';
import { ButtonModule } from 'primeng/button';
import { DropdownModule } from 'primeng/dropdown';
import { FormsModule } from '@angular/forms';
import { ClientService } from '../../../service/client.service';
import { FileService } from '../../../service/File.service';
import { EventResDto } from '../../../dto/requestchange/event-res.dto';
import { EventReqDto } from '../../../dto/requestchange/event-req.dto';
import { FileUploadModule } from 'primeng/fileupload';
import { CalendarModule } from 'primeng/calendar';

@Component({
  selector: 'app-upload-doc',
  standalone: true,
  imports: [
    NavbarComponent,
    ButtonModule,
    DropdownModule,
    FormsModule,
    FileUploadModule,
    CalendarModule
  ],
  templateUrl: './upload-doc.component.html',
  styleUrls: ['./upload-doc.component.css']
})
export class UploadDocComponent implements OnInit {
  fileToUpload: File | null = null;
  selectedEvent: any = null;
  events: any[] = [];  // Now an empty array, to be filled by backend data
  date: String = "06-2024"

  private deadlineTypeMapping: { [key: number]: string } = {
    1: 'Send Data',
    2: 'Payroll Report',
    3: 'Bank Upload',
    4: 'Payroll Journal',
    5: 'Paid Out'
  };

  constructor(private fileService: FileService, private clientService: ClientService) {}

  ngOnInit(): void {
    this.loadEvents();
  }

  loadEvents() {
    console.log("it's running");
    const reqDto = new EventReqDto(); 
    this.clientService.getEvents(reqDto).subscribe({
      next: (response: EventResDto) => {
        this.events = response.events.map(detail => ({
          name: this.deadlineTypeMapping[detail.deadlineType],
          code: detail.deadlineType
        }));
      },
      error: (error) => {
        console.error('Error fetching events:', error);
      }
    });
  }

  onFileSelect(event: any) {
    if (event.target.files.length > 0) {
      this.fileToUpload = event.target.files[0];
    }
  }

  uploadDocument() {
    if (this.fileToUpload && this.selectedEvent) {
      const email = localStorage.getItem("email") || "default@email.com";
      this.fileService.uploadFile(this.fileToUpload, email, this.selectedEvent.code).subscribe({
        next: (response) => {
          alert('File uploaded successfully!');
        },
        error: (error) => {
          alert('Error uploading file: ' + error);
        }
      });
    } else {
      alert('Please select a file and event.');
    }
  }
}
