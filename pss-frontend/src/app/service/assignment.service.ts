import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpClient, HttpParams, HttpHeaders } from '@angular/common/http';
import { UserResDto } from '../dto/user/user.dto';
import { AssignUserResDto } from '../dto/assignment/assign-user-res.dto';
import { EmailDto } from '../dto/assignment/email.dto';

@Injectable({
    providedIn: 'root'
})


export class AssignmentService {
    private baseUrl = 'http://localhost:8080/assignuser'; 

    constructor(private http: HttpClient) {}

    getAllPs(): Observable<UserResDto[]> {
        const headers = new HttpHeaders({
            'Authorization': 'Bearer ' + localStorage.getItem('token')
        });
        return this.http.get<UserResDto[]>(`${this.baseUrl}/getallps`, { headers });
    }

    getAllCByPs(email: EmailDto): Observable<UserResDto[]> {
        const headers = new HttpHeaders({
            'Authorization': 'Bearer ' + localStorage.getItem('token')
        });
        return this.http.post<UserResDto[]>(`${this.baseUrl}/getallcbyps`, email , { headers });
    }

    getClientByEmail(email: string): Observable<UserResDto> {
        const params = new HttpParams().set('email', email);
        const headers = new HttpHeaders({
            'Authorization': 'Bearer ' + localStorage.getItem('token'),
        });
        return this.http.get<UserResDto>(`${this.baseUrl}/getclientbyemail`, { params, headers });
    }

    createAssignment(clientId: number, payrollServiceId: number): Observable<AssignUserResDto> {
        const payload = {
            clientId: clientId,
            payrollServiceId: payrollServiceId
        };

        const headers = new HttpHeaders({
            'Authorization': 'Bearer ' + localStorage.getItem('token')
        });

        return this.http.post<AssignUserResDto>(`${this.baseUrl}/create`, payload, { headers });
    }
}


