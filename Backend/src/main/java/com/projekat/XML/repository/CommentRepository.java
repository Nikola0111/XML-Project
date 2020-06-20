package com.projekat.XML.repository;

import com.projekat.XML.model.Comment;
import com.projekat.XML.model.EndUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

    Comment findOneById(Long id);
    List<Comment> findByAd_Id(Long id);
    public List<Comment> findByApproved(Boolean approved);
    void deleteByEndUser(EndUser endUser);
}
