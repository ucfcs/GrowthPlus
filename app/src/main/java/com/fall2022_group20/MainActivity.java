package com.fall2022_group20;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Resources;
import android.os.Bundle;
import com.fall2022_group20.realmImporter.LanguagesRealmImporter;
import java.io.InputStream;


import io.realm.Realm;

public class MainActivity extends AppCompatActivity {

    Realm realm;
    Resources resources;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        realm = Realm.getDefaultInstance();
        resources = getResources();
        InputStream languagesInputStream = resources.openRawResource(R.raw.languages);

        LanguagesRealmImporter languagesRealmImporter = new LanguagesRealmImporter(realm, resources, languagesInputStream);
        languagesRealmImporter.importLanguagesFromJson();

    }

    @Override
    protected void onDestroy() {
        if (!realm.isClosed()) realm.close();
        super.onDestroy();
    }

}