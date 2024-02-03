package com.example.progkino.Models;

import java.util.Date;

public class Eventum {
    public Eventum() {}

    long id;
    String img, color, title, dateEventum;
   // Date dateEventum;
    String type, eventumDescription;


    public Eventum(long id, String color, String dateEventum,String eventumDescription,   String img,  String title,  String type) {
        this.id = id;
        this.color = color;
        this.dateEventum = dateEventum;
        this.eventumDescription = eventumDescription;

        this.img = img;
        this.title = title;
        this.type = type;
    }

    /*public Eventum(String eventumDescription) {
        this.eventumDescription = eventumDescription;
    }*/

    public String getEventumDescription() {
        return eventumDescription;
    }

    public void setEventumDescription(String eventumDescription) {
        this.eventumDescription = eventumDescription;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDateEventum() {
        return dateEventum;
    }

    public void setDateEventum(String dateEventum) {
        this.dateEventum = dateEventum;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
