import { Component } from '@angular/core';
import { NavbarComponent } from '../../navbar/navbar.component';
import { FileUploadModule } from 'primeng/fileupload';
import { DropdownModule } from 'primeng/dropdown';
import { FormsModule } from '@angular/forms';
import { FileService } from '../../../service/File.service';
import { ClientService } from '../../../service/client.service';
import { EventReqDto } from '../../../dto/requestchange/event-req.dto';
import { EventResDto } from '../../../dto/requestchange/event-res.dto';
import { CalendarModule } from 'primeng/calendar';

@Component({
  selector: 'app-download-doc',
  standalone: true,
  imports: [NavbarComponent,
    FileUploadModule,
    DropdownModule,
    FormsModule,
    CalendarModule
  ],
  templateUrl: './download-doc.component.html',
  styleUrl: './download-doc.component.css'
})
export class DownloadDocComponent {

  selectedEvent: any = null;
  events: any[] = [];  
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

  downloadDocument(){

  }
}
