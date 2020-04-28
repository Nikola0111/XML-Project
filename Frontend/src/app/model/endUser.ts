import {User} from './user';

export class EndUser extends User {
  activity: boolean;
  adminApproved: boolean;
  blocked: boolean;
  numberOfRequestsCanceled: number;

  constructor() {
    super();
  }
}
