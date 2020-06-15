package com.projekat.XML.impl;

import com.projekat.XML.interfaces.AdvertisementInterface;
import com.projekat.XML.model.Advertisement;
import com.projekat.XML.repository.AdvertisementRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class AdvertisementImpl implements AdvertisementInterface {

    @Autowired
    AdvertisementRepository advertisementRepository;

    @Override
    public Advertisement save(Advertisement advertisement) {
        return advertisementRepository.save(advertisement);
    }
}
