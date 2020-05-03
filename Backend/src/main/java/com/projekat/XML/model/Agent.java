package com.projekat.XML.model;

import com.projekat.XML.enums.UserType;

import javax.persistence.*;

@Entity
public class Agent extends User {

    @Column
    private int number_ads;

    @Column(name = "first_login")
    private boolean first_login;

    @Column
    private String adress;

    @Column(name ="business_registration_number")
    private String bsregnum;

    public Agent(String name, String surname, LoginInfo loginInfo, String jmbg, String phoneNumber, UserType ut, int number_ads, boolean first_login, String adress, String bsregnum) {
        super(name, surname, loginInfo, jmbg, phoneNumber, ut);
        this.number_ads = number_ads;
        this.first_login = first_login;
        this.adress = adress;
        this.bsregnum = bsregnum;
    }

    public Agent(){

    }

    public int getNumber_ads() {
        return number_ads;
    }

    public void setNumber_ads(int number_ads) {
        this.number_ads = number_ads;
    }

    public boolean isFirst_login() {
        return first_login;
    }

    public void setFirst_login(boolean first_login) {
        this.first_login = first_login;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public String getBsregnum() {
        return bsregnum;
    }

    public void setBsregnum(String bsregnum) {
        this.bsregnum = bsregnum;
    }
}
