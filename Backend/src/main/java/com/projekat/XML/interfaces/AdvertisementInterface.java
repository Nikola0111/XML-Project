package com.projekat.XML.interfaces;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import com.projekat.XML.model.Advertisement;

@WebService(name = "Advertisement",
            targetNamespace = "com.projekat.XML.interfaces")
@SOAPBinding(style = SOAPBinding.Style.RPC, use = SOAPBinding.Use.LITERAL)
public interface AdvertisementInterface {

    @WebMethod(operationName = "save")
    public Advertisement save(@WebParam(name = "advertisement") Advertisement advertisement);

}
