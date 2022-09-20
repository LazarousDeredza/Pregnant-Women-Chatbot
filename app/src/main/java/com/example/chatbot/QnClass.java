package com.example.chatbot;

public class QnClass {
    private String Question,Answer;

    public QnClass(String question, String answer) {
        Question = question;
        Answer = answer;
    }

    public QnClass() {
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

    String ans(){
        return "Answer : "+ Answer;
    }

    String qn(){
        return "Question : "+ Question;
    }

}
