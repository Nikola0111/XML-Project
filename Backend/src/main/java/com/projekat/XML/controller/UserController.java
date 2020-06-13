package com.projekat.XML.controller;

import com.projekat.XML.dtos.UserDTO;
import com.projekat.XML.model.EntityUser;

import com.projekat.XML.model.LoginInfo;
import com.projekat.XML.service.LoginInfoService;
import com.projekat.XML.service.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping(value = "authentication")
public class UserController {

    @Autowired
    private UserService userService;
    
    @Autowired
    private LoginInfoService loginInfoService;


    @PostMapping(value = "/login")
    public String Login(){


        System.out.println("PROSO PROSO");

      return "proslo";
    }


    @GetMapping(value = "/loginToken")
    public ResponseEntity getToken() {
    
        System.out.println("POGODIO JE LOGIN TOKEN");

        return new ResponseEntity<>(HttpStatus.OK);
    }

/*
    @PostMapping(value = "/login", produces=MediaType.APPLICATION_JSON_VALUE, consumes=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<EntityUser> Login(@RequestBody EntityUser user){

        System.out.println(user.getLoginInfo().getEmail() + " " + user.getLoginInfo().getPassword());

        //provera email-a i username-a:
        EntityUser userDB = userService.findUserByEmailAndPassword(user);
        if(userDB == null){
            System.out.println("Wrong username or password");
            return new ResponseEntity<>(user, HttpStatus.BAD_REQUEST);
        }


        LoginInfo loginInfo = loginInfoService.findOneByUsername(login.getUsername());
        if (loginInfo == null) {
            return new ResponseEntity<>(loginInfo, HttpStatus.BAD_REQUEST);
        }  

        userService.login(loginInfo);


        return new ResponseEntity<>(loginInfo, HttpStatus.OK);
    }

    @GetMapping(value = "/logout")
    public ResponseEntity getAllForCart() {
        userService.logOut();

        return new ResponseEntity<>(userDB, HttpStatus.OK);
    } */

    @GetMapping(value = "/logout")
    public ResponseEntity getAllForCart() {
      
        
        System.out.println("izbrisao sve proslo izlogovan");


        return new ResponseEntity<>(HttpStatus.OK);
    }



    @GetMapping(value = "/getUserByUsername/{username}")
    public ResponseEntity<EntityUser> getUserByUsername(@PathVariable("username") String username) {

        EntityUser user = userService.findByUsername(username);

        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    
    @PostMapping(value = "/passwordChange", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Long> changePassword(@RequestBody UserDTO userDTO) {
    //s    userService.changePassword(userDTO.getJmbg(), userDTO.getPassword());

    // @PostMapping(value = "/passwordChange", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    // public ResponseEntity<Long> changePassword(@RequestBody UserDTO userDTO){
    //     userService.changePassword(userDTO.getJmbg(), userDTO.getPassword());


         return new ResponseEntity<Long>((long) 1, HttpStatus.OK);
     }

}
