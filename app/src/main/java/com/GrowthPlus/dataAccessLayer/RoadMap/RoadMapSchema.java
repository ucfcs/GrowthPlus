package com.GrowthPlus.dataAccessLayer.RoadMap;

import com.GrowthPlus.dataAccessLayer.Lesson.LessonSchema;
import com.GrowthPlus.dataAccessLayer.Quiz.QuizSchema;
import com.GrowthPlus.dataAccessLayer.ScenarioGame.ScenarioGameSchema;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class RoadMapSchema extends RealmObject {

    @PrimaryKey
    private String roadMapId;
    private String roadMapName;
    private RealmList<LessonSchema> lessons;
    private RealmList<QuizSchema> quizzes;
    private ScenarioGameSchema scenarioGame;

    public String getRoadMapId() {
        return roadMapId;
    }

    public void setRoadMapId(String roadMapId) {
        this.roadMapId = roadMapId;
    }

    public String getRoadMapName() {
        return roadMapName;
    }

    public void setRoadMapName(String roadMapName) {
        this.roadMapName = roadMapName;
    }

    public RealmList<LessonSchema> getLessons() {
        return lessons;
    }

    public void setLessons(RealmList<LessonSchema> lessons) {
        this.lessons = lessons;
    }

    public ScenarioGameSchema getScenarioGame() {
        return scenarioGame;
    }

    public void setScenarioGame(ScenarioGameSchema scenarioGame) {
        this.scenarioGame = scenarioGame;
    }

    public RealmList<QuizSchema> getQuizzes() {
        return quizzes;
    }

    public void setQuizzes(RealmList<QuizSchema> quizzes) {
        this.quizzes = quizzes;
    }
}
