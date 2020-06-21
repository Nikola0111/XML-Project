package com.projekat.XML.service;

import com.soap.SaveAdvertisement.GetAdvertisementRequest;
import com.soap.SaveAdvertisement.GetAdvertisementResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.ws.client.core.WebServiceTemplate;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.client.core.SoapActionCallback;

public class SoapClient extends WebServiceGatewaySupport {

    @Autowired
    private Jaxb2Marshaller marshaller;

    private WebServiceTemplate template;


    public GetAdvertisementResponse saveAdvertisement(GetAdvertisementRequest request){
        template = new WebServiceTemplate(marshaller);
        GetAdvertisementResponse ret = (GetAdvertisementResponse) template.marshalSendAndReceive("http://localhost:8085/ws/saveAdvertisementSoap", request,
                new SoapActionCallback("http://com.Advertisement/JavaGeneratedFiles/getAdvertisementRequest"));
        System.out.println("The advertisement is here: " + ret.getName());
        return ret;
    }
}
