package com.projekat.XML;


import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class XmlApplication {

	public static void main(String[] args) {
		SpringApplication.run(XmlApplication.class, args);
	}


}
