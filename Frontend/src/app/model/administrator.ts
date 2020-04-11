import {User} from './user';

export class Administrator extends User {
  private _surname: string;
  private _jmbg: string;


  constructor(id: number, name: string, email: string, password: string, surname: string, jmbg: string) {
    super(id, name, email, password);
    this._surname = surname;
    this._jmbg = jmbg;
  }


  get surname(): string {
    return this._surname;
  }

  set surname(value: string) {
    this._surname = value;
  }

  get jmbg(): string {
    return this._jmbg;
  }

  set jmbg(value: string) {
    this._jmbg = value;
  }
}
