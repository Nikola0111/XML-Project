package com.projekat.XML.service;

import com.projekat.XML.dtos.AdvertisementDTO;
import com.projekat.XML.dtos.FilterAdsDTO;
import com.projekat.XML.model.Advertisement;
import com.projekat.XML.repository.AdvertisementRepository;
import com.projekat.XML.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
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

		// kada se kreira korisnik kreira mu se i korpa u koju ce moci da dodaje oglase!

		return advertisementRepository.save(new Advertisement(advertisementDTO.getName(), advertisementDTO.getModel(),
				advertisementDTO.getBrand(), advertisementDTO.getFuelType(), advertisementDTO.getTransType(),
				advertisementDTO.getCarClass(), advertisementDTO.getTravelled(), advertisementDTO.getCarSeats(),
				advertisementDTO.getPrice(), userRepository.findOneByid(id),advertisementDTO.getPictures()));
	}

	public void saveImage(MultipartFile image) {

		String path = System.getProperty("user.dir");
		System.out.println("Putanja do direktorijuma je :" + path);
		String newPath = path.replace("Backend", "Frontend\\src\\assets\\images");
		System.out.println("PRVI POKUSAJ=" + newPath);
		byte[] bytes;
		try {
			 bytes = image.getBytes();
			 Path put= Paths.get(newPath, image.getOriginalFilename());
			Files.write(put,bytes);
		System.out.println("UPISAO");
		} catch (IOException e) {
			
			e.printStackTrace();
			System.out.println("UPAO U EXCEPTION");
		}
		


	}
	
	public List<Advertisement> findAll() {
		return advertisementRepository.findAll();
	}
	
	
	public Advertisement findOneByid(Long id) {
		return advertisementRepository.findOneByid(id);
	}


	public List<Advertisement> filterAds(FilterAdsDTO filterAdsDTO)
	{
		List<Advertisement> allAds = advertisementRepository.findAll();
		List<Advertisement> filteredAds = new ArrayList<Advertisement>();
		List<Advertisement> filteredAvailableAds = new ArrayList<Advertisement>();

/*
		List<ListaZakazanihTermina> zakazani
*/

		for(Advertisement ad : allAds)
		{
			if((ad.getFuelType() == filterAdsDTO.getFuelType() || filterAdsDTO.getFuelType() == null) &&
					(ad.getTransType() == filterAdsDTO.getTransmissionType() || filterAdsDTO.getTransmissionType() == null) &&
					(ad.getCarClass() == filterAdsDTO.getCarClass() || filterAdsDTO.getCarClass() == null) &&
					(ad.getTravelled() >= filterAdsDTO.getTravelledFrom() || filterAdsDTO.getTravelledFrom() == 0) &&
					(ad.getTravelled() <= filterAdsDTO.getTravelledTo() || filterAdsDTO.getTravelledTo() == 0) &&
					(ad.getFuelType() == filterAdsDTO.getFuelType() || filterAdsDTO.getFuelType() == null) &&
					(ad.getPrice() >= filterAdsDTO.getPriceFrom() || filterAdsDTO.getPriceFrom() == 0) &&
					(ad.getPrice() <= filterAdsDTO.getPriceTo() || filterAdsDTO.getPriceTo() == 0))
			{
				filteredAds.add(ad);
			}

		}

		/*int taken = 0;

		LocalDateTime timeFrom = LocalDateTime.parse(filterAdsDTO.getTimeFrom());
		LocalDateTime timeTo = LocalDateTime.parse(filterAdsDTO.getTimeTo());
		for(Advertisement ad : filteredAds)
		{
			taken = 0;
			if(filterAdsDTO.getTimeFrom() == null || filterAdsDTO.getTimeTo() == null) {
				for (Termin termin : zakazaniTermini) {

					if (termin.Ad.getID == ad.getId()) {
						if(filterAdsDTO.getTimeFrom() != null) {
							if (timeFrom.isAfter(zakazaniTermin.getStartTime) && timeFrom.isBefore(zakazaniTermin.getEndTime())) {
								taken = 1;
							}
						}

						if(filterAdsDTO.getTimeTo() != null) {
							if (timeTo.isAfter(zakazaniTermin.getStartTime) && timeTo.isBefore(zakazaniTermin.getEndTime())) {
								taken = 1;
							}
						}
					}
				}
			}
			if(taken == 0)
			{
				filteredAvailableAds.add(ad);
			}
		}*/

		//KAD SE OTKOMENTARISE, VRACACE FILTEREDAVAILABLEADS
		return filteredAds;
	}
}
