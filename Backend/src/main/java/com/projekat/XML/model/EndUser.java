package com.projekat.XML.model;

import com.projekat.XML.enums.UserType;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "endentity")
public class EndUser extends User {

    private int numberOfRequestsCanceled;

    @Column(name = "activated")
    private boolean activity;

    @Column(name = "admin_approved")
    private boolean adminApproved;

    @Column(name = "blocked")
    private boolean blocked;

    public  EndUser() {

    }

    public EndUser(String name, String surname, LoginInfo loginInfo, String jmbg, String phoneNumber, UserType ut,
                   int number_of_requests, boolean activity, boolean adminApproved, boolean blocked) {
        super(name, surname, loginInfo, jmbg, phoneNumber, ut);
        this.numberOfRequestsCanceled = number_of_requests;
        this.activity = activity;
        this.adminApproved = adminApproved;
        this.blocked = blocked;
    }

    @Override
    public String toString() {
        return "EndUser{}";
    }

    public int getNumber_of_requests() {
        return numberOfRequestsCanceled;
    }

    public void setNumber_of_requests(int number_of_requests) {
        this.numberOfRequestsCanceled = number_of_requests;
    }

    public boolean isAccount_activated() {
        return activity;
    }

    public void setAccount_activated(boolean account_activated) {
        this.activity = account_activated;
    }

    public boolean isAdminApproved() {
        return adminApproved;
    }

    public void setAdminApproved(boolean adminApproved) {
        this.adminApproved = adminApproved;
    }

    public boolean isBlocked() {
        return blocked;
    }

    public void setBlocked(boolean blocked) {
        this.blocked = blocked;
    }
}
