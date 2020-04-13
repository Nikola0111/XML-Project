import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-homepage',
  templateUrl: './homepage.component.html',
  styleUrls: ['./homepage.component.css']
})
export class HomepageComponent implements OnInit {
  login: boolean;
  register: boolean;
  constructor() { }

  ngOnInit() {
    this.login = false;
    this.register = false;
  }

  showRegister(){
    console.log('pokazuje registraciju');
    this.register = true;
    this.login = false;
  }

  showLogin(){
    console.log('pokazuje login')
    this.register = false;
    this.login = true;
  }

}
