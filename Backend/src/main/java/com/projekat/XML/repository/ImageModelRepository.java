package com.projekat.XML.repository;

import com.projekat.XML.model.ImageModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ImageModelRepository extends JpaRepository<ImageModel, Long> {

    public ImageModel findOneByid(Long id);

    public Optional<ImageModel> findOneByName(String name);

    public List<ImageModel> findAll();
}
