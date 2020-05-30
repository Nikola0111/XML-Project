package com.projekat.XML.repository;




import com.projekat.XML.model.KeyPairClass;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface KeyPairClassRepository extends  JpaRepository<KeyPairClass, Long>  {
    

    public KeyPairClass findOneById(Long id);
}