import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { UserRegisReqDto, UserRegisResDto } from '../dto/user/register.dto';
import { HttpClient } from '@angular/common/http';

@Injectable({
    providedIn: 'root'
})

export class UserService {
    private baseUrl = 'http://localhost:8080'; 

    constructor(private http: HttpClient) {}
    
    getRole(): string {
        return localStorage.getItem("role")!
    }

    registerUser(reqDto: UserRegisReqDto): Observable<UserRegisResDto> {
        const url = `http://localhost:8080/register`;
        return this.http.post<UserRegisResDto>(url, reqDto);
    }

    

}

