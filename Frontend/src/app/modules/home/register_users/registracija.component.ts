import { Component, OnInit } from '@angular/core';
import { EndUser } from '../../../model/endUser';
import { RegisterService } from '../../../services/RegisterService/register.service';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';

@Component({
  selector: 'app-registracija',
  templateUrl: './registracija.component.html',
  styleUrls: ['./registracija.component.css']
})
export class RegistracijaComponent implements OnInit {
  forma: FormGroup;
  endUser: EndUser;

  loading = false;
  submitted = false;

  constructor(private formBuilder: FormBuilder, private registerService: RegisterService ) {
    this.endUser = new EndUser();
  }

  // ngOnInit() {
  //   this.forma = this.formBuilder.group({
  //     name: ['', [Validators.required, Validators.pattern('^[A-Z][a-z]+')]],
  //     surname: ['', [Validators.required, Validators.pattern('^[A-Z][a-z]+')]],
  //     username: ['', Validators.required],
  //     email: ['', [Validators.required, Validators.email]],
  //     password: ['', [Validators.required, Validators.minLength(5), Validators.pattern('^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{5,}$')]],
  //     jmbg: ['', [Validators.required, Validators.pattern('^[0-9]*$'), Validators.minLength(13)]],
  //     phoneNumber: ['', [Validators.required, Validators.pattern('^[0-9]*$'), Validators.minLength(9),  Validators.maxLength(12)]]
  //   });
  // }

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

  get f() { return this.forma.controls; }


  onSubmit() {
    this.submitted = true;

    console.log(this.endUser)
    this.loading = true;

    this.registerService.register(this.endUser).subscribe(
      data => alert('uspesna registracija'),
      error => {
        if (error.error === 'username'){
          alert('The username is taken!');
        } else if (error.error === 'email') {
          alert('The email is taken!');
        } else if (error.error === 'jmbg') {
          alert('The jmbg already exists in our system!');
        }
      }
    );
  }
}
