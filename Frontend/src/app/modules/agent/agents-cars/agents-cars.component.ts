import { Component, OnInit } from '@angular/core';
import {AgentService} from '../../../services/AgentService/agent.service';
import {SessionService} from '../../../services/SessionService/session.service';
import {CarDTO} from '../../../dtos/car-dto';
import {Router} from '@angular/router';
import {AdvertisementreportDTO} from '../../../dtos/advertisementreportDTO';
import {AdvertisementService} from '../../../services/advertisement.service/advertisement.service';

@Component({
  selector: 'app-agents-cars',
  templateUrl: './agents-cars.component.html',
  styleUrls: ['./agents-cars.component.css']
})
export class AgentsCarsComponent implements OnInit {
  cars: AdvertisementreportDTO[];
  constructor(private advertisementService: AdvertisementService, private sessionService: SessionService, private router: Router) { }

  ngOnInit() {
    this.advertisementService.getAllByPostedByCars(this.sessionService.ulogovaniKorisnik.id).subscribe(data => {
      this.cars = data;
      console.log(this.cars);
    });
  }

  enterReport(car: AdvertisementreportDTO) {
    this.sessionService.bookingID = car.bookingID;
    this.router.navigate(['/report', car.id]);
  }
}
