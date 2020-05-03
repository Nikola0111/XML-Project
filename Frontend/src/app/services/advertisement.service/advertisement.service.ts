import { HttpHeaders, HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Advertisement } from 'src/app/model/advertisement';
import { FilterAdsDTO } from 'src/app/model/filterAdsDTO';
import { ItemInCart } from 'src/app/model/itemInCart';

const httpOptions = {
    headers: new HttpHeaders({'Content-Type' : 'application/json'})
  };

@Injectable()
  export class AdvertisementService {
    private requestUrl: string;
    constructor(private http: HttpClient) {}



    public upload(file : File){
      const fd = new FormData();
      
      

        fd.append("file",file,file.name);

    

      return this.http.post('/server/advertisement/saveImage',fd);
    }

      public save(advertisement: Advertisement) {
        console.log(advertisement);
        return this.http.post<Advertisement>('/server/advertisement/save', advertisement, httpOptions);
      }
      public getAll() {
      this.requestUrl = '/server/advertisement/all';
      return this.http.get<Array<Advertisement>>(this.requestUrl, httpOptions);
      }

      public addAd(itemInCart: ItemInCart) {
        const body = JSON.stringify(itemInCart);
        return this.http.post<ItemInCart>('/server/itemInCart/addItem', body, httpOptions);

      }

      public filter(filterAdsDTO: FilterAdsDTO) {
        console.log(filterAdsDTO);
        const body = JSON.stringify(filterAdsDTO);
        return this.http.post<Array<Advertisement>>('/server/advertisement/filterAdv', body, httpOptions);
      }

      public getAdvertisement(id: number) {
        this.requestUrl = '/server/advertisement/' + id;
        return this.http.get<Advertisement>(this.requestUrl, httpOptions);
      }
  }

