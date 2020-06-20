package com.projekat.XML.service;

import com.projekat.XML.model.Agent;
import com.projekat.XML.model.EndUser;
import com.projekat.XML.model.EntityUser;
import com.projekat.XML.model.LoginInfo;
import com.projekat.XML.repository.*;
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

    @Autowired
    private ShoppingCartService shoppingCartService;

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private AgentRepository agentRepository;

    @Autowired
    private ReplyRepository replyRepository;

    public void save(EndUser endUser) {

        endUserRepository.save(endUser);
  

        shoppingCartService.save(endUser.getEntityUser().getId());
    }

    public LoginInfo findByEmail(String email) {
        return loginInfoRepository.findByEmail(email);
    }

    public LoginInfo findByUsername(String username) {
        return loginInfoRepository.findByUsername(username);
    }

    public EntityUser findByJmbg(String jmbg) {
        return userRepository.findByJmbg(jmbg);
    }

    public List<EndUser> getUnregistered() {
        return endUserRepository.findByActivity(false);
    }

    public List<EndUser> getAdminUnregistered() {
        return endUserRepository.findByAdminApproved(false);
    }

    public EndUser findById(Long id) {
        return endUserRepository.findById(id).get();
    }

    @Transactional
    public EndUser acceptRegistration(Long id) {
        Optional opt = endUserRepository.findById(id);

        if (opt.isPresent()) {
            EndUser endUser = (EndUser) opt.get();

            endUser.setAccount_activated(true);
            endUser.setBlocked(false);
            endUserRepository.save(endUser);

            return endUser;
        }

        return null;
    }

    @Transactional
    public EndUser changeAdminActivated(Long id) {
        Optional opt = endUserRepository.findById(id);

        if (opt.isPresent()) {
            EndUser endUser = (EndUser) opt.get();

            endUser.setAdminApproved(true);
            endUserRepository.save(endUser);

            return endUser;
        }

        return null;
    }

    @Transactional
    public void rejectRegistration(Long id) {
        Optional opt = endUserRepository.findById(id);

        if (opt.isPresent()) {
            endUserRepository.delete((EndUser) opt.get());
        }
    }

    public List<EndUser> getRegisteredUsers(){
        return endUserRepository.findAllByActivity(true);
    }

    @Transactional
    public void deactivate(Long id){
        EndUser endUser = endUserRepository.findOneById(id);
        System.out.println("Id usera:" + id);
        if(endUser != null){
            commentRepository.deleteByEndUser(endUser);
            endUserRepository.delete(endUser);
        }
    }

    @Transactional
    public Boolean block(Long id){
        EndUser endUser = endUserRepository.findOneById(id);

        if(endUser == null){
            return false;
        }

        endUser.setBlocked(true);

        endUserRepository.save(endUser);

        return true;
    }

    @Transactional
    public Boolean unblock(Long id){
        EndUser endUser = endUserRepository.findOneById(id);

        if(endUser == null){
            return false;
        }

        endUser.setBlocked(false);
        endUserRepository.save(endUser);

        return true;
    }
}
