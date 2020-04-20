import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {EndUser} from '../../model/endUser';
import {User} from '../../model/user';
import {Observable} from 'rxjs';


const httpOptions = {headers: new HttpHeaders({'Content-Type' : 'application/json'})};

@Injectable({
  providedIn: 'root'
})
export class LoginService {
  private requestUrl: string;

  constructor(private http: HttpClient) { }

  public login(user: User): Observable<User> {
    const body = JSON.stringify(user);
    return this.http.post<User>('/server/user/login', body, httpOptions);

  }

  public logOut() {
    console.log("Izlogovan");

    this.requestUrl = '/server/user/logout';
    return this.http.get<String>(this.requestUrl, httpOptions);
    }
}
