package com.projekat.XML;

import com.projekat.XML.model.Administrator;
import com.projekat.XML.model.LoginInfo;
import com.projekat.XML.model.EntityUser;
import com.projekat.XML.repository.AdministratorRepository;
import com.projekat.XML.repository.LoginInfoRepository;
import com.projekat.XML.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class XmlApplication {

	public static void main(String[] args) {
		SpringApplication.run(XmlApplication.class, args);
	}

}
