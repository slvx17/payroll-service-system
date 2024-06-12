import { HttpClient, HttpHeaders } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { EmailDto } from "../dto/email/email.dto";
import { NotificationResDto } from "../dto/notification/notification-res.dto";
import { Observable } from "rxjs";

@Injectable({
    providedIn: 'root'
})

export class NotificationService {
    constructor(private http: HttpClient){

    }
    private baseUrl = 'http://localhost:8080/notification'
    token = localStorage.getItem('token');

    notificationsGetAll(dto: EmailDto): Observable<NotificationResDto[]>  {
        const headers = new HttpHeaders({
            'Authorization': `Bearer ${this.token}`
        });
        // console.log(this.http.post<CalendarResDto>('http://localhost:8080/CL/getCalendar', dto))
        return this.http.post<NotificationResDto[]>(`${this.baseUrl}/getall`, dto, { headers })
    }

    notificationsCheckDeadlines(dto: EmailDto): Observable<NotificationResDto[]>  {
        const headers = new HttpHeaders({
            'Authorization': `Bearer ${this.token}`
        });
        // console.log(this.http.post<CalendarResDto>('http://localhost:8080/CL/getCalendar', dto))
        return this.http.post<NotificationResDto[]>(`${this.baseUrl}/checkdeadlines`, dto, { headers })
    }

    notificationsDeleteAll(dto: EmailDto): Observable<NotificationResDto>  {
        const headers = new HttpHeaders({
            'Authorization': `Bearer ${this.token}`
        });
        return this.http.post<NotificationResDto>(`${this.baseUrl}/deleteall`, dto, { headers })
    }
}