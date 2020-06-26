package com.projekat.XML.controller;

import com.projekat.XML.dtos.RegistrationDTO;
import com.projekat.XML.model.*;
import com.projekat.XML.model.LoginInfo;

import com.projekat.XML.model.EntityUser;

import com.projekat.XML.model.VerificationToken;
import com.projekat.XML.service.EndUserService;
import com.projekat.XML.service.LoginInfoService;
import com.projekat.XML.service.MailSenderService;
import com.projekat.XML.service.UserService;
import com.projekat.XML.service.VerificationTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.context.request.WebRequest;

import javax.print.attribute.standard.Media;

import java.security.SecureRandom;
import java.util.Base64;
import java.util.Calendar;
import java.util.List;

import java.util.UUID;
import java.util.Base64.Encoder;


@RestController
@RequestMapping(value = "authentication")
public class EndUserController {

    @Autowired
    private EndUserService endUserService;

    @Autowired
    private MailSenderService mailSenderService;

    @Autowired
    private VerificationTokenService verificationTokenService;

    @Autowired
    private UserService userService;

    @Autowired
    private LoginInfoService loginInfoService;


    @PostMapping(value = "/register", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> register(@RequestBody RegistrationDTO registrationDTO){


        LoginInfo loginInfo;

        loginInfo = endUserService.findByEmail(registrationDTO.getLoginInfo().getEmail());
        if(loginInfo != null){
            return new ResponseEntity<>("email", HttpStatus.BAD_REQUEST);
        }

        loginInfo = endUserService.findByUsername(registrationDTO.getLoginInfo().getUsername());
        if(loginInfo != null){
            return new ResponseEntity<>("username", HttpStatus.BAD_REQUEST);
        }

        EntityUser user = endUserService.findByJmbg(registrationDTO.getJmbg());
        if(user != null){
            return new ResponseEntity<>("jmbg", HttpStatus.BAD_REQUEST);
        }


        userService.saveNewUser(registrationDTO);


        return new ResponseEntity<>("ok", HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('user:read')")
    @GetMapping(value = "/getUnregistered", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<EndUser>> getUnregistered(){
        List<EndUser> users = endUserService.getUnregistered();

        return new ResponseEntity<>(users, HttpStatus.OK);
    }


    @PreAuthorize("hasAuthority('user:read')")
    @GetMapping(value = "/getAdminUnregistered", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<EndUser>> getAdminUnregistered(){
        List<EndUser> users = endUserService.getAdminUnregistered();

        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('user:write')")
    @PostMapping(value = "/accept", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Long> acceptRegistration(@RequestBody Long id){
        EndUser endUser = endUserService.changeAdminActivated(id);
        VerificationToken verificationToken = verificationTokenService.findByUser(endUser);

        try {

            String email= loginInfoService.findOneById(endUser.getUser().getLoginInfoId()).getEmail();

            mailSenderService.sendSimpleMessage(email, "Aktivacioni link",
                    "Vaša registracija je prihvaćena! Kliknite na link da bi aktivirali vaš nalog i koristili usluge našeg servisa!\n\n"
                            + "https://localhost:4200/registrationConfirm.html?token=" + verificationToken.getToken());
        }catch (Exception e){
            System.out.println("Slanje mail-a nije uspelo!");
        }

        return new ResponseEntity<>(id, HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('user:write')")
    @PostMapping(value = "/reject", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Long> rejectRegistration(@RequestBody Long id){
        verificationTokenService.delete(id);
        endUserService.rejectRegistration(id);

        return new ResponseEntity<>(id, HttpStatus.OK);
    }


    @PostMapping(value = "/registrationConfirm")
    public ResponseEntity<Void> confirmRegistration(@RequestBody String token){
        System.out.println("usao je ovde");
        VerificationToken verificationToken = verificationTokenService.findByToken(token);

        if(verificationToken == null){
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }


        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, -7);
        if ((verificationToken.getExpiryDate().getTime() - cal.getTime().getTime()) <= 0) {
        
                return new ResponseEntity(0, HttpStatus.BAD_REQUEST);
            }
            
        EndUser endUser = verificationToken.getUser();
        endUserService.acceptRegistration(endUser.getId());
        verificationTokenService.delete(endUser.getId());
        return new ResponseEntity(HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('user:read')")
    @GetMapping(value = "/getRegisteredUsers", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<EndUser>> getRegisteredUsers(){
        List<EndUser> data = endUserService.getRegisteredUsers();
        return new ResponseEntity<>(data, HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('user:write')")
    @PostMapping(value = "/deactivate/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> deactivateAccount(@PathVariable("id") Long id){
        endUserService.deactivate(id);


        return new ResponseEntity<>(HttpStatus.OK);
    }
  /*

    @PostMapping(value = "/block/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Boolean> blockAccount(@PathVariable("id") Long id){
        Boolean ret = endUserService.block(id);

        return new ResponseEntity<>(ret, HttpStatus.OK);
    }

    @PostMapping(value = "/unblock/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Boolean> unblockAccount(@PathVariable("id") Long id){
        Boolean ret = endUserService.unblock(id);

        return new ResponseEntity<>(ret, HttpStatus.OK);
    } */
}
