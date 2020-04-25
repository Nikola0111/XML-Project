package com.projekat.XML.controller;

import java.util.List;

import com.projekat.XML.dtos.AdvertisementInCartDTO;
import com.projekat.XML.model.requests.BookingRequest;
import com.projekat.XML.service.BookingRequestService;

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


    @PostMapping(value = "/acceptRequest")
    public ResponseEntity<Long> acceptRequest(@RequestBody Long grupa){

        System.out.println("Pogodio prihvatanje zahteva grupe: " + grupa);

        bookingRequestService.acceptRequest(grupa);

        return new ResponseEntity<>(HttpStatus.OK);
    }




    @GetMapping(value = "/getAllForMe")
    public ResponseEntity<List<BookingRequest>> getAllForCart() {
		
	   
		List<BookingRequest> requests = bookingRequestService.getAllForRenter();

		System.out.println("pogodio je kontroler, broj oglasa vraca=="+requests.size());
		
        return new ResponseEntity<>(requests, HttpStatus.OK);
    }

  
    @GetMapping(value = "/getGroupsForMe")
    public ResponseEntity<List<Long>> getAllGroupsForCart() {
		
       
        
        
		List<Long> groups = bookingRequestService.getGroupsForRequest();

		System.out.println("pogodio je kontroler, broj grupa=="+groups.size());
		
        return new ResponseEntity<>(groups, HttpStatus.OK);
    }

    
}