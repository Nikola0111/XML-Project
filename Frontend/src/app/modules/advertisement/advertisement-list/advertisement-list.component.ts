import { AfterViewInit, Component, OnInit, ViewChild } from '@angular/core';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { MatTable } from '@angular/material/table';
import { AdvertisementListDataSource, AdvertisementListItem } from './advertisement-list-datasource';
import { Advertisement } from '../../../model/advertisement';
import {FormBuilder, FormGroup} from '@angular/forms';

import { AdvertisementService } from '../../../services/advertisement.service/advertisement.service';
import { from, forkJoin } from 'rxjs';
import { NavbarComponent } from 'src/app/navbar/navbar.component';
import { FilterAdsDTO } from 'src/app/model/filterAdsDTO';


@Component({
  selector: 'app-advertisement-list',
  templateUrl: './advertisement-list.component.html',
  styleUrls: ['./advertisement-list.component.css']
})
export class AdvertisementListComponent implements AfterViewInit, OnInit {
  @ViewChild(MatPaginator, { static: false }) paginator: MatPaginator;
  @ViewChild(MatSort, { static: false }) sort: MatSort;
  @ViewChild(MatTable, { static: false }) table: MatTable<AdvertisementListItem>;
  dataSource: AdvertisementListDataSource;
  advertisements: Advertisement[];
  dialogData: Advertisement;
  selected: Advertisement;
  filterForm: FormGroup;
  filterAdsDTO: FilterAdsDTO = new FilterAdsDTO();
  fuelType: string;
  transmissionType: string;
  carClass: string;
  /** Columns displayed in the table. Columns IDs can be added, removed, or reordered. */
  displayedColumns = ['name', 'model', 'brand', 'fuelType', 'transType', 'carClass', 'travelled', 'price', 'carSeats', "button"];

  constructor(private formBuilder: FormBuilder, private advertisementService: AdvertisementService) {
  }



  ngOnInit() {

    this.filterForm = this.formBuilder.group({
      fuelType: [''],
      transmissionType: [''],
      carClass: [''],
      travelledFrom: [''],
      travelledTo: [''],
      priceFrom: [''],
      priceTo: [''],
      carSeats: [''],
      timeFrom: [''],
      timeTo: ['']

    });

    this.dataSource = new AdvertisementListDataSource(null);
    this.advertisementService.getAll().subscribe(
      data => {
        this.advertisements = data;
        this.dataSource = new AdvertisementListDataSource(this.advertisements);
      }

    );


  }

  public save(advertisement: Advertisement) {
    this.selected = advertisement;
    console.log(this.selected);
    console.log("Pogodi dugme")
    this.advertisementService.addAd(this.selected).subscribe();


  }

  public filter(filterAdsDTO: FilterAdsDTO) {

    if (this.fuelType === 'Gasoline') {
      this.filterAdsDTO.fuelType = 0;
    } else if (this.fuelType === 'Gas') {
      this.filterAdsDTO.fuelType = 1;
    } else if (this.fuelType === 'Diesel') {
      this.filterAdsDTO.fuelType = 2;
    }

    if ( this.transmissionType === 'Manual') {
      this.filterAdsDTO.transmissionType = 0;
    } else if ( this.transmissionType === 'Automatic') {
      this.filterAdsDTO.transmissionType = 1;
    } else if ( this.transmissionType === 'Semi-Automatic') {
      this.filterAdsDTO.transmissionType = 2;
    }

    if ( this.carClass === 'Old-Timer') {
      this.filterAdsDTO.carClass = 0;
    } else if ( this.carClass === 'City-Car') {
      this.filterAdsDTO.carClass = 1;
    } else if ( this.carClass === 'SUV') {
      this.filterAdsDTO.carClass = 2;
    }

    console.log("uso u predservis" + this.filterAdsDTO.carClass);

    this.dataSource = new AdvertisementListDataSource(null);
    this.advertisementService.filter(this.filterAdsDTO).subscribe(
      data => {
        this.advertisements = data;
        this.dataSource = new AdvertisementListDataSource(this.advertisements);
      }

    );
  }

  ngAfterViewInit() {
    this.table.dataSource = this.dataSource;
    this.dataSource.sort = this.sort;
    this.dataSource.paginator = this.paginator;

    console.log(this.dataSource);
  }
  ngBeforeViewInit() {

  }
}
