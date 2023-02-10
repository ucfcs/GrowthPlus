package com.GrowthPlus.dataAccessLayer.LessonContent;

import java.util.ArrayList;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class LessonContent extends RealmObject {
    @PrimaryKey
    private String lessonContentId;
    private String word;
    private String firstNumber;
    private String firstOperator;
    private String secondNumber;
    private String secondOperator;
    private String thirdNumber;
    private String imgOne;
    private String imgTwo;
    private String imgThree;
    private String imgFour;
    private String imgFive;

    public String getLessonContentId() { return lessonContentId; }

    public void setLessonContentId(String lessonContentId) { this.lessonContentId = lessonContentId; }

    public String getWord() { return word; }

    public void setWord(String word) { this.word = word; }

    public String getFirstNumber() { return firstNumber; }

    public void setFirstNumber(String firstNumber) { this.firstNumber = firstNumber; }

    public String getFirstOperator() { return firstOperator; }

    public void setFirstOperator(String firstOperator) { this.firstOperator = firstOperator; }

    public String getSecondNumber() { return secondNumber; }

    public void setSecondNumber(String secondNumber) { this.secondNumber = secondNumber; }

    public String getSecondOperator() { return secondOperator; }

    public void setSecondOperator(String secondOperator) { this.secondOperator = secondOperator; }

    public String getThirdNumber() { return thirdNumber; }

    public void setThirdNumber(String thirdNumber) { this.thirdNumber = thirdNumber; }

    public String getImgOne() { return imgOne; }

    public void setImgOne(String imgOne) { this.imgOne = imgOne; }

    public String getImgTwo() { return imgTwo; }

    public void setImgTwo(String imgTwo) { this.imgTwo = imgTwo; }

    public String getImgThree() { return imgThree; }

    public void setImgThree(String imgThree) { this.imgThree = imgThree; }

    public String getImgFour() { return imgFour; }

    public void setImgFour(String imgFour) { this.imgFour = imgFour; }

    public String getImgFive() { return imgFive; }

    public void setImgFive(String imgFive) { this.imgFive = imgFive; }

}
