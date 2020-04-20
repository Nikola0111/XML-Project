import { HttpHeaders, HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Advertisement } from 'src/app/model/advertisement';
import { AdvertisementInCart } from 'src/app/model/advertisementInCart';

const httpOptions = {
    headers: new HttpHeaders({'Content-Type' : 'application/json'})
  };


  
@Injectable()
  export class ShopingCartService {
    private requestUrl: string;
    constructor(private http: HttpClient) {}

    
      public getAllForCart() {
      this.requestUrl = '/server/advertisement/forCart';
      return this.http.get<Array<AdvertisementInCart>>(this.requestUrl, httpOptions);
      }

  }

