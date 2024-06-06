import { CardModule } from 'primeng/card';
import { Component, OnInit } from '@angular/core';
import { NavbarComponent } from '../../navbar/navbar.component';
import { DataViewModule } from 'primeng/dataview';
import { FormsModule } from '@angular/forms';
import { DropdownModule } from 'primeng/dropdown';
import { ButtonModule } from 'primeng/button';
import { ChangeRequest } from '../../../helper/change-request.class';
import { GetChangeReqDto } from '../../../dto/changeapproval/get-change-req.dto';
import { PsService } from '../../../service/ps.service';
import { CommonModule } from '@angular/common';
import { UpdateChangeReqDto } from '../../../dto/changeapproval/update-change-req.dto';

@Component({
  selector: 'app-schedule-change-approval',
  standalone: true,
  imports: [
    CommonModule,
    NavbarComponent,
    DataViewModule,
    FormsModule,
    DataViewModule,
    DropdownModule,
    ButtonModule,
    CardModule
  ],
  templateUrl: './schedule-change-approval.component.html',
  styleUrl: './schedule-change-approval.component.css'
})
export class ScheduleChangeApprovalComponent implements OnInit {
  changeRequests: ChangeRequest[] = [];

  constructor(private psService: PsService) {}

  ngOnInit() {
    this.fetchChangeRequests();
  }

  fetchChangeRequests(): void {
    const requestData: GetChangeReqDto = {
      typeId: 1
    };

    this.psService.getChangeRequest(requestData).subscribe({
      next: (response) => {
        // console.log(response)
        this.changeRequests = response.reqRes.map((item: any) => {
          const newItem: ChangeRequest = {
            id: item.id,
            initialDate: item.date.deadlineDate,
            newDate: item.newDate,
            dateId: item.date.id,
            requestStatus: item.requestStatus.requestName
          };
          return newItem;
        });
        console.log(this.changeRequests)
      },
      error: (err) => console.error('Failed to fetch change requests:', err)
    });
  }


  updateRequest(id: number, approved: boolean): void {
    const requestData: UpdateChangeReqDto = {
      reqId: id,
      status: approved ? 1 : 0  // Assuming '1' means approved and '0' means rejected
    };

    this.psService.updateChangeRequest(requestData).subscribe({
      next: (response) => {
        console.log('Update successful:', response);
        // Optionally refresh list or display a message
        this.changeRequests = this.changeRequests.filter(request => request.id !== id);
      },
      error: (error) => {
        console.error('Failed to update change request:', error);
        // Optionally handle errors, e.g., display an error message
      }
    });
  }
}