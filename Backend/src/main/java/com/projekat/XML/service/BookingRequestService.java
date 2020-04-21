package com.projekat.XML.service;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import com.projekat.XML.dtos.AdvertisementInCartDTO;
import com.projekat.XML.enums.RequestStates;
import com.projekat.XML.model.User;
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




    public void makeRequests(List<AdvertisementInCartDTO> listaZahteva){
        //pretrazujem za svakog vlasnika, ako se pogodi da je isti, pogledam dal ide oglas odvojeno ili zajedno i na osnovu toga 
        //ubacujem u listu, cuvam i obrisem, onda se predje na sledeceg vlasnika
        //EROR STA AKO JE U PRVOJ LISTI OPET ISTI
        List<Long> vlasnici=new ArrayList<Long>();
        List<AdvertisementInCartDTO> copyList=listaZahteva;
        ArrayList<AdvertisementInCartDTO> together=new ArrayList<>();
        ArrayList<AdvertisementInCartDTO> seprate=new ArrayList<>();


        for (AdvertisementInCartDTO advertisementInCartDTO : listaZahteva) {
            
            if(!vlasnici.contains(advertisementInCartDTO.getPostedBy().getId())){

                vlasnici.add(advertisementInCartDTO.getPostedBy().getId());

            }
        }

        System.out.println("Broj vlasnika je : "+vlasnici.size());


        for (Long id: vlasnici) {
            
            for (AdvertisementInCartDTO adv :copyList) {
                
                if(adv.getPostedBy().getId().equals(id)){

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
         
            
            try{
                lastGroupId=request.getGroupId();
               

            }
            catch(Exception e){
                lastGroupId=Long.valueOf(0);
              
            }

           

            System.out.println("Poslednji id je : "+lastGroupId);
            
            if(!seprate.isEmpty()){

                for (AdvertisementInCartDTO advertisementInCartDTO2 : seprate) {
                
                lastGroupId++;
                bookingRequestRepository.save(new BookingRequest( advertisementInCartDTO2.getPostedBy().getId(),getLogedUserId(),
                 lastGroupId, RequestStates.PENDING,advertisementRepository.findOneByid(advertisementInCartDTO2.getId()) , advertisementInCartDTO2.isTogether()));



                 
                }
                seprate.clear();

               

            }


           lastGroupId++;

            if(!together.isEmpty()){

                for (AdvertisementInCartDTO advertisementInCartDTO : together) {
                    
                bookingRequestRepository.save(new BookingRequest( advertisementInCartDTO.getPostedBy().getId(),getLogedUserId(),
                lastGroupId, RequestStates.PENDING,advertisementRepository.findOneByid(advertisementInCartDTO.getId()),
                 advertisementInCartDTO.isTogether()));


                }

                together.clear();
            }


           

        }

        
        
    }

    public Long getLogedUserId(){
        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpSession session = attr.getRequest().getSession(true);
    
        Long id = (Long) session.getAttribute("user");
        return id;
    }
    

}