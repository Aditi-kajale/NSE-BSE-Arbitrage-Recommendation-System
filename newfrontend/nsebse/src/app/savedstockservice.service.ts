import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class SavedStockService {
  private baseUrl = "http://localhost:9000/savedStocks";
  headers={
    headers: new HttpHeaders({
        'Content-Type': 'application/json'
    })
}
  constructor(private httpClient: HttpClient) {}

  savedStocks(email: String){
    let obj = {
        email: email
    }
    return this.httpClient.post(`${this.baseUrl}`, JSON.stringify(obj), this.headers);
  }
}
