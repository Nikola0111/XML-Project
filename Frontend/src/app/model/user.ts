import {LoginInfo} from './login-info';

export class User {
  id: number;
  name: string;
  surname: string;
  jmbg: string;
  phoneNumber: string;
  loginInfo: LoginInfo;


  constructor() {
    this.loginInfo = new LoginInfo();
  }
}
