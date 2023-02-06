package com.GrowthPlus.realmImporter;

import android.content.res.Resources;
import com.GrowthPlus.R;

import java.io.InputStream;
import io.realm.Realm;

public class JsonSampleData {

    Realm realm;
    Resources resources;

    public JsonSampleData(Realm realm, Resources resources){
        this.realm = realm;
        this.resources = resources;
    }

    public void importDataFromJson(){

//        //Language Data
//        InputStream languagesInputStream = resources.openRawResource(R.raw.languages);
//        LanguagesRealmImporter languagesRealmImporter = new LanguagesRealmImporter(realm, resources, languagesInputStream);
//        languagesRealmImporter.importLanguagesFromJson();

        // Children data
        InputStream childInputStream = resources.openRawResource(R.raw.child);
        ChildRealmImporter childRealmImporter = new ChildRealmImporter(realm, childInputStream);
        childRealmImporter.importChildFromJson();

        // Parent data
        InputStream parentInputStream = resources.openRawResource(R.raw.parent);
        ParentRealmImporter parentRealmImporter = new ParentRealmImporter(realm, parentInputStream);
        parentRealmImporter.importParentFromJson();
    }
}
