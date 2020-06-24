package com.projekat.XML.dtos;

import com.projekat.XML.dtos.*;

public class RegistrationDTO {
    private String name;
    private String surname;
    private String jmbg;
    private String phoneNumber;
    private LoginInfoDTO loginInfo;

    public RegistrationDTO(String name, String surname, String jmbg, String phoneNumber, LoginInfoDTO loginInfo){
        this.name = name;
        this.surname = surname;
        this.jmbg = jmbg;
        this.phoneNumber = phoneNumber;
        this.loginInfo = loginInfo;
    }

    public RegistrationDTO() {

    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return this.surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getJmbg() {
        return this.jmbg;
    }

    public void setJmbg(String jmbg) {
        this.jmbg = jmbg;
    }

    public String getPhoneNumber() {
        return this.phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public LoginInfoDTO getLoginInfo() {
        return this.loginInfo;
    }

    public void setLoginInfo(LoginInfoDTO loginInfo) {
        this.loginInfo = loginInfo;
    }

    

}