package com.projekat.XML.endpoints;

import com.projekat.XML.service.AdvertisementService;
import com.projekat.xml.javageneratedfiles.GetAdvertisementResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
public class AdvertisementEndpoint {
    private static final String NAMESPACE_URI = "http://XML.projekat.com/JavaGeneratedFiles";

    @Autowired
    public AdvertisementService advertisementService;

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getAdvertisementResponse")
    @ResponsePayload
    public void setAdvertisement(@RequestPayload GetAdvertisementResponse response){
        advertisementService.saveSoapAdvertisement(response);
    }
}
