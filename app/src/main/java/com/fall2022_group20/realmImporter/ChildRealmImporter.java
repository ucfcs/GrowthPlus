package com.fall2022_group20.realmImporter;

import android.content.res.Resources;
import com.fall2022_group20.dataAccessLayer.child.ChildSchema;
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
