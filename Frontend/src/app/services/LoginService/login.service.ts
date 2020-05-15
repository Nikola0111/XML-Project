import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders, HttpResponse} from '@angular/common/http';
import {EndUser} from '../../model/endUser';
import {User} from '../../model/user';
import {Observable} from 'rxjs';
import 'rxjs/add/operator/do';
import 'rxjs/add/observable/throw';
import 'rxjs/add/operator/catch';
import 'rxjs/add/operator/shareReplay';

import {UserDTO} from '../../dtos/user-dto';


const httpOptions = {headers: new HttpHeaders({'Content-Type' : 'application/json'})};

@Injectable({
  providedIn: 'root'
})
export class LoginService {
  private requestUrl: string;

  constructor(private http: HttpClient) { }

  public login(username: string,password:string){
    const body = {"username":username,
  "password":password};
    return this.http.post<HttpResponse<any>>('/server/login', body, { observe: 'response' })
    .do(response => this.setSession(response)) 
    .shareReplay();

  }


  public setSession(authResult) {
    console.log("USAO OVDE ");
    console.log(authResult.headers.get('authorization'));
   

    localStorage.setItem('id_token', authResult.headers.get('authorization'));
}          

logout() {
    localStorage.removeItem("id_token");
}



  public passwordChanged(userDTO: UserDTO) {
    const body = JSON.stringify(userDTO);
    return this.http.post('/server/user/passwordChange', body, httpOptions);
  }

  public logOut() {
    console.log('Izlogovan');
    localStorage.removeItem("id_token");
    this.requestUrl = '/server/user/logout';
    return this.http.get<string>(this.requestUrl, httpOptions);
    }
}
