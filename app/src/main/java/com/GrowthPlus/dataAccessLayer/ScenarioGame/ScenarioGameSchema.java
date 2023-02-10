package com.GrowthPlus.dataAccessLayer.ScenarioGame;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class ScenarioGameSchema extends RealmObject{
    @PrimaryKey
    private String scenarioGameId;
    private String scenarioGameName;
    private Integer minPoints;
    private Integer maxPoints;
    private String firstOperand;
    private String firstOperator;
    private String secondOperand;
    private String secondOperator;
    private String thirdOperand;

    public String getScenarioGameId() {
        return scenarioGameId;
    }

    public void setScenarioGameId(String scenarioGameId) {
        this.scenarioGameId = scenarioGameId;
    }

    public String getScenarioGameName() {
        return scenarioGameName;
    }

    public void setScenarioGameName(String scenarioGameName) {
        this.scenarioGameName = scenarioGameName;
    }

    public Integer getMinPoints() {
        return minPoints;
    }

    public void setMinPoints(Integer minPoints) {
        this.minPoints = minPoints;
    }

    public Integer getMaxPoints() {
        return maxPoints;
    }

    public void setMaxPoints(Integer maxPoints) {
        this.maxPoints = maxPoints;
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

    public String getThirdOperand() {
        return thirdOperand;
    }

    public void setThirdOperand(String thirdOperand) {
        this.thirdOperand = thirdOperand;
    }
}
