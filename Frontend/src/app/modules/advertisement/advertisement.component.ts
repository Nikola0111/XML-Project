
import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup} from '@angular/forms';
import {Advertisement} from 'src/app/model/advertisement';
import {AdvertisementService} from '../../services/advertisement.service/advertisement.service';
import {ImageModelService} from '../../services/imageModel.service/imageModel.service';
import {ImageModel} from '../../model/imageModel';


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

  imagesUrl: string[];
  reader: FileReader;
  formData: FormData;
  selectedFiles: Array<File> = new Array<File>();
  imageModel: ImageModel;

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

    this.onUpload();

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
