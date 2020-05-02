import { Component, OnInit } from '@angular/core';
import {Advertisement} from '../../../model/advertisement';
import {AdvertisementService} from '../../../services/advertisement.service/advertisement.service';
import {ActivatedRoute, Router} from '@angular/router';

@Component({
  selector: 'app-advertisement-details',
  templateUrl: './advertisement-details.component.html',
  styleUrls: ['./../advertisement.component.css']
})
export class AdvertisementDetailsComponent implements OnInit {
  dataSource: Advertisement;
  id: number;

  constructor(private advertisementService: AdvertisementService, private router: Router, private activatedRoute: ActivatedRoute) {
  }

  ngOnInit() {
    this.activatedRoute.params.subscribe(
      params => {
      this.id = params.id;
    });
    this.advertisementService.getAdvertisement(this.id).subscribe(
      data => {
        this.dataSource = data;
    });
  }

}
