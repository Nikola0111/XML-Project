package com.projekat.XML.dtos;

import com.projekat.XML.enums.UserType;
import com.projekat.XML.model.EntityUser;
import com.projekat.XML.model.LoginInfo;

public class UserMessageDTO {

    private Long id;
    private String name;
    private String surname;
    private LoginInfo loginInfo;
    private String jmbg;
    private String phone;

    public UserMessageDTO() {
    }

    
    public UserMessageDTO(Long id,String name, String surname, LoginInfo loginInfo, String jmbg,
            String phone) {
        this.id=id;
        this.name = name;
        this.surname = surname;
        this.loginInfo=loginInfo;
        this.jmbg = jmbg;
        this.phone = phone;
    }
    

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }


    public String getJmbg() {
        return jmbg;
    }

    public void setJmbg(String jmbg) {
        this.jmbg = jmbg;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public LoginInfo getLoginInfo() {
        return loginInfo;
    }

    public void setLoginInfo(LoginInfo loginInfo) {
        this.loginInfo = loginInfo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    

    

}
