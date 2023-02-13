package com.GrowthPlus.dataAccessLayer.ChildRoadMap;

import com.GrowthPlus.dataAccessLayer.RoadMapLesson.RoadMapLesson;
import com.GrowthPlus.dataAccessLayer.RoadMapQuiz.RoadMapQuiz;
import com.GrowthPlus.dataAccessLayer.RoadMapScenarioGame.RoadMapScenarioGame;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.RealmClass;

@RealmClass(embedded = true)
public class ChildRoadMap extends RealmObject {
    String roadMapName;
    Integer lessonsCompleted;
    Boolean isCurrent;
    Boolean isCompleted;
    Boolean isLocked;
    RealmList<RoadMapLesson> roadMapLessons;
    RealmList<RoadMapQuiz> roadMapQuizzes;
    RoadMapScenarioGame scenarioGame;
    String databaseRoadMapId;

    public  ChildRoadMap(){}

    public ChildRoadMap(String roadMapName,
                        Integer lessonsCompleted,
                        Boolean isCurrent,
                        Boolean isCompleted,
                        Boolean isLocked,
                        RealmList<RoadMapLesson> roadMapLessons,
                        RealmList<RoadMapQuiz> roadMapQuizzes,
                        RoadMapScenarioGame scenarioGame,
                        String databaseRoadMapId
    ){
        this.roadMapName = roadMapName;
        this.lessonsCompleted = lessonsCompleted;
        this.isCurrent = isCurrent;
        this.isCompleted = isCompleted;
        this.isLocked = isLocked;
        this.roadMapLessons = roadMapLessons;
        this.roadMapQuizzes = roadMapQuizzes;
        this.scenarioGame = scenarioGame;
        this.databaseRoadMapId = databaseRoadMapId;
    }

    public String getRoadMapName() {
        return roadMapName;
    }

    public void setRoadMapName(String roadMapName) {
        this.roadMapName = roadMapName;
    }

    public Integer getLessonsCompleted() {
        return lessonsCompleted;
    }

    public void setLessonsCompleted(Integer lessonsCompleted) {
        this.lessonsCompleted = lessonsCompleted;
    }

    public Boolean getCurrent() {
        return isCurrent;
    }

    public void setCurrent(Boolean current) {
        isCurrent = current;
    }

    public Boolean getCompleted() {
        return isCompleted;
    }

    public void setCompleted(Boolean completed) {
        isCompleted = completed;
    }

    public Boolean getLocked() {
        return isLocked;
    }

    public void setLocked(Boolean locked) {
        isLocked = locked;
    }

    public RealmList<RoadMapLesson> getRoadMapLessons() {
        return roadMapLessons;
    }

    public void setRoadMapLessons(RealmList<RoadMapLesson> roadMapLessons) {
        this.roadMapLessons = roadMapLessons;
    }

    public RealmList<RoadMapQuiz> getRoadMapQuizzes() {
        return roadMapQuizzes;
    }

    public void setRoadMapQuizzes(RealmList<RoadMapQuiz> roadMapQuizzes) {
        this.roadMapQuizzes = roadMapQuizzes;
    }

    public RoadMapScenarioGame getScenarioGame() {
        return scenarioGame;
    }

    public void setScenarioGame(RoadMapScenarioGame scenarioGame) {
        this.scenarioGame = scenarioGame;
    }
}
