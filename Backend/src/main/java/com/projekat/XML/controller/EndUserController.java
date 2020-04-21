package com.projekat.XML.controller;

import com.projekat.XML.model.EndUser;
import com.projekat.XML.model.LoginInfo;
import com.projekat.XML.model.User;
import com.projekat.XML.service.EndUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;


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

        endUser.setNumber_of_requests(0);
        endUser.setAccount_activated(false);
        endUserService.save(endUser);
        return new ResponseEntity<>("ok", HttpStatus.OK);
    }

    @GetMapping(value = "/getUnregistered", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<EndUser>> getUnregistered(){
        List<EndUser> users = endUserService.getUnregistered();

        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @PostMapping(value = "/accept", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Long> acceptRegistration(@RequestBody Long id){
        endUserService.acceptRegistration(id);

        return new ResponseEntity<>(id, HttpStatus.OK);
    }

    @PostMapping(value = "/reject", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Long> rejectRegistration(@RequestBody Long id){
        endUserService.rejectRegistration(id);

        return new ResponseEntity<>(id, HttpStatus.OK);
    }
}
