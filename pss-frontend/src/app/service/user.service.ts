import { Injectable } from '@angular/core';

@Injectable({
    providedIn: 'root'
})

export class UserService {
    
    getRole(): string {
        return localStorage.getItem("role")!
    }
}

