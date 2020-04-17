package com.projekat.XML.service;

import com.projekat.XML.model.EndUser;
import com.projekat.XML.model.LoginInfo;
import com.projekat.XML.model.User;
import com.projekat.XML.repository.EndUserRepository;
import com.projekat.XML.repository.LoginInfoRepository;
import com.projekat.XML.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EndUserService {

    @Autowired
    private EndUserRepository endUserRepository;

    @Autowired
    private LoginInfoRepository loginInfoRepository;

    @Autowired
    private UserRepository userRepository;

    public void save(EndUser endUser) { endUserRepository.save(endUser);}

    public LoginInfo findByEmail(String email) {
        return  loginInfoRepository.findByEmail(email);
    }

    public LoginInfo findByUsername(String username){
        return loginInfoRepository.findByUsername(username);
    }

    public User findByJmbg(String jmbg){
        return userRepository.findByJmbg(jmbg);
    }
}
