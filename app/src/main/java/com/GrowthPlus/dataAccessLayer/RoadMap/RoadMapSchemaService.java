package com.GrowthPlus.dataAccessLayer.RoadMap;

import android.util.Log;

import com.GrowthPlus.dataAccessLayer.Lesson.LessonSchema;
import com.GrowthPlus.dataAccessLayer.Quiz.QuizSchema;
import com.GrowthPlus.dataAccessLayer.ScenarioGame.ScenarioGameSchema;

import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmResults;

public class RoadMapSchemaService {
    private Realm realm;
    private String roadMapId;
    private String roadMapName;
    private RealmList<LessonSchema> lessons;
    private RealmList<QuizSchema> quizzes;
    private ScenarioGameSchema scenarioGame;

    public RoadMapSchemaService(Realm realm,String roadMapName, RealmList<LessonSchema> lessons, RealmList<QuizSchema> quizzes, ScenarioGameSchema scenarioGame) {
        this.realm = realm;
        this.roadMapName = roadMapName;
        this.lessons = lessons;
        this.quizzes = quizzes;
        this.scenarioGame = scenarioGame;
    }

    /*
    * This methods creates a new roadmap schema
    * The creation of a new schema must be executed within a realm transaction
    * */
    public void createRoadMap (String roadMapId){
        this.roadMapId = roadMapId;
        realm.executeTransactionAsync(realm -> {
            RoadMapSchema newRoadMap = realm.createObject(RoadMapSchema.class, String.valueOf(roadMapId));
            newRoadMap.setRoadMapName(roadMapName);
            newRoadMap.setLessons(lessons);
            newRoadMap.setLessons(lessons);
            newRoadMap.setScenarioGame(scenarioGame);
        }, () -> { // Lambda expression
            /* success actions */
            Log.i("Success", "New Road map object added to realm!");
        }, error -> {
            /* failure actions */
            Log.e("Error", "Something went wrong! " + error);
        });
    }

    /*
    * Method to return all instances of roadmaps
    * */
    public RealmResults<RoadMapSchema> getAllRoadMaps(){
        return realm.where(RoadMapSchema.class).findAll();
    }
    /*
    * Method to return a given roadmap by name
    * */
    public RoadMapSchema getRoadMapByName (){
        return realm.where(RoadMapSchema.class)
                .equalTo("roadMapName", roadMapName)
                .findFirst();
    }

    /*
    * Method to return all Lesson of a given roadmap
    * */
    public RealmList<LessonSchema> getAllLessonFromRoadMap(){
        RoadMapSchema roadMap = getRoadMapByName();
        return roadMap.getLessons();
    }

    /*
    * Method to return a the scenario Game corresponding to the road map
    * */
    public ScenarioGameSchema getRoadMapScenarioGame(){
        RoadMapSchema roadMap = getRoadMapByName();
        return roadMap.getScenarioGame();
    }

    /*
    * Method to insert a scenario Game into the given existing roadmap
    * Invokes the getRoadMap() method
    * Assumes new Game is already created and populated
    * The upsert must be executed within a realm transaction
    * TODO: Implement this method signature in the realm sample app
    * */
    public void setRoadMapScenarioGame (ScenarioGameSchema newGame){
        realm.executeTransactionAsync(realm -> {
            RoadMapSchema roadMap = getRoadMapByName();
            roadMap.setScenarioGame(newGame);
        });
    }

    /*
    * Method to insert a list of Lesson into the given existing roadmap
    * Invokes the getRoadMap() method
    * Assumes list of Lesson are already created and populated
    * The upsert must be executed within a realm transaction
    * TODO: Implement this method signature in the realm sample app
    * */
    public void setRoadMapLessons (RealmList<LessonSchema> lessons){
        realm.executeTransactionAsync(realm -> {
            RoadMapSchema roadMap = getRoadMapByName();
            roadMap.setLessons(lessons);
        });
    }

    /*
    * Method to delete a scenario Game data from a given roadmap
    * The deletion must be executed within a realm transaction
    * TODO: Implement this method signature in the realm sample app
    * */
    public void deleteRoadMapScenarioGame(){
        realm.executeTransactionAsync(realm -> {
            RoadMapSchema roadMap = getRoadMapByName();
            ScenarioGameSchema scenarioGame = roadMap.getScenarioGame();
            scenarioGame.deleteFromRealm();
            scenarioGame = null;
        });
    }

    /*
     * Method to delete all lessons data from a given roadmap
     * The deletion must be executed within a realm transaction
     * TODO: Implement this method signature in the realm sample app
     * */
    public void deleteRoadMapLessons(){
        realm.executeTransactionAsync(realm -> {
            RoadMapSchema roadMap = getRoadMapByName();
            RealmList<LessonSchema> lessons = roadMap.getLessons();
            lessons.deleteAllFromRealm();
            lessons = null;
        });
    }

    /*
    * Method to delete a given roadMap
    * */
    public void deleteRealmRoadMap(){
        realm.executeTransactionAsync(realm -> {
            RoadMapSchema roadMap = getRoadMapByName();
            roadMap.deleteFromRealm();
            roadMap = null;
        });
    }
}

