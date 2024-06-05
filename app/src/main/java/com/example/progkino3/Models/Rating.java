package com.example.progkino3.Models;

public class Rating {

    public Rating(){}

    String answerYes,answerNo,tema_test,userName;

    public Rating(String answerYes, String answerNo,String tema_test,String userName){
        this.answerYes = answerYes;
        this.answerNo = answerNo;
        this.tema_test = tema_test;
        this.userName = userName;
    }

    public void setAnswerYes(String answerYes) {this.answerYes = answerYes;}
    public String getAnswerYes(){return answerYes;}

    public void setAnswerNo(String answerNo) {this.answerNo = answerNo;}
    public String getAnswerNo(){return answerNo;}
    public void setAnswerTema_test(String tema_test) {this.tema_test = tema_test;}
    public String getAnswerTema_test(){return tema_test;}
    public void setUserName(String userName) {this.userName = userName;}
    public String getUserName(){return userName;}

}
