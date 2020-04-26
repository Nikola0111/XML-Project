package com.projekat.XML.repository;

import com.projekat.XML.model.Advertisement;
import com.projekat.XML.model.ImageModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AdvertisementRepository extends JpaRepository<Advertisement, Long> {

    public Advertisement findOneByid(Long id);
    public List<Advertisement> findAll();
}
