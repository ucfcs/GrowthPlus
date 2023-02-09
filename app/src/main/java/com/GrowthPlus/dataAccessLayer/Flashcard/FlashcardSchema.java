package com.GrowthPlus.dataAccessLayer.Flashcard;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class FlashcardSchema extends RealmObject {
    @PrimaryKey
    private String flashCardId;
    private String flashCardName;
    private String question;
    private String firstOperand;
    private String firstOperator;
    private String secondOperand;
    private String secondOperator;
    private String answer;

    public String getFlashCardId() {
        return flashCardId;
    }

    public void setFlashCardId(String flashCardId) {
        this.flashCardId = flashCardId;
    }

    public String getFlashCardName() {
        return flashCardName;
    }

    public void setFlashCardName(String flashCardName) {
        this.flashCardName = flashCardName;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getFirstOperand() {
        return firstOperand;
    }

    public void setFirstOperand(String firstOperand) {
        this.firstOperand = firstOperand;
    }

    public String getFirstOperator() {
        return firstOperator;
    }

    public void setFirstOperator(String firstOperator) {
        this.firstOperator = firstOperator;
    }

    public String getSecondOperand() {
        return secondOperand;
    }

    public void setSecondOperand(String secondOperand) {
        this.secondOperand = secondOperand;
    }

    public String getSecondOperator() {
        return secondOperator;
    }

    public void setSecondOperator(String secondOperator) {
        this.secondOperator = secondOperator;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
}
