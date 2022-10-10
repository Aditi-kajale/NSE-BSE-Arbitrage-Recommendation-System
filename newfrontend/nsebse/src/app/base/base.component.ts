import { Component, Inject} from '@angular/core';
import { CookieService } from 'ngx-cookie-service';
import { MatTableDataSource } from '@angular/material/table';
import { DashboardService } from '../dashboard.service';
import {NgToastService } from 'ng-angular-popup';
import { BnNgIdleService } from 'bn-ng-idle';
import { DOCUMENT } from '@angular/common';

@Component({
  selector: 'app-login',
  templateUrl: './base.component.html',
  styleUrls: ['./base.component.css']
})
export class BaseComponent {
  displayedColumns: string[] = ['company_name', 'close_nse', 'close_bse', 'recommendation',  'percent_diff'];
  datasourceTopFive: MatTableDataSource<any> = new MatTableDataSource();

  constructor(@Inject(DOCUMENT) private document: Document, private bnIdle: BnNgIdleService, private toast: NgToastService, private dashboardService: DashboardService, private cookieService: CookieService) { }
  topFive: any;

  ngOnInit(): void {
    this.dashboardService.top().subscribe(data => {
      this.topFive = data;
      this.datasourceTopFive.data = this.topFive;
    }, error=>this.toast.error({summary:"Server Down", duration:3000}));

    this.showDashboard() && this.bnIdle.startWatching(600).subscribe((res) => {
      if (res && this.document.location.href == "http://localhost:4200/base") {
        this.cookieService.delete('email');
      }
    });

  }
  
  showDashboard() :Boolean {
    return this.cookieService.get('email') !== "";
  }
}
