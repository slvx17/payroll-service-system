import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { Router, RouterModule } from '@angular/router';
import { CardModule } from 'primeng/card';
import { InputTextModule } from 'primeng/inputtext';
import { PasswordModule } from 'primeng/password';
import { ButtonModule } from 'primeng/button';
import { AuthService } from "../../service/auth.service";
import { NonNullableFormBuilder, Validators } from '@angular/forms';
import { ToastModule } from 'primeng/toast';
import { DividerModule } from 'primeng/divider';
import { MessageService } from 'primeng/api';

@Component({
  selector: 'app-login',
  standalone: true,
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'],
  imports: [
    CommonModule,
    FormsModule,
    ReactiveFormsModule,

    CardModule,
    InputTextModule,
    PasswordModule,
    ButtonModule,
    RouterModule,
    ToastModule,
    DividerModule,
  ],
  providers: [MessageService]
})
export class LoginComponent {
    name = "";
    loginReq = this.fb.group({
        email: ["", [Validators.required]],
        password: ["", [Validators.required, Validators.minLength(10)]],
    });
    loading = false;
    submitted = false;

    constructor(private fb:NonNullableFormBuilder, private router:Router, private authService: AuthService){}

    onSubmit() {
        this.submitted = true;

        if (this.loginReq.invalid) {
            return;
        }
        this.loading = true;

        const loginReqRaw = this.loginReq.getRawValue();
        this.authService.login(loginReqRaw).subscribe({
            next: (res) => {
                this.authService.saveLocal(res.token, res.role, res.email);
                // console.log(res.role);
                this.router.navigate([`${res.role}/dashboard`]);
                this.loading = false;
            },
            error: (err) => {
                console.error(err);
                this.loading = false;
            }
        });
    }
}
