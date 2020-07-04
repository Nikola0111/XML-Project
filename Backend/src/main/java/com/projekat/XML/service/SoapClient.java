package com.projekat.XML.service;

import com.projekat.XML.dtos.AdvertisementCreationDTO;
import com.projekat.XML.dtos.AdvertisementSoapDTO;
import com.projekat.XML.model.*;
import com.projekat.XML.repository.*;
import com.soap.SaveAdvertisement.Advertisement;
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

    @Autowired
    private ModelRepository modelRepository;

    @Autowired
    private BrandRepository brandRepository;

    @Autowired
    private CarClassRepository carClassRepository;

    @Autowired
    private FuelTypeRepository fuelTypeRepository;

    @Autowired
    private TransmissionTypeRepository transmissionTypeRepository;

    @Autowired
    private UserService userService;

    private WebServiceTemplate template;


    public GetAdvertisementResponse saveAdvertisement(AdvertisementCreationDTO adv){
        GetAdvertisementRequest request = new GetAdvertisementRequest();

        Model model = modelRepository.findByName(adv.getModel());
        Brand brand = brandRepository.findByName(adv.getBrand());

        CarClass carClass = carClassRepository.findByName(adv.getCarClass());
        FuelType fuelType = fuelTypeRepository.findByName(adv.getFuelType());
        TransmissionType transmissionType = transmissionTypeRepository
                .findByName(adv.getTransType());

        System.out.println(model);
        System.out.println(brand);
        System.out.println(carClass);
        System.out.println(fuelType);
        System.out.println(transmissionType);

        Advertisement temp = new Advertisement();
        Advertisement.CarClass carClass1 = new Advertisement.CarClass();
        Advertisement.Brand brand1 = new Advertisement.Brand();
        Advertisement.TransType transType = new Advertisement.TransType();
        Advertisement.Model model1 = new Advertisement.Model();
        Advertisement.FuelType fuelType1 = new Advertisement.FuelType();

        carClass1.setId(carClass.getID());
        model1.setId(model.getID());
        fuelType1.setId(fuelType.getID());
        brand1.setId(brand.getID());
        transType.setId(transmissionType.getID());

        temp.setCarClass(carClass1);
        temp.setFuelType(fuelType1);
        temp.setModel(model1);
        temp.setBrand(brand1);
        temp.setTransType(transType);
        temp.setUserID(userService.getLoggedUserId());
        request.setAdvertisement(temp);

        request.getAdvertisement().setTravelled(adv.getTravelled());
        request.getAdvertisement().setName(adv.getName());
        request.getAdvertisement().setCarSeats(adv.getCarSeats());
        request.getAdvertisement().setPrice(adv.getPrice());

        template = new WebServiceTemplate(marshaller);
        GetAdvertisementResponse ret = (GetAdvertisementResponse) template.marshalSendAndReceive("http://localhost:8085/ws/saveAdvertisementSoap", request,
                new SoapActionCallback("http://com.Advertisement/JavaGeneratedFiles/getAdvertisementRequest"));
        System.out.println("The advertisement is here: " + ret.getName());
        return ret;
    }

//    public GetAdvertisementResponse saveSoapAdvertisement(GetAdvertisementRequest request){
//        template = new WebServiceTemplate(marshaller);
//        GetAdvertisementResponse ret = (GetAdvertisementResponse) template.marshalSendAndReceive("http://localhost:8085/ws/saveAdvertisementSoap", request,
//                new SoapActionCallback("http://com.Advertisement/JavaGeneratedFiles/getAdvertisementRequest"));
//        System.out.println("The advertisement is here: " + ret.getName());
//        return ret;
//    }
}
