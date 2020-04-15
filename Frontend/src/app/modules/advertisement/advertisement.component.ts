
import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup} from '@angular/forms';
import {Advertisement} from 'src/app/model/advertisement';
import {AdvertisementService} from '../../services/advertisement.service/advertisement.service';
import {FuelType} from '../../enums/fuelType';
import {TransmissionType} from '../../enums/transmissionType';
import {CarClass} from '../../enums/carClass';


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
  fuelType: string;
  transType: string;
  carClass: string;

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
      transType: [''],
      carClass: [''],
      travelled: [''],
      price: [''],
      carSeats: ['']
    });
  }

  onSubmit() {
    if (this.fuelType === 'Gasoline') {
      this.advertisement.fuelType = 0;
    } else if (this.fuelType === 'Gas') {
      this.advertisement.fuelType = 1;
    } else if (this.fuelType === 'Diesel') {
      this.advertisement.fuelType = 2;
    }

    if ( this.transType === 'Manual') {
      this.advertisement.transType = 0;
    } else if ( this.transType === 'Automatic') {
      this.advertisement.transType = 1;
    } else if ( this.transType === 'Semi-Automatic') {
      this.advertisement.transType = 2;
    }

    if ( this.carClass === 'Old-Timer') {
      this.advertisement.carClass = 0;
    } else if ( this.carClass === 'City-Car') {
      this.advertisement.carClass = 1;
    } else if ( this.carClass === 'SUV') {
      this.advertisement.carClass = 2;
    }

    this.advertisementService.save(this.advertisement).subscribe();
  }

}
