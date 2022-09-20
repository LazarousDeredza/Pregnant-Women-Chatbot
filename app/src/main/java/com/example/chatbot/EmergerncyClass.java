package com.example.chatbot;

public class EmergerncyClass {

    String ID,content;

    public EmergerncyClass(String ID, String content) {
        this.ID = ID;
        this.content = content;
    }

    public EmergerncyClass() {
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }


}
