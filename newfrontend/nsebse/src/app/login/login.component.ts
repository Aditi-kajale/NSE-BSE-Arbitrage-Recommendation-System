import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { LoginserviceService } from '../loginservice.service';
import {Router} from '@angular/router';
import {NgToastService } from 'ng-angular-popup';
import {FormControl} from '@angular/forms';
import { User } from '../user';
import { CookieService } from 'ngx-cookie-service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent{
  user: User;
  form: FormGroup = new FormGroup({
    email: new FormControl('', [Validators.required, Validators.email]),
    password: new FormControl(''),
  });
  constructor(private toast: NgToastService, private loginservice: LoginserviceService, private formBuilder: FormBuilder,private router: Router, private cookieService: CookieService ) { }

  submit(){
    this.loginservice.Login(this.form.value).subscribe(data=>{
      this.toast.success({summary:"Login Successful!", duration:5000});
      this.cookieService.set('email', this.form.value.email);
      this.form.reset();
      this.router.navigateByUrl('/dashboard');
    }, error=>this.toast.error({summary:"Login Failed, Try again!", duration:5000}));
  }

  
ngOnInit(): void {
  
}
get f()
{
  return this.form.controls;
}
}

