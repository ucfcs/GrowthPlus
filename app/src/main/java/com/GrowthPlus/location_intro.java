package com.GrowthPlus;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.GrowthPlus.customViews.TopBar;
import com.GrowthPlus.dataAccessLayer.ChildRoadMap.ChildRoadMap;
import com.GrowthPlus.dataAccessLayer.child.ChildSchema;
import com.GrowthPlus.dataAccessLayer.child.ChildSchemaService;

import io.realm.Realm;

public class location_intro extends AppCompatActivity {

    private String childId;
    private Realm realm;
    private ChildSchema child;
    private ChildRoadMap childRoadMap;
    private TopBar topBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location_intro);
        init();
        setTopBar();

        Log.i("Child Roadmap", String.valueOf(childRoadMap));

    }

    private void init(){

        Bundle extras = getIntent().getExtras();
        if(extras != null){
            childId = extras.getString("childId");
        }
         realm = Realm.getDefaultInstance();
         child = realm.where(ChildSchema.class).equalTo("childId", childId).findFirst();
         childRoadMap = child.getRoadMapOne();
         topBar = findViewById(R.id.topBar);
    }

    private void setTopBar(){
        topBar.setPoints(String.valueOf(child.getScore()));
    }
}