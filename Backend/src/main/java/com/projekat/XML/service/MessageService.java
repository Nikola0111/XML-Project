package com.projekat.XML.service;

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
import com.projekat.XML.enums.RequestStates;
import com.projekat.XML.model.Message;
import com.projekat.XML.model.User;
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
        

        List<User> allUsers = userService.findAll();
        
        if(messageDTO.getEmail() != null && !messageDTO.getEmail().equals("")){
            for (User user : allUsers) {
                if (user.getLoginInfo().getEmail().equals(messageDTO.getEmail())) {
                    
                    message.setReceiver(user);
                }
            }
        }
        else{
            message.setReceiver(userService.findOneByid(messageDTO.getReceiverID()));
        }
        
        
        message.setText(messageDTO.text);
        message.setTimeSent(LocalDateTime.now());

        messageRepository.save(message);

        return 1;
    }

    
    public List<User> getAllUsers()
    {
        return userService.findAll();
    }

    public List<User> getInboxUsers()
    {
        List<Message> allMessages = messageRepository.findAll();
        List<Message> filteredMessages = new ArrayList<Message>();
        List<User> users = new ArrayList<User>();
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

        //return users;         OVAKO KASNIJE
        return userService.findAll();
    }

    public List<User> getAllMessagableUsers()
    {
        List<User> allUsers = userService.findAll();
        List<User> messagableUsers = new ArrayList<User>();
        List<BookingRequest> bookingRequests = bookingRequestService.findAll();
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

        return messagableUsers;
    }

    private Long getLogedUserId() {
        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpSession session = attr.getRequest().getSession(true);

        Long id = (Long) session.getAttribute("user");
        return id;
    }

    private User getLoggedUser() {
        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpSession session = attr.getRequest().getSession(true);

        Long id = (Long) session.getAttribute("user");
        User user = userService.findOneByid(id);

        return user;
    }

    

}