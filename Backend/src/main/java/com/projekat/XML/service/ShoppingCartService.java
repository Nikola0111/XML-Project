package com.projekat.XML.service;

import com.projekat.XML.model.ItemInCart;
import com.projekat.XML.model.ShoppingCart;
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
public class ShoppingCartService {

    @Autowired
    ShoppingCartRepository shoppingCartRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    ItemInCartRepository itemInCartRepository;

    @Autowired
    SessionService sessionService;

public void save(Long id){

    shoppingCartRepository.save(new ShoppingCart(id, new ArrayList<Long>()));

}


public void addAItemInCart(Long itemId){
    ShoppingCart cart=shoppingCartRepository.findOneByuserId(getLogedUserId());
    cart.addOneItemInCart(itemId);
    shoppingCartRepository.save(cart);
}

public void removeItemInCart(Long itemId){
    ShoppingCart cart=shoppingCartRepository.findOneByuserId(getLogedUserId());
    cart.removeOneItemInCart(itemId);
    shoppingCartRepository.save(cart);

}

public void removeAll(){
    System.out.println("OVO JE USER"+getLogedUserId());
    ShoppingCart cart=shoppingCartRepository.findOneByuserId(getLogedUserId());
    cart.removeAllItems();
    shoppingCartRepository.save(cart);

}

public ShoppingCart getShoppingCart(Long id){
    return shoppingCartRepository.findOneByuserId(id);
}


public List<ItemInCart> fotCart(){
    List< Long> oglasi=new ArrayList<>();
    List<ItemInCart> vrati=new ArrayList<ItemInCart>();

    for(ShoppingCart shoppingCart: shoppingCartRepository.findAll()) {
        if(shoppingCart.getUserId().equals(getLogedUserId())){
            oglasi=shoppingCart.getItemInCartList();
        }


    }
    for(ItemInCart itemInCart: itemInCartRepository.findAll()){
    
        for(Long id : oglasi){
        
            if(itemInCart.getId().equals(id)){
             
                vrati.add(itemInCart);
            }
        }

    }
    
return vrati;

}


public Long getLogedUserId(){
    return sessionService.getLoggedUser().getId();
}

    


}