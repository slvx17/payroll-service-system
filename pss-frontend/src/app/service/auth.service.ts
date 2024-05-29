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
        return this.http.post<LoginResDto>('http://192.168.40.42:8080/users/login', dto)
    }

    saveToken(token: string){
        localStorage.setItem("token", token)
    }
}