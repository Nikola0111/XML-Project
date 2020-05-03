
import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup} from '@angular/forms';
import {Advertisement} from 'src/app/model/advertisement';
import {AdvertisementService} from '../../services/advertisement.service/advertisement.service';

import {FuelType} from '../../enums/fuelType';
import {TransmissionType} from '../../enums/transmissionType';
import {CarClass} from '../../enums/carClass';
import {CarDetails} from '../../model/car-details';
import {CarDetailsService} from '../../services/CarDetailsService/car-details.service';



@Component({
  selector: 'app-advertisement',
  templateUrl: './advertisement.component.html',
  styleUrls: ['./advertisement.component.css']
})
export class AdvertisementComponent implements OnInit {

  constructor(private formBuilder: FormBuilder,
              private advertisementService: AdvertisementService,
              private imageModelService: ImageModelService) {
    this.advertisement = new Advertisement();
  }
  loading = false;
  forma: FormGroup;
  submitted = false;
  advertisement: Advertisement;
  fuelType: string;
  transType: string;
  carClass: string;
  brand: string;
  model: string;
  carDetails: CarDetails[];

  carClasses: CarDetails[];
  carFuels: CarDetails[];
  carModels: CarDetails[];
  carBrands: CarDetails[];
  carGearshifts: CarDetails[];


  imagesUrl: string[];
  reader: FileReader;
  formData: FormData;
  selectedFiles: Array<File> = new Array<File>();

  constructor(private formBuilder: FormBuilder,
              private advertisementService: AdvertisementService, private carDetailsService: CarDetailsService) {
                this.advertisement = new Advertisement();
                this.carDetailsService.getAllDetails().subscribe(data => {
                  this.carDetails = data;

                  this.carClasses = this.carDetails.filter(item => {
                    console.log(item.type.toLowerCase());
                    return item.type.toLowerCase() === 'carclass';
                  });

                  this.carFuels = this.carDetails.filter(item => {
                    return item.type.toLowerCase() === 'fuel';
                  });

                  this.carModels = this.carDetails.filter(item => {
                    return item.type.toLowerCase() === 'carmodel';
                  });

                  this.carBrands = this.carDetails.filter(item => {
                    return item.type.toLowerCase() === 'brand';
                  });

                  this.carGearshifts = this.carDetails.filter(item => {
                    return item.type.toLowerCase() === 'gearshift';
                  });
                });
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
      carSeats: [''],
      images: ['']
    });
  }

  onSubmit() {
    this.advertisement.fuelType = this.fuelType;
    this.advertisement.carClass = this.carClass;
    this.advertisement.transType = this.transType;
    this.advertisement.brand = this.brand;
    this.advertisement.model = this.model;


    this.onUpload();


    console.log(this.advertisement);

    this.advertisementService.save(this.advertisement).subscribe();
  }

  onFileSelected(event) {
    console.log(event);
    this.selectedFiles = new Array<File>();
    this.selectedFiles = event.target.files;
  }

  onUpload() {
    if (this.selectedFiles.length === 0) {
      return;
    }
    this.advertisement.images = new Array<ImageModel>();
    // tslint:disable-next-line:prefer-for-of
    for (let i = 0; i < this.selectedFiles.length; i++) {
      // this.imageModelService.uploadImage(this.selectedFiles[i]);
      this.imageModel = new ImageModel();
      this.imageModel.name = this.selectedFiles[i].name;
      this.imageModel.type = this.selectedFiles[i].type;
      this.advertisement.images.push(this.imageModel);
      this.imageModelService.save(this.selectedFiles[i]).subscribe();
    }
  }
}
