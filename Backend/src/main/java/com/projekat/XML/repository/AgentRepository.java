package com.projekat.XML.repository;

import com.projekat.XML.model.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AgentRepository extends JpaRepository<Agent, Long> {
    
    public Agent findByUser(EntityUser entityUser);
    public Agent findOneById(Long id);
    public Agent findByUser_LoginInfo_Email(String email);
}
