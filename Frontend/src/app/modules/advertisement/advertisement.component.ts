import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup,ReactiveFormsModule} from '@angular/forms';
import { Advertisement } from 'src/app/model/advertisement';
import { AdvertisementService } from '../service/advertisement.service';

@Component({
  selector: 'app-advertisement',
  templateUrl: './advertisement.component.html',
  styleUrls: ['./advertisement.component.css']
})
export class AdvertisementComponent implements OnInit {
  loading = false;
  forma: FormGroup;
  submitted = false;
  advertisement: Advertisement;

  constructor(private formBuilder: FormBuilder,
              private advertisementService: AdvertisementService) {
                this.advertisement = new Advertisement();
               }

  ngOnInit(): void {
    this.forma = this.formBuilder.group({
      name: [''],
      model: [''],
      brand: [''],
      fuelType: [''],
      transmissionType: [''],
      carClass: [''],
      travelled: [''],
      price: [''],
      carSeats: ['']
    });
  }

  onSubmit() {
    this.advertisementService.save(this.advertisement).subscribe();
  }

}
