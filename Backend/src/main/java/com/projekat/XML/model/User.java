package com.projekat.XML.model;

import com.projekat.XML.enums.UserType;

import javax.persistence.*;

@Entity
@Table(name = "user_entity")
@Inheritance(strategy = InheritanceType.JOINED)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "surname", nullable = false)
    private String surname;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "login_info", referencedColumnName = "id")
    private LoginInfo loginInfo;

    @Column(name = "jmbg", unique = true, nullable = false)
    private String jmbg;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "user_type")
    private UserType userType;

    public User(String name, String surname, LoginInfo loginInfo, String jmbg, String phoneNumber, UserType ut) {
        this.name = name;
        this.surname = surname;
        this.loginInfo = loginInfo;
        this.jmbg = jmbg;
        this.phoneNumber = phoneNumber;
        this.userType = ut;
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

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    public String getPassword(){
        return this.loginInfo.getPassword();
    }

    public void setPassword(String password){
        this.loginInfo.setPassword(password);
    }

    public String getUsername(){
        return this.loginInfo.getUsername();
    }

    public void setUsername(String username){
        this.loginInfo.setUsername(username);
    }

    public void setEmail(String email){
        this.loginInfo.setEmail(email);
    }

    public String getEmail(){
        return this.loginInfo.getEmail();
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", loginInfo=" + loginInfo +
                ", jmbg='" + jmbg + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", userType=" + userType +
                '}';
    }
}
