package com.GrowthPlus.dataAccessLayer.ScenarioGame;

import android.util.Log;

import com.GrowthPlus.dataAccessLayer.ScenarioGame.ScenarioGameSchema;

import io.realm.Realm;
import io.realm.RealmResults;

public class ScenarioGameSchemaService {
    private Realm realm;
    private String scenarioGameId;
    private String scenarioGameName;
    private Integer points;

    public ScenarioGameSchemaService(Realm realm, String scenarioGameName, Integer points){
        this.realm = realm;
        this.scenarioGameName = scenarioGameName;
        this.points = points;
    }

    public void createScenarioGame( String gameId){
        this.scenarioGameId = gameId;
        realm.executeTransactionAsync(realm -> {
            ScenarioGameSchema newScenarioGame = realm.createObject(ScenarioGameSchema.class, String.valueOf(scenarioGameId));
            newScenarioGame.setPoints(points);
            newScenarioGame.setScenarioGameName(scenarioGameName);
        }, () -> {
            Log.i("Success", "New Scenario Game ");
        }, error -> {
            Log.e("Error", "Something went wrong! " + error);
        });
    }

    public RealmResults<ScenarioGameSchema> getAllScenarioGames(){
        return realm.where(ScenarioGameSchema.class).findAll();
    }
    public ScenarioGameSchema getScenarioGame(){
        return realm.where(ScenarioGameSchema.class)
                .equalTo("scenarioGameName", scenarioGameName)
                .findFirst();
    }

    public void deleteRealmScenarioGame(){
        realm.executeTransactionAsync(realm ->{
            ScenarioGameSchema scenarioGame = getScenarioGame();
            scenarioGame.deleteFromRealm();
            scenarioGame = null;
        });
    }


}
