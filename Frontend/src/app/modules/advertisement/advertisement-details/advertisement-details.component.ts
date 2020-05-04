import { Component, OnInit } from '@angular/core';
import {Advertisement} from '../../../model/advertisement';
import {AdvertisementService} from '../../../services/advertisement.service/advertisement.service';
import {ActivatedRoute, Router} from '@angular/router';
import {Grade} from '../../../model/grade';
import {CommentDto} from '../../../dtos/comment-dto';
import {CommentService} from '../../../services/CommentService/comment.service';
import {SessionService} from '../../../services/SessionService/session.service';
import {AdvertisementDTO} from '../../../dtos/advertisement-dto';
import {CommentPreviewDTO} from '../../../dtos/comment-preview-dto';

@Component({
  selector: 'app-advertisement-details',
  templateUrl: './advertisement-details.component.html',
  styleUrls: ['./../advertisement.component.css']
})
export class AdvertisementDetailsComponent implements OnInit {
  stars: number[] = [1, 2, 3, 4, 5];
  dataSource: AdvertisementDTO = new AdvertisementDTO();
  id: number;
  selectedValue: number;
  comment: string;
  rented: boolean;
  rentedCars: number[];

  constructor(private messageService: CommentService, private advertisementService: AdvertisementService, private router: Router,
              private activatedRoute: ActivatedRoute, private sessionService: SessionService) {
    this.advertisementService.getRentedCars(this.sessionService.ulogovaniKorisnik.id).subscribe(data => {
      this.rentedCars = data;
      console.log(this.rentedCars);
    });
  }

  ngOnInit() {
    this.activatedRoute.params.subscribe(
      params => {
      this.id = params.id;
    });
    this.advertisementService.getAdvertisementPreview(this.id).subscribe(
      data => {
        this.dataSource = data;
        console.log(this.dataSource);
        const smth = this.rentedCars.filter(item => {
          return item === this.dataSource.id;
        });
        if (smth.length !== 0) {
          this.rented = true;
        } else {
          this.rented = false;
        }
    });
  }

  countStar(star) {
    this.selectedValue = star;
    console.log('Value of star', star);
  }

  onSubmit() {
    const newComment = new CommentDto(this.comment, this.selectedValue, this.dataSource.id, this.sessionService.ulogovaniKorisnik.id);

    this.messageService.saveCommentAndGrade(newComment).subscribe(data => {
      this.dataSource.comments.push(new CommentPreviewDTO(this.comment,
        this.sessionService.ulogovaniKorisnik.loginInfo.email, this.selectedValue))
      this.comment = '';
      this.selectedValue = -1;
    });
  }
}
