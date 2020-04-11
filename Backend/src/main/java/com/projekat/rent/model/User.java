package com.projekat.rent.model;

import javax.persistence.*;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "surname", nullable = false)
    private String surname;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "login_info", referencedColumnName = "username")
    private LoginInfo loginInfo;

    @Column(name = "jmbg", unique = true, nullable = false)
    private String jmbg;

    @Column(name = "phone_number")
    private String phoneNumber;

    public User(Long id, String name, String surname, LoginInfo loginInfo, String jmbg, String phoneNumber) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.loginInfo = loginInfo;
        this.jmbg = jmbg;
        this.phoneNumber = phoneNumber;
    }

    public User() {

    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public LoginInfo getLoginInfo() {
        return loginInfo;
    }

    public void setLoginInfo(LoginInfo loginInfo) {
        this.loginInfo = loginInfo;
    }

    public String getJmbg() {
        return jmbg;
    }

    public void setJmbg(String jmbg) {
        this.jmbg = jmbg;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
