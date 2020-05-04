import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {CommentDto} from '../../dtos/comment-dto';

const httpOptions = {headers: new HttpHeaders({'Content-Type' : 'application/json'})};

@Injectable({
  providedIn: 'root'
})
export class CommentService {

  constructor(private http: HttpClient) { }

  public saveCommentAndGrade(commentDTO: CommentDto) {
    const body = JSON.stringify(commentDTO);
    return this.http.post<number>('/server/advertisement/saveCommentAndGrade', body, httpOptions);
  }
}
