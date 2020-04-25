package com.projekat.XML.service;

import com.projekat.XML.model.EndUser;
import com.projekat.XML.model.LoginInfo;
import com.projekat.XML.model.User;
import com.projekat.XML.repository.EndUserRepository;
import com.projekat.XML.repository.LoginInfoRepository;
import com.projekat.XML.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

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

    public List<EndUser> getUnregistered(){ return endUserRepository.findByActivity(false); }

    public List<EndUser> getAdminUnregistered(){ return endUserRepository.findByAdminApproved(false); }

    public EndUser findById(Long id){
        return endUserRepository.findById(id).get();
    }

    @Transactional
    public EndUser acceptRegistration(Long id){
        Optional opt = endUserRepository.findById(id);

        if(opt.isPresent()){
            EndUser endUser = (EndUser) opt.get();

            endUser.setAccount_activated(true);
            endUserRepository.save(endUser);

            return endUser;
        }

        return null;
    }

    @Transactional
    public EndUser changeAdminActivated(Long id){
        Optional opt = endUserRepository.findById(id);

        if(opt.isPresent()){
            EndUser endUser = (EndUser) opt.get();

            endUser.setAdminApproved(true);
            endUserRepository.save(endUser);

            return endUser;
        }

        return null;
    }

    @Transactional
    public void rejectRegistration(Long id){
        Optional opt = endUserRepository.findById(id);

        if(opt.isPresent()){
            endUserRepository.delete((EndUser) opt.get());
        }
    }
}
