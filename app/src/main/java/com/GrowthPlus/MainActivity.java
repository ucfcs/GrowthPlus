package com.GrowthPlus;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;

import com.GrowthPlus.dataAccessLayer.child.ChildSchemaService;

import com.GrowthPlus.realmImporter.JsonSampleData;
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

        JsonSampleData jsonSampleData = new JsonSampleData(realm, resources);
        jsonSampleData.importDataFromJson();

        // TODO: this button is currently navigating to child portal, change to parent portal
        ImageButton childPortal = findViewById(R.id.idParent);
        childPortal.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, ChildPortal.class)));

        ImageButton language = findViewById(R.id.langBtn);
        language.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, LanguageSettingActivity.class)));
    }

    @Override
    protected void onDestroy() {
        if (!realm.isClosed()) realm.close();
        super.onDestroy();
    }

}