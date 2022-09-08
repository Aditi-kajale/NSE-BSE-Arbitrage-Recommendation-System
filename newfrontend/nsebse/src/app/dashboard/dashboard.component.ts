import { Component, OnInit, ViewChild} from '@angular/core';
import { DashboardService } from '../dashboard.service';
import { LiveStockService } from '../livestockservice.service';
import {MatPaginator} from '@angular/material/paginator';


@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent implements OnInit { 
  displayedColumns: string[] = ['company_name', 'close_nse', 'close_bse', 'difference', 'percent_diff'];
  topFive: any;
  liveStocks: any;
  constructor(private dashboardService: DashboardService, private liveStockService: LiveStockService) { }

  // @ViewChild(MatPaginator) paginator: MatPaginator;


  ngOnInit() {
    this.dashboardService.top().subscribe(data => {
      this.topFive = data;
    });
    this.liveStockService.liveStocks().subscribe(data => {
      this.liveStocks = data;
    });
    // this.liveStocks.paginator = this.paginator;
  }
}

