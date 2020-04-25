import { Component, OnInit } from '@angular/core';
import { User } from 'src/app/model/user';
import {FormBuilder, FormGroup} from '@angular/forms';
import { PorukeService } from './poruke.component.service';
import {MessageDTO} from 'src/app/model/messageDTO';


@Component({
  selector: 'app-poruke',
  templateUrl: './poruke.component.html',
  styleUrls: ['./poruke.component.css']
})
export class PorukeComponent implements OnInit {
  users: User[];
  message:MessageDTO = new MessageDTO();
  email: string;
  text: string;
  forma: FormGroup;

  constructor(private formBuilder: FormBuilder, private porukeService: PorukeService) {

   }

  ngOnInit() {

    this.porukeService.getAllUsers().subscribe(
      
      data=>{
        this.users=data;
      }

    )

    this.forma = this.formBuilder.group({
      email: [''],
      text: ['']
     
    });

  }

  sent(){
    
    console.log(this.message.email);
    console.log(this.message.text);
   
    this.porukeService.sendMessage(this.message).subscribe();
  }

}
