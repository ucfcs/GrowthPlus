package com.fall2022_group20.dataAccessLayer.ScenarioGame;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class ScenarioGameSchema extends RealmObject{
    @PrimaryKey
    private Integer scenarioGameId;
    private Integer points;
    private String scenarioGameName;

    public Integer getScenarioGameId() {
        return scenarioGameId;
    }

    public void setScenarioGameId(Integer scenarioGameId) {
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
