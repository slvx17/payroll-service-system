import { HttpClient, HttpHeaders } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable } from 'rxjs';
import { Router } from '@angular/router';
import { CreateCalendarReqDto } from '../dto/calendar/create-calendar-req.dto';  // Adjust path as necessary
import { CreateCalendarResDto } from '../dto/calendar/create-calendar-res.dto';  // Adjust path as necessary

@Injectable({
    providedIn: 'root'
})
export class PsService {
    private apiUrl = 'http://localhost:8080/CL';  // Adjust this URL to match your actual backend API

    constructor(private http: HttpClient, private router: Router) {}

    createScheduleAndDates(requestData: CreateCalendarReqDto): Observable<CreateCalendarResDto> {
        const token = localStorage.getItem('token');
        const headers = new HttpHeaders({
            'Authorization': `Bearer ${token}`
        });

        return this.http.post<CreateCalendarResDto>(`${this.apiUrl}/createschedule`, requestData, { headers });
    }
}
