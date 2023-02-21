package com.GrowthPlus.dataAccessLayer.ScenarioGame;

import android.util.Log;

import com.GrowthPlus.dataAccessLayer.ScenarioGame.ScenarioGameSchema;

import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmResults;

public class ScenarioGameSchemaService {
    private Realm realm;
    private String scenarioGameId;
    private String scenarioGameName;
    private Integer minPoints;
    private Integer maxPoints;
    private RealmList<ScenarioGameContent> questions;

    /**
     * @param realm
     */
    /*
    * This constructor should used only if and only if a realm object already exists
    * and the primary key (id) is known.
    * */
    public ScenarioGameSchemaService(Realm realm) {
        this.realm = realm;
    }

    /**
     * @param realm
     * @param scenarioGameId
     * @param scenarioGameName
     * @param minPoints
     * @param maxPoints
     * @param questions
     */
    /*
     * This constructor should be used for the creating of a brand new realm object.
     * Provide a unique identifier to act as the primary key.
     * */
    public ScenarioGameSchemaService(
            Realm realm,
            String scenarioGameId,
            String scenarioGameName,
            Integer minPoints,
            Integer maxPoints,
            RealmList<ScenarioGameContent> questions
    ) {
        this.realm = realm;
        this.scenarioGameId = scenarioGameId;
        this.scenarioGameName = scenarioGameName;
        this.minPoints = minPoints;
        this.maxPoints = maxPoints;
        this.questions = questions;
    }

    public void createScenarioGame(){
        realm.executeTransactionAsync(realm -> {
            ScenarioGameSchema newScenarioGame = realm.createObject(ScenarioGameSchema.class, String.valueOf(scenarioGameId));
            newScenarioGame.setScenarioGameName(scenarioGameName);
            newScenarioGame.setMinPoints(minPoints);
            newScenarioGame.setMaxPoints(maxPoints);
            newScenarioGame.setQuestions(questions);
        }, () -> {
            Log.i("Success", "New Scenario Game added to realm");
        }, error -> {
            Log.e("Error", "Something went wrong! " + error);
        });
    }

    public RealmResults<ScenarioGameSchema> getAllScenarioGames(){
        return realm.where(ScenarioGameSchema.class).findAll();
    }

    public ScenarioGameSchema getScenarioGameById(String id){
        return realm.where(ScenarioGameSchema.class)
                .equalTo("scenarioGameId", id)
                .findFirst();
    }

    public ScenarioGameSchema getScenarioGameByName(String scenarioGameName){
        return realm.where(ScenarioGameSchema.class)
                .equalTo("scenarioGameName", scenarioGameName)
                .findFirst();
    }

    public void deleteScenarioGameById(String id){
        realm.executeTransactionAsync(realm ->{
            ScenarioGameSchema scenarioGame = getScenarioGameById(id);
            scenarioGame.deleteFromRealm();
        });
    }

    public void deleteScenarioGameByName(String scenarioGameName){
        realm.executeTransactionAsync(realm ->{
            ScenarioGameSchema scenarioGame = getScenarioGameByName(scenarioGameName);
            scenarioGame.deleteFromRealm();
        });
    }


}
