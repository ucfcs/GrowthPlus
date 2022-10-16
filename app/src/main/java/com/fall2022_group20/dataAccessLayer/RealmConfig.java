package com.fall2022_group20.dataAccessLayer;

import android.app.Application;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/*
*   Realm configuration determines some basic features for realm.
*   This configuration fires up automatically every time the app starts.
*   This configuration should be only done once,
*   no need to explicitly call this class anywhere in the app.
*   As of 10/16/2022, we are not defining any real data into the schemas.
*   Notice realmVersion is not deployed yet.
*   This means that we don't have to worry about the version of realm yet.
*   After the schemas are set up and defined with their respective fields,
*   we will realm version 0 and include a migration function to the
*   configurations.
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
