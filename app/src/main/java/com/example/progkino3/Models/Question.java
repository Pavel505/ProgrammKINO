package com.example.progkino3.Models;

public class Question {

    public Question() {}

    int id, nominal;
    String userEmail, category, content,answer,comment,sources,author,tema;

    public Question(int id, String category, String content, String answer, String comment, String sources, String author, int nominal,
    String tema) {
        this.id = id;
        this.category = category;
        this.content = content;
        this.answer = answer;
        this.comment = comment;
        this.sources = sources;
        this.author = author;
        this.nominal = nominal;
        this.tema = tema;
    }
    public String getTema() {
        return tema;
    }

    public void setTema(String tema) {
        this.tema = tema;
    }
    public int getNominal() {
        return nominal;
    }

    public void setNominal(int nominal) {
        this.nominal = nominal;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getSources() {
        return sources;
    }

    public void setSources(String sources) {
        this.sources = sources;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
