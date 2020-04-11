package com.projekat.rent.model;

import com.projekat.rent.model.User;

public class Administrator extends User {

    private String surname;

    private long jmbg;

    public Administrator() {
        super();
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public long getJmbg() {
        return jmbg;
    }

    public void setJmbg(long jmbg) {
        this.jmbg = jmbg;
    }
}
