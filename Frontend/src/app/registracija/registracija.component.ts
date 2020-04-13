import { Component, OnInit } from '@angular/core';
import { EndUser } from '../model/endUser';
import { RegisterService } from '../services/registration_service/register.service';

@Component({
  selector: 'app-registracija',
  templateUrl: './registracija.component.html',
  styleUrls: ['./registracija.component.css']
})
export class RegistracijaComponent implements OnInit {

  constructor(private registracijaService:  RegisterService ){ }

  ngOnInit() {
  }

  klikni(){
    this.registracijaService.register(new EndUser()).subscribe();
  }

}
