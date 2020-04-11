package com.projekat.rent.model;

import com.projekat.rent.model.User;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
public class Administrator extends User {

    public Administrator() {
        super();
    }


}
