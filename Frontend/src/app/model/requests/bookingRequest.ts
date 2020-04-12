import {EndUser} from '../endUser';
import {RequestStates} from '../../enums/requestStates';

export class BookingRequest {
  private requestingUser: EndUser;
  private stateOfRequest: RequestStates;
}
