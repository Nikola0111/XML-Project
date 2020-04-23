package com.projekat.XML.controller;

import java.util.List;

import com.projekat.XML.dtos.AdvertisementInCartDTO;
import com.projekat.XML.service.BookingRequestService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "booking")
public class BookingRequestController {

@Autowired
BookingRequestService bookingRequestService;

    @PostMapping(value = "/save", produces=MediaType.APPLICATION_JSON_VALUE, consumes=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Long> Login(@RequestBody List<AdvertisementInCartDTO> lista){

        System.out.println("Pogodio je back");

        bookingRequestService.makeRequests(lista);

        return new ResponseEntity<>(HttpStatus.OK);
    }




    
}