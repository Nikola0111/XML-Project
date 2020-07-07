package com.projekat.XML.soapconfig;

import com.projekat.XML.service.BookingClient;
import com.projekat.XML.service.SoapClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

import com.projekat.XML.service.SoapClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

@Configuration
public class SoapBookingWsConfig {

    @Bean
    public BookingClient bookingClient(Jaxb2Marshaller marshaller) {
        BookingClient client = new BookingClient();
        client.setDefaultUri("http://localhost:8086/ws");
        client.setMarshaller(marshaller);
        client.setUnmarshaller(marshaller);
        return client;
    }
}
