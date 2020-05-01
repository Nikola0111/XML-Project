import {AfterViewInit, Component, Inject, OnInit, ViewChild} from '@angular/core';
import {MatPaginator} from '@angular/material/paginator';
import {MatSort} from '@angular/material/sort';
import {MatTable} from '@angular/material/table';
import {StatisticsDataSource, StatisticsItem} from './statistics-datasource';
import {Advertisement} from '../../model/advertisement';
import {MAT_DIALOG_DATA, MatDialog, MatDialogRef} from '@angular/material/dialog';
import {AdvertisementService} from '../../services/advertisement.service/advertisement.service';

export interface DialogData {
  advertisement: Advertisement;
}

@Component({
  selector: 'app-statistics',
  templateUrl: './statistics.component.html',
  styleUrls: ['./../advertisement/advertisement-list/advertisement-list.component.css']
})
export class StatisticsComponent implements AfterViewInit, OnInit {
  @ViewChild(MatPaginator, {static: false}) paginator: MatPaginator;
  @ViewChild(MatSort, {static: false}) sort: MatSort;
  @ViewChild(MatTable, {static: false}) table: MatTable<StatisticsItem>;
  dataSource: StatisticsDataSource = new StatisticsDataSource(new Array<Advertisement>());
  advertisements: Array<Advertisement> = new Array<Advertisement>();
  maxTravelled = 0;
  idMaxTravelled: any;

  constructor(public dialog: MatDialog,
              private advertisementService: AdvertisementService) {

  }

  /** Columns displayed in the table. Columns IDs can be added, removed, or reordered. */
  displayedColumns = ['for', 'id', 'name', 'actions'];

  ngOnInit() {
    this.dataSource = new StatisticsDataSource(null);
    this.advertisementService.getAll().subscribe(
    data1 => {
      // tslint:disable-next-line:prefer-for-of
      for (let i = 0; i < data1.length; i++) {
        if (data1[i].travelled >= this.maxTravelled)  {
          this.maxTravelled = data1[i].travelled;
          this.idMaxTravelled = data1[i].id;
        }
      }
      this.advertisementService.getAdvertisement(this.idMaxTravelled).subscribe(
        data => {
          this.advertisements.push(data as Advertisement);

          this.dataSource = new StatisticsDataSource(this.advertisements);
          this.dataSource.sort = this.sort;
          this.dataSource.paginator = this.paginator;
        }
      );

      });

  }

  ngAfterViewInit() {
    this.dataSource.sort = this.sort;
    this.dataSource.paginator = this.paginator;
    this.table.dataSource = this.dataSource;
  }

  openDialog(row: Advertisement) {
    const dialogRef = this.dialog.open(StatisticsDialogComponent, {
      width: '500px',
      height: '325px',
      data: row
    });
  }
}

@Component({
  selector: 'app-statistics-dialog',
  templateUrl: 'statistics-dialog.component.html'
})
export class StatisticsDialogComponent {
  constructor(public dialogRef: MatDialogRef<StatisticsDialogComponent>,
              @Inject(MAT_DIALOG_DATA) public data: DialogData) {
  }
  onNoClick(): void {
    this.dialogRef.close();
  }

}
