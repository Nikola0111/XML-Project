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
    this.router.navigate(['/register']);
  }

  rabbitSend(){

    this.loginService.rabbitSend().subscribe();

  }

  showLogin() {
    this.router.navigate(['/login']);
  }
  // Admin components
  showRequests() {
    this.sessionService.requests = true;
    this.sessionService.adminhome = false;
    this.sessionService.agentreg = false;
    this.sessionService.manageUsers = false;
    this.sessionService.codebook = false;
    this.sessionService.manageComments = false;
  }

  codebook() {
    this.sessionService.requests = false;
    this.sessionService.adminhome = false;
    this.sessionService.agentreg = false;
    this.sessionService.manageUsers = false;
    this.sessionService.codebook = true;
    this.sessionService.manageComments = false;
  }

  showHome() {
    this.sessionService.adminhome = true;
    this.sessionService.requests = false;
    this.sessionService.agentreg = false;
    this.sessionService.manageUsers = false;
    this.sessionService.codebook = false;
    this.sessionService.manageComments = false;
  }

  showAgentReg() {
    this.sessionService.adminhome = false;
    this.sessionService.requests = false;
    this.sessionService.agentreg = true;
    this.sessionService.manageUsers = false;
    this.sessionService.codebook = false;
    this.sessionService.manageComments = false;
  }

  manageUsers() {
    this.sessionService.adminhome = false;
    this.sessionService.requests = false;
    this.sessionService.agentreg = false;
    this.sessionService.manageUsers = true;
    this.sessionService.codebook = false;
    this.sessionService.manageComments = false;
  }

  manageComments(){
    this.sessionService.adminhome = false;
    this.sessionService.requests = false;
    this.sessionService.agentreg = false;
    this.sessionService.manageUsers = false;
    this.sessionService.codebook = false;
    this.sessionService.manageComments = true;
  }
  // Admin components

  createReport() {
    this.sessionService.homeAgent = false;
    this.sessionService.report = true;
  }

  showHomeAgent() {
    this.sessionService.report = false;
    this.sessionService.homeAgent = true;
  }

  logOut() {
    this.sessionService.ulogovaniKorisnik = undefined;
    console.log(this.sessionService.ulogovaniKorisnik);
    this.sessionService.isAdmin = false;
    this.router.navigate(['']);
    this.loginService.logOut().subscribe();
  }
}
