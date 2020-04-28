package com.projekat.XML.repository;

import com.projekat.XML.model.EndUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EndUserRepository extends JpaRepository<EndUser, Long> {

    public List<EndUser> findByActivity(boolean activity);

    public List<EndUser> findByAdminApproved(boolean admin);

    public EndUser findByJmbg(String jmbg);

    public List<EndUser> findAllByActivity(boolean act);

    public Integer deleteByJmbg(String jmbg);
}
