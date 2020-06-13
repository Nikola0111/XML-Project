import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpResponse } from '@angular/common/http';
import { EndUser } from '../../model/endUser';
import { User } from '../../model/user';
import { Observable } from 'rxjs';
import 'rxjs/add/operator/do';
import 'rxjs/add/observable/throw';
import 'rxjs/add/operator/catch';
import 'rxjs/add/operator/shareReplay';

<<<<<<< HEAD
import { UserDTO } from '../../dtos/user-dto';
=======
import {UserDTO} from '../../dtos/user-dto';
import { LoginInfo } from 'src/app/model/login-info';
>>>>>>> 11edff44088f81a78f6d9ccc1a25c0c0671b63ab


const httpOptions = { headers: new HttpHeaders({ 'Content-Type': 'application/json' }) };

@Injectable({
  providedIn: 'root'
})
export class LoginService {
  private requestUrl: string;

  constructor(private http: HttpClient) { }

<<<<<<< HEAD
  public login(username: string, password: string) {
    const body = {
      "username": username,
      "password": password
    };
=======

  public login(username: string,password:string){
    const body = {"username":username,
  "password":password};
>>>>>>> 11edff44088f81a78f6d9ccc1a25c0c0671b63ab
    return this.http.post<HttpResponse<any>>('/server/login', body, { observe: 'response' })
      .do(response => this.setSession(response))
      .shareReplay();

  }

  public loginToken() {
    console.log("Pogodio");
    return this.http.get<HttpResponse<any>>('/server/loginToken', { observe: 'response' })
      .do(response => localStorage.setItem("xsrfToken", (this.getCookie("XSRF-TOKEN"))))
      .shareReplay();
  }


  public setSession(authResult) {
    console.log("USAO OVDE ");
    console.log(authResult.headers.get('authorization'));
<<<<<<< HEAD



    localStorage.setItem('jwt', authResult.headers.get('authorization'));
  }

  public getCookie(cname) {
    console.log("POGODIO TRAZENJE COOKIeA")

    var name = cname + "=";
    var decodedCookie = decodeURIComponent(document.cookie);
    var ca = decodedCookie.split(';');
    for (var i = 0; i < ca.length; i++) {
      var c = ca[i];
      while (c.charAt(0) == ' ') {
        c = c.substring(1);
      }
      if (c.indexOf(name) == 0) {
        return c.substring(name.length, c.length);
      }
=======
  
    localStorage.setItem('jwt', authResult.headers.get('authorization'));
}    


public getCookie(cname) {
console.log("POGODIO TRAZENJE COOKIeA")

  var name = cname + "=";
  var decodedCookie = decodeURIComponent(document.cookie);
  var ca = decodedCookie.split(';');
  for(var i = 0; i <ca.length; i++) {
    var c = ca[i];
    while (c.charAt(0) == ' ') {
      c = c.substring(1);
    }
    if (c.indexOf(name) == 0) {
      return c.substring(name.length, c.length);
>>>>>>> 11edff44088f81a78f6d9ccc1a25c0c0671b63ab
    }
    return "";
  }




  public passwordChanged(userDTO: UserDTO) {
    const body = JSON.stringify(userDTO);
    return this.http.post('/server/user/passwordChange', body, httpOptions);
  }

  public logOut() {
    console.log('Izlogovan');
    localStorage.removeItem("jwt");
    localStorage.removeItem("xsrfToken")

    this.requestUrl = '/server/authentication/logout';

    return this.http.get<string>(this.requestUrl, httpOptions);
  }
}
