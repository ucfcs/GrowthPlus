package com.GrowthPlus;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.Resources;
import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import com.GrowthPlus.R;

import android.view.View;
import android.widget.Button;
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
        ImageButton childPortal = (ImageButton) findViewById(R.id.idParent);
        childPortal.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                startActivity(new Intent(MainActivity.this, ParentSignup.class));
            }
        });

        ImageButton language = (ImageButton) findViewById(R.id.langBtn);
        language.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, LanguageSettingActivity.class));
            }
        });
    }

    @Override
    protected void onDestroy() {
        if (!realm.isClosed()) realm.close();
        super.onDestroy();
    }

}