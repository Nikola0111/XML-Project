import { Component, OnInit } from '@angular/core';
import { HistoryService } from './history.component.service';
import { BookingRequest } from 'src/app/model/requests/bookingRequest';
import {RequestStates} from 'src/app/enums/requestStates';


@Component({
  selector: 'app-history',
  templateUrl: './history.component.html',
  styleUrls: ['./history.component.css']
})
export class HistoryComponent implements OnInit {

  groups: number[];
  requests: BookingRequest[];
  requestStatus: RequestStates;
  checker: number;
  status:string;
  

  constructor(private historyService : HistoryService) { 

    this.requestStatus=RequestStates.PENDING;
  }

  ngOnInit() {
    
    console.log(this.requestStatus);
    this.historyService.getSpecificGroupsForCart( this.requestStatus).subscribe(
      
      data=>{
        this.groups=data;
      }

    )

    this.historyService.getAllSpecificRequests( this.requestStatus).subscribe(
      data => {
        this.requests = data;
       
        
      }
      
    );
    this.checker=0;
  }

  public onChange(event): void {

    if(event.target.value==="PENDING"){
      this.requestStatus=RequestStates.PENDING;
      this.historyService.getSpecificGroupsForCart(this.requestStatus).subscribe(
       
        data=>{
          this.groups=data;

          this.historyService.getAllSpecificRequests(this.requestStatus).subscribe(

            data=>{
              this.requests=data;
    
            
            }
  
          )
          this.checker=0;
        },
        
      
      )
    }
    else if(event.target.value==="RESERVED"){
      this.requestStatus=RequestStates.RESERVED;
      this.historyService.getSpecificGroupsForCart(this.requestStatus).subscribe(
       
        data=>{
          this.groups=data;

          this.historyService.getAllSpecificRequests(this.requestStatus).subscribe(

            data=>{
              this.requests=data;
    
            
            }
  
          )
          this.checker=1;
        },
        
      
      )
      this.historyService.getAllSpecificRequests(this.requestStatus).subscribe(

        data=>{
          this.requests=data;

        
        }

      )
    }
    else if(event.target.value==="CANCELED"){
      this.requestStatus=RequestStates.CANCELED;
      this.historyService.getSpecificGroupsForCart(this.requestStatus).subscribe(
       
        data=>{
          this.groups=data;

          this.historyService.getAllSpecificRequests(this.requestStatus).subscribe(

            data=>{
              this.requests=data;
    
            
            }
  
          )
          this.checker=1;
        },
      
      )
    }
    else if(event.target.value==="PAID"){
      this.historyService.getSpecificGroupsForCart(this.requestStatus).subscribe(
       
        data=>{
          this.groups=data;

          this.historyService.getAllSpecificRequests(this.requestStatus).subscribe(

            data=>{
              this.requests=data;
    
            
            }
  
          )
          this.checker=1;
        },
      
      )
    }

}


public cancelRequest(group: number){

  this.historyService.cancelRequest(group).subscribe();


}


}
