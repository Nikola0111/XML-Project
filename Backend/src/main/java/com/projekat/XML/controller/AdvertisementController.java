package com.projekat.XML.controller;

import com.projekat.XML.dtos.AdvertisementDTO;
import com.projekat.XML.model.Advertisement;
import com.projekat.XML.service.AdvertisementService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;



import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping(value = "/all")
    public ResponseEntity<List<Advertisement>> getAll() {
        List<Advertisement> advertisements = advertisementService.findAll();
        for(Advertisement ad : advertisements) {
			System.out.println(ad.getName());
		}
        return new ResponseEntity<>(advertisements, HttpStatus.OK);
    }

}
