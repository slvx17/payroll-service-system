import { Component, OnInit } from '@angular/core';
import { NavbarComponent } from '../../navbar/navbar.component';
import { StepperModule } from 'primeng/stepper';
import { ButtonModule } from 'primeng/button';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { SelectItem } from 'primeng/api';
import { DropdownModule } from 'primeng/dropdown';
import { HttpClient } from '@angular/common/http';
import { AssignUserResDto } from '../../../dto/assignment/assign-user-res.dto';

@Component({
  selector: 'app-assign-user',
  standalone: true,
  imports: [
    CommonModule,
    NavbarComponent,
    StepperModule,
    ButtonModule,
    FormsModule,
    DropdownModule
  ],
  templateUrl: './assign-user.component.html',
  styleUrl: './assign-user.component.css'
})
export class AssignUserComponent implements OnInit {

  searchTerm: string = '';
  searchResult: { username: string, id: number } | null = null;
  searchMade: boolean = false;

  users: SelectItem[] = [];
  selectedClientUser: any = null;
  selectedPayrollServiceUser: any;

  constructor(private http: HttpClient) { }

  ngOnInit(): void {
    // Simulated user data for dropdowns
    this.users = [
      { label: 'John Doe', value: { id: 1, name: 'John Doe', role: 'Client' }},
      { label: 'Jane Smith', value: { id: 2, name: 'Jane Smith', role: 'Client' }},
      { label: 'Jim Brown', value: { id: 3, name: 'Jim Brown', role: 'Payroll Service' }}
    ];
  }

  searchUseremail() {
    this.searchMade = true;
    const users = [
      { id: 1, username: 'john' },
      { id: 2, username: 'jane' }
    ];

    this.searchResult = users.find(user => user.username.toLowerCase() === this.searchTerm.toLowerCase()) || null;
    this.selectedClientUser = this.searchResult
    // console.log(this.selectedClientUser)
  }

  createAssignment() {
    const payload = {
        clientId: this.selectedClientUser?.id, 
        payrollServiceId: this.selectedPayrollServiceUser?.value.id
    };

    this.http.post<AssignUserResDto>('http://localhost:8080/assignuser/create', payload).subscribe({
        next: (response) => {
            console.log('Assignment created successfully', response);
        },
        error: (error) => {
            console.error('Failed to create assignment', error);
        }
    });
}

}
