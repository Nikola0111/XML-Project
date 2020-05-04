import {AfterViewInit, Component, Inject, OnInit, ViewChild} from '@angular/core';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { MatTable } from '@angular/material/table';
import { AdvertisementListDataSource, AdvertisementListItem } from './advertisement-list-datasource';
import { Advertisement } from '../../../model/advertisement';
import { FormBuilder, FormGroup } from '@angular/forms';

import { AdvertisementService } from '../../../services/advertisement.service/advertisement.service';
import { from, forkJoin } from 'rxjs';
import { NavbarComponent } from 'src/app/navbar/navbar.component';
import { FilterAdsDTO } from 'src/app/model/filterAdsDTO';
import { ItemInCart } from 'src/app/model/itemInCart';
import {AdvertisementDetailsComponent} from '../advertisement-details/advertisement-details.component';
import {Router} from '@angular/router';
import {MAT_DIALOG_DATA, MatDialog, MatDialogRef} from '@angular/material/dialog';

export interface DialogData {
  advertisement: Advertisement;
}

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

  itemInCart: ItemInCart;

  advertisementDetails: AdvertisementDetailsComponent;

  /** Columns displayed in the table. Columns IDs can be added, removed, or reordered. */
  displayedColumns = ['name', 'model', 'brand', 'fuelType', 'travelled', 'price', 'discountPrice', 'carSeats', 'button', 'details', 'changeDiscount'];


  constructor(private formBuilder: FormBuilder, private advertisementService: AdvertisementService,
              private router: Router, private dialog: MatDialog) {
    this.itemInCart = new ItemInCart();
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
        console.log(this.dataSource);
      }

    );


  }

  public save(advertisement: Advertisement) {

    this.itemInCart.advertisement = advertisement;
    console.log(this.itemInCart);
    this.advertisementService.addAd(this.itemInCart).subscribe();


  }

  public filter(filterAdsDTO: FilterAdsDTO) {

    if (this.fuelType === 'Gasoline') {
      this.filterAdsDTO.fuelType = 0;
    } else if (this.fuelType === 'Gas') {
      this.filterAdsDTO.fuelType = 1;
    } else if (this.fuelType === 'Diesel') {
      this.filterAdsDTO.fuelType = 2;
    }

    if (this.transmissionType === 'Manual') {
      this.filterAdsDTO.transmissionType = 0;
    } else if (this.transmissionType === 'Automatic') {
      this.filterAdsDTO.transmissionType = 1;
    } else if (this.transmissionType === 'Semi-Automatic') {
      this.filterAdsDTO.transmissionType = 2;
    }

    if (this.carClass === 'Old-Timer') {
      this.filterAdsDTO.carClass = 0;
    } else if (this.carClass === 'City-Car') {
      this.filterAdsDTO.carClass = 1;
    } else if (this.carClass === 'SUV') {
      this.filterAdsDTO.carClass = 2;
    }

    this.itemInCart.timeFrom = this.filterAdsDTO.timeFrom;
    this.itemInCart.timeTo = this.filterAdsDTO.timeTo;


    this.dataSource = new AdvertisementListDataSource(null);
    this.advertisementService.filter(this.filterAdsDTO).subscribe(
      data => {
        this.advertisements = data;
        this.dataSource.data = data;
        this.table.dataSource = this.dataSource;
        this.dataSource.sort = this.sort;
        this.dataSource.paginator = this.paginator;
        console.log(this.dataSource.data);
        console.log(data);
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

  showDetails(row: Advertisement) {
    this.router.navigate(['/advertisement-details', row.id]);
  }

  changeDiscount(row: Advertisement): void {
    const dialogRef = this.dialog.open(ChangeDiscountDialogComponent, {
      data : row,
    });
    dialogRef.afterClosed().subscribe(data => {
      if (data != null) {
        row.discount = data.discount;
        row.priceWithDiscount = row.price - (row.price  * row.discount / 100);
        alert('izmenjena cena' + row.priceWithDiscount)
        this.advertisementService.update(row).subscribe();
        this.ngOnInit();
      }
    });
  }
}

@Component({
  selector: 'app-change-discount-dialog',
  templateUrl: './change-discount-dialog.html',
})
export class ChangeDiscountDialogComponent {
  constructor(public dialogRef: MatDialogRef<ChangeDiscountDialogComponent>,
              @Inject(MAT_DIALOG_DATA) public data) {
  }
  onOkClick(): void {
    alert(this.data.discount);
    this.dialogRef.close(this.data);
  }
  onNoClick(): void {
    this.dialogRef.close();
  }


}
