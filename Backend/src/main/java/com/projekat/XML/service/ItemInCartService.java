package com.projekat.XML.service;
import com.projekat.XML.dtos.ItemInCartDTO;
import com.projekat.XML.model.ItemInCart;
import com.projekat.XML.model.ShoppingCart;
import com.projekat.XML.repository.AdvertisementRepository;
import com.projekat.XML.repository.ItemInCartRepository;
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
public class ItemInCartService {


    @Autowired
   ShoppingCartService shoppingCartService;

    @Autowired
    ItemInCartRepository itemInCartRepository;


public void save(ItemInCartDTO itemInCartDTO){

    ItemInCart item=new ItemInCart(itemInCartDTO.getAdvertisement(),itemInCartDTO.getTimeFrom(), itemInCartDTO.getTimeTo());
    itemInCartRepository.save(item);

    shoppingCartService.addAItemInCart(item.getId());

}







    


}