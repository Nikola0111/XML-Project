import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup} from '@angular/forms';
import {LoginService} from '../../service/LoginService/login.service';
import {User} from '../../../model/user';
import {SessionService} from '../../service/SessionService/session.service';
import {Router} from '@angular/router';
import {UserType} from '../../../enums/UserType';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  user: User;
  forma: FormGroup;
  constructor(private formBuilder: FormBuilder, private loginService: LoginService, private sessionService: SessionService,
              private router: Router) {
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
        console.log('proverava')
        if (data.userType.toString() === 'ADMINISTRATOR') {
          console.log('administrator je');
          this.sessionService.isAdmin = true;
          this.router.navigate(['/administrator']);
        }
      },
      error => alert('Neuspesno logovanje')
    );

  }

}
