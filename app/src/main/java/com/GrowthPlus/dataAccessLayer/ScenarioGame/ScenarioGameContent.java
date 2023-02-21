package com.GrowthPlus.dataAccessLayer.ScenarioGame;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class ScenarioGameContent extends RealmObject {
    @PrimaryKey
    private String idGameContent;
    private String question;
    private String optionOne;
    private String optionTwo;
    private String optionThree;
    private String answer;

    public String getIdGameContent() {
        return idGameContent;
    }

    public void setIdGameContent(String idGameContent) {
        this.idGameContent = idGameContent;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getOptionOne() {
        return optionOne;
    }

    public void setOptionOne(String optionOne) {
        this.optionOne = optionOne;
    }

    public String getOptionTwo() {
        return optionTwo;
    }

    public void setOptionTwo(String optionTwo) {
        this.optionTwo = optionTwo;
    }

    public String getOptionThree() {
        return optionThree;
    }

    public void setOptionThree(String optionThree) {
        this.optionThree = optionThree;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

}
