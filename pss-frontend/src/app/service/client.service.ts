import { Router } from '@angular/router';
import { HttpClient, HttpHeaders } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { CalendarReqDto } from '../dto/calendar/calendar-req.dto';
import { CalendarResDto } from '../dto/calendar/calendar-res.dto';
import { Observable } from 'rxjs';
import { ReqChangeReqDto } from '../dto/requestchange/request-change-req.dto';
import { ReqChangeResDto } from '../dto/requestchange/request-change-res.dto';
import { GetAssignmentDto } from '../dto/assignment/get-assignment.dto';
import { EventResDto } from '../dto/requestchange/event-res.dto';
import { EventReqDto } from '../dto/requestchange/event-req.dto';

@Injectable({
    providedIn: 'root'
})
export class ClientService {
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

    getEvents(reqDto: EventReqDto): Observable<EventResDto> {
        const token = localStorage.getItem('token');
        const headers = new HttpHeaders({
            'Authorization': `Bearer ${token}`
        });

        return this.http.post<EventResDto>(`${this.baseUrl}/getevents`, reqDto, { headers });
    }

    getClientAssignmentById(id: number): Observable<GetAssignmentDto> {
        const token = localStorage.getItem('token');
        const headers = new HttpHeaders({
            'Authorization': `Bearer ${token}`
        });

        return this.http.get<GetAssignmentDto>(`${this.baseUrl}/getbyid?id=${id}`, { headers });
    }


}