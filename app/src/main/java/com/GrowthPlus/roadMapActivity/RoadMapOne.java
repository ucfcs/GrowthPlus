package com.GrowthPlus.roadMapActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.GrowthPlus.R;
import com.GrowthPlus.customViews.RoadMapLessonTrail;
import com.GrowthPlus.customViews.RoadMapTile;
import com.GrowthPlus.customViews.TopBar;
import com.GrowthPlus.dataAccessLayer.child.ChildSchema;
import com.GrowthPlus.dataAccessLayer.child.ChildSchemaService;
import com.GrowthPlus.location_intro;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import io.realm.Realm;

public class RoadMapOne extends AppCompatActivity {
    Button goBackButton;
    BottomNavigationView bottomNavigationView;
    RoadMapLessonTrail roadMapOneLessonTrail;
    ConstraintLayout roadMapOne;
    RoadMapTile tile1, tile2, tile3, tile4, tile5, tile6, tile7, tile8, tile9, tile10, tile11, tile12, tile13;
    TopBar topBarOne;
    Intent IntentIntro; // Leads to location_intro page
    String childID;
    ChildSchemaService childSchemaService;
    Realm realm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_road_map_one);

        realm = Realm.getDefaultInstance();
        childSchemaService = new ChildSchemaService(realm);

        // !!! MUST PASS CHILD.ID WITH 'putExtra' WHEN NAVIGATING BETWEEN LEVELS OR ELSE SYSTEM CRASH !!!
        Bundle extras = getIntent().getExtras();
        if(extras != null){
            childID = extras.getString("childIdentify");
        }
        ChildSchema child = childSchemaService.getChildSchemaById(childID);
        init(child);

        roadMapOneLessonTrail.unLockRoadMap();
        roadMapOneLessonTrail.setSelectedState(roadMapOneLessonTrail.getRoadMapTile1(), child);

        bottomNavigationView.setSelectedItemId(R.id.roadMap1item);
        bottomNavigationView.setOnItemSelectedListener(item -> {

            switch (item.getItemId()){

                case R.id.roadMap1item:{
                    return true;

                }
                case R.id.roadMap2item:{
                    Intent intent = new Intent(getApplicationContext(), RoadMapTwo.class);
                    intent.putExtra("childIdentify", childID);
                    startActivity(intent);
                    overridePendingTransition(0,0);
                    return true;
                }
                case R.id.roadMap3item:{
                    Intent intent = new Intent(getApplicationContext(), RoadMapThree.class);
                    intent.putExtra("childIdentify", childID);
                    startActivity(intent);
                    overridePendingTransition(0,0);
                    return true;
                }

                case R.id.roadMap4item:{
                    Intent intent = new Intent(getApplicationContext(), RoadMapFour.class);
                    intent.putExtra("childIdentify", childID);
                    startActivity(intent);
                    overridePendingTransition(0,0);
                    return true;
                }
            }
            return false;
        });

        goBackButton.setOnClickListener(v -> onBackPressed());
    }

    private void init(ChildSchema child){
        bottomNavigationView = findViewById(R.id.roadMapBottomNavigationView);
        roadMapOneLessonTrail = findViewById(R.id.roadMapOneLessonTrail);
        IntentIntro = new Intent(RoadMapOne.this, location_intro.class);

        roadMapOne = findViewById(R.id.roadMapOne);
        goBackButton = roadMapOne.findViewById(R.id.goBackBtn);
        topBarOne = findViewById(R.id.topBarOne);
        topBarOne.setPoints(String.valueOf(child.getScore())); // Update points for specific child

        tile1 = roadMapOneLessonTrail.getRoadMapTile1();
        tile1.setOnClickListener(v -> {
            IntentIntro.putExtra("childId", childID);
            startActivity(IntentIntro);
        });

        tile2 = roadMapOneLessonTrail.getRoadMapTile2();
        tile2.setOnClickListener(v -> {
            IntentIntro.putExtra("childId", childID);
            startActivity(IntentIntro);
        });

        tile3 = roadMapOneLessonTrail.getRoadMapTile3();
        tile3.setOnClickListener(v -> {
            IntentIntro.putExtra("childId", childID);
            startActivity(IntentIntro);
        });

        tile4 = roadMapOneLessonTrail.getRoadMapTile4();
        tile4.setOnClickListener(v -> {
            IntentIntro.putExtra("childId", childID);
            startActivity(IntentIntro);
        });

        tile5 = roadMapOneLessonTrail.getRoadMapTile5();
        tile5.setOnClickListener(v -> {
            IntentIntro.putExtra("childId", childID);
            startActivity(IntentIntro);
        });

        tile6 = roadMapOneLessonTrail.getRoadMapTile6();
        tile6.setOnClickListener(v -> {
            IntentIntro.putExtra("childId", childID);
            startActivity(IntentIntro);
        });

        tile7 = roadMapOneLessonTrail.getRoadMapTile7();
        tile7.setOnClickListener(v -> {
            IntentIntro.putExtra("childId", childID);
            startActivity(IntentIntro);
        });

        tile8 = roadMapOneLessonTrail.getRoadMapTile8();
        tile8.setOnClickListener(v -> {
            IntentIntro.putExtra("childId", childID);
            startActivity(IntentIntro);
        });

        tile9 = roadMapOneLessonTrail.getRoadMapTile9();
        tile9.setOnClickListener(v -> {
            IntentIntro.putExtra("childId", childID);
            startActivity(IntentIntro);
        });

        tile10 = roadMapOneLessonTrail.getRoadMapTile10();
        tile10.setOnClickListener(v -> {
            IntentIntro.putExtra("childId", childID);
            startActivity(IntentIntro);
        });

        tile11 = roadMapOneLessonTrail.getRoadMapTile11();
        tile11.setOnClickListener(v -> {
            IntentIntro.putExtra("childId", childID);
            startActivity(IntentIntro);
        });

        tile12 = roadMapOneLessonTrail.getRoadMapTile12();
        tile12.setOnClickListener(v -> {
            IntentIntro.putExtra("childId", childID);
            startActivity(IntentIntro);
        });

        tile13 = roadMapOneLessonTrail.getRoadMapTile13();
        tile13.setOnClickListener(v -> {
            IntentIntro.putExtra("childId", childID);
            startActivity(IntentIntro);
        });
    }

    private void lockedState(){
        roadMapOneLessonTrail.setAlpha(.7f);
    }
}