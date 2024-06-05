import { Router } from '@angular/router';
import { UserService } from './../../../service/user.service';
import { Component, OnInit } from '@angular/core';
import { NavbarComponent } from '../../navbar/navbar.component';
import { ButtonModule } from 'primeng/button';
import { InputTextModule } from 'primeng/inputtext';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators, } from '@angular/forms';
import { DropdownModule } from 'primeng/dropdown';
import { FloatLabelModule } from 'primeng/floatlabel';
import { SelectItem } from 'primeng/api';

@Component({
  selector: 'app-create-user',
  standalone: true,
  imports: [
    NavbarComponent,
    ReactiveFormsModule,
    InputTextModule,
    DropdownModule,
    ButtonModule,
    FloatLabelModule,
    // NoopAnimationsModule
  ],
  templateUrl: './create-user.component.html',
  styleUrl: './create-user.component.css'
})
export class CreateUserComponent implements OnInit {
  userForm!: FormGroup;
  roles: SelectItem[] = [];

  constructor(private fb: FormBuilder, private userService: UserService, private router: Router) {}

  ngOnInit() {
    this.userForm = this.fb.group({
      userName: ['', Validators.required],
      userEmail: ['', [Validators.required, Validators.email]],
      password: ['', Validators.required],
      createdById: [localStorage.getItem("id"), Validators.required],
      roleId: ['', Validators.required]
    });

    
    this.roles = [
      { label: 'Super Admin', value: 1 },
      { label: 'Payroll Service', value: 2 },
      { label: 'Client', value: 3 }
    ];
  }



  onSubmit() {
    // console.log(this.userForm.value);
    if (this.userForm.valid) {
      this.userService.registerUser(this.userForm.value).subscribe({
        next: (response) => {
          console.log('Registration successful', response);
          this.router.navigate(["/SA/dashboard"]);
        },
        error: (error) => {
          console.error('Registration failed', error);
        }
      });
    } else {
      console.log('Form is not valid');
    }
  }
}
