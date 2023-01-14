package com.fall2022_group20.realmImporter;

import android.content.res.Resources;

import com.fall2022_group20.dataAccessLayer.Language.LanguageSchema;

import java.io.InputStream;

import io.realm.Realm;

public class LanguagesRealmImporter {
    Realm realm;
    Resources resources;
    InputStream inputStream;

    public LanguagesRealmImporter(Realm realm, Resources resources, InputStream languagesInputStream){
        this.realm = realm;
        this.resources = resources;
        this.inputStream = languagesInputStream;

    }
    public void importLanguagesFromJson(){
        // Insert multiple items using an InputStream
        realm.executeTransactionAsync(realm -> realm.createOrUpdateAllFromJson(LanguageSchema.class, inputStream));
    }
}
