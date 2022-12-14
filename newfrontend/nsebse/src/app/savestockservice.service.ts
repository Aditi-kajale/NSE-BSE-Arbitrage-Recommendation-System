import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class SaveStockService {
  headers={
    headers: new HttpHeaders({
        'Content-Type': 'application/json'
    })
}
  constructor(private httpClient: HttpClient) {}

  saveStock(email: String, companyName: String, closeBse: Number, closeNse: Number, difference: Number, percentDiff: Number, quantity: Number, profit: Number):Observable<object>{
    let obj = {
        email: email,
        companyName: companyName,
        closeBSE: closeBse,
        closeNSE: closeNse,
        diff: difference,
        percDiff: percentDiff,
        dateTime: "",
        quantity: quantity,
        profit: profit
    }
    return this.httpClient.post("http://localhost:9000/save", JSON.stringify(obj), this.headers);
  }



  deleteStock(email: String, companyName: String, closeBse: Number, closeNse: Number, difference: Number, percentDiff: Number, dateTime: String, quantity: Number):Observable<object>{
    let obj = {
        email: email,
        companyName: companyName,
        closeBSE: closeBse,
        closeNSE: closeNse,
        diff: difference,
        percDiff: percentDiff,
        dateTime: dateTime,
        quantity: quantity
    }
    return this.httpClient.post("http://localhost:9000/delete", JSON.stringify(obj), this.headers);
  }

}
