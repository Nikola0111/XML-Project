package com.projekat.XML.dtos;

import com.projekat.XML.enums.RequestStates;
import com.projekat.XML.model.Advertisement;

import javax.xml.bind.annotation.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "advertisementSoapDTO",
        namespace="com.projekat.XML.dtos",
        propOrder = {"userForId", "userToId", "stateOfRequest", "advertisementIdInMonolith", "groupId", "together","timeFrom", "timeTo"})
public class BookingSoapDTO implements Serializable {

    @XmlElement(name="userForId", required = true)
    private Long userForId;

    @XmlElement(name="userToId", required = true)
    private Long userToId;

    @XmlElement(name="stateOfRequest", required = true)
    private RequestStates stateOfRequest;

    @XmlElement(name="advertisementIdInMonolith", required = true)
    private Long advertisementIdInMonolith;

    @XmlElement(name="groupId", required = true)
    private Long groupId;

    @XmlElement(name="together", required = true)
    private boolean together;

    @XmlElement(name="timeFrom", required = true)
    private LocalDateTime timeFrom;

    @XmlElement(name="timeTo", required = true)
    private LocalDateTime timeTo;

    public BookingSoapDTO(Long userForId, Long userToId, RequestStates stateOfRequest, Long advertisementIdInMonolith, Long groupId, boolean together, LocalDateTime timeFrom, LocalDateTime timeTo) {
        this.userForId = userForId;
        this.userToId = userToId;
        this.stateOfRequest = stateOfRequest;
        this.advertisementIdInMonolith = advertisementIdInMonolith;
        this.groupId = groupId;
        this.together = together;
        this.timeFrom = timeFrom;
        this.timeTo = timeTo;
    }

    public BookingSoapDTO(){

    }


    public Long getUserForId() {
        return userForId;
    }

    public void setUserForId(Long userForId) {
        this.userForId = userForId;
    }

    public Long getUserToId() {
        return userToId;
    }

    public void setUserToId(Long userToId) {
        this.userToId = userToId;
    }

    public RequestStates getStateOfRequest() {
        return stateOfRequest;
    }

    public void setStateOfRequest(RequestStates stateOfRequest) {
        this.stateOfRequest = stateOfRequest;
    }

    public Long getAdvertisementIdInMonolith() {
        return advertisementIdInMonolith;
    }

    public void setAdvertisementIdInMonolith(Long advertisementIdInMonolith) {
        this.advertisementIdInMonolith = advertisementIdInMonolith;
    }

    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }

    public boolean isTogether() {
        return together;
    }

    public void setTogether(boolean together) {
        this.together = together;
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
