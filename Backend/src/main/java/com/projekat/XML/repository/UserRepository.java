package com.projekat.XML.repository;


import com.projekat.XML.model.EntityUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<EntityUser, Long> {

  

    public EntityUser findByJmbg(String jmbg);


    public  EntityUser findOneByid(Long id);

   public EntityUser findByLoginInfoId(Long loginInfoId);



}
