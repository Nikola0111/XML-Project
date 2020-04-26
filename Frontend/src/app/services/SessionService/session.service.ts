import { Injectable } from '@angular/core';
import {User} from '../../model/user';

@Injectable({
  providedIn: 'root'
})
export class SessionService {
  /////////////////////////////// Registracija i login
  login: boolean;
  register: boolean;
  ulogovaniKorisnik: User;
  /////////////////////////////// Registracija i login
  /////////////////////////////// Uloge
  isAdmin = false;
  isAgent = false;
  /////////////////////////////// Uloge
  /////////////////////////////// Admin komponente
  adminhome = true;
  requests = false;
  agentreg = false;
  /////////////////////////////// Admin komponente
  /////////////////////////////// Random
  pwChanging = false;

  constructor() { }
}
