import { Router } from '@angular/router';
import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { CalendarReqDto } from '../dto/calendar/calendar-req.dto';
import { CalendarResDto } from '../dto/calendar/calendar-res.dto';
import { Observable } from 'rxjs';

@Injectable({
    providedIn: 'root'
})
export class ClientCalendarService {
    constructor(private http: HttpClient, private router: Router) {

    }

    calendarGet(dto: CalendarReqDto): Observable<CalendarResDto>  {
        console.log(this.http.post<CalendarResDto>('http://localhost:8080/CL/getCalendar', dto))
        return this.http.post<CalendarResDto>('http://localhost:8080/CL/getCalendar', dto)
    }

    // calendarRequestUpdate(dto: CalendarReqUpdReqDto){
    //     return null;
    // }
}