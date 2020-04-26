package com.projekat.XML.service;

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

import javax.servlet.http.HttpSession;
import java.util.Optional;

@Service
public class AgentService {

    @Autowired
    private AgentRepository agentRepository;

    @Autowired
    private UserRepository userRepository;

    public int save(UserDTO userDTO) {
        User user;

        user = userRepository.findByLoginInfo_Username(userDTO.getUsername());

        if(user != null){
            return 1;
        }

        user = userRepository.findByLoginInfo_Email(userDTO.getEmail());

        if(user != null){
            return 2;
        }

        user = userRepository.findByJmbg(userDTO.getJmbg());

        if(user != null){
            return 3;
        }

        LoginInfo loginInfo = new LoginInfo(userDTO.getEmail(), userDTO.getUsername(), userDTO.getPassword());

        Agent agent = new Agent(userDTO.getName(), userDTO.getSurname(), loginInfo, userDTO.getJmbg(),
                userDTO.getPhone(), UserType.AGENT, 0, true);

        agentRepository.save(agent);

        return 0;
    }

    public boolean checkPasswordChanged(){
        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder
                .currentRequestAttributes();
        HttpSession session = attr.getRequest().getSession(true);
        Long id = (Long) session.getAttribute("user");

        Optional opt = agentRepository.findById(id);
        if(opt.isPresent()){
            Agent agent = (Agent) opt.get();
            return agent.isFirst_login() ? false : true;
        }

        return false;
    }
}
