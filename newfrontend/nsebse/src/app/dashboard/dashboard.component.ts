import { Component, OnInit, ViewChild} from '@angular/core';
import { DashboardService } from '../dashboard.service';
import { LiveStockService } from '../livestockservice.service';
import {MatPaginator} from '@angular/material/paginator';
import { MatTableDataSource } from '@angular/material/table';
import { SaveStockService } from '../savestockservice.service';
import {NgToastService } from 'ng-angular-popup';
import { CookieService } from 'ngx-cookie-service';
import { SavedStockService } from '../savedstockservice.service';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent implements OnInit { 
  displayedColumns: string[] = ['company_name', 'close_nse', 'close_bse', 'difference', 'percent_diff', 'save'];
  displayedColumns1: string[] = ['company_name', 'close_nse', 'close_bse', 'difference', 'percent_diff'];
  displayedColumns2: string[] = ['company_name', 'close_nse', 'close_bse', 'difference', 'percent_diff', 'date_time'];
  datasourceSavedStocks: MatTableDataSource<any> = new MatTableDataSource();
  datasourceLiveStocks: MatTableDataSource<any> = new MatTableDataSource();
  datasourceTopFive: MatTableDataSource<any> = new MatTableDataSource();

  constructor(private toast: NgToastService, private dashboardService: DashboardService, private liveStockService: LiveStockService, private saveStockService: SaveStockService, private cookieService: CookieService, private savedStockService: SavedStockService) { }


  saveStock(companyName: String, closeBSE: Number, closeNSE: Number, diff: Number, percDiff: Number){
    this.saveStockService.saveStock(this.cookieService.get('email'), companyName, closeBSE, closeNSE, diff, percDiff).subscribe(data=>{
      this.toast.success({detail: "Success Message", summary:"Saved Successfully!", duration:5000});
    }, error=>this.toast.error({detail: "Error Message", summary:"Failed to Save, Try again!", duration:5000}));
  }


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
    this.dashboardService.top().subscribe(data => {
      this.topFive = data;
      this.datasourceTopFive.data = this.topFive;
    });

    this.liveStockService.liveStocks().subscribe(data => {
      this.liveStocks = data;
      this.datasourceLiveStocks.data = this.liveStocks;
      });


      this.savedStockService.savedStocks(this.cookieService.get('email')).subscribe(data => {
        this.savedStocks = data;
        this.datasourceSavedStocks.data = this.savedStocks;
        });
  }
  logout() {
    this.cookieService.delete('email');
  }
}

