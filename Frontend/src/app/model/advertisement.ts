import {FuelType} from '../enums/fuelType';
import {TransmissionType} from '../enums/transmissionType';
import {CarClass} from '../enums/carClass';
import {User} from '../model/user';
import {CarDetails} from './car-details';

export class Advertisement {
  id: number;
  name: string;
  model: string;
  brand: string;
  fuelType: string;
  transType: string;
  carClass: string;
  travelled: number;
  price: number;
  carSeats: number;
  postedBy: User;
  pictures: FileList[];


  constructor() {

  }


}
