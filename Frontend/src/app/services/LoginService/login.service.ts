import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {EndUser} from '../../model/endUser';
import {User} from '../../model/user';
import {Observable} from 'rxjs';
import {UserDTO} from '../../dtos/user-dto';


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

  public passwordChanged(userDTO: UserDTO) {
    const body = JSON.stringify(userDTO);
    return this.http.post('/server/user/passwordChange', body, httpOptions);
  }

  public logOut() {
    console.log('Izlogovan');

    this.requestUrl = '/server/user/logout';
    return this.http.get<string>(this.requestUrl, httpOptions);
    }
}
