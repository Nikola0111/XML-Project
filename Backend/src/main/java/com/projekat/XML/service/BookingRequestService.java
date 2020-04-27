package com.projekat.XML.service;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import com.projekat.XML.dtos.ItemInCartDTO;
import com.projekat.XML.enums.RequestStates;
import com.projekat.XML.model.requests.BookingRequest;
import com.projekat.XML.repository.AdvertisementRepository;
import com.projekat.XML.repository.BookingRequestRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Service
public class BookingRequestService {

    @Autowired
    BookingRequestRepository bookingRequestRepository;

    @Autowired
    AdvertisementRepository advertisementRepository;




    public void makeRequests(List<ItemInCartDTO> listaZahteva){
        //pretrazujem za svakog vlasnika, ako se pogodi da je isti, pogledam dal ide oglas odvojeno ili zajedno i na osnovu toga 
        //ubacujem u listu, cuvam i obrisem, onda se predje na sledeceg vlasnika
        //EROR STA AKO JE U PRVOJ LISTI OPET ISTI
        List<Long> vlasnici=new ArrayList<Long>();
        List<ItemInCartDTO> copyList=listaZahteva;
        ArrayList<ItemInCartDTO> together=new ArrayList<>();
        ArrayList<ItemInCartDTO> seprate=new ArrayList<>();


        for (ItemInCartDTO ItemInCartDTO : listaZahteva) {
            
            if(!vlasnici.contains(ItemInCartDTO.getAdvertisement().getPostedBy().getId())){

                vlasnici.add(ItemInCartDTO.getAdvertisement().getPostedBy().getId());

            }
        }

        System.out.println("Broj vlasnika je : "+vlasnici.size());


        for (Long id: vlasnici) {
            
            for (ItemInCartDTO adv :copyList) {
                
                if(adv.getAdvertisement().getPostedBy().getId().equals(id)){

                    if(adv.isTogether()){


                        together.add(adv);

                    }
                    else{
                        seprate.add(adv);

                    }

                }



            }

            // treba ovde da sejvujem ako nisu prazne i da ih ispraznim nakon sejvanja
           BookingRequest request=bookingRequestRepository.findTopByOrderByIdDesc();
           Long lastGroupId;
         
           System.out.println(request);
            
            if(request==null){
                lastGroupId=Long.valueOf(0);

            }
            else{
                lastGroupId=request.getGroupId();
            }

            // try{
            //     lastGroupId=request.getGroupId();
               

            // }
            // catch(Exception e){
            //     lastGroupId=Long.valueOf(0);
              
            // }

           

            System.out.println("Poslednji id je : "+lastGroupId);
            
            if(!seprate.isEmpty()){

                for (ItemInCartDTO ItemInCartDTO2 : seprate) {
                
                lastGroupId++;
                bookingRequestRepository.save(new BookingRequest( ItemInCartDTO2.getAdvertisement().getPostedBy().getId(),getLogedUserId(),
                 lastGroupId, RequestStates.PENDING,ItemInCartDTO2.getAdvertisement() , ItemInCartDTO2.isTogether(),ItemInCartDTO2.getTimeFrom(),ItemInCartDTO2.getTimeTo()));



                 
                }
                seprate.clear();

               

            }


           lastGroupId++;

            if(!together.isEmpty()){

                for (ItemInCartDTO ItemInCartDTO : together) {
                    
                bookingRequestRepository.save(new BookingRequest( ItemInCartDTO.getAdvertisement().getPostedBy().getId(),getLogedUserId(),
                lastGroupId, RequestStates.PENDING,ItemInCartDTO.getAdvertisement(),
                 ItemInCartDTO.isTogether(),ItemInCartDTO.getTimeFrom(),ItemInCartDTO.getTimeTo()));


                }

                together.clear();
            }


           

        }

        
        
    }


    public List<BookingRequest> getAllForRenter(){
       
        return bookingRequestRepository.findByUserForId(getLogedUserId());
    }

    public List<Long> getGroupsForRequest (){
        List<Long> group=new ArrayList<Long>();

        for (BookingRequest bookingRequest : bookingRequestRepository.findByUserForId(getLogedUserId())) {
            
            if(!group.contains(bookingRequest.getGroupId())){

                group.add(bookingRequest.getGroupId());

            }
        }

        return group;


    }

    public List<BookingRequest> findAll()
    {
        return bookingRequestRepository.findAll();
    }

    public void acceptRequest(Long grupa){

        System.out.println("Broj zahteva"+bookingRequestRepository.findAllByGroupId(grupa).size());

        for (BookingRequest request : bookingRequestRepository.findAllByGroupId(grupa)) {
            
            
            request.setStateOfRequest(RequestStates.RESERVED);

            bookingRequestRepository.save(request);

        }
    }

    private Long getLogedUserId(){
        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpSession session = attr.getRequest().getSession(true);
    
        Long id = (Long) session.getAttribute("user");
        return id;
    }
    

}