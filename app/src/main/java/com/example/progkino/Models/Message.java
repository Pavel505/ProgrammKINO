package com.example.progkino.Models;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Message {
    public String userName;
    public String textMessage;
    private long messageTime;

    public Message(){}
    public Message(String userName, String textMessage){
        this.messageTime = new Date().getTime();
        //Date date = new Date();
        //SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //String strDate = formatter.format(date);
        //this.messageTime = strDate.tolong;
        this.textMessage = textMessage;
        this.userName = userName;
    }

    public String getuserName() {
        return userName;
    }
    public void setuserName(String userName) {
        this.userName = userName;
    }
    public String gettextMessage() {
        return textMessage;
    }
    public void settextMessage(String textMessage) {
        this.textMessage = textMessage;
    }
    public Long getmessageTime() {
        return messageTime;
    }

    public void setmessageTime(Long messageTime) {
        this.messageTime = messageTime;
    }

}
