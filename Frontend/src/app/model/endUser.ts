import {User} from './user';

export class EndUser extends User {
  id: number;
  activity: boolean;
  adminApproved: string;
  constructor() {
    super();
  }
}
