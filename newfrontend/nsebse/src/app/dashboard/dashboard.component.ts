import { Component} from '@angular/core';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent {
  displayedColumns: string[] = ['company_name', 'close_nse', 'close_bse', 'difference', 'percent_diff'];
  dataSource = ELEMENT_DATA;
}


export interface PeriodicElement {
  company_name: string;
  close_nse: number;
  close_bse: number;
  difference: number;
  percent_diff:number;
}


const ELEMENT_DATA: PeriodicElement[] = [
  {company_name: '1', close_nse: 1, close_bse: 1,difference: 1,percent_diff: 1},
  {company_name: '2', close_nse: 1, close_bse: 1,difference: 1,percent_diff: 1},
  {company_name: '3', close_nse: 1, close_bse: 1,difference: 1,percent_diff: 1},
  {company_name: '4', close_nse: 1, close_bse: 1,difference: 1,percent_diff: 1},
  {company_name: '5', close_nse: 1, close_bse: 1,difference: 1,percent_diff: 1}
];

