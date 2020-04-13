package com.projekat.XML.service;

import com.projekat.XML.dtos.AdvertisementDTO;
import com.projekat.XML.model.Advertisement;
//import com.projekat.XML.repository.AdvertisementRepository;

import com.projekat.XML.repository.AdvertisementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdvertisementService {

	@Autowired
	AdvertisementRepository advertisementRepository;
	
	public Advertisement save(AdvertisementDTO advertisementDTO) {
		System.out.println(advertisementDTO);
		return advertisementRepository.save(new Advertisement(advertisementDTO.getName(), advertisementDTO.getModel(), advertisementDTO.getBrand(),advertisementDTO.getFuelType(),advertisementDTO.getTransType(),advertisementDTO.getCarClass(),advertisementDTO.getTravelled(), advertisementDTO.getCarSeats(),advertisementDTO.getPrice()));
	}
	
	public List<Advertisement> findAll() {
		return advertisementRepository.findAll();
	}
	
	
	public Advertisement findOneByid(Long id) {
		return advertisementRepository.findOneByid(id);
	}
}
