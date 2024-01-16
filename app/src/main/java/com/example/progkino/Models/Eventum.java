package com.example.progkino.Models;

import java.util.Date;

public class Eventum {


    int id;
    String img, color, name;
    Date dateEventum;
    String type, eventumDescription;

    public Eventum(int id, String img, String color, String name, Date dateEventum, String type,String eventumDescription) {
        this.id = id;
        this.img = img;
        this.color = color;
        this.name = name;
        this.dateEventum = dateEventum;
        this.type = type;
        this.eventumDescription = eventumDescription;
    }

    public Eventum(String eventumDescription) {
        this.eventumDescription = eventumDescription;
    }

    public String getEventumDescription() {
        return eventumDescription;
    }

    public void setEventumDescription(String eventumDescription) {
        this.eventumDescription = eventumDescription;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDateEventum() {
        return dateEventum;
    }

    public void setDateEventum(Date dateEventum) {
        this.dateEventum = dateEventum;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
