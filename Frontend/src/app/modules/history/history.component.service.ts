import { HttpHeaders, HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Advertisement } from 'src/app/model/advertisement';
import { AdvertisementInCart } from 'src/app/model/advertisementInCart';
import { BookingRequest } from 'src/app/model/requests/bookingRequest';
import { RequestStates } from 'src/app/enums/requestStates';

const httpOptions = {
    headers: new HttpHeaders({'Content-Type' : 'application/json'})
  };


  
@Injectable()
  export class HistoryService {
    private requestUrl: string;
    constructor(private http: HttpClient) {}

    
     


        public   getAllSpecificRequests(state: RequestStates) {
          this.requestUrl = '/server/booking/getAllSpecificForBuyer';
          return this.http.post<Array<BookingRequest>>(this.requestUrl, state ,httpOptions);
          }
    
          
    
          public getSpecificGroupsForCart(state: RequestStates) {
            this.requestUrl = '/server/booking/grups';
            return this.http.post<Array<number>>(this.requestUrl, state, httpOptions);
            }

      public acceptRequest(group : number){
        
        console.log("Odobreni zahtevi");
        return this.http.post<number>('/server/booking/acceptRequest', group, httpOptions);
        
      }

  }
