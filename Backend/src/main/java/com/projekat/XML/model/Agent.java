package com.projekat.XML.model;

import com.projekat.XML.enums.UserType;

import javax.persistence.*;

@Entity
public class Agent extends User {

    @Column
    private int number_ads;

    public Agent(String name, String surname, LoginInfo loginInfo, String jmbg, String phoneNumber, UserType ut, int number_ads) {
        super(name, surname, loginInfo, jmbg, phoneNumber, ut);
        this.number_ads = number_ads;
    }
}
