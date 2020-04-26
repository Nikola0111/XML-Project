import { AfterViewInit, Component, OnInit, ViewChild } from '@angular/core';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { MatTable } from '@angular/material/table';
import { AdvertisementListDataSource, AdvertisementListItem } from './advertisement-list-datasource';
import {Advertisement} from '../../../model/advertisement';


import {AdvertisementService} from '../../../services/advertisement.service/advertisement.service';
import { from } from 'rxjs';
import { NavbarComponent } from 'src/app/navbar/navbar.component';
import { ItemInCart } from 'src/app/model/itemInCart';


@Component({
  selector: 'app-advertisement-list',
  templateUrl: './advertisement-list.component.html',
  styleUrls: ['./advertisement-list.component.css']
})
export class AdvertisementListComponent implements AfterViewInit, OnInit {
  @ViewChild(MatPaginator, {static: false}) paginator: MatPaginator;
  @ViewChild(MatSort, {static: false}) sort: MatSort;
  @ViewChild(MatTable, {static: false}) table: MatTable<AdvertisementListItem>;
  dataSource: AdvertisementListDataSource;
  advertisements: Advertisement[];
  dialogData: Advertisement;
  selected: Advertisement;
itemInCart: ItemInCart;
  /** Columns displayed in the table. Columns IDs can be added, removed, or reordered. */
  displayedColumns = ['name', 'model', 'brand', 'fuelType', 'transType', 'carClass', 'travelled', 'price', 'carSeats',"button"];

  constructor(private advertisementService: AdvertisementService) {
    this.itemInCart=new ItemInCart();
  }

  

  ngOnInit() {
    this.dataSource = new AdvertisementListDataSource(null);
    this.advertisementService.getAll().subscribe(
      data => {
        this.advertisements = data;
        this.dataSource = new AdvertisementListDataSource(this.advertisements);
      }
      
    );
    
    
  }

  public save(advertisement: Advertisement) {
   
    this.itemInCart.advertisement=advertisement;
    console.log(this.itemInCart);
    var from = new Date("2015-03-25 12:00:00");
    var to = new Date("2015-03-25 15:00:00");
    this.itemInCart.timeFrom=from;
    this.itemInCart.timeTo=to;

    this.advertisementService.addAd(this.itemInCart).subscribe();

    
  }

  ngAfterViewInit() {
    this.table.dataSource = this.dataSource;
    this.dataSource.sort = this.sort;
    this.dataSource.paginator = this.paginator;
    
    console.log(this.dataSource);
  }
  ngBeforeViewInit(){
    
  }
}
