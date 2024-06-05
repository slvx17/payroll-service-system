import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpClient, HttpParams } from '@angular/common/http';
import { UserResDto } from '../dto/user/user.dto';

@Injectable({
    providedIn: 'root'
})

export class UserService {
    private baseUrl = 'http://localhost:8080'; 

    constructor(private http: HttpClient) {}

    getAllPs(): Observable<UserResDto[]> {
        return this.http.get<UserResDto[]>(`${this.baseUrl}/getallps`);
    }

    getClientByEmail(email: string): Observable<UserResDto> {
        const params = new HttpParams().set('email', email);
        return this.http.get<UserResDto>(`${this.baseUrl}/getclientbyemail`, { params });
    }

}

