package com.example.progkino3.Models;

public class QuestionTrenTutor {

    public QuestionTrenTutor() {}

    int id_quest;
    String capital, country, imgUrl;

    public QuestionTrenTutor(int id_quest, String capital, String country, String imgUrl) {
        this.id_quest = id_quest;
        this.capital = capital;
        this.country = country;
        this.imgUrl = imgUrl;
    }

    public int getId() {
        return id_quest;
    }

    public void setId(int id_quest) {
        this.id_quest = id_quest;
    }

    public String getCapital() {
        return capital;
    }

    public void setCapital(String capital) {
        this.capital = capital;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }
}
