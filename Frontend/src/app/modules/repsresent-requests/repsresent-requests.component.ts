import { Component, OnInit } from '@angular/core';
import { RepresentRequestsService } from './represent-requests.service';
import { BookingRequest } from 'src/app/model/requests/bookingRequest';

@Component({
  selector: 'app-repsresent-requests',
  templateUrl: './repsresent-requests.component.html',
  styleUrls: ['./repsresent-requests.component.css']
})
export class RepresentRequestsComponent implements OnInit {
  groups: number[];
  requests: BookingRequest[];
  napravioJedan: number;


  constructor(private representRequestsService:RepresentRequestsService) { }
  
  ngOnInit() {
    this.representRequestsService.getGroupsForCart().subscribe(
      
      data=>{
        this.groups=data;
      }

    )

    this.representRequestsService.getAllRequests().subscribe(
      data => {
        this.requests = data;
        this.napravioJedan=0;
        
      }
      
    );


  }

  acceptRequest(group: number){

    this.representRequestsService.acceptRequest(group).subscribe();

  }

  
}
