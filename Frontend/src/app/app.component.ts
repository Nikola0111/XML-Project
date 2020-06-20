import { Component } from '@angular/core';
import {RegisterService} from './services/RegisterService/register.service';
import {EndUser} from './model/endUser';
import { SessionService } from './services/SessionService/session.service';
import {LoginService} from './services/LoginService/login.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'angularclient';

  constructor(private registrationService: RegisterService, private loginService: LoginService, private sessionService:SessionService) {

    this.loginService.getUserByUsername(localStorage.getItem("korisnik")).subscribe(

      data =>{


        this.sessionService.ulogovaniKorisnik=data;

      }

    );
    

  }

  register() {
    this.registrationService.register(new EndUser()).subscribe();
  }
}
