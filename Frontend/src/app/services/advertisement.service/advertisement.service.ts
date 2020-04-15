import { HttpHeaders, HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Advertisement } from 'src/app/model/advertisement';

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
  }

