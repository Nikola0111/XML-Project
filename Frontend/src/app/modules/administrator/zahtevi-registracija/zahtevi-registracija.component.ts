import { Component, OnInit } from '@angular/core';
import {EndUser} from '../../../model/endUser';
import {EndUserServiceService} from '../../../services/EndUserService/end-user-service.service';

@Component({
  selector: 'app-zahtevi-registracija',
  templateUrl: './zahtevi-registracija.component.html',
  styleUrls: ['./zahtevi-registracija.component.css']
})
export class ZahteviRegistracijaComponent implements OnInit {
  endusers: EndUser[];
  constructor(private endUserService: EndUserServiceService) {
    this.endUserService.getUnregistered().subscribe(data =>
      {
        this.endusers = data;
        console.log(this.endusers);}
    );
  }

  ngOnInit() {

  }

  accept(endUser: EndUser){
    this.endUserService.confirm(endUser.id).subscribe(data => {
      this.endUserService.getUnregistered().subscribe(newData => this.endusers = newData);
    });
  }

  reject(endUser: EndUser){
    this.endUserService.reject(endUser.id).subscribe(data => {
      this.endUserService.getUnregistered().subscribe(newData => this.endusers = newData);
    });
  }

}
