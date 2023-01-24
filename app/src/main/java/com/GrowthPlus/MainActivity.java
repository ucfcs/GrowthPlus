package com.GrowthPlus;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import com.GrowthPlus.dataAccessLayer.child.ChildSchemaService;
import com.GrowthPlus.realmImporter.JsonSampleData;
import com.GrowthPlus.roadMapActivity.RoadMapOneActivity;

import io.realm.Realm;

public class MainActivity extends AppCompatActivity {

    Realm realm;
    Resources resources;
    Button goToRoadMapOne;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        realm = Realm.getDefaultInstance();
        resources = getResources();


        JsonSampleData jsonSampleData = new JsonSampleData(realm, resources);
        jsonSampleData.importDataFromJson();

        ChildSchemaService childService = new ChildSchemaService();

        // This is casting to string so that it can be printed to the console as example
        // Do not use string values of realm results, it does not work that way
        Log.i("Child", String.valueOf(childService.getAllChildSchemas()));
        Log.i("Child Report", String.valueOf(childService.getReportByChildName("Child Zero").getChildScore()));

        goToRoadMapOne = findViewById(R.id.roadMapOneBtn);
        goToRoadMapOne.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, RoadMapOneActivity.class);
            startActivity(intent);
        });
    }

    @Override
    protected void onDestroy() {
        if (!realm.isClosed()) realm.close();
        super.onDestroy();
    }

}