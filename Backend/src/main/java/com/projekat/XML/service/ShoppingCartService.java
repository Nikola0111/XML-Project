package com.projekat.XML.service;

import com.projekat.XML.model.Advertisement;
import com.projekat.XML.model.ShoppingCart;
import com.projekat.XML.repository.AdvertisementRepository;
import com.projekat.XML.repository.ShoppingCartRepository;
import com.projekat.XML.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;


import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

@Service
public class ShoppingCartService {

    @Autowired
    ShoppingCartRepository shoppingCartRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    AdvertisementRepository advertisementRepository;

public void save(Long id){

    shoppingCartRepository.save(new ShoppingCart(id, new ArrayList<Long>()));

}

public void addAdvertisement(Long advId){

    

    ShoppingCart cart=shoppingCartRepository.findOneByuserId(getLogedUserId());
    cart.addOneAdv(advId);
    shoppingCartRepository.save(cart);
}

public List<Advertisement> fotCart(){
    List< Long> oglasi=new ArrayList<>();
    List<Advertisement> vrati=new ArrayList<Advertisement>();

    for(ShoppingCart shoppingCart: shoppingCartRepository.findAll()) {
        if(shoppingCart.getUserId().equals(getLogedUserId())){
            oglasi=shoppingCart.getAdvList();
        }


    }
    for(Advertisement advertisement: advertisementRepository.findAll()){
    
        for(Long id : oglasi){
        
            if(advertisement.getId().equals(id)){
             
                vrati.add(advertisement);
            }
        }

    }
    
return vrati;

}


public Long getLogedUserId(){
    ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
    HttpSession session = attr.getRequest().getSession(true);

    Long id = (Long) session.getAttribute("user");
    return id;
}

    


}