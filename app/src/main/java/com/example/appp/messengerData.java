package com.example.appp;


public class messengerData {
    private String Message;
    private String user;
    public messengerData(){}
    public messengerData(String message, String user) {
        Message = message;
        this.user = user;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String message) {
        Message = message;
    }
}
