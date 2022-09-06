import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder } from '@angular/forms';
import { LoginserviceService } from '../loginservice.service';
import { User } from '../user';
import {Router} from '@angular/router';
import {NgToastService } from 'ng-angular-popup';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  user:User=new User();
  public loginForm !: FormGroup;
  constructor(private toast: NgToastService, private loginservice: LoginserviceService, private formBuilder: FormBuilder,private router: Router) { }

  ngOnInit(): void {
    this.loginForm = this.formBuilder.group({
      fullname:[''],
      email:[''],
      password:['']
    })
  }
  userLogin(){
    console.log(this.user);
    this.loginservice.Login(this.user).subscribe(data=>{
      this.toast.success({detail: "Success Message", summary:"Login Successful!", duration:5000});
      this.loginForm.reset();
      this.router.navigateByUrl('/dashboard');
    }, error=>this.toast.error({detail: "Error Message", summary:"Login Failed, Try again!", duration:5000}));
  }
}
