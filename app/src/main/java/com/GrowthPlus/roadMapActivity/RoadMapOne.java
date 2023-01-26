package com.GrowthPlus.roadMapActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.GrowthPlus.R;
import com.GrowthPlus.customViews.RoadMapLessonTrail;
import com.GrowthPlus.customViews.TopBar;
import com.google.android.material.bottomnavigation.BottomNavigationView;


public class RoadMapOne extends AppCompatActivity {

    Button goBackButton;
    BottomNavigationView bottomNavigationView;
    RoadMapLessonTrail roadMapOneLessonTrail;
    ConstraintLayout roadMapOne;
    TopBar topBarOne;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_road_map_one);

        init();

        roadMapOneLessonTrail.unLockRoadMap();
        roadMapOneLessonTrail.setSelectedState(roadMapOneLessonTrail.getRoadMapTile1());

        bottomNavigationView.setSelectedItemId(R.id.roadMap1item);
        bottomNavigationView.setOnItemSelectedListener(item -> {

            switch (item.getItemId()){
                case R.id.roadMap1item:
                    return true;
                case R.id.roadMap2item:
                    startActivity(new Intent(getApplicationContext(), RoadMapTwo.class));
                    overridePendingTransition(0,0);
                    return true;
                case R.id.roadMap3item:
                    startActivity(new Intent(getApplicationContext(), RoadMapThree.class));
                    overridePendingTransition(0,0);
                    return true;
                case R.id.roadMap4item:
                    startActivity(new Intent(getApplicationContext(), RoadMapFour.class));
                    overridePendingTransition(0,0);
                    return true;
            }
            return false;
        });

        goBackButton.setOnClickListener(v -> onBackPressed());

    }

    private void init(){

        bottomNavigationView = findViewById(R.id.roadMapBottomNavigationView);
        roadMapOneLessonTrail = findViewById(R.id.roadMapOneLessonTrail);
        roadMapOne = findViewById(R.id.roadMapOne);
        goBackButton = roadMapOne.findViewById(R.id.goBackBtn);
        topBarOne = findViewById(R.id.topBarOne);
    }

    private void lockedState(){
        roadMapOneLessonTrail.setAlpha(.7f);
    }
}