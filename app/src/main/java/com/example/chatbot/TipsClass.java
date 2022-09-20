package com.example.chatbot;

public class TipsClass {

    String ID,content;

    public TipsClass(String ID, String content) {
        this.ID = ID;

        this.content = content;
    }

    public TipsClass() {
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
