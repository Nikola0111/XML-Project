package com.projekat.XML.controller;

import com.projekat.XML.dtos.*;
import com.projekat.XML.model.Advertisement;
import com.projekat.XML.model.Brand;
import com.projekat.XML.model.Comment;
import com.projekat.XML.rebbit.SenderClass;
import com.projekat.XML.service.AdvertisementService;
import com.projekat.XML.service.ShoppingCartService;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;



import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "advertisement")
public class AdvertisementController {

	@Autowired
	private AdvertisementService advertisementService;

	@Autowired
	private SenderClass sender;

	private final RabbitTemplate rabbitTemplate;

	public AdvertisementController(RabbitTemplate rabbitTemplate){
		this.rabbitTemplate=rabbitTemplate;
	}


	//@GetMapping(value = "/sendRabbit")
    public ResponseEntity<?> rabbit() {
	   
		System.out.println("Pogodio kontroler");
		sender.sendMessage();

		
        return new ResponseEntity<>( HttpStatus.OK);
	}

	@GetMapping(value = "/sendRabbit")
	public String sendMessage(){
		Brand brand=new Brand();
        brand.setName("IMEEE LEPO IME");
       // rabbitTemplate.convertAndSend("spring-boot-exchange",
		//		"foo.bar.blaa", advert);

		rabbitTemplate.convertAndSend("spring-boot-exchange","foo.bar.blaa", brand);
		
		
		
        return "We have sent a message! :";
    }
	
	@RequestMapping(value = "/saveImage", method = RequestMethod.POST, headers = "content-type=multipart/form-data")
    public ResponseEntity<Long> uploadImage(@RequestParam(value = "file", required = false) MultipartFile file) throws IOException {


	System.out.println("Pogodio");
		System.out.println(file.getOriginalFilename());

		advertisementService.saveImage(file);

         return new ResponseEntity<>(HttpStatus.OK);
}

	@PostMapping(value="/save")
	public ResponseEntity<Long> save(@RequestBody AdvertisementCreationDTO advertisementDTO) {

		System.out.println(advertisementDTO.getName()+advertisementDTO.getModel()+advertisementDTO.getBrand());
		System.out.println("AMIN");
	
		advertisementService.save(advertisementDTO);

		return new ResponseEntity<>(HttpStatus.OK);
	}

	

    @GetMapping(value = "/all")
    public ResponseEntity<List<Advertisement>> getAll() {
        List<Advertisement> advertisements = advertisementService.findAll();
        return new ResponseEntity<>(advertisements, HttpStatus.OK);
	}
	


	@PostMapping(value="/filterAdv", consumes= MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Advertisement>> filterAds(@RequestBody FilterAdsDTO filterAdsDTO) {
		System.out.println("POGODIO");
		return new ResponseEntity<>(advertisementService.filterAds(filterAdsDTO), HttpStatus.OK);
	}

	@GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE,  consumes= MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<AdvertisementDTO> getAdvertisement(@PathVariable Long id) {
		Advertisement advertisement = advertisementService.findOneByid(id);
		if(advertisement == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(new AdvertisementDTO(advertisement), HttpStatus.OK);
	}

	@PostMapping(value = "/update", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Advertisement> updateAdvertisement(@RequestBody Advertisement advertisement) throws Exception{
	    Advertisement ad = advertisementService.update(advertisement);
	    System.out.println(ad.getDiscount());
	    if(ad != null) {
	        return new ResponseEntity<>(advertisementService.findOneByid(ad.getId()), HttpStatus.OK);
        }
	    else {
	        return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }
    }

	@PostMapping(value = "/saveCommentAndGrade", consumes = MediaType.APPLICATION_JSON_VALUE)
	public  ResponseEntity<Double> saveCommentAndGrade(@RequestBody CommentDTO commentDTO){

		return new ResponseEntity<>(advertisementService.saveCommentAndGrade(commentDTO), HttpStatus.OK);

	}

	@GetMapping(value = "/preview/{id}", produces = MediaType.APPLICATION_JSON_VALUE,  consumes= MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<AdvertisementDTO> getAdvertisementPreview(@PathVariable Long id) {
		AdvertisementDTO advertisementDTO = advertisementService.findAdAndComments(id);
		if(advertisementDTO == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		System.out.println(advertisementDTO.getGrade());
		return new ResponseEntity<>(advertisementDTO, HttpStatus.OK);
	}

	@GetMapping(value = "/getAllComments/{id}", produces = MediaType.APPLICATION_JSON_VALUE,  consumes= MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<CommentPreviewDTO>> getAllComments(@PathVariable Long id) {
		System.out.println(id);
		AdvertisementDTO advertisementDTO = advertisementService.findAdAndComments(id);
		if(advertisementDTO == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		System.out.println(advertisementDTO.getName());
		System.out.println(advertisementDTO.getComments());
		return new ResponseEntity<>(advertisementDTO.getComments(), HttpStatus.OK);
	}


	@GetMapping(value = "/getRentedCars/{id}", produces = MediaType.APPLICATION_JSON_VALUE,  consumes= MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Long>> getRentedCars(@PathVariable Long id) {
		List<Long> rentedCars = advertisementService.getRentedCars(id);
		return new ResponseEntity<>(rentedCars, HttpStatus.OK);
	}

	@GetMapping(value = "/getAllByPostedBy/{id}", produces = MediaType.APPLICATION_JSON_VALUE,  consumes= MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Advertisement>> getAllByPostedBy(@PathVariable Long id) {
		List<Advertisement> advertisements = advertisementService.getAllByPostedBy(id);
		return new ResponseEntity<>(advertisements, HttpStatus.OK);
	}

	@PostMapping(value = "/saveReply",  produces = MediaType.APPLICATION_JSON_VALUE,  consumes= MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Long> saveReply(@RequestBody ReplyDTO replyDTO){
		advertisementService.saveReply(replyDTO);

		return new ResponseEntity<>((long) 1, HttpStatus.OK);
	}

	@GetMapping(value = "/getAllDetails", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<CarDetailsDTO>> getAllDetails() {
		List<CarDetailsDTO> cardetails = advertisementService.getCarDetails();

		return new ResponseEntity<>(cardetails, HttpStatus.OK);
	}

	@PostMapping(value = "/saveCarDetail", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Boolean> saveCarDetail(@RequestBody CarDetailsDTO carDetailsDTO) {
		Boolean ret = advertisementService.saveCarDetail(carDetailsDTO);

		return new ResponseEntity<>(ret, HttpStatus.OK);
	}

	@PostMapping(value = "/deleteCarDetail", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Boolean> deleteCarDetail(@RequestBody CarDetailsDTO carDetailsDTO) {
		Boolean ret = advertisementService.deleteCarDetail(carDetailsDTO);

		return new ResponseEntity<>(ret, HttpStatus.OK);
	}

	@GetMapping(value = "/getAllByUser", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Advertisement>> getAllByUser() {

		List<Advertisement> ads = advertisementService.getAllByUser();

		return new ResponseEntity<>(ads, HttpStatus.OK);
	}

	@GetMapping(value = "/getUnapprovedComments", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<CommentPreviewDTO>> getUnapprovedComments(){
		return new ResponseEntity<>(advertisementService.getUnapprovedComments(), HttpStatus.OK);
	}

	@PostMapping(value = "/approve/{id}", produces = MediaType.APPLICATION_JSON_VALUE, consumes= MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> approve(@PathVariable("id") Long id){
		System.out.println(id + " Id komentara");
		advertisementService.approveComment(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@PostMapping(value = "/delete/{id}", produces = MediaType.APPLICATION_JSON_VALUE, consumes= MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> delete(@PathVariable("id") Long id){
		advertisementService.deleteComment(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
