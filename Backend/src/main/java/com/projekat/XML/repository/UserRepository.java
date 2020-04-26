package com.projekat.XML.repository;


import com.projekat.XML.model.User;
import com.projekat.XML.model.VerificationToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    public User findByLoginInfo_EmailAndLoginInfo_Password(String email, String password);

    public User findByJmbg(String jmbg);


    public  User findOneByid(Long id);

    public User findByLoginInfo_Email(String email);

    public User findByLoginInfo_Username(String username);



}
