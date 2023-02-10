package com.GrowthPlus.dataAccessLayer.Flashcard;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class FlashcardSchema extends RealmObject {
    @PrimaryKey
    private String flashCardId;
    private String flashCardName;
    private String question;
    private String firstNumber;
    private String firstOperator;
    private String secondNumber;
    private String secondOperator;
    private String thirdNumber;

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

    public String getFirstNumber() {
        return firstNumber;
    }

    public void setFirstNumber(String firstNumber) {
        this.firstNumber = firstNumber;
    }

    public String getFirstOperator() {
        return firstOperator;
    }

    public void setFirstOperator(String firstOperator) {
        this.firstOperator = firstOperator;
    }

    public String getSecondNumber() {
        return secondNumber;
    }

    public void setSecondNumber(String secondNumber) {
        this.secondNumber = secondNumber;
    }

    public String getSecondOperator() {
        return secondOperator;
    }

    public void setSecondOperator(String secondOperator) {
        this.secondOperator = secondOperator;
    }

    public String getThirdNumber() {
        return thirdNumber;
    }

    public void setThirdNumber(String thirdNumber) {
        this.thirdNumber = thirdNumber;
    }
}
