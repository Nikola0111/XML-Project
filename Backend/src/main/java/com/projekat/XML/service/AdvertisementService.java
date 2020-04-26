package com.projekat.XML.service;

import com.projekat.XML.dtos.AdvertisementDTO;
import com.projekat.XML.model.Advertisement;
//import com.projekat.XML.repository.AdvertisementRepository;

import com.projekat.XML.model.ImageModel;
import com.projekat.XML.repository.AdvertisementRepository;
import com.projekat.XML.repository.ImageModelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

@Service
public class AdvertisementService {

	@Autowired
	AdvertisementRepository advertisementRepository;

	@Autowired
	ImageModelRepository imageModelRepository;
	
	public Advertisement save(AdvertisementDTO advertisementDTO) {
		/*for (ImageModel img: advertisementDTO.getImages()) {
			imageModelRepository.save(img);
		}*/
		return advertisementRepository.save(new Advertisement(advertisementDTO.getName(), advertisementDTO.getModel(), advertisementDTO.getBrand(),advertisementDTO.getFuelType(),advertisementDTO.getTransType(),advertisementDTO.getCarClass(),advertisementDTO.getTravelled(), advertisementDTO.getCarSeats(),advertisementDTO.getPrice(), advertisementDTO.getImages()));
	}
	
	public List<Advertisement> findAll() {
		return advertisementRepository.findAll();
	}
	
	
	public Advertisement findOneByid(Long id) {
		return advertisementRepository.findOneByid(id);
	}

}
