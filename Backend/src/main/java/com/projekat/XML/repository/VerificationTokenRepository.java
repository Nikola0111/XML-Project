package com.projekat.XML.repository;

import com.projekat.XML.model.EndUser;
import com.projekat.XML.model.VerificationToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface VerificationTokenRepository extends JpaRepository<VerificationToken, Long> {

    VerificationToken save(VerificationToken verificationToken);

    VerificationToken findByUser(EndUser endUser);

    VerificationToken findByToken(String token);

    void deleteByUser(EndUser endUser);
}
