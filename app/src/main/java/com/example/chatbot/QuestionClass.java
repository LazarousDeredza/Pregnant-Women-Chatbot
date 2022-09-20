package com.example.chatbot;

public class QuestionClass {

    String ID,Question,Answer;

    public QuestionClass() {
    }

    public QuestionClass(String ID, String question, String answer) {
        this.ID = ID;
        this.Question = question;
        this.Answer = answer;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getQuestion() {
        return Question;
    }

    public void setQuestion(String question) {
        Question = question;
    }

    public String getAnswer() {
        return Answer;
    }

    public void setAnswer(String answer) {
        Answer = answer;
    }
}
