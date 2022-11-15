package com.fall2022_group20.dataAccessLayer.RoadMap;

import android.util.Log;

import com.fall2022_group20.dataAccessLayer.Lesson.LessonSchema;
import com.fall2022_group20.dataAccessLayer.Report.ReportSchema;
import com.fall2022_group20.dataAccessLayer.ScenarioGame.ScenarioGameSchema;

import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmResults;

public class RoadMapSchemaService {
    private Realm realm;
    private String childId;
    private String roadMapName;
    private String roadMapId;

    public RoadMapSchemaService(Realm realm, String childId, String roadMapName, String roadMapId) {
        this.realm = realm;
        this.childId = childId;
        this.roadMapName = roadMapName;
        this.roadMapId = roadMapId;
    }

    /*
    * This methods creates a new roadmap schema
    * The creation of a new schema must be executed within a realm transaction
    * */

    public void createRoadMap (ScenarioGameSchema newGame, RealmList<LessonSchema> lessons){
        realm.executeTransactionAsync(realm -> {
            RoadMapSchema newRoadMap = realm.createObject(RoadMapSchema.class, String.valueOf(roadMapId));
            newRoadMap.setRoadMapName(roadMapName);
            newRoadMap.setLessons(lessons);
            newRoadMap.setScenarioGame(newGame);
        }, () -> { // Lambda expression
            /* success actions */
            Log.i("Success", "New Road map object added to realm!");
            // TODO: Investigate if this is correct
            //realm.close();
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
    public RoadMapSchema getRoadMap (){
        return realm.where(RoadMapSchema.class)
                .equalTo("roadMapName", roadMapName)
                .findFirst();
    }

    /*
    * Method to return all lesson of a given roadmap
    * */
    public RealmList<LessonSchema> getAllLessonFromRoadMap(){
        RoadMapSchema roadMap = getRoadMap();
        return roadMap.getLessons();
    }

    /*
    * Method to return a the scenario game corresponding to the road map
    * */
    public ScenarioGameSchema getRoadMapScenarioGame(){
        RoadMapSchema roadMap = getRoadMap();
        return roadMap.getScenarioGame();
    }

    /*
    * Method to insert a scenario game into the given existing roadmap
    * Invokes the getRoadMap() method
    * Assumes new game is already created and populated
    * The upsert must be executed within a realm transaction
    * TODO: Implement this method signature in the realm sample app
    * */
    public void setRoadMapScenarioGame (ScenarioGameSchema newGame){
        realm.executeTransactionAsync(realm -> {
            RoadMapSchema roadMap = getRoadMap();
            roadMap.setScenarioGame(newGame);
        });
    }

    /*
    * Method to insert a list of lesson into the given existing roadmap
    * Invokes the getRoadMap() method
    * Assumes list of lesson are already created and populated
    * The upsert must be executed within a realm transaction
    * TODO: Implement this method signature in the realm sample app
    * */
    public void setRoadMapLessons (RealmList<LessonSchema> lessons){
        realm.executeTransactionAsync(realm -> {
            RoadMapSchema roadMap = getRoadMap();
            roadMap.setLessons(lessons);
        });
    }

    /*
    * Method to delete a scenario game data from a given roadmap
    * The deletion must be executed within a realm transaction
    * TODO: Implement this method signature in the realm sample app
    * */
    public void deleteRoadMapScenarioGame(){
        realm.executeTransactionAsync(realm -> {
            RoadMapSchema roadMap = getRoadMap();
            ScenarioGameSchema scenarioGame = roadMap.getScenarioGame();
            scenarioGame.deleteFromRealm();
            scenarioGame = null;
        });
    }

    /*
     * Method to delete a scenario game data from a given roadmap
     * The deletion must be executed within a realm transaction
     * TODO: Implement this method signature in the realm sample app
     * */
    public void deleteRoadMapLessons(){
        realm.executeTransactionAsync(realm -> {
            RoadMapSchema roadMap = getRoadMap();
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
            RoadMapSchema roadMap = getRoadMap();
            roadMap.deleteFromRealm();
            roadMap = null;
        });
    }
}

