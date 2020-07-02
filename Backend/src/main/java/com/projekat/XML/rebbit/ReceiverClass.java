package com.projekat.XML.rebbit;

import com.projekat.XML.model.Advertisement;
import com.projekat.XML.model.Brand;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class ReceiverClass {
    

    @RabbitListener(queues = "spring-boot")
    public void receiveMessage(final Brand brand) {
        
        System.out.println("POGODIO JE RESIVERRAAAAAA");
        System.out.println("AAA BRENDA BRENDA JE=="+brand.getName() );
    }

    public void handleMessage(final Brand brand){
        System.out.println("POGODIO JE RESIVERRAAAAAA");
        System.out.println("AAA naziv  oglas je "+brand.getName() );
    }
}