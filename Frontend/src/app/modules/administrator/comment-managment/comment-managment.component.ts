import { Component, OnInit } from '@angular/core';
import {CommentPreviewDTO} from '../../../dtos/comment-preview-dto';
import {CommentService} from '../../../services/CommentService/comment.service';

@Component({
  selector: 'app-comment-managment',
  templateUrl: './comment-managment.component.html',
  styleUrls: ['./comment-managment.component.css']
})
export class CommentManagmentComponent implements OnInit {

  comments: CommentPreviewDTO[];
  constructor(private commentService: CommentService) {
    this.commentService.getUnapprovedComments().subscribe(data =>{
      this.comments = data;
    });
  }

  ngOnInit() {
  }

  approve(comment: CommentPreviewDTO) {
    console.log(comment.id);
    this.commentService.approve(comment.id).subscribe(data => {
      this.commentService.getUnapprovedComments().subscribe(newDataSource => {
        this.comments = newDataSource;
      });
    });
  }

  delete(comment: CommentPreviewDTO) {
    this.commentService.delete(comment.id).subscribe(data => {
      this.commentService.getUnapprovedComments().subscribe(newDataSource => {
        this.comments = newDataSource;
      });
    });
  }

}
