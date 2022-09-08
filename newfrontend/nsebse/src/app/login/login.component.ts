import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder } from '@angular/forms';
import { LoginserviceService } from '../loginservice.service';
import { User } from '../user';
import {Router} from '@angular/router';
import {NgToastService } from 'ng-angular-popup';
import {FormControl} from '@angular/forms';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent{
  form: FormGroup = new FormGroup({
    email: new FormControl(''),
    password: new FormControl(''),
  });
  constructor(private toast: NgToastService, private loginservice: LoginserviceService, private formBuilder: FormBuilder,private router: Router) { }

  submit(){
    this.loginservice.Login(this.form.value).subscribe(data=>{
      this.toast.success({detail: "Success Message", summary:"Login Successful!", duration:5000});
      this.form.reset();
      this.router.navigateByUrl('/dashboard');
    }, error=>this.toast.error({detail: "Error Message", summary:"Login Failed, Try again!", duration:5000}));
  }
}
