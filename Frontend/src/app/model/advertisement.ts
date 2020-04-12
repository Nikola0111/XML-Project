import {FuelType} from '../enums/fuelType';
import {TransmissionType} from '../enums/transmissionType';
import {CarClass} from '../enums/carClass';

export class Advertisement {
    private _id: number;
    private _name: string;
    private _model: string;
    private _brand: string;
    private _fuelType: FuelType;
    private _transmissionType: TransmissionType;
    private _carClass: CarClass;
    private _travelled: number;
    private _price: number;
    private _carSeats: number;


  constructor() {
    
  }
  
  get id(): number {
    return this._id;
  }

  set id(value: number) {
    this._id = value;
  }

  get name(): string {
    return this._name;
  }

  set name(value: string) {
    this._name = value;
  }

  get model(): string {
    return this._model;
  }

  set model(value: string) {
    this._model = value;
  }

  get brand(): string {
    return this._brand;
  }

  set brand(value: string) {
    this._brand = value;
  }

  get fuelType(): FuelType {
    return this._fuelType;
  }

  set fuelType(value: FuelType) {
    this._fuelType = value;
  }

  get transmissionType(): TransmissionType {
    return this._transmissionType;
  }

  set transmissionType(value: TransmissionType) {
    this._transmissionType = value;
  }

  get carClass(): CarClass {
    return this._carClass;
  }

  set carClass(value: CarClass) {
    this._carClass = value;
  }

  get travelled(): number {
    return this._travelled;
  }

  set travelled(value: number) {
    this._travelled = value;
  }

  get price(): number {
    return this._price;
  }

  set price(value: number) {
    this._price = value;
  }

  get carSeats(): number {
    return this._carSeats;
  }

  set carSeats(value: number) {
    this._carSeats = value;
  }
}
