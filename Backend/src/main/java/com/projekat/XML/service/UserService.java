package com.projekat.XML.service;

import java.security.SecureRandom;
import java.util.Base64;
import java.util.HashSet;
import java.util.List;
import java.util.Base64.Encoder;


import javax.servlet.http.HttpSession;

import com.projekat.XML.dtos.RegistrationDTO;
import com.projekat.XML.enums.UserType;
import com.projekat.XML.model.Agent;
import com.projekat.XML.model.EndUser;
import com.projekat.XML.model.EntityUser;
import com.projekat.XML.model.LoginInfo;
import com.projekat.XML.repository.AgentRepository;
import com.projekat.XML.repository.EndUserRepository;
import com.projekat.XML.repository.LoginInfoRepository;
import com.projekat.XML.repository.UserRepository;
import com.projekat.XML.security.ApplicationUserRole;
import com.projekat.XML.security.PasswordConfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import java.util.Set;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AgentRepository agentRepository;

    @Autowired
    private EndUserService endUserService;

    @Autowired
    private MailSenderService mailSenderService;

    @Autowired
    private VerificationTokenService verificationTokenService;

    @Autowired
    private LoginInfoService loginInfoService;

    @Autowired
    private EndUserRepository endUserRepository;

    @Autowired
    private LoggerService loggerService;

    @Autowired
    private SessionService sessionService;

    @Autowired
    private LoginInfoRepository loginInfoRepository;

    private final PasswordEncoder passwordEncoder=new BCryptPasswordEncoder(10);


    public void login(LoginInfo log) {


        // Sacuvati korisnika u sesiji
        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes(); 
		HttpSession session = attr.getRequest().getSession(true); 

        for (EntityUser user : userRepository.findAll()) {

            if (user.getLoginInfoId().equals(log.getId())) {
                session.setAttribute("user", user.getId());
            }

        }
    }

    public Long getLoggedUserId() {
    return sessionService.getLoggedUser().getId();

    }

    public EntityUser getLoggedUser() {
      return sessionService.getLoggedUser();
    }

    public EndUser getLoggedEndUser() {
       return sessionService.getLoggedEndUser();
    }

    public int getEndusersNumberOfAds(Long endUserId) {
        EndUser endUser = endUserRepository.findOneById(endUserId);
        if (endUser == null) {
            return 0;
        }
        return endUser.getNumberOfAds();
    }
  
  

    public List<EntityUser> getAll(){



        return userRepository.findAll();
    }


    public Long increaseEndUsersNumberOfAds() {
        EndUser endUser = getLoggedEndUser();
        endUser.setNumberOfAds(endUser.getNumberOfAds() + 1);
        endUserRepository.save(endUser);
        return endUser.getId();
    }

    public void saveInDatabase(EntityUser entity) {


        userRepository.save(entity);

    }


    public EntityUser findByUsername(String username) {

        LoginInfo login=loginInfoRepository.findByUsername(username);

        return userRepository.findByLoginInfoId(login.getId());
    }


    public void saveNewUser(RegistrationDTO registrationDTO){

        String salt=makeSalt();      

        

        LoginInfo loginInfo=new LoginInfo(
            registrationDTO.getLoginInfo().getUsername(),
        hashIt(registrationDTO.getLoginInfo().getPassword(),salt ), 
        registrationDTO.getLoginInfo().getEmail(),
        salt,
        ApplicationUserRole.ENDUSER.getGrantedAuthorities(),
        true,
        true,
        true,
        true);


        loginInfoService.save(loginInfo);

        EntityUser entityUser = new EntityUser(registrationDTO);
        entityUser.setLoginInfoId(loginInfo.getId());
        entityUser.setUserType(UserType.ENDUSER);


        // cuvanje u bazi
        saveInDatabase(entityUser);

        EndUser endUser = new EndUser();


        endUser.setNumber_of_requests(0);
        endUser.setAccount_activated(false);
        endUser.setAdminApproved(false);
        endUser.setUser(findOneByid(entityUser.getId()));
        endUser.setNumberOfAds(0);

        endUserService.save(endUser);

        // String verificationToken = UUID.randomUUID().toString();
        // verificationTokenService.save(endUser, verificationToken);
    }


   

 

    private String hashIt(String password, String salt){
        
        String passwordPlusSalt=password+salt;

        String hashedPassword=passwordEncoder.encode(passwordPlusSalt);

        return hashedPassword;
        
    }

    private String makeSalt(){
        SecureRandom random = new SecureRandom();
        byte bytes[] = new byte[20];
        random.nextBytes(bytes);
        Encoder encoder = Base64.getUrlEncoder().withoutPadding();
        String token = encoder.encodeToString(bytes);
        return token;
    }

    public void logOut(){


        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpSession session = attr.getRequest().getSession(true);
        session.invalidate();
        System.out.println("Izlogovao se");
    }



    // public void changePassword(String jmbg, String password){
    //     EntityUser user = userRepository.findByJmbg(jmbg);
    //     System.out.println(user);
    //     if(user.getUserType() == UserType.AGENT){
    //         Agent agent = agentRepository.findByJmbg(jmbg);

    //         agent.setFirst_login(false);
    //         agent.setPassword(password);

    //         agentRepository.save(agent);
    //     }else{
    //         user.setPassword(password);
    //         userRepository.save(user);
    //     }
    // }
  
    public List<EntityUser> findAll()
    {
        return userRepository.findAll();
    }

    public EntityUser findOneByid(Long id)
    {
        return userRepository.findOneByid(id);
    }

}
