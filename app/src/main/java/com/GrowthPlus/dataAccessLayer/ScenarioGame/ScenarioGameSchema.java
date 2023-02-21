package com.GrowthPlus.dataAccessLayer.ScenarioGame;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class ScenarioGameSchema extends RealmObject{
    @PrimaryKey
    private String scenarioGameId;
    private String scenarioGameName;
    private String image;
    private Integer minPoints;
    private Integer maxPoints;
    private RealmList<ScenarioGameContent> questions;

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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
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

    public RealmList<ScenarioGameContent> getQuestions() {
        return questions;
    }

    public void setQuestions(RealmList<ScenarioGameContent> questions) {
        this.questions = questions;
    }
}
