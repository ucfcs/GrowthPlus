package com.GrowthPlus.dataAccessLayer.ScenarioGame;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class ScenarioGameSchema extends RealmObject{
    @PrimaryKey
    private String scenarioGameId;
    private Integer points;
    private String scenarioGameName;

    public String getScenarioGameId() {
        return scenarioGameId;
    }

    public void setScenarioGameId(String scenarioGameId) {
        this.scenarioGameId = scenarioGameId;
    }

    public Integer getPoints() {
        return points;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }

    public String getScenarioGameName() {
        return scenarioGameName;
    }

    public void setScenarioGameName(String scenarioGameName) {
        this.scenarioGameName = scenarioGameName;
    }
}
