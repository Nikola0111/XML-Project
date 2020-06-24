package com.projekat.XML.service;

import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.*;

import javax.servlet.http.HttpSession;

import com.projekat.XML.dtos.MessageDTO;
import com.projekat.XML.dtos.UserDTO;
import com.projekat.XML.dtos.UserMessageDTO;
import com.projekat.XML.enums.RequestStates;
import com.projekat.XML.model.*;
import com.projekat.XML.model.requests.BookingRequest;
import com.projekat.XML.repository.MessageRepository;

@Service
public class MessageService {

    @Autowired
    MessageRepository messageRepository;

    @Autowired
    UserService userService;

    @Autowired
    BookingRequestService bookingRequestService;

    @Autowired
    LoggerService loggerService;

    @Autowired
    SessionService sessionService;

    @Autowired
    LoginInfoService loginInfoService;

    public List<Message> getConversation(Long id) {
        List<Message> allMessages = messageRepository.findAll();
        List<Message> filteredMessages = new ArrayList<Message>();

        for (Message message : allMessages) {
            if (message.getSender().getId() == id && message.getReceiver().getId() == getLogedUserId()) {
                filteredMessages.add(message);
            }

            if (message.getSender().getId() == getLogedUserId() && message.getReceiver().getId() == id) {
                filteredMessages.add(message);
            }
        }

         Collections.sort(filteredMessages, new Comparator<Message>() {
             public int compare(Message m1, Message m2) {
                return m1.getTimeSent().compareTo(m2.getTimeSent());
           }
         });

        return filteredMessages;
    }

    public int newMessage(MessageDTO messageDTO) {
       
        System.out.println(messageDTO.email + " "  + messageDTO.text);

        if (getLoggedUser() == null) {
            return 0;
        }

        Message message = new Message();
        message.setSender(getLoggedUser());
        

        List<EntityUser> allUsers = userService.findAll();
        
        if(messageDTO.getEmail() != null && !messageDTO.getEmail().equals("")){
            String email="";

            for (EntityUser user : allUsers) {
                email=loginInfoService.findOneById(user.getLoginInfoId()).getEmail();

                if (email.equals(messageDTO.getEmail())) {
                    
                    message.setReceiver(user);
                }
            }
        }
        else{
            message.setReceiver(userService.findOneByid(messageDTO.getReceiverID()));
        }
        
        
        message.setText(messageDTO.text);
        message.setTimeSent(LocalDateTime.now());

        String email=loginInfoService.findOneById(message.getReceiver().getLoginInfoId()).getEmail();

        messageRepository.save(message);
        loggerService.doLog("12", "Tekst: " + message.getText() + " Primalac: " + email, "INFO");

        return 1;
    }

    
    public List<EntityUser> getAllUsers()
    {
        return userService.findAll();
    }

    public List<UserMessageDTO> getInboxUsers()
    {
        List<Message> allMessages = messageRepository.findAll();
        List<Message> filteredMessages = new ArrayList<Message>();
        List<EntityUser> users = new ArrayList<EntityUser>();
        List<UserMessageDTO> returnUsers=new ArrayList<UserMessageDTO>();
        Long loggedID = getLogedUserId();

        for (Message message : allMessages) {
            if (message.getReceiver().getId() == loggedID) {
                filteredMessages.add(message);
            }

            if (message.getSender().getId() == loggedID) {
                filteredMessages.add(message);
            }
        }

        for(Message message : filteredMessages)
        {
            if(!users.contains(message.getReceiver()) && message.getReceiver().getId() != loggedID)
            {
                users.add(message.getReceiver());
            }

            if(!users.contains(message.getSender()) && message.getSender().getId() != loggedID)
            {
                users.add(message.getSender());
            }
        }


        for (EntityUser entityUser : users) {
            LoginInfo login= loginInfoService.findOneById(entityUser.getLoginInfoId());

            returnUsers.add(new UserMessageDTO(entityUser.getId(),entityUser.getName(), entityUser.getSurname(),
             login, entityUser.getJmbg(), entityUser.getPhoneNumber()));

        }

        return returnUsers;  

    }

    public List<UserMessageDTO> getAllMessagableUsers()
    {

        List<EntityUser> messagableUsers = new ArrayList<EntityUser>();
        List<BookingRequest> bookingRequests = bookingRequestService.findAll();
        List<UserMessageDTO> returnUsers=new ArrayList<UserMessageDTO>();
        Long loggedID = getLogedUserId();

        for(BookingRequest bookingRequest : bookingRequests)
        {
            if(bookingRequest.getUserForId() == loggedID && bookingRequest.getStateOfRequest() == RequestStates.RESERVED)
            {
                if(!messagableUsers.contains(userService.findOneByid(bookingRequest.getUserToId())))
                {
                    messagableUsers.add(userService.findOneByid(bookingRequest.getUserToId()));
                }
            }

            if(bookingRequest.getUserToId() == loggedID && bookingRequest.getStateOfRequest() == RequestStates.RESERVED)
            {
                if(!messagableUsers.contains(userService.findOneByid(bookingRequest.getUserForId())))
                {
                    messagableUsers.add(userService.findOneByid(bookingRequest.getUserForId()));
                }
            }
        }

        for (EntityUser entityUser : messagableUsers) {
            LoginInfo login= loginInfoService.findOneById(entityUser.getLoginInfoId());

            returnUsers.add(new UserMessageDTO(entityUser.getId(),entityUser.getName(), entityUser.getSurname(),
             login, entityUser.getJmbg(), entityUser.getPhoneNumber()));

        }

        return returnUsers;  
    }

    public Long getLogedUserId(){
        return sessionService.getLoggedUser().getId();
    }
    

    private EntityUser getLoggedUser() {
      

        return sessionService.getLoggedUser();
    }

    

}