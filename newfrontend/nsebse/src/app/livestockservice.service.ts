import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class LiveStockService {
  private baseUrl = "http://localhost:9000/liveStocks";
  headers={
    headers: new HttpHeaders({
        'Content-Type': 'application/json'
    })
}
  constructor(private httpClient: HttpClient) {}

  liveStocks(){
    return this.httpClient.get(`${this.baseUrl}`);
  }
}
