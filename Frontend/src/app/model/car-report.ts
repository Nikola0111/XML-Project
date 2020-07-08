export class CarReport {
  public carId: number;
  public travelled: number;
  public comment: string;
  public bookingID: number;


  constructor(carId: number, traveled: number, comment: string) {
    this.carId = carId;
    this.travelled = traveled;
    this.comment = comment;
  }
}
