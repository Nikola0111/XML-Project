import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Injectable} from '@angular/core';
import {Advertisement} from '../../model/advertisement';
import {ImageModel} from '../../model/imageModel';

const httpOptions = {
  headers: new HttpHeaders({'Content-Type' : 'application/json'})
};

@Injectable()
export class ImageModelService {

  private requestUrl: string;
  retrievedImages: any;
  base64Data: any;
  retrieveResponses: any;
  proba: string ;
  imageModel: ImageModel;

  constructor(private http: HttpClient) {}

  /*public save(imageModel: ImageModel) {
    const body = JSON.stringify(imageModel);
    return this.http.post<ImageModel>('/server/imageModel/save', body, httpOptions);
  }*/

  public save(file: File) {
    this.requestUrl = '/server/imageModel/save';
    const fd = new FormData();
    fd.append('imageFile', file, file.name);
    return this.http.post<File>(this.requestUrl, file, httpOptions);
  }

  public getImage(file: ImageModel) {
    this.requestUrl = '/server/imageModel/getImage/' + file.name;
    this.http.get(this.requestUrl, httpOptions).subscribe(
      res => {
        this.retrieveResponses.add(res);
        this.base64Data.add(this.retrieveResponses.picByte);
        this.retrievedImages.add('data:image/jpeg;base64,' + this.base64Data);
      }
    );
  }

}
