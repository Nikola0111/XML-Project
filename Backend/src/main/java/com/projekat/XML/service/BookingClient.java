package com.projekat.XML.service;

import com.projekat.XML.model.requests.BookingRequest;
import com.soap.SaveAdvertisement.GetBookingRequest;
import com.soap.SaveAdvertisement.GetBookingResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.ws.client.core.WebServiceTemplate;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.client.core.SoapActionCallback;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;

public class BookingClient extends WebServiceGatewaySupport {

    private WebServiceTemplate template;

    @Autowired
    private Jaxb2Marshaller marshaller;

    public GetBookingResponse saveSoapBooking(BookingRequest booking) throws DatatypeConfigurationException {
        GetBookingResponse ret = new GetBookingResponse();

        try {
            GetBookingRequest request = new GetBookingRequest();
            System.out.println("Id je ovde" + booking.getUserForId());

            request.setUserFromID(booking.getUserForId());
            request.setUserToID(booking.getUserToId());
            request.setAdvertisementIdInMonolith(booking.getAdvertisement().getId());
            request.setTimeFrom(DatatypeFactory.newInstance().newXMLGregorianCalendar(booking.getTimeFrom().toString()));
            request.setTimeTo(DatatypeFactory.newInstance().newXMLGregorianCalendar(booking.getTimeTo().toString()));
            request.setGroupID(booking.getGroupId());
            request.setTogether(booking.isTogether());

            template = new WebServiceTemplate(marshaller);
            ret = (GetBookingResponse) template.marshalSendAndReceive("http://localhost:8086/ws/saveSoapBooking", request,
                    new SoapActionCallback("http://com.Booking/JavaGeneratedFiles/getBookingRequest"));
        }catch (Exception e){
            System.out.println("sacuvan!");
        }
        return ret;
    }
}
