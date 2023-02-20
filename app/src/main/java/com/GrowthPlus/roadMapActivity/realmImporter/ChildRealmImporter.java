package com.GrowthPlus.roadMapActivity.realmImporter;

import com.GrowthPlus.dataAccessLayer.child.ChildSchema;
import java.io.InputStream;
import io.realm.Realm;

public class ChildRealmImporter {

    Realm realm;
    InputStream inputStream;

    public ChildRealmImporter(Realm realm, InputStream childInputStream){
        this.realm = realm;
        this.inputStream = childInputStream;

    }
    public void importChildFromJson(){
        // Insert multiple items using an InputStream
        realm.executeTransactionAsync(realm -> realm.createOrUpdateAllFromJson(ChildSchema.class, inputStream));
    }
}
