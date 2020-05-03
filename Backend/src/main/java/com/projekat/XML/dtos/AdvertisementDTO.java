package com.projekat.XML.dtos;

import com.projekat.XML.enums.CarClass;
import com.projekat.XML.enums.FuelType;
import com.projekat.XML.enums.TransmissionType;
import com.projekat.XML.model.Advertisement;

public class AdvertisementDTO {

    private Long id;

    

    private String name;

    

    private String model;

    private String brand;

    private String fuelType;

    private String transmissionType;

    private String carClass;

    private int travelled;
  
    private int carSeats;

    private double price;

    private double discount;

    private double priceWithDiscount;


    public AdvertisementDTO(String name,String model, String brand, String fuelType, String transmissionType, String carClass, int travelled, int carSeats, double price, double discount) {
        this.name=name;
        this.model = model;
        this.brand = brand;
        this.fuelType = fuelType;
        this.transmissionType = transmissionType;
        this.carClass = carClass;
        this.travelled = travelled;
        this.carSeats = carSeats;
        this.price=price;
        this.discount = discount;
        this.priceWithDiscount = price - (price * discount / 100);
    }

    public AdvertisementDTO(Advertisement ad)
    {
        this.id = ad.getId();
        this.name=ad.getName();
        this.model = ad.getModel();
        this.brand = ad.getBrand();
        this.fuelType = ad.getFuelType();
        this.transmissionType = ad.getTransType();
        this.carClass = ad.getCarClass();
        this.travelled = ad.getTravelled();
        this.carSeats = ad.getCarSeats();
        this.price=ad.getPrice();
        this.discount = ad.getDiscount();
        this.priceWithDiscount = ad.getPriceWithDiscount();
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getFuelType() {
        return this.fuelType;
    }

    public void setFuelType(String fuelType) {
        this.fuelType = fuelType;
    }

    public String getTransType() {
        return this.transmissionType;
    }

    public void setTransType(String transType) {
        this.transmissionType = transType;
    }

    public String getCarClass() {
        return this.carClass;
    }

    public void setCarClass(String carClass) {
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

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public double getPriceWithDiscount() {
        return priceWithDiscount;
    }

    public void setPriceWithDiscount(double priceWithDiscount) {
        this.priceWithDiscount = priceWithDiscount;
    }
}
