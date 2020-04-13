package com.projekat.XML.service;

import com.projekat.XML.model.EndUser;
import com.projekat.XML.repository.EndUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EndUserService {

    @Autowired
    private EndUserRepository endUserRepository;

    public void save(EndUser endUser) { endUserRepository.save(endUser);}
}
