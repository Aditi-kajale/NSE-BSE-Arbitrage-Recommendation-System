import { Component, Inject, OnDestroy, OnInit, ViewChild} from '@angular/core';
import { DashboardService } from '../dashboard.service';
import { LiveStockService } from '../livestockservice.service';
import {MatPaginator} from '@angular/material/paginator';
import { MatTableDataSource } from '@angular/material/table';
import { SaveStockService } from '../savestockservice.service';
import {NgToastService } from 'ng-angular-popup';
import { CookieService } from 'ngx-cookie-service';
import { SavedStockService } from '../savedstockservice.service';
import {Router} from '@angular/router';
import { BnNgIdleService } from 'bn-ng-idle';
import { DOCUMENT } from '@angular/common';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent implements OnInit{ 
  displayedColumns: string[] = ['company_name', 'close_nse', 'close_bse', 'percent_diff', 'recommendation', 'quantity', 'save'];
  displayedColumns1: string[] = ['company_name', 'close_nse', 'close_bse', 'higher',  'percent_diff'];
  displayedColumns2: string[] = ['company_name', 'close_nse', 'close_bse',  'date_time', 'percent_diff', 'quantity', 'profit', 'delete'];
  datasourceSavedStocks: MatTableDataSource<any> = new MatTableDataSource();
  datasourceLiveStocks: MatTableDataSource<any> = new MatTableDataSource();
  datasourceTopFive: MatTableDataSource<any> = new MatTableDataSource();

  constructor(@Inject(DOCUMENT) private document: Document, private toast: NgToastService, private dashboardService: DashboardService, private liveStockService: LiveStockService, private saveStockService: SaveStockService, private cookieService: CookieService, private savedStockService: SavedStockService,private router: Router, private bnIdle: BnNgIdleService) { }

  saveStock(companyName: String, closeBSE: number, closeNSE: number, diff: number, percDiff: number, quantity: number){
    this.profit = diff * quantity;
    this.saveStockService.saveStock(this.cookieService.get('email'), companyName, closeBSE, closeNSE, diff, percDiff, quantity, this.profit).subscribe(data=>{
      this.toast.success({summary:"Profit earned = " + Math.round(this.profit * 100) / 100 + " Rs", duration:5000});
    }, error=>this.toast.error({ summary:"Failed to Save, Try again!", duration:5000}));
  }

  deleteStock(companyName: String, closeBSE: Number, closeNSE: Number, diff: Number, percDiff: Number, dateTime: String, quantity: Number){
    this.saveStockService.deleteStock(this.cookieService.get('email'), companyName, closeBSE, closeNSE, diff, percDiff, dateTime, quantity).subscribe(data=>{
      this.toast.success({summary:"Deleted Successfully!", duration:5000});
    }, error=>this.toast.error({ summary:"Failed to Delete, Try again!", duration:5000}));
  }

  profit: number = 0;
  interval: number = 10;
  savedStocks: any;
  liveStocks: any;
  topFive: any;

  @ViewChild('datasourceLiveStocksPaginator')
  set paginator1(value: MatPaginator) {
    if (value){
      this.datasourceLiveStocks.paginator = value;
    }
  }

  
  @ViewChild('datasourceSavedStocksPaginator')
  set paginator2(value: MatPaginator) {
    if (value){
      this.datasourceSavedStocks.paginator = value;
    }
  }

  ngOnInit(): void {
     this.bnIdle.startWatching(600).subscribe((res) => {
      if (res && this.document.location.href == "http://localhost:4200/dashboard") {
        this.cookieService.delete('email');
        this.router.navigateByUrl('/base');
        this.toast.info({summary:"Session expired", duration:5000});
        console.log(this.document.location.href);
      }}
    );

   
    this.dashboardService.top().subscribe(data => {
      this.topFive = data;
      this.datasourceTopFive.data = this.topFive;
    }, error=>this.toast.error({summary:"Server Down", duration:5000}));

    this.liveStockService.liveStocks().subscribe(data => {
      this.liveStocks = data;
      this.datasourceLiveStocks.data = this.liveStocks;
      }, error=>this.toast.error({summary:"Server Down", duration:5000}));


      this.savedStockService.savedStocks(this.cookieService.get('email')).subscribe(data => {
        this.savedStocks = data;
        this.datasourceSavedStocks.data = this.savedStocks;
        }, error=>this.toast.error({summary:"Server Down", duration:3000}));

        if(!this.showDashboard()) {
          this.router.navigateByUrl('/base');
        }
      }
  logout() {
    this.cookieService.delete('email');
    this.toast.success({summary:"Logout Successful", duration:3000});
  }
  showDashboard() :Boolean {
    return this.cookieService.get('email') !== "";
  }
}

