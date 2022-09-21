import { Component} from '@angular/core';
import { CookieService } from 'ngx-cookie-service';
import { MatTableDataSource } from '@angular/material/table';
import { DashboardService } from '../dashboard.service';

@Component({
  selector: 'app-login',
  templateUrl: './base.component.html',
  styleUrls: ['./base.component.css']
})
export class BaseComponent {
  displayedColumns: string[] = ['company_name', 'close_nse', 'close_bse', 'recommendation',  'percent_diff'];
  datasourceTopFive: MatTableDataSource<any> = new MatTableDataSource();

  constructor(private dashboardService: DashboardService, private cookieService: CookieService) { }
  topFive: any;

  ngOnInit(): void {
    this.dashboardService.top().subscribe(data => {
      this.topFive = data;
      this.datasourceTopFive.data = this.topFive;
    });
  }
  showDashboard() :Boolean {
    return this.cookieService.get('email') !== "";
  }
}
