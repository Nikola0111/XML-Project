import {User} from './user';
import {Advertisement} from './advertisement';

export class EndUser  {
  id:number;
  activity: boolean;
  advertisements: Array<Advertisement>;
  adminApproved: boolean;
  user: User;
  blocked: boolean;
  numberOfRequestsCanceled: number;
  rentedCars: Advertisement[];

  constructor() {

  }
}
