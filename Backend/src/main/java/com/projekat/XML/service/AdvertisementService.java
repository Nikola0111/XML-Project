package com.projekat.XML.service;

import com.projekat.XML.dtos.AdvertisementDTO;
import com.projekat.XML.dtos.CommentDTO;
import com.projekat.XML.dtos.CommentPreviewDTO;
import com.projekat.XML.dtos.FilterAdsDTO;
import com.projekat.XML.model.Advertisement;
import com.projekat.XML.model.Comment;
import com.projekat.XML.model.EndUser;
import com.projekat.XML.model.Grade;
import com.projekat.XML.repository.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.*;

import javax.servlet.http.HttpSession;

@Service
public class AdvertisementService {

	@Autowired
	AdvertisementRepository advertisementRepository;

	@Autowired
	UserRepository userRepository;

	@Autowired
	GradeService gradeService;

	@Autowired
	CommentRepository commentRepository;

	@Autowired
	EndUserRepository endUserRepository;
	
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
		Advertisement ad = advertisementRepository.findOneByid(id);

		ad.setGrade(gradeService.calculateGradeForAd(id));
		System.out.println(ad.getGrade());
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

	public void saveCommentAndGrade(CommentDTO commentDTO){
		Advertisement ad = advertisementRepository.findOneByid(commentDTO.getAd());
		Grade grade = new Grade(commentDTO.getGrade(), ad);

		gradeService.save(grade);

		Optional obj = endUserRepository.findById(commentDTO.getUserId());

		if(obj.isPresent()) {
			Date date = new Date();
			System.out.println(date);

			Comment comment = new Comment(commentDTO.getMessage(), date, ad, (EndUser) obj.get(), commentDTO.getGrade());

			commentRepository.save(comment);
		}
		//sacuvaj komentar
	}

	public AdvertisementDTO findAdAndComments(Long id) {
		Advertisement ad = advertisementRepository.findOneByid(id);
		System.out.println(ad);
		List<Comment> db = commentRepository.findByAd_Id(id);
		System.out.println(db);
		List<CommentPreviewDTO> comments = new ArrayList<CommentPreviewDTO>();
		for(int i = 0;i < db.size();i++) {
			CommentPreviewDTO temp = new CommentPreviewDTO(db.get(i).getValue(), db.get(i).getEndUser().getEmail(),
					db.get(i).getGrade(), db.get(i).getDate());

			comments.add(temp);
		}

		AdvertisementDTO adDTO = new AdvertisementDTO(ad);
		adDTO.setComments(comments);
		return adDTO;
	}

	public List<Long> getRentedCars(Long userId){
		Optional obj = endUserRepository.findById(userId);
		List<Long> list = new ArrayList<>();
		if(obj.isPresent()){
			EndUser endUser = (EndUser) obj.get();

			for(int i = 0;i < endUser.getRentedCars().size(); i++){
				list.add(endUser.getRentedCars().get(i).getId());
			}
		}

		return list;
	}
}
