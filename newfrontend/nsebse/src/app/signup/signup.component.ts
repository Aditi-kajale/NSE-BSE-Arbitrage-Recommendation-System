import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import { FormControl } from '@angular/forms';
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
    email: new FormControl('', [Validators.required, Validators.email]),
    password: new FormControl(''),
  });
  constructor(private toast: NgToastService, private signupservice: SignupserviceService, private formBuilder: FormBuilder,private router: Router) { }

  submit(){
    if (this.form.valid) {
      console.log(this.form.value);
    this.signupservice.signUp(this.form.value).subscribe(data=>{
      this.toast.success({summary:"Sign Up Successful!", duration:5000});
      this.form.reset();
      this.router.navigateByUrl('/login');
    }, error=>this.toast.error({summary:"Sign Up Failed, Try again!", duration:5000}));
  }
  }
  get f()
  {
    return this.form.controls;
  }
}
