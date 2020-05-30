package com.projekat.XML.controller;

import com.projekat.XML.dtos.UserDTO;
import com.projekat.XML.model.EntityUser;
import com.projekat.XML.model.KeyPairClass;
import com.projekat.XML.service.KeyPairClassService;
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
@RequestMapping(value = "")
public class UserController {

    @Autowired
    private UserService userService;

  

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


        userService.saveUser(userDB);

        return new ResponseEntity<>(userDB, HttpStatus.OK);
    } */

    @GetMapping(value = "/logout")
    public ResponseEntity getAllForCart() {
      
        
        System.out.println("izbrisao sve proslo izlogovan");

        return new ResponseEntity<>(HttpStatus.OK);
    }

    // @PostMapping(value = "/passwordChange", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    // public ResponseEntity<Long> changePassword(@RequestBody UserDTO userDTO){
    //     userService.changePassword(userDTO.getJmbg(), userDTO.getPassword());

    //     return new ResponseEntity<Long>((long) 1, HttpStatus.OK);
    // }

}
