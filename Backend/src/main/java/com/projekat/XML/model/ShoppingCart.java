package com.projekat.XML.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity
public class ShoppingCart  {


    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

   
    private Long userId;
    
    
    private ArrayList<Long> advList;


    public ShoppingCart(){
        
    }

    public ShoppingCart( Long userId, ArrayList<Long> advList) {
        this.userId =userId;
        this.advList = advList;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return this.userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public List<Long> getAdvList() {
        return this.advList;
    }

    public void setAdvList(ArrayList<Long> advList) {
        this.advList = advList;
    }

    public void addOneAdv(Long adv){
        this.advList.add(adv);
    }

   

}