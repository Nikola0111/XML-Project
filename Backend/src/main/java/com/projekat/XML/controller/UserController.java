package com.projekat.XML.controller;

import com.projekat.XML.model.User;
import com.projekat.XML.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping(value = "user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping(value = "/login", produces=MediaType.APPLICATION_JSON_VALUE, consumes=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> Login(@RequestBody User user){

        System.out.println(user.getLoginInfo().getEmail() + " " + user.getLoginInfo().getPassword());

        //provera email-a i username-a:
        User userDB = userService.findUserByEmailAndPassword(user);
        if(userDB == null){
            System.out.println("Wrong username or password");
            return new ResponseEntity<>(user, HttpStatus.BAD_REQUEST);
        }


        userService.saveUser(userDB);

        return new ResponseEntity<>(userDB, HttpStatus.OK);
    }

    @GetMapping(value = "/logout")
    public ResponseEntity getAllForCart() {
	    userService.logOut();

        return new ResponseEntity<>(HttpStatus.OK);
    }



}
