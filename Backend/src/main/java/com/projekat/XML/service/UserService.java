package com.projekat.XML.service;

import com.projekat.XML.model.EndUser;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    public void register(EndUser endUser){
        System.out.println(endUser);
    }
}
