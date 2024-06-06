import { Component, OnInit } from '@angular/core';
import { NavbarComponent } from '../../navbar/navbar.component';
import { StepperModule } from 'primeng/stepper';
import { ButtonModule } from 'primeng/button';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { SelectItem } from 'primeng/api';
import { DropdownModule } from 'primeng/dropdown';
import { HttpClient } from '@angular/common/http';
import { UserResDto } from '../../../dto/user/user.dto';
import { AssignmentService } from '../../../service/assignment.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-assign-user',
  standalone: true,
  imports: [
    CommonModule,
    NavbarComponent,
    StepperModule,
    ButtonModule,
    FormsModule,
    DropdownModule,
  ],
  templateUrl: './assign-user.component.html',
  styleUrl: './assign-user.component.css'
})


export class AssignUserComponent implements OnInit {

  private DEFAULT_USER: UserResDto = {
    id: 0,
    username: 'No Selection',
    message: 'no action yet'
  };

  searchTerm: string = '';
  searchResult: UserResDto | null = null;
  searchMade: boolean = false;
  message: string = "";

  users: SelectItem[] = [];
  selectedClientUser!: UserResDto;
  selectedPayrollServiceUser!: UserResDto;
  selectedPayrollServiceUserId!: number;
  assignmentCreated: boolean = false;

  constructor(private http: HttpClient, private assignmentService: AssignmentService, private router: Router) { }

  ngOnInit(): void {
    this.assignmentService.getAllPs().subscribe(psUsers => {
      this.users = psUsers.map(user => ({ label: user.username, value: user }));
    });
  }

  onUserSelect(userId: number): void {
    const user = this.users.find(u => u.value.id === userId)?.value;
    if (user) {
      this.selectedPayrollServiceUser = user;
    }
  }

  searchUserEmail() {
    this.searchMade = true;
    this.assignmentService.getClientByEmail(this.searchTerm).subscribe({
      next: (res) => {
        this.message = res.message;
        this.searchResult = res;
        this.selectedClientUser = res;
      },
      error: (res) => {
        console.log(res.message)
      }
    });
  }

  createAssignment() {
    if (this.selectedClientUser && this.selectedPayrollServiceUser) {
        this.assignmentService.createAssignment(this.selectedClientUser.id, this.selectedPayrollServiceUser.id).subscribe({
            next: (response) => {
                console.log('Assignment created successfully', response);
                this.assignmentCreated = true;
                setTimeout(() => {
                  this.resetStepper(); 
                }, 2000); 
            },
            error: (error) => {
                console.error('Failed to create assignment', error);
                this.assignmentCreated = false;
            }
        });
    }
  }

  resetStepper() {
    this.router.navigate(["/SA/dashboard"]);
    this.assignmentCreated = false; 
    this.selectedClientUser = this.DEFAULT_USER;
    this.selectedPayrollServiceUser = this.DEFAULT_USER;

    this.selectedPayrollServiceUserId = -1; 
  }
}

