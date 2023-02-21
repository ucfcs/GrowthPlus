package com.GrowthPlus.realmImporter;
import com.GrowthPlus.dataAccessLayer.parent.ParentSchema;
import java.io.InputStream;
import io.realm.Realm;

public class ParentRealmImporter {

    Realm realm;
    InputStream inputStream;

    public ParentRealmImporter(Realm realm, InputStream childInputStream){
        this.realm = realm;
        this.inputStream = childInputStream;

    }
    public void importParentFromJson(){
        // Insert multiple items using an InputStream
        realm.executeTransactionAsync(realm -> realm.createOrUpdateAllFromJson(ParentSchema.class, inputStream));
    }
}
