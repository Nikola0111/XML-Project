package com.projekat.XML.controller;

import com.projekat.XML.dtos.UserDTO;
import com.projekat.XML.service.AgentService;
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
@RequestMapping(value = "agent")
public class AgentController {

    @Autowired
    private AgentService agentService;

    @PostMapping(value = "/register", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Long> register(@RequestBody UserDTO userDTO){
        int value = agentService.save(userDTO);

        if(value == 0){
            return new ResponseEntity(value, HttpStatus.OK);
        }

        return new ResponseEntity(value, HttpStatus.BAD_REQUEST);
    }
}
