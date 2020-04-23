package com.projekat.XML.controller;

import com.projekat.XML.model.EndUser;
import com.projekat.XML.model.LoginInfo;
import com.projekat.XML.model.User;
import com.projekat.XML.model.VerificationToken;
import com.projekat.XML.service.EndUserService;
import com.projekat.XML.service.MailSenderService;
import com.projekat.XML.service.VerificationTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.context.request.WebRequest;

import javax.print.attribute.standard.Media;
import java.util.List;

import java.util.UUID;


@RestController
@RequestMapping(value = "enduser")
public class EndUserController {

    @Autowired
    private EndUserService endUserService;

    @Autowired
    private MailSenderService mailSenderService;

    @Autowired
    private VerificationTokenService verificationTokenService;

    @PostMapping(value = "/register", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> register(@RequestBody EndUser endUser){
        System.out.println(endUser.getLoginInfo());

        LoginInfo loginInfo;

        loginInfo = endUserService.findByEmail(endUser.getLoginInfo().getEmail());
        if(loginInfo != null){
            return new ResponseEntity<>("email", HttpStatus.BAD_REQUEST);
        }

        loginInfo = endUserService.findByUsername(endUser.getLoginInfo().getUsername());
        if(loginInfo != null){
            return new ResponseEntity<>("username", HttpStatus.BAD_REQUEST);
        }

        User user = endUserService.findByJmbg(endUser.getJmbg());
        if(user != null){
            return new ResponseEntity<>("jmbg", HttpStatus.BAD_REQUEST);
        }

        endUser.setNumber_of_requests(0);
        endUser.setAccount_activated(false);
        endUser.setAdminApproved(false);
        endUserService.save(endUser);

        String verificationToken = UUID.randomUUID().toString();
        verificationTokenService.save(endUser, verificationToken);

        return new ResponseEntity<>("ok", HttpStatus.OK);
    }

    @GetMapping(value = "/getUnregistered", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<EndUser>> getUnregistered(){
        List<EndUser> users = endUserService.getUnregistered();

        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping(value = "/getAdminUnregistered", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<EndUser>> getAdminUnregistered(){
        List<EndUser> users = endUserService.getAdminUnregistered();

        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @PostMapping(value = "/accept", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Long> acceptRegistration(@RequestBody Long id){
        EndUser endUser = endUserService.changeAdminActivated(id);
        VerificationToken verificationToken = verificationTokenService.findByUser(endUser);

        try {
            mailSenderService.sendSimpleMessage(endUser.getLoginInfo().getEmail(), "Aktivacioni link",
                    "Vaša registracija je prihvaćena! Kliknite na link da bi aktivirali vaš nalog i koristili usluge našeg servisa!\n\n"
                            + "http://localhost:4200/registrationConfirm.html?token=" + verificationToken.getToken());
        }catch (Exception e){
            System.out.println("Slanje mail-a nije uspelo!");
        }

        return new ResponseEntity<>(id, HttpStatus.OK);
    }

    @PostMapping(value = "/reject", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Long> rejectRegistration(@RequestBody Long id){
        verificationTokenService.delete(id);
        endUserService.rejectRegistration(id);

        return new ResponseEntity<>(id, HttpStatus.OK);
    }

    @PostMapping(value = "/registrationConfirm")
    public ResponseEntity<Long> confirmRegistration(@RequestBody String token){
        System.out.println("usao je ovde");
        VerificationToken verificationToken = verificationTokenService.findByToken(token);

        if(verificationToken == null){
            return new ResponseEntity(1, HttpStatus.BAD_REQUEST);
        }

        //iz nekog razloga ne vraca nista na front, servis se nikad ne izvrsi na frontu i ne ode na homepage, vecno se zaglavi u ucitavanju

        EndUser endUser = verificationToken.getUser();
        endUserService.acceptRegistration(endUser.getId());
        verificationTokenService.delete(endUser.getId());
        return new ResponseEntity(0, HttpStatus.OK);
    }
}
