import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder } from '@angular/forms';
import { LoginserviceService } from '../loginservice.service';
import { User } from '../user';
import {Router} from '@angular/router';
import {NgToastService } from 'ng-angular-popup';

@Component({
  selector: 'app-login',
  templateUrl: './base.component.html',
  styleUrls: ['./base.component.css']
})
export class BaseComponent {
  
}
