package com.projekat.XML.model;

import com.projekat.XML.enums.UserType;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "endentity")
public class EndUser extends User {

    @Column(name = "number_of_requests", nullable = false)
    private int number_of_requests;

    @Column(name = "activated")
    private boolean activity;

    public  EndUser() {

    }

    public EndUser(String name, String surname, LoginInfo loginInfo, String jmbg, String phoneNumber, int broj_zahteva, UserType ut, boolean account_activated) {
        super( name, surname, loginInfo, jmbg, phoneNumber, ut);
        this.number_of_requests = broj_zahteva;
        this.activity = account_activated;
    }

    @Override
    public String toString() {
        return "EndUser{}";
    }

    public int getNumber_of_requests() {
        return number_of_requests;
    }

    public void setNumber_of_requests(int number_of_requests) {
        this.number_of_requests = number_of_requests;
    }

    public boolean isAccount_activated() {
        return activity;
    }

    public void setAccount_activated(boolean account_activated) {
        this.activity = account_activated;
    }
}
