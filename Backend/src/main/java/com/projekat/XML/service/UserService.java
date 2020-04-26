package com.projekat.XML.service;

import javax.servlet.http.HttpSession;

import com.projekat.XML.dtos.UserDTO;
import com.projekat.XML.enums.UserType;
import com.projekat.XML.model.Agent;
import com.projekat.XML.model.LoginInfo;
import com.projekat.XML.model.User;
import com.projekat.XML.repository.AgentRepository;
import com.projekat.XML.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Service
public class UserService {
    
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ShoppingCartService shoppingCartService;

    @Autowired
    private AgentRepository agentRepository;
    
    public User findUserByEmailAndPassword(User user) {
        return userRepository.findByLoginInfo_EmailAndLoginInfo_Password(user.getLoginInfo().getEmail(), user.getLoginInfo().getPassword());
    }
    

    public void saveUser(User userDB){

        //Sacuvati korisnika u sesiji
        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder
                .currentRequestAttributes();
        HttpSession session = attr.getRequest().getSession(true);

        session.setAttribute("user", userDB.getId());
        shoppingCartService.save(userDB.getId());

    }

    public void logOut(){

        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
		HttpSession session = attr.getRequest().getSession(true);
        session.invalidate();
        System.out.println("Izlogovao se");
    }

    public void changePassword(String jmbg, String password){
        User user = userRepository.findByJmbg(jmbg);
        System.out.println(user);
        if(user.getUserType() == UserType.AGENT){
            Agent agent = agentRepository.findByJmbg(jmbg);

            agent.setFirst_login(false);
            agent.setPassword(password);

            agentRepository.save(agent);
        }else{
            user.setPassword(password);

            userRepository.save(user);
        }


    }
}
