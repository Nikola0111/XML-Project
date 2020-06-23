package com.projekat.XML.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;


import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Set;



//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Set;

//implements UserDetails 
@Entity

public class LoginInfo implements UserDetails {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @NotBlank(message="Username must not be empty")
    @Size(min = 2, max = 12)
    private String username;

    @NotBlank(message="Password must not be empty")
    @Pattern(regexp = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{10,}$", message = "10 karaktera, brojevi i jedno malo jedno veliko i ovi ?=.*?[#?!@$%^&*-]")
    private  String password;
    

    @ElementCollection(targetClass=GrantedAuthority.class,fetch = FetchType.EAGER)
    private Set<? extends GrantedAuthority> grantedAuthorities;
    private  boolean isAccountNonExpired;
    private  boolean isAccountNonLocked;
    private  boolean isCredentialsNonExpired;
    private  boolean isEnabled;

    @NotBlank(message="Email must not be empty")
    @Pattern(regexp = "[a-zA-Z0-9]+@[a-zA-Z0-9]+.com")
    private  String email;

    private  String salt;


 
   
public LoginInfo(){

}

    public LoginInfo(String username,
                           String password,
                           String email,

                           String salt,
                           Set<? extends GrantedAuthority> grantedAuthorities,

                           boolean isAccountNonExpired,
                           boolean isAccountNonLocked,
                           boolean isCredentialsNonExpired,
                           boolean isEnabled) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.salt=salt;
        this.grantedAuthorities = grantedAuthorities;
        this.isAccountNonExpired = isAccountNonExpired;
        this.isAccountNonLocked = isAccountNonLocked;
        this.isCredentialsNonExpired = isCredentialsNonExpired;
        this.isEnabled = isEnabled;
    }


    public LoginInfo(String username, String email, String password){


        
    }
  
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return grantedAuthorities;
    }

    @Override
    public String getPassword() {
        return password;
    }


    @Override
    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getSalt() {
        return salt;
    }


    
    public Long getId() {
        return id;
    }

    @Override
    public boolean isAccountNonExpired() {
        return isAccountNonExpired;

    }
    

    @Override
    public boolean isAccountNonLocked() {
        return isAccountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return isCredentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return isEnabled;
    }

    public Set<? extends GrantedAuthority> getGrantedAuthorities() {
        return grantedAuthorities;
    }

    public void setGrantedAuthorities(Set<? extends GrantedAuthority> grantedAuthorities) {
        this.grantedAuthorities = grantedAuthorities;
    }

   

}        

