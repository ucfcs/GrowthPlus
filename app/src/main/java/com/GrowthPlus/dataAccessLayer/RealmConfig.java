package com.GrowthPlus.dataAccessLayer;

import android.app.Application;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/*
*   Realm configuration determines some basic features for realm.
*   This configuration fires up automatically every time the app starts.
*   This configuration should be only done once,
*   No need to explicitly call this class anywhere in the app.
*   A migration function handles any changes to the realm schemas.
*
*   https://www.mongodb.com/docs/realm/sdk/java/fundamentals/schema-versions-and-migrations/
*
* */

public class RealmConfig extends Application {

    private int realmVersion = 0;

    @Override
    public void onCreate(){
        super.onCreate();

        //Initialize the realm database
        Realm.init(this);

        // Configuring realm version 0
        RealmConfiguration realmConfig = new RealmConfiguration.Builder()
                .deleteRealmIfMigrationNeeded() // This is temporary
                .build();
        Realm.setDefaultConfiguration(realmConfig);
    }
}
