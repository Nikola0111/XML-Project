package com.projekat.XML.model;

import com.projekat.XML.enums.CarClass;
import com.projekat.XML.enums.FuelType;
import com.projekat.XML.enums.TransmissionType;
import com.sun.xml.messaging.saaj.soap.ImageDataContentHandler;

import javax.persistence.*;

import java.util.ArrayList;


@Entity
public class Advertisement  {


    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String model;

    private String brand;

    private String fuelType;

    private String transType;

    private String carClass;

    private int travelled;
  
    private int carSeats;

    private double price;


    @ManyToOne
    @JoinColumn
    private User postedBy;



   public Advertisement(){

   }


    public Advertisement(String name, String model, String brand, String fuelType, String transType, String carClass, int travelled, int carSeats, double price, User postedBy) {
        this.name = name;
        this.model = model;
        this.brand = brand;
        this.fuelType = fuelType;
        this.transType = transType;
        this.carClass = carClass;
        this.travelled = travelled;
        this.carSeats = carSeats;
        this.price = price;
        this.postedBy = postedBy;

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
        return fuelType;
    }

    public void setFuelType(String fuelType) {
        this.fuelType = fuelType;
    }

    public String getTransType() {
        return transType;
    }

    public void setTransType(String transType) {
        this.transType = transType;
    }

    public String getCarClass() {
        return carClass;
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

    public String getName() {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    
    public User getPostedBy() {
        return this.postedBy;
    }

    public void setPostedBy(User postedBy) {
        this.postedBy = postedBy;
    }

}
