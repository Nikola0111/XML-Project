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
        
        
        return loginInfoRepository.findByUsername(username);

    }

    public void save(LoginInfo loginInfo){

        loginInfoRepository.save(loginInfo);

    }

    public LoginInfo findOneById(Long id){

        return loginInfoRepository.findOneById(id);

    }

}