package com.projekat.XML.repository;


import com.projekat.XML.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
