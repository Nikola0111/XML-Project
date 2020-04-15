import { Component, OnInit } from '@angular/core';
import {EndUser} from '../../../model/endUser';
import {FormBuilder, FormGroup} from '@angular/forms';
import {LoginService} from '../../service/LoginService/login.service';
import {User} from '../../../model/user';
import {SessionService} from '../../service/SessionService/session.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  user: User;
  forma: FormGroup;
  constructor(private formBuilder: FormBuilder, private loginService: LoginService, private sessionService: SessionService) {
    this.user = new User();
  }

  ngOnInit() {
    this.forma = this.formBuilder.group({
      email: [''],
      password: ['']
    });
  }

  onSubmit() {
    this.loginService.login(this.user).subscribe(
      data => {
        console.log(data);
        this.sessionService.ulogovaniKorisnik = data;
      },
      error => alert('Neuspesno logovanje')
    );

  }

}
