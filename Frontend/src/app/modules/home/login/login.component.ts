import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup} from '@angular/forms';
import {LoginService} from '../../../services/LoginService/login.service';
import {User} from '../../../model/user';
import {SessionService} from '../../../services/SessionService/session.service';
import {Router} from '@angular/router';
import {UserType} from '../../../enums/UserType';
import {AgentService} from '../../../services/AgentService/agent.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  user: User;
  forma: FormGroup;
  constructor(private agentService: AgentService, private formBuilder: FormBuilder, private loginService: LoginService, private sessionService: SessionService,
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
          this.sessionService.isAgent = false;
          console.log(this.sessionService.isAdmin + ' Admin registrovan');
          this.router.navigate(['/administrator']);
        } else if (data.userType.toString() === 'AGENT') {
          this.agentService.checkPasswordChanged().subscribe(isChanged =>{
            if (isChanged === false){
              this.sessionService.pwChanging = true;
              this.router.navigate(['/izmenaLozinke']);
            } else {
              this.sessionService.isAgent = true;
              this.sessionService.isAdmin = false;
              this.router.navigate(['/agent']);
            }
          });

        }
      },
      error => alert('Neuspesno logovanje')
    );

  }

}
