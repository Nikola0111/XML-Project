package com.projekat.XML.model.requests;

import java.util.ArrayList;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.projekat.XML.enums.RequestStates;

@Entity
public class BookingRequest{
    
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    private Long userId;

    private RequestStates stateOfRequest;

    private ArrayList<Long> advertisementIdList;

    

    public BookingRequest(Long userId, RequestStates stateOfRequest, ArrayList<Long> advertisementIdList) {
        this.userId = userId;
        this.stateOfRequest = stateOfRequest;
        this.advertisementIdList = advertisementIdList;
    }

    public BookingRequest() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public RequestStates getStateOfRequest() {
        return stateOfRequest;
    }

    public void setStateOfRequest(RequestStates stateOfRequest) {
        this.stateOfRequest = stateOfRequest;
    }

    public ArrayList<Long> getAdvertisementIdList() {
        return advertisementIdList;
    }

    public void setAdvertisementIdList(ArrayList<Long> advertisementIdList) {
        this.advertisementIdList = advertisementIdList;
    }
    

}
