package com.projekat.XML.service;
import com.projekat.XML.model.LoginInfo;
import com.projekat.XML.repository.LoginInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class LoginInfoService implements UserDetailsService {


   @Autowired
   private LoginInfoRepository loginInfoRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        
    for (LoginInfo login : loginInfoRepository.findAll()) {
        
        if(login.getUsername().equals(username)){
            return login;
        }

    }

    System.out.println("NIJE PROSAO");
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