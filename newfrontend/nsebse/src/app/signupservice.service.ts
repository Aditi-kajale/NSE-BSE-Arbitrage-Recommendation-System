import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import {User} from './user';

@Injectable({
  providedIn: 'root'
})
export class SignupserviceService {
  private baseUrl = "http://localhost:9000/signUp";
  headers={
    headers: new HttpHeaders({
        'Content-Type': 'application/json'
    })
}
  constructor(private httpClient: HttpClient) {}
  signUp(user: User):Observable<object>{
    console.log(user.email);
    console.log(user.password);

    return this.httpClient.post(`${this.baseUrl}`, JSON.stringify(user), this.headers);
  }
}
