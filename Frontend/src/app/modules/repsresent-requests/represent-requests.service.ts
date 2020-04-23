import { HttpHeaders, HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Advertisement } from 'src/app/model/advertisement';
import { AdvertisementInCart } from 'src/app/model/advertisementInCart';
import { BookingRequest } from 'src/app/model/requests/bookingRequest';

const httpOptions = {
    headers: new HttpHeaders({'Content-Type' : 'application/json'})
  };


  
@Injectable()
  export class RepresentRequestsService {
    private requestUrl: string;
    constructor(private http: HttpClient) {}

    
      public getAllRequests() {
      this.requestUrl = '/server/booking/getAllForMe';
      return this.http.get<Array<BookingRequest>>(this.requestUrl, httpOptions);
      }

      

      public getGroupsForCart() {
        this.requestUrl = '/server/booking/getGroupsForMe';
        return this.http.get<Array<number>>(this.requestUrl, httpOptions);
        }

      public sentRequests(advertisements :Advertisement[]){
        const body = JSON.stringify(advertisements);
        return this.http.post<Advertisement[]>('/server/booking/save', body, httpOptions);
        
      }

  }

