import { AfterViewInit, Component, OnInit, ViewChild } from '@angular/core';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { MatTable } from '@angular/material/table';
import { ShoppingCartDataSource, ShoppingCartItem } from './shopping-cart-datasource';
import { Advertisement } from 'src/app/model/advertisement';
import { ShopingCartService } from './shoping-cart.service';
import { AdvertisementInCart } from 'src/app/model/advertisementInCart';
import {SessionService} from '../../services/SessionService/session.service';




@Component({
  selector: 'app-shopping-cart',
  templateUrl: './shopping-cart.component.html',
  styleUrls: ['./shopping-cart.component.css']
})
export class ShoppingCartComponent implements AfterViewInit, OnInit {
  @ViewChild(MatPaginator, {static: false}) paginator: MatPaginator;
  @ViewChild(MatSort, {static: false}) sort: MatSort;
  @ViewChild(MatTable, {static: false}) table: MatTable<ShoppingCartItem>;
  dataSource: ShoppingCartDataSource;

  advertisements: AdvertisementInCart[];
  dialogData: AdvertisementInCart;
  selected: AdvertisementInCart;
  sameOwner: AdvertisementInCart[];

  /** Columns displayed in the table. Columns IDs can be added, removed, or reordered. */
  displayedColumns = ['name', 'model', 'brand', 'fuelType', 'transType', 'carClass','owner', "checkbox", "button"];


  constructor(private shopingCartService: ShopingCartService, private sessionService: SessionService){
    console.log(this.sessionService.ulogovaniKorisnik);
  }

  ngOnInit() {
    this.dataSource = new ShoppingCartDataSource(null);
    this.shopingCartService.getAllForCart().subscribe(
      data => {
        this.advertisements = data;
        this.sameOwner= data;

        this.sameOwner.forEach(same => {


            this.advertisements.forEach(element => {

              if(same.id!=element.id){

              if(same.postedBy.jmbg===element.postedBy.jmbg){
                same.owner=true;
              }


            }

            });

        });


        this.dataSource = new ShoppingCartDataSource(this.sameOwner);
      }

    );


  }

  sendRequest(){
    console.log("Pogodio dugme u ts");
    this.shopingCartService.sentRequests(this.sameOwner).subscribe();

  }

  remove(advertisement :AdvertisementInCart){
    console.log("pogodi ga")
    this.dataSource.data.splice(this.dataSource.data.indexOf(advertisement), 1);

    //this.sameOwner.splice(this.sameOwner.indexOf(advertisement),1);
    //this.dataSource=new ShoppingCartDataSource(this.sameOwner);

  }




  ngAfterViewInit() {
    this.dataSource.sort = this.sort;
    this.dataSource.paginator = this.paginator;
    this.table.dataSource = this.dataSource;
  }
}
