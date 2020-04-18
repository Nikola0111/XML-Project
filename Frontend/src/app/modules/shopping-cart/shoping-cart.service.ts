import { HttpHeaders, HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Advertisement } from 'src/app/model/advertisement';

const httpOptions = {
    headers: new HttpHeaders({'Content-Type' : 'application/json'})
  };

  @Injectable({
    providedIn: 'root'
  })
  export class ShopingCartService {
    private requestUrl: string;
    constructor(private http: HttpClient) {}

    
      public getAllForCart() {
      this.requestUrl = '/server/advertisement/forCart';
      return this.http.get<Array<Advertisement>>(this.requestUrl, httpOptions);
      }

  }

