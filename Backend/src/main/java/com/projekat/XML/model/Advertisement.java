package com.projekat.XML.model;

import com.projekat.XML.enums.CarClass;
import com.projekat.XML.enums.FuelType;
import com.projekat.XML.enums.TransmissionType;

public class Advertisement {

    private String name;

    private String model;

    private String brand;

    private FuelType fuelType;

    private TransmissionType transType;

    private CarClass carClass;

    private int travelled;
  
    private int carSeats;

    private double price;

   

    public Car(String name,String model, String brand, FuelType fuelType, TransmissionType transType, CarClass carClass, int travelled, int carSeats, double price) {
        this.name=name;
        this.model = model;
        this.brand = brand;
        this.fuelType = fuelType;
        this.transType = transType;
        this.carClass = carClass;
        this.travelled = travelled;
        this.carSeats = carSeats;
        this.price=price;
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


    public double getPrice() {
        return this.price;
    }

    public void setPrice(double price) {
        this.price = price;
    }


}
