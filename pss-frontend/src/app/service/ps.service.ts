import { HttpClient, HttpHeaders } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable } from 'rxjs';
import { Router } from '@angular/router';
import { CreateCalendarReqDto } from '../dto/calendar/create-calendar-req.dto';  
import { CreateCalendarResDto } from '../dto/calendar/create-calendar-res.dto';  
import { UpdateChangeReqDto } from "../dto/changeapproval/update-change-req.dto";
import { UpdateChangeResDto } from "../dto/changeapproval/update-change-res.dto";
import { GetChangeResDto } from "../dto/changeapproval/get-change-res.dto";
import { GetChangeReqDto } from "../dto/changeapproval/get-change-req.dto";

@Injectable({
    providedIn: 'root'
})
export class PsService {
    private apiUrl = 'http://localhost:8080/CL'; 

    constructor(private http: HttpClient, private router: Router) {}

    createScheduleAndDates(requestData: CreateCalendarReqDto): Observable<CreateCalendarResDto> {
        const token = localStorage.getItem('token');
        const headers = new HttpHeaders({
            'Authorization': `Bearer ${token}`
        });

        return this.http.post<CreateCalendarResDto>(`${this.apiUrl}/createschedule`, requestData, { headers });
    }

    updateChangeRequest(requestData: UpdateChangeReqDto): Observable<UpdateChangeResDto> {
        const headers = new HttpHeaders({
            'Authorization': `Bearer ${localStorage.getItem('token')}`
        });
        return this.http.post<UpdateChangeResDto>(`${this.apiUrl}/updatechangereq`, requestData, { headers });
    }

    getChangeRequest(requestData: GetChangeReqDto): Observable<GetChangeResDto> {
        const headers = new HttpHeaders({
            'Authorization': `Bearer ${localStorage.getItem('token')}`,
            // 'Content-Type': 'application/json'
        });
        return this.http.post<GetChangeResDto>(`${this.apiUrl}/getchangereq`, requestData, {headers});
    }
}
