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
    if (this.advertisement.fuelType === 0) {
      this.advertisement.fuelType = FuelType.GASOLINE;
    } else if (this.advertisement.fuelType === 1) {
      this.advertisement.fuelType = FuelType.GAS;
    } else if (this.advertisement.fuelType === 2) {
      this.advertisement.fuelType = FuelType.DISEL;
    }

    if ( this.advertisement.transmissionType === 0) {
      this.advertisement.transmissionType = TransmissionType.MANUAL;
    } else if ( this.advertisement.transmissionType === 1) {
      this.advertisement.transmissionType = TransmissionType.AUTOMATIC;
    } else if ( this.advertisement.transmissionType === 2) {
      this.advertisement.transmissionType = TransmissionType.SEMI_AUTOMATIC;
    }

    if ( this.advertisement.carClass === 0) {
      this.advertisement.carClass = CarClass.OLD_TIMER;
    } else if ( this.advertisement.carClass === 1) {
      this.advertisement.carClass = CarClass.CITY_CAR;
    } else if ( this.advertisement.carClass === 2) {
      this.advertisement.carClass = CarClass.SUV;
    }

    this.advertisementService.save(this.advertisement).subscribe();
  }

}
