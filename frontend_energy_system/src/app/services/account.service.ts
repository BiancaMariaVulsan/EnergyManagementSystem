import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Client, LoginUserReply, LoginUserRequest, SignupUserRequest, UserRole } from '../models/loginuser.model';
import { environment } from 'src/environments/environment';

@Injectable ()
export class AccountService {

    constructor(private http: HttpClient) {}

    getRoles() {
        return this.http.get<UserRole[]>(environment.personApiUrl + 'role');
    }

    loginUser(loginUserInformation: LoginUserRequest): Observable<LoginUserReply> {
        return this.http.post<LoginUserReply>(environment.personApiUrl + 'person/login', loginUserInformation);
    }

    signupUser(loginUserInformation: SignupUserRequest, confirmationpassword: string): Observable<LoginUserReply> {
        if (confirmationpassword == loginUserInformation.password)
            return this.http.post<LoginUserReply>(environment.personApiUrl + 'person/signup', loginUserInformation);
        else
            alert('Please make sure that you typed the password correctly!')
    }

    insertUser(loginUserInformation: SignupUserRequest, confirmationpassword: string): Observable<LoginUserReply> {
        if (confirmationpassword == loginUserInformation.password)
            return this.http.post<LoginUserReply>(environment.personApiUrl + 'person', loginUserInformation);
        else
            alert('Please make sure that you typed the password correctly!')
    }

    deleteUser(id: number):  Observable<boolean> {
        return this.http.delete<boolean>(environment.personApiUrl + 'person/' + id.toString())
    }

    getUsers() {
        return this.http.get<LoginUserReply[]>(environment.personApiUrl + 'person');
    }

    updateUser(client: Client): Observable<boolean> {
        return this.http.put<boolean>(environment.personApiUrl + 'person/' + client.id, client)
    }
}