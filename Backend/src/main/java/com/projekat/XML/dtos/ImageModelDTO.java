package com.projekat.XML.dtos;

import com.projekat.XML.model.ImageModel;

public class ImageModelDTO {

    private long id;

    private String name;

    private String path;

    private byte[] picByte;

    public ImageModelDTO(String name, String path) {
        this.name = name;
        this.path = path;
    }

    public ImageModelDTO(String name, String path, byte[] picByte) {
        this.name = name;
        this.path = path;
        this.picByte = picByte;
    }

    public ImageModelDTO() {
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
