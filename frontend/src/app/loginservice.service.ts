import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import {User} from './user';

@Injectable({
  providedIn: 'root'
})
export class LoginserviceService {
  private baseUrl = "http://localhost:9000/login";
  headers={
    headers: new HttpHeaders({
        'Content-Type': 'application/json'
    })
}
  constructor(private httpClient: HttpClient) {}
  Login(user: User):Observable<object>{
    return this.httpClient.post(`${this.baseUrl}`, JSON.stringify(user), this.headers);
  }
}
