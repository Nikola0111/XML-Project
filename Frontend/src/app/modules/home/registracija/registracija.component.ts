import { Component, OnInit } from '@angular/core';
import { EndUser } from '../../../model/endUser';
import { RegisterService } from '../../service/register.service';
import {FormBuilder, FormGroup} from '@angular/forms';

@Component({
  selector: 'app-registracija',
  templateUrl: './registracija.component.html',
  styleUrls: ['./registracija.component.css']
})
export class RegistracijaComponent implements OnInit {
  forma: FormGroup;
  endUser: EndUser;

  constructor(private formBuilder: FormBuilder, private registerService: RegisterService ) {
    this.endUser = new EndUser();
  }

  ngOnInit() {
    this.forma = this.formBuilder.group({
      name: [''],
      surname: [''],
      username: [''],
      email: [''],
      password: [''],
      jmbg: [''],
      phoneNumber: ['']
    });
  }

  onSubmit() {
    this.registerService.register(this.endUser).subscribe();
  }
}
