import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {EndUser} from '../../model/endUser';
import {UserType} from '../../enums/UserType';

const httpOptions = {headers: new HttpHeaders({'Content-Type' : 'application/json'})};


@Injectable({
  providedIn: 'root'
})
export class RegisterService {

  constructor(private http: HttpClient) { }

  public register(endUser: EndUser) {
    endUser.userType = UserType.ENDUSER;
    const body = JSON.stringify(endUser);
    return this.http.post<EndUser>('/server/enduser/register', body, httpOptions);
  }
}
