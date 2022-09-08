import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import {User} from './user';

@Injectable({
  providedIn: 'root'
})
export class DashboardService {
  private baseUrl = "http://localhost:9000/top";
  headers={
    headers: new HttpHeaders({
        'Content-Type': 'application/json'
    })
}
  constructor(private httpClient: HttpClient) {}

  top(){
    return this.httpClient.get(`${this.baseUrl}`);
  }
}
