import {User} from './user';
import {Advertisement} from './advertisement';

export class EndUser extends User {
  id: number;
  activity: boolean;
  adminApproved: string;
  advertisements: Array<Advertisement>;
  constructor() {
    super();
  }
}
