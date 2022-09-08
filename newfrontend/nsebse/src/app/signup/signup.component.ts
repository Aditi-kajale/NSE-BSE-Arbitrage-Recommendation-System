import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup} from '@angular/forms';
import {HttpClient} from '@angular/common/http';
import { Input, Output, EventEmitter } from '@angular/core';
import { FormControl } from '@angular/forms';
import { User } from '../user';
import { SignupserviceService } from '../signupservice.service';
import {NgToastService } from 'ng-angular-popup';
import { Router } from '@angular/router';
@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.css']
})
export class SignupComponent {
  form: FormGroup = new FormGroup({
    email: new FormControl(''),
    password: new FormControl(''),
  });
  constructor(private toast: NgToastService, private signupservice: SignupserviceService, private formBuilder: FormBuilder,private router: Router) { }

  submit(){
    if (this.form.valid) {
      console.log(this.form.value);
    this.signupservice.signUp(this.form.value).subscribe(data=>{
      this.toast.success({detail: "Success Message", summary:"Sign Up Successful!", duration:5000});
      this.form.reset();
      this.router.navigateByUrl('/login');
    }, error=>this.toast.error({detail: "Error Message", summary:"Sign Up Failed, Try again!", duration:5000}));
  }
  }
}
