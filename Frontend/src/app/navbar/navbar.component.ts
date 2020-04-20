import {Component, OnInit} from '@angular/core';
import {SessionService} from '../modules/service/SessionService/session.service';
import {UserType} from '../enums/UserType';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit {

  isAdmin = false;

  constructor(private sessionService: SessionService) {
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

  showRequests() {
    this.sessionService.requests = true;
    this.sessionService.adminhome = false;
  }

  showHome() {
    this.sessionService.adminhome = true;
    this.sessionService.requests = false;
  }
}
