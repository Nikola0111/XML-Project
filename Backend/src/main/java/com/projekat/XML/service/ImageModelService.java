package com.projekat.XML.service;

import com.projekat.XML.dtos.ImageModelDTO;
import com.projekat.XML.model.ImageModel;
import com.projekat.XML.repository.ImageModelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ImageModelService {

    @Autowired
    ImageModelRepository imageModelRepository;

    public void save(ImageModelDTO imageModelDTO) {
        imageModelRepository.save(new ImageModel(imageModelDTO.getName(), imageModelDTO.getType()));
    }

    public Optional<ImageModel> findImage(String name) { return imageModelRepository.findOneByName(name);}

}
