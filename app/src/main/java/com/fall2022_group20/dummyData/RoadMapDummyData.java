package com.fall2022_group20.dummyData;

import com.fall2022_group20.dataAccessLayer.RoadMap.RoadMapSchemaService;

import org.bson.types.ObjectId;

import io.realm.Realm;

public class RoadMapDummyData {
    private Realm realm;
    private String roadMapName;
    private String roadMapId;

    public RoadMapDummyData(Realm realm, String roadMapName){
        this.realm = realm;
        this.roadMapName = roadMapName;
    }

    public void generateRoadMapDummyData(){
        RoadMapSchemaService newRoadMap = new RoadMapSchemaService(realm, roadMapName);
        if(newRoadMap.getRoadMapByName() == null){
            this.roadMapId = String.valueOf(new ObjectId());

        }

    }
}
