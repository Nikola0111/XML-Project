package com.projekat.XML.service;

import com.projekat.XML.dtos.AdvertisementDTO;
import com.projekat.XML.model.Advertisement;
import com.projekat.XML.model.ShoppingCart;
import com.projekat.XML.model.User;
import com.projekat.XML.repository.AdvertisementRepository;
import com.projekat.XML.repository.ShoppingCartRepository;
import com.projekat.XML.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.ArrayList;

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

    ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
    HttpSession session = attr.getRequest().getSession(true);

    Long id = (Long) session.getAttribute("user");

    ShoppingCart cart=shoppingCartRepository.findOneByuserId(id);
    cart.addOneAdv(advId);
    shoppingCartRepository.save(cart);
}
    


}