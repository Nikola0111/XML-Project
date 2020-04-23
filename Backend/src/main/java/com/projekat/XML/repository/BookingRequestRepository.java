package com.projekat.XML.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import com.projekat.XML.model.requests.BookingRequest;




public interface BookingRequestRepository extends JpaRepository <BookingRequest,Long> {

public BookingRequest findOneByid(Long id);

public BookingRequest findTopByOrderByIdDesc();
    
}