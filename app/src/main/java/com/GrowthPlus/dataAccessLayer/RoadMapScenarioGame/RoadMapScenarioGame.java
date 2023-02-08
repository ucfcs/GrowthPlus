package com.GrowthPlus.dataAccessLayer.RoadMapScenarioGame;

import io.realm.RealmObject;
import io.realm.annotations.RealmClass;

@RealmClass(embedded = true)
public class RoadMapScenarioGame extends RealmObject {

    String gameName;
    String image;
    Integer maxScore;
    Integer minScore;
    Boolean isCompleted;

    public RoadMapScenarioGame() {
    }

    public RoadMapScenarioGame(String gameName, String image, Integer maxScore, Integer minScore, Boolean isCompleted) {
        this.gameName = gameName;
        this.image = image;
        this.maxScore = maxScore;
        this.minScore = minScore;
        this.isCompleted = isCompleted;
    }

    public String getGameName() {
        return gameName;
    }

    public void setGameName(String gameName) {
        this.gameName = gameName;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Integer getMaxScore() {
        return maxScore;
    }

    public void setMaxScore(Integer maxScore) {
        this.maxScore = maxScore;
    }

    public Integer getMinScore() {
        return minScore;
    }

    public void setMinScore(Integer minScore) {
        this.minScore = minScore;
    }

    public Boolean getCompleted() {
        return isCompleted;
    }

    public void setCompleted(Boolean completed) {
        isCompleted = completed;
    }
}
