package com.projekat.rent.model;

import com.projekat.rent.enums.CarClass;
import com.projekat.rent.enums.FuelType;
import com.projekat.rent.enums.TransmissionType;

public class Car {

    private String model;

    private String brand;

    private FuelType fuelType;

    private TransmissionType transType;

    private CarClass carClass;

    private int price;

    private int travelled;
  
    private int carSeats;

    public Car(String model, String brand, FuelType fuelType, TransmissionType transType, CarClass carClass, int price, int travelled, int carSeats) {
        this.model = model;
        this.brand = brand;
        this.fuelType = fuelType;
        this.transType = transType;
        this.carClass = carClass;
        this.price = price;
        this.travelled = travelled;
        this.carSeats = carSeats;
    }

    public String getBrand() {
		return this.brand;
	}

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return this.model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public FuelType getFuelType() {
        return this.fuelType;
    }

    public void setFuelType(FuelType fuelType) {
        this.fuelType = fuelType;
    }

    public TransmissionType getTransType() {
        return this.transType;
    }

    public void setTransType(TransmissionType transType) {
        this.transType = transType;
    }

    public CarClass getCarClass() {
        return this.carClass;
    }

    public void setCarClass(CarClass carClass) {
        this.carClass = carClass;
    }

    public int getPrice() {
        return this.price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getTravelled() {
        return this.travelled;
    }

    public void setTravelled(int travelled) {
        this.travelled = travelled;
    }

    public int getCarSeats() {
        return this.carSeats;
    }

    public void setCarSeats(int carSeats) {
        this.carSeats = carSeats;
    }





}
