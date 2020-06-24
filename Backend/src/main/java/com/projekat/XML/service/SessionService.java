package com.projekat.XML.service;

import com.projekat.XML.repository.AdministratorRepository;
import com.projekat.XML.repository.AgentRepository;
import com.projekat.XML.repository.EndUserRepository;
import com.projekat.XML.repository.LoginInfoRepository;
import com.projekat.XML.repository.UserRepository;
import com.projekat.XML.model.Agent;
import com.projekat.XML.model.EndUser;
import com.projekat.XML.model.EntityUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class SessionService {

    @Autowired
    UserRepository userRepository;

    @Autowired 
    LoginInfoRepository loginInfoRepository;

    @Autowired
    EndUserRepository endUserRepository;

    @Autowired
    AdministratorRepository administratorRepository;

    @Autowired
    AgentRepository agentRepository;
    
    
    public String getLoggedUsername(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        return username;
    }

    public String getLoggedEmail(){
        String username = getLoggedUsername();
        String email = loginInfoRepository.findByUsername(username).getEmail();

        return email;
    }
    
    public EntityUser getLoggedUser(){
        String username = getLoggedUsername();
        Long loginInfoID = loginInfoRepository.findByUsername(username).getId();

        List<EntityUser> users = userRepository.findAll();
        for(EntityUser user : users){
            if(user.getLoginInfoId() == loginInfoID){
                return user;
            }
        }
        return null;
    }

    public EndUser getLoggedEndUser(){
        Long userID = getLoggedUser().getId();

        List<EndUser> all = endUserRepository.findAll();

        for(EndUser endUser : all){
            if(endUser.getUser().getId() == userID){
                return endUser;
            }
        }
        return null;
    }

    // public Administrator getLoggedAdministrator(){
    //     Long userID = getLoggedUser().getId();

    //     List<Administrator> all = administratorRepository.findAll();

    //     for(Administrator administrator : all){
    //         if(administrator.getUser().getId() == userID){
    //             return administrator;
    //         }
    //     }
    //     return null;
    // }

    public Agent getLoggedAgent(){
        Long userID = getLoggedUser().getId();

        List<Agent> all = agentRepository.findAll();

        for(Agent agent : all){
            if(agent.getUser().getId() == userID){
                return agent;
            }
        }
        return null;
    }

}