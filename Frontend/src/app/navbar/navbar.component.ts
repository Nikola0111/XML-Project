import {Component, OnInit} from '@angular/core';
import {SessionService} from '../services/SessionService/session.service';
import { LoginService } from '../services/LoginService/login.service';
import {Router} from '@angular/router';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit {

  constructor(private sessionService: SessionService, private loginService: LoginService, private router: Router) {
    console.log(this.sessionService.isAdmin);
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

  logOut() {
    this.sessionService.ulogovaniKorisnik = undefined;
    console.log(this.sessionService.ulogovaniKorisnik);
    this.sessionService.isAdmin = false;
    this.router.navigate(['']);
    this.loginService.logOut().subscribe();
  }
}
