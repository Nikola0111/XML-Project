package com.projekat.XML.dtos;

import com.projekat.XML.model.TransmissionType;
import com.soap.SaveAdvertisement.Advertisement;

import java.io.Serializable;
import java.util.ArrayList;

import javax.xml.bind.annotation.*;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "advertisementSoapDTO",
        namespace="com.projekat.XML.dtos",
        propOrder = {"id", "name", "model", "brand", "fuelType", "transmissionType","carClass", "travelled",
                "carSeats", "price", "discount", "priceWithDiscount", "pictures", "grade", "postedByID"})
public class AdvertisementSoapDTO implements Serializable {

    @XmlElement(name="id", required = true)
    private Long id;

    @XmlElement(name="idInMonolith", required = true)
    private Long idInMonolith;

    @XmlElement(name="name", required = true)
    private String name;

    @XmlElement(name="model", required = false)
    private Advertisement.Model model;

    @XmlElement(name="brand", required = false)
    private Advertisement.Brand brand;

    @XmlElement(name="fuelType", required = false)
    private Advertisement.FuelType fuelType;

    @XmlElement(name="transmissionType", required = false)
    private Advertisement.TransType transmissionType;

    @XmlElement(name="carClass", required = false)
    private Advertisement.CarClass carClass;

    @XmlElement(name="travelled", required = false)
    private int travelled;

    @XmlElement(name="carSeats", required = false)
    private int carSeats;

    @XmlElement(name="price", required = false)
    private double price;

    @XmlElement(name="discount", required = false)
    private double discount;

    @XmlElement(name="priceWithDiscount", required = false)
    private double priceWithDiscount;

    @XmlElementWrapper(name="pictures", required=false)
    @XmlElement(name="picture", required = false)
    private ArrayList<String> pictures;

    @XmlElement(name="grade", required = false)
    private double grade;

    @XmlElement(name="postedByID", required = false)
    private long postedByID;

    public AdvertisementSoapDTO() {
    }

    public AdvertisementSoapDTO(String name, Advertisement.Model model, Advertisement.Brand brand, Advertisement.FuelType fuelType, Advertisement.TransType transmissionType, Advertisement.CarClass carClass, int travelled, int carSeats, double price, double discount, ArrayList<String> pictures, long postedByID) {
        this.idInMonolith = this.id;
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
        this.pictures=pictures;
        this.postedByID = postedByID;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Advertisement.Brand getBrand() {
        return this.brand;
    }

    public void setBrand(Advertisement.Brand brand) {
        this.brand = brand;
    }

    public Advertisement.Model getModel() {
        return this.model;
    }

    public void setModel(Advertisement.Model model) {
        this.model = model;
    }

    public Advertisement.FuelType getFuelType() {
        return this.fuelType;
    }

    public void setFuelType(Advertisement.FuelType fuelType) {
        this.fuelType = fuelType;
    }

    public Advertisement.TransType getTransType() {
        return this.transmissionType;
    }

    public void setTransType(Advertisement.TransType transType) {
        this.transmissionType = transType;
    }

    public Advertisement.CarClass getCarClass() {
        return this.carClass;
    }

    public void setCarClass(Advertisement.CarClass carClass) {
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
    public ArrayList<String> getPictures() {
        return this.pictures;
    }

    public void setPictures(ArrayList<String> pictures) {
        this.pictures = pictures;
    }

    public double getGrade() {
        return grade;
    }

    public void setGrade(double grade) {
        this.grade = grade;
    }

    public long getPostedByID() {
        return postedByID;
    }

    public void setPostedByID(long postedByID) {
        this.postedByID = postedByID;
    }
}
