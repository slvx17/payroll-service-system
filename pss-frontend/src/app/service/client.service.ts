import { Router } from '@angular/router';
import { HttpClient } from "@angular/common/http";
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

    calendarGet(dto: CalendarReqDto): Observable<CalendarResDto>  {
        // console.log(this.http.post<CalendarResDto>('http://localhost:8080/CL/getCalendar', dto))
        return this.http.post<CalendarResDto>(`${this.baseUrl}/getCalendar`, dto)
    }

    requestChange(reqDto: ReqChangeReqDto): Observable<ReqChangeResDto> {
        return this.http.post<ReqChangeResDto>(`${this.baseUrl}/requestchange`, reqDto);
    }


}