import { Component, OnInit } from '@angular/core';
import {SessionService} from '../modules/service/SessionService/session.service';
import { LoginService } from '../modules/service/LoginService/login.service';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit {

 

  constructor(private sessionService: SessionService,private loginService:LoginService) { 
    
  }

  ngOnInit() {
    this.sessionService.login = false;
    this.sessionService.register = false;
  }

  showRegister() {
    this.sessionService.register  = true;
    this.sessionService.login  = false;
  }

  showLogin() {
    this.sessionService.register  = false;
    this.sessionService.login  = true;
  }

  logOut(){
    this.loginService.logOut().subscribe();
  }



}
