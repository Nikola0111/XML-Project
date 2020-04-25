import {EndUser} from '../endUser';
import {RequestStates} from '../../enums/requestStates';
import { Advertisement } from '../advertisement';

export class BookingRequest {
 
    id: number;

    userForId: number;

    userToId: number;

    advertisement:Advertisement;

    groupId: number;

    together: boolean;
   

}
