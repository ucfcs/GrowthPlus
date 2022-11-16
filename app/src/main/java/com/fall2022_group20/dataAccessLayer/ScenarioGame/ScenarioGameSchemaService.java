package com.fall2022_group20.dataAccessLayer.ScenarioGame;

import android.util.Log;

import com.fall2022_group20.dataAccessLayer.ScenarioGame.ScenarioGameSchema;

import io.realm.Realm;
import io.realm.RealmResults;

public class ScenarioGameSchemaService {
    private Realm realm;
    private String Id;
    private Integer points;

    public ScenarioGameSchemaService(Realm realm, String Id, Integer points){
        this.realm = realm;
        this.Id = Id;
        this.points = points;
    }

    public void createScenarioGame(){
        realm.executeTransactionAsync(realm -> {
            ScenarioGameSchema newScenarioGame = realm.createObject(ScenarioGameSchema.class, String.valueOf(Id));
            newScenarioGame.setPoints(points);
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
                .equalTo("Id", Id)
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
