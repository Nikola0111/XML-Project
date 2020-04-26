package com.projekat.XML.dtos;

import com.projekat.XML.enums.CarClass;
import com.projekat.XML.enums.FuelType;
import com.projekat.XML.enums.TransmissionType;

import java.time.LocalDateTime;

public class FilterAdsDTO {

    private FuelType fuelType;

    private TransmissionType transmissionType;

    private CarClass carClass;

    private int travelledFrom;

    private int travelledTo;

    private double priceFrom;

    private double priceTo;

    private int carSeats;

    private LocalDateTime timeFrom;

    private LocalDateTime timeTo;

    public FilterAdsDTO() {

    }

    public FilterAdsDTO(FuelType fuelType, TransmissionType transmissionType, CarClass carClass, int travelledFrom, int travelledTo, double priceFrom, double priceTo, int carSeats, LocalDateTime timeFrom, LocalDateTime timeTo) {
        this.fuelType = fuelType;
        this.transmissionType = transmissionType;
        this.carClass = carClass;
        this.travelledFrom = travelledFrom;
        this.travelledTo = travelledTo;
        this.priceFrom = priceFrom;
        this.priceTo = priceTo;
        this.carSeats = carSeats;
        this.timeFrom = timeFrom;
        this.timeTo = timeTo;
    }

    public FuelType getFuelType() {
        return fuelType;
    }

    public void setFuelType(FuelType fuelType) {
        this.fuelType = fuelType;
    }

    public TransmissionType getTransmissionType() {
        return transmissionType;
    }

    public void setTransmissionType(TransmissionType transmissionType) {
        this.transmissionType = transmissionType;
    }

    public CarClass getCarClass() {
        return carClass;
    }

    public void setCarClass(CarClass carClass) {
        this.carClass = carClass;
    }

    public int getTravelledFrom() {
        return travelledFrom;
    }

    public void setTravelledFrom(int travelledFrom) {
        this.travelledFrom = travelledFrom;
    }

    public int getTravelledTo() {
        return travelledTo;
    }

    public void setTravelledTo(int travelledTo) {
        this.travelledTo = travelledTo;
    }

    public double getPriceFrom() {
        return priceFrom;
    }

    public void setPriceFrom(double priceFrom) {
        this.priceFrom = priceFrom;
    }

    public double getPriceTo() {
        return priceTo;
    }

    public void setPriceTo(double priceTo) {
        this.priceTo = priceTo;
    }

    public int getCarSeats() {
        return carSeats;
    }

    public void setCarSeats(int carSeats) {
        this.carSeats = carSeats;
    }

    public LocalDateTime getTimeFrom() {
        return timeFrom;
    }

    public void setTimeFrom(LocalDateTime timeFrom) {
        this.timeFrom = timeFrom;
    }

    public LocalDateTime getTimeTo() {
        return timeTo;
    }

    public void setTimeTo(LocalDateTime timeTo) {
        this.timeTo = timeTo;
    }

}
