package com.GrowthPlus.dataAccessLayer;

import java.util.Locale;

import io.realm.DynamicRealm;
import io.realm.RealmMigration;

/*
*
*   Migration process:
*   First, make the required schema change.
*   Then, increment the schema version.
*   Finally, if the change is breaking (destructive),
*   create a corresponding migration function
*   to move data from the original schema to the updated schema.
*
*   https://www.mongodb.com/docs/realm/sdk/java/examples/modify-an-object-schema/
*
*   There are some levels of abstraction to this function.
*   oldVersion is the version of realm before any changes
*   newVersion is the version of realm after the changes are done.
*   One must manually increment the version in the RealmConfig file.
*   Then, come back here to programmatically tell realm of the changes within the migrate function.
*
*   Realm migration example:
*   https://github.com/realm/realm-java/blob/master/examples/migrationExample/src/main/java/io/realm/examples/realmmigrationexample/model/Migration.java
*
*   Realm migration from the practice app:
*   https://github.com/JulioC-19/SampleAppRealm/blob/main/app/src/main/java/com/sampleapprealm/Migration.java
*
* */

public class Migration implements RealmMigration {
    @Override
    public void migrate(DynamicRealm realm, long oldVersion, long newVersion) {


        if (oldVersion < newVersion) {
            throw new IllegalStateException(String.format(Locale.US, "Migration missing from v%d to v%d", oldVersion, newVersion));
        }

    }
}
