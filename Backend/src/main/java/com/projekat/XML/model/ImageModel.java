package com.projekat.XML.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name = "imageModel")
public class ImageModel implements java.io.Serializable {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long id;

    private String name;

    private String path;

    private byte[] picByte;

    public ImageModel(String name, String path) {
        this.name = name;
        this.path = path;
    }

    public ImageModel(String name, String path, byte[] picByte) {
        this.name = name;
        this.path = path;
        this.picByte = picByte;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return path;
    }

    public void setType(String path) {
        this.path = path;
    }

    public byte[] getPicByte() {
        return picByte;
    }

    public void setPicByte(byte[] picByte) {
        this.picByte = picByte;
    }
}
