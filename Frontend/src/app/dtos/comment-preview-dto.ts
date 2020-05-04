export class CommentPreviewDTO {
  constructor(comment: string, userMail: string, grade: number) {
    this.comment = comment;
    this.userMail = userMail;
    this.grade = grade
  }
  comment: string;
  userMail: string;
  grade: number;


}
