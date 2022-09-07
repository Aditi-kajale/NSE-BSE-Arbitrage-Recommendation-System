import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup} from '@angular/forms';
import {HttpClient} from '@angular/common/http';
import { User } from '../user';
import { SignupserviceService } from '../signupservice.service';
import {NgToastService } from 'ng-angular-popup';
import { Router } from '@angular/router';
@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.css']
})
export class SignupComponent implements OnInit {
  public signupForm !: FormGroup;
  user:User=new User();
  constructor(private toast: NgToastService, private signupservice: SignupserviceService, private formBuilder: FormBuilder,private router: Router) { }

  ngOnInit(): void {
    this.signupForm = this.formBuilder.group({
      email:[''],
      password:['']
    })
  }
  
  signUp(){
    console.log(this.user.email);
    this.signupservice.signUp(this.user).subscribe(data=>{
      this.toast.success({detail: "Success Message", summary:"Sign Up Successful!", duration:5000});
      this.signupForm.reset();
      this.router.navigateByUrl('/login');
    }, error=>this.toast.error({detail: "Error Message", summary:"Sign Up Failed, Try again!", duration:5000}));
  }
}
