package com.projekat.XML.dtos;

import java.util.Date;

public class CommentPreviewDTO {

    private String comment;

    private String userMail;

    private Double grade;

    private Date date;

    public CommentPreviewDTO(String comment, String userMail, Double grade, Date date) {
        this.comment = comment;
        this.userMail = userMail;
        this.grade = grade;
        this.date = date;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getUserMail() {
        return userMail;
    }

    public void setUserMail(String userMail) {
        this.userMail = userMail;
    }

    public Double getGrade() {
        return grade;
    }

    public void setGrade(Double grade) {
        this.grade = grade;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
