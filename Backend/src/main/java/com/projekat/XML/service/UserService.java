package com.projekat.XML.service;

import com.projekat.XML.model.EndUser;
import com.projekat.XML.model.User;
import com.projekat.XML.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    
    @Autowired
    private UserRepository userRepository;
    
    public User findUserByEmailAndPassword(User user) {
        return userRepository.findByLoginInfo_EmailAndLoginInfo_Password(user.getLoginInfo().getEmail(), user.getLoginInfo().getPassword());
    }
    
}
