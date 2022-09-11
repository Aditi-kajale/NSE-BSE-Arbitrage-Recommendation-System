import { Observable } from 'rxjs';
import { Injectable } from '@angular/core';
import { CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot, Router } from '@angular/router';
import { CookieService } from 'ngx-cookie-service';
import { NgToastService } from 'ng-angular-popup';

@Injectable()
export class Guard implements CanActivate {
  constructor(private toast: NgToastService, private router: Router, private cookieService: CookieService) {}
  canActivate(
    next: ActivatedRouteSnapshot,
    state: RouterStateSnapshot): Observable<boolean> | Promise<boolean> | boolean {
    if (this.cookieService.get('email') === '') {
        this.toast.info({summary:"Login to your account", duration:5000});
        this.router.navigateByUrl('/login');
      return false;
    }
    return true;
  }
}

