package com.projekat.XML.service;

import com.projekat.XML.dtos.AdvertisementDTO;
import com.projekat.XML.model.Advertisement;
import com.projekat.XML.repository.AdvertisementRepository;
import com.projekat.XML.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.List;

import javax.servlet.http.HttpSession;

@Service
public class AdvertisementService {

	@Autowired
	AdvertisementRepository advertisementRepository;

	@Autowired
	UserRepository userRepository;
	
	public Advertisement save(AdvertisementDTO advertisementDTO) {
		ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
		HttpSession session = attr.getRequest().getSession(true);

		Long id = (Long) session.getAttribute("user");

		//kada se kreira korisnik kreira mu se i korpa u koju ce moci da dodaje oglase!
		

		return advertisementRepository.save(new Advertisement(advertisementDTO.getName(), advertisementDTO.getModel(), advertisementDTO.getBrand(),advertisementDTO.getFuelType(),advertisementDTO.getTransType(),advertisementDTO.getCarClass(),advertisementDTO.getTravelled(), advertisementDTO.getCarSeats(),advertisementDTO.getPrice(),userRepository.findOneByid(id)));
	}
	
	public List<Advertisement> findAll() {
		return advertisementRepository.findAll();
	}
	
	
	public Advertisement findOneByid(Long id) {
		return advertisementRepository.findOneByid(id);
	}
}
