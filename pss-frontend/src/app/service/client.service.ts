import { Router } from '@angular/router';
import { HttpClient, HttpHeaders } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { CalendarReqDto } from '../dto/calendar/calendar-req.dto';
import { CalendarResDto } from '../dto/calendar/calendar-res.dto';
import { Observable } from 'rxjs';
import { ReqChangeReqDto } from '../dto/requestchange/request-change-req.dto';
import { ReqChangeResDto } from '../dto/requestchange/request-change-res.dto';

@Injectable({
    providedIn: 'root'
})
export class ClientCalendarService {
    constructor(private http: HttpClient, private router: Router) {

    }
    private baseUrl = 'http://localhost:8080/CL';
    token = localStorage.getItem('token');

    calendarGet(dto: CalendarReqDto): Observable<CalendarResDto>  {
        const headers = new HttpHeaders({
            'Authorization': `Bearer ${this.token}`
        });
        // console.log(this.http.post<CalendarResDto>('http://localhost:8080/CL/getCalendar', dto))
        return this.http.post<CalendarResDto>(`${this.baseUrl}/getCalendar`, dto, { headers })
    }

    requestChange(reqDto: ReqChangeReqDto): Observable<ReqChangeResDto> {
        const headers = new HttpHeaders({
            'Authorization': `Bearer ${this.token}`
        });

        return this.http.post<ReqChangeResDto>(`${this.baseUrl}/requestchange`, reqDto, { headers });
    }


}