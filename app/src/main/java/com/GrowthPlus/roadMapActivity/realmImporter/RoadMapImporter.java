package com.GrowthPlus.roadMapActivity.realmImporter;

import com.GrowthPlus.dataAccessLayer.ChildRoadMap.ChildRoadMap;
import com.GrowthPlus.dataAccessLayer.RoadMap.RoadMapSchema;
import com.GrowthPlus.dataAccessLayer.child.ChildSchema;

import java.io.InputStream;

import io.realm.Realm;

public class RoadMapImporter {
    Realm realm;
    InputStream inputStream;

    public RoadMapImporter(Realm realm, InputStream roadMapInputStream){
        this.realm = realm;
        this.inputStream = roadMapInputStream;
    }
    public void importRoadMapFromJson(){
        // Insert multiple items using an InputStream
        realm.executeTransactionAsync(realm -> realm.createOrUpdateAllFromJson(RoadMapSchema.class, inputStream));
    }
}
