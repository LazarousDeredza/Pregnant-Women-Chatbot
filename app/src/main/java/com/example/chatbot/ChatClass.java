package com.example.chatbot;

public class ChatClass {
    String question;
    String answer;
    String date;

    public ChatClass(String question, String answer, String date) {
        this.question = question;
        this.answer = answer;
        this.date = date;
    }

    public ChatClass() {
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String toString(){
        return "Date : "+this.date +"                       " +"Question : "+ question ;
    }
    public String AIanswer(){
        return "AI Answer :                                                                         " +answer;
    }
}
