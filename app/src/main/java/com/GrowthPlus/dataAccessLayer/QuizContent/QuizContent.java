package com.GrowthPlus.dataAccessLayer.QuizContent;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class QuizContent extends RealmObject {
    @PrimaryKey
    private String quizContentId;
    private String image;
    private String question;
    private String answerOne;
    private String answerTwo;
    private String answerThree;
    private String answerFour;
    private String answer;

    public String getQuizContentId() {
        return quizContentId;
    }

    public void setQuizContentId(String quizContentId) {
        this.quizContentId = quizContentId;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswerOne() {
        return answerOne;
    }

    public void setAnswerOne(String answerOne) {
        this.answerOne = answerOne;
    }

    public String getAnswerTwo() {
        return answerTwo;
    }

    public void setAnswerTwo(String answerTwo) {
        this.answerTwo = answerTwo;
    }

    public String getAnswerThree() {
        return answerThree;
    }

    public void setAnswerThree(String answerThree) {
        this.answerThree = answerThree;
    }

    public String getAnswerFour() {
        return answerFour;
    }

    public void setAnswerFour(String answerFour) {
        this.answerFour = answerFour;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

}
