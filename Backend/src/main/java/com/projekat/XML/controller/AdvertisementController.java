package com.projekat.XML.controller;

import com.projekat.XML.dtos.AdvertisementDTO;
import com.projekat.XML.service.AdvertisementService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;



import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "advertisement")
public class AdvertisementController {

	@Autowired
	private AdvertisementService advertisementService;


	@PostMapping(value="/save", consumes= MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Long> save(@RequestBody AdvertisementDTO advertisementDTO) {

		System.out.println(advertisementDTO.getName()+advertisementDTO.getModel()+advertisementDTO.getBrand());
		System.out.println("AMIN");
		advertisementService.save(advertisementDTO);

		return new ResponseEntity<>(HttpStatus.OK);
	}
}
