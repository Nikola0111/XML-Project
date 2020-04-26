import { HttpHeaders, HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Advertisement } from 'src/app/model/advertisement';
<<<<<<< HEAD
import { FilterAdsDTO } from 'src/app/model/filterAdsDTO';
=======
import { ItemInCart } from 'src/app/model/itemInCart';
>>>>>>> master

const httpOptions = {
    headers: new HttpHeaders({'Content-Type' : 'application/json'})
  };

@Injectable()
  export class AdvertisementService {
    private requestUrl: string;
    constructor(private http: HttpClient) {}

      public save(advertisement: Advertisement) {
        console.log(advertisement);
        const body = JSON.stringify(advertisement);
        return this.http.post<Advertisement>('/server/advertisement/save', body, httpOptions);
      }
      public getAll() {
      this.requestUrl = '/server/advertisement/all';
      return this.http.get<Array<Advertisement>>(this.requestUrl, httpOptions);
      }

      public addAd(itemInCart:ItemInCart){
        const body = JSON.stringify(itemInCart);
        return this.http.post<ItemInCart>('/server/itemInCart/addItem', body, httpOptions);
        
      }

      public filter(filterAdsDTO: FilterAdsDTO)
      {
        console.log(filterAdsDTO);
        const body = JSON.stringify(filterAdsDTO);
        return this.http.post<Array<Advertisement>>('/server/advertisement/filterAdv', body, httpOptions);
      }
  }

