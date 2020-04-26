import {AfterViewInit, Component, Inject, OnInit, ViewChild} from '@angular/core';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { MatTable } from '@angular/material/table';
import { AdvertisementListDataSource, AdvertisementListItem } from './advertisement-list-datasource';
import {Advertisement} from '../../../model/advertisement';

import {AdvertisementService} from '../../../services/advertisement.service/advertisement.service';
import {MAT_DIALOG_DATA, MatDialog, MatDialogRef} from '@angular/material/dialog';
import {ImageModelService} from '../../../services/imageModel.service/imageModel.service';
import {ImageModel} from '../../../model/imageModel';

export interface DialogData {
  images: Array<ImageModel>;
}

@Component({
  selector: 'app-advertisement-list',
  templateUrl: './advertisement-list.component.html',
  styleUrls: ['./advertisement-list.component.css', '/../advertisement.component.css']
})
export class AdvertisementListComponent implements AfterViewInit, OnInit {
  @ViewChild(MatPaginator, {static: false}) paginator: MatPaginator;
  @ViewChild(MatSort, {static: false}) sort: MatSort;
  @ViewChild(MatTable, {static: false}) table: MatTable<AdvertisementListItem>;
  dataSource: AdvertisementListDataSource;
  advertisements: Advertisement[];
  images: Array<ImageModel> = new Array<ImageModel>();
  imagesUrl: string[];


  /** Columns displayed in the table. Columns IDs can be added, removed, or reordered. */
  displayedColumns = ['name', 'model', 'brand', 'fuelType', 'transType', 'carClass', 'travelled', 'price', 'carSeats', 'actions'];

  constructor(private advertisementService: AdvertisementService,
              private imageModelService: ImageModelService,
              public dialog: MatDialog) {
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

  ngAfterViewInit() {
    this.dataSource.sort = this.sort;
    this.dataSource.paginator = this.paginator;
    this.table.dataSource = this.dataSource;
  }

  openDialog(row: Array<ImageModel>): void {
    alert(row[0].name)
    /*this.images.forEach(element => {
      element.name = '"' + element.name + '"';
    });*/
    const dialogRef = this.dialog.open(ImagesDialogComponent, {
      width: '500px',
      height: '325px',
      data: row
    });
  }

}



@Component({
  selector: 'app-images-dialog',
  templateUrl: 'images-dialog.component.html'
})
export class ImagesDialogComponent {
  constructor(public dialogRef: MatDialogRef<ImagesDialogComponent>,
              @Inject(MAT_DIALOG_DATA) public data: DialogData) {
  }
  onNoClick(): void {
    this.dialogRef.close();
  }

}
