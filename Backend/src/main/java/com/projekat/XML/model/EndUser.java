package com.projekat.XML.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "endentity")
public class EndUser extends User {

    @Column(name = "broj_zahteva", nullable = false)
    private int broj_zahteva;

    public  EndUser() {

    }

    public EndUser(Long id, String name, String surname, LoginInfo loginInfo, String jmbg, String phoneNumber, int broj_zahteva) {
        super(id, name, surname, loginInfo, jmbg, phoneNumber);
        this.broj_zahteva = broj_zahteva;
    }

    @Override
    public String toString() {
        return "EndUser{}";
    }

    public int getBroj_zahteva() {
        return broj_zahteva;
    }

    public void setBroj_zahteva(int broj_zahteva) {
        this.broj_zahteva = broj_zahteva;
    }
}
