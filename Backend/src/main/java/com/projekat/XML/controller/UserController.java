package com.projekat.XML.controller;

import com.projekat.XML.model.User;

import com.projekat.XML.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Context;

@RestController
@RequestMapping(value = "user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping(value = "/login", produces=MediaType.APPLICATION_JSON_VALUE, consumes=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> Login(@RequestBody User user, @Context HttpServletRequest request){

        System.out.println(user.getLoginInfo().getEmail() + " " + user.getLoginInfo().getPassword());

        //provera email-a i username-a:
        User userDB = userService.findUserByEmailAndPassword(user);
        if(userDB == null){
            System.out.println("Wrong username or password");
            return new ResponseEntity<>(user, HttpStatus.BAD_REQUEST);
        }

        System.out.println("Account with email " + userDB.getLoginInfo().getEmail() + "has been logged in");

        //Sacuvati korisnika u sesiji
        request.getSession().setAttribute("ulogovanKorisnik", userDB.getJmbg());
        return new ResponseEntity<>(userDB, HttpStatus.OK);
    }



}
