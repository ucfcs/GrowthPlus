package com.fall2022_group20.roadMapActivity;


import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;

import com.fall2022_group20.R;
import com.fall2022_group20.customViews.RoadMapLessonTrail;
import com.fall2022_group20.customViews.RoadMapTile;
import com.fall2022_group20.customViews.TopBar;
import com.google.android.material.bottomnavigation.BottomNavigationView;


public class RoadMapOneActivity extends AppCompatActivity {

    ImageButton goBackButton;
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
                    startActivity(new Intent(getApplicationContext(), RoadMapTwoActivity.class));
                    overridePendingTransition(0,0);
                    return true;
                case R.id.roadMap3item:
                    startActivity(new Intent(getApplicationContext(), RoadMapThreeActivity.class));
                    overridePendingTransition(0,0);
                    return true;
                case R.id.roadMap4item:
                    startActivity(new Intent(getApplicationContext(), RoadMapFourActivity.class));
                    overridePendingTransition(0,0);
                    return true;
            }
            return false;
        });

        goBackButton = findViewById(R.id.goBackBtn);
        goBackButton.setOnClickListener(v -> onBackPressed());

    }

    private void init(){
        bottomNavigationView = findViewById(R.id.roadMapBottomNavigationView);
        roadMapOneLessonTrail = findViewById(R.id.roadMapOneLessonTrail);
        roadMapOne = findViewById(R.id.roadMapOne);
        topBarOne = findViewById(R.id.topBarOne);
    }

    private void lockedState(){
        roadMapOneLessonTrail.setAlpha(.7f);
    }
}
