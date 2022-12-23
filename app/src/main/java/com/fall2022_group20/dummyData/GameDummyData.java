package com.fall2022_group20.dummyData;

import com.fall2022_group20.dataAccessLayer.ScenarioGame.ScenarioGameSchemaService;

import org.bson.types.ObjectId;

import io.realm.Realm;

public class GameDummyData {

    private String scenarioGameId;
    private Integer points;
    private String scenarioGameName;
    private Realm realm;

    public GameDummyData(Realm realm, Integer points, String name){
        this.points = points;
        this.scenarioGameName = name;
        this.realm = realm;
    }

    public void createGameDummyData(){
        ScenarioGameSchemaService scenarioGame = new ScenarioGameSchemaService(realm, scenarioGameName, points);
        if(scenarioGame.getScenarioGame() == null){
            this.scenarioGameId = String.valueOf(new ObjectId());
            scenarioGame.createScenarioGame(scenarioGameId);
        }
    }
}
