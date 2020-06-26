package com.projekat.XML.service;
import com.projekat.XML.model.EndUser;
import com.projekat.XML.model.EntityUser;
import com.projekat.XML.model.LoginInfo;
import com.projekat.XML.repository.EndUserRepository;
import com.projekat.XML.repository.LoginInfoRepository;
import com.projekat.XML.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class LoginInfoService implements UserDetailsService {


   @Autowired
   private LoginInfoRepository loginInfoRepository;

   @Autowired
   private UserRepository userRepository;

   @Autowired
   private EndUserRepository endUserRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        
        LoginInfo login = loginInfoRepository.findByUsername(username);
        EndUser endUser = new EndUser();
        for(EntityUser user : userRepository.findAll()){
            if(user.getLoginInfoId().equals(login.getId())){
                endUser = getEndUserByUser(user);


                if(endUser != null ){
                    if(endUser.isAccount_activated()) {
                        return login;
                    }
                } else {
                    return login;
                }
            }
        }

        return null;


    }

    public EndUser getEndUserByUser(EntityUser user){
        for(EndUser endUser : endUserRepository.findAll()){
            if(endUser.getUser().getId().equals(user.getId())){
                return endUser;
            }
        }
        return null;
    }


    public void save(LoginInfo loginInfo){

        loginInfoRepository.save(loginInfo);

    }

    public String findSaltByUsername(String username){

return loginInfoRepository.findByUsername(username).getSalt();

    }


    public LoginInfo findByEmail(String email){


        return loginInfoRepository.findByEmail(email);
    }


    public LoginInfo findOneById(Long id){

        return loginInfoRepository.findOneById(id);

    }


    public LoginInfo findOneByUsername(String username){

        return loginInfoRepository.findByUsername(username);
        
    }

}