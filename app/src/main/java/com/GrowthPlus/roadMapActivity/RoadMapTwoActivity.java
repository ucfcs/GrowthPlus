package com.GrowthPlus.roadMapActivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;

import com.GrowthPlus.R;
import com.GrowthPlus.customViews.RoadMapLessonTrail;
import com.GrowthPlus.customViews.TopBar;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class RoadMapTwoActivity extends AppCompatActivity {

    ImageButton goBackButton;
    BottomNavigationView bottomNavigationView;
    ConstraintLayout roadMapTwo;
    RoadMapLessonTrail roadMapTwoLessonTrail;
    TopBar topBarTwo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_road_map_two);

        init();
        lockedState();

        bottomNavigationView.setSelectedItemId(R.id.roadMap2item);
        bottomNavigationView.setOnItemSelectedListener(item -> {

            switch (item.getItemId()){
                case R.id.roadMap1item:
                    startActivity(new Intent(getApplicationContext(), RoadMapOneActivity.class));
                    overridePendingTransition(0,0);
                    return true;
                case R.id.roadMap2item:
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
        roadMapTwo = findViewById(R.id.roadMapTwo);
        topBarTwo = roadMapTwo.findViewById(R.id.topBarTwo);
        roadMapTwoLessonTrail = roadMapTwo.findViewById(R.id.roadMapTwoLessonTrail);

    }

    private void lockedState(){
        roadMapTwoLessonTrail.setAlpha(.7f);
    }
}