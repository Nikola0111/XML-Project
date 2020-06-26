import { Component } from '@angular/core';
import {RegisterService} from './services/RegisterService/register.service';
import {EndUser} from './model/endUser';
import { User } from './model/user';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'angularclient';

  constructor(private registrationService: RegisterService) {}

  register() {
    this.registrationService.register(new User()).subscribe();
  }
}
