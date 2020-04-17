package com.projekat.XML.repository;


import com.projekat.XML.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    public User findByLoginInfo_EmailAndLoginInfo_Password(String email, String password);

    public User findByJmbg(String jmbg);
}
