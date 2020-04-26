package com.projekat.XML.model.requests;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.projekat.XML.enums.RequestStates;
import com.projekat.XML.model.Advertisement;

@Entity
public class BookingRequest{
    
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    //onaj ko je objavio
    private Long userForId;

    //onaj ko podnosi
    private Long userToId;

    private RequestStates stateOfRequest;

    @ManyToOne
    @JoinColumn
    private Advertisement advertisement;

    private Long groupId;

   
    private boolean together;

  
    

    public BookingRequest(Long userForId,Long userToId,Long groupId, RequestStates stateOfRequest, Advertisement advertisement, boolean together) {
        this.userForId = userForId;
        this.userToId=userToId;
        this.groupId=groupId;
        this.stateOfRequest = stateOfRequest;
        this.advertisement=advertisement;
        this.together=together;
    }

    public BookingRequest() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserForId() {
        return userForId;
    }

    public void setUserForId(Long userId) {
        this.userForId = userId;
    }

    public RequestStates getStateOfRequest() {
        return stateOfRequest;
    }

    public void setStateOfRequest(RequestStates stateOfRequest) {
        this.stateOfRequest = stateOfRequest;
    }

    public Advertisement getAdvertisement() {
        return this.advertisement;
    }

    public void setAdvertisement(Advertisement advertisement) {
        this.advertisement = advertisement;
    }

    public boolean isTogether() {
        return this.together;
    }

    public void setTogether(boolean together) {
        this.together = together;
    }

    
    public Long getUserToId() {
        return this.userToId;
    }

    public void setUserToId(Long userToId) {
        this.userToId = userToId;
    }

    public Long getGroupId() {
        return this.groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }

    

}
