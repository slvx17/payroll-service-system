import { HttpClient, HttpHeaders } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable } from 'rxjs';
import { Router } from '@angular/router';
import { CreateCalendarReqDto } from '../dto/calendar/create-calendar-req.dto';  
import { CreateCalendarResDto } from '../dto/calendar/create-calendar-res.dto';  
import { GetAssignmentDto } from "../dto/assignment/get-assignment.dto";
import { EventResDto } from "../dto/requestchange/event-res.dto";
import { EventReqDto } from "../dto/requestchange/event-req.dto";
import { ReqChangeReqDto } from "../dto/requestchange/request-change-req.dto";
import { ReqChangeResDto } from "../dto/requestchange/request-change-res.dto";

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

    getEvents(reqDto: EventReqDto): Observable<EventResDto> {
        const token = localStorage.getItem('token');
        const headers = new HttpHeaders({
            'Authorization': `Bearer ${token}`
        });

        return this.http.post<EventResDto>(`${this.apiUrl}/getevents`, reqDto, { headers });
    }

    getClientAssignmentById(id: number): Observable<GetAssignmentDto> {
        const token = localStorage.getItem('token');
        const headers = new HttpHeaders({
            'Authorization': `Bearer ${token}`
        });

        return this.http.get<GetAssignmentDto>(`${this.apiUrl}/getbyid?id=${id}`, { headers });
    }

    requestChange(reqDto: ReqChangeReqDto): Observable<ReqChangeResDto> {
        const token = localStorage.getItem('token');
        const headers = new HttpHeaders({
            'Authorization': `Bearer ${token}`
        });

        return this.http.post<ReqChangeResDto>(`${this.apiUrl}/requestchange`, reqDto, {headers});
    }
}
