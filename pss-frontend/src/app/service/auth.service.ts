import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { LoginReqDto } from "../dto/user/login-req.dto";
import { LoginResDto } from "../dto/user/login-res.dto";

@Injectable({
    providedIn: 'root'
})
export class AuthService {
    constructor(private http: HttpClient){

    }

    login(dto: LoginReqDto){
        return this.http.post<LoginResDto>('http://localhost:8080/login', dto)
    }

    saveLocal(token: string, role: string, email: string){
        localStorage.setItem("token", token)
        localStorage.setItem("role", role)
        localStorage.setItem("email", email)
    }

}