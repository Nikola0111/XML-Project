package com.projekat.XML.controller;

import com.projekat.XML.model.EndUser;
import com.projekat.XML.model.LoginInfo;
import com.projekat.XML.model.User;
import com.projekat.XML.service.EndUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.awt.*;

@RestController
@RequestMapping(value = "enduser")
public class EndUserController {

    @Autowired
    private EndUserService endUserService;

    @PostMapping(value = "/register", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> register(@RequestBody EndUser endUser){
        System.out.println(endUser.getLoginInfo());

        LoginInfo loginInfo;

        loginInfo = endUserService.findByEmail(endUser.getLoginInfo().getEmail());
        if(loginInfo != null){
            return new ResponseEntity<>("email", HttpStatus.BAD_REQUEST);
        }

        loginInfo = endUserService.findByUsername(endUser.getLoginInfo().getUsername());
        if(loginInfo != null){
            return new ResponseEntity<>("username", HttpStatus.BAD_REQUEST);
        }

        User user = endUserService.findByJmbg(endUser.getJmbg());
        if(user != null){
            return new ResponseEntity<>("jmbg", HttpStatus.BAD_REQUEST);
        }

        endUser.setBroj_zahteva(0);
        endUserService.save(endUser);
        return new ResponseEntity<>("ok", HttpStatus.OK);
    }
}
