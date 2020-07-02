package com.projekat.XML.rebbit;

import com.projekat.XML.model.Advertisement;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
public class SenderClass {
  
    private  RabbitTemplate rabbitTemplate=new RabbitTemplate();

    public SenderClass(final RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public SenderClass(){
        
    }

    public void sendMessage() {
       
        Advertisement advert=new Advertisement();
        advert.setName("IMEEE LEPO IME");

        rabbitTemplate.convertAndSend(Config.getT,"foo.bar.poruka", advert);
    }
    
}