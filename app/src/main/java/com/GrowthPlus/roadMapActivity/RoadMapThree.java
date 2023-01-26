package com.GrowthPlus.roadMapActivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;

import com.GrowthPlus.R;
import com.GrowthPlus.customViews.RoadMapLessonTrail;
import com.GrowthPlus.customViews.TopBar;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class RoadMapThree extends AppCompatActivity {

    Button goBackButton;
    BottomNavigationView bottomNavigationView;
    ConstraintLayout roadMapThree;
    RoadMapLessonTrail roadMapThreeLessonTrail;
    TopBar topBarThree;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_road_map_three);

        init();
        initState();

        bottomNavigationView.setSelectedItemId(R.id.roadMap3item);
        bottomNavigationView.setOnItemSelectedListener(item -> {

            switch (item.getItemId()){
                case R.id.roadMap1item:
                    startActivity(new Intent(getApplicationContext(), RoadMapOne.class));
                    overridePendingTransition(0,0);
                    return true;
                case R.id.roadMap2item:
                    startActivity(new Intent(getApplicationContext(), RoadMapTwo.class));
                    overridePendingTransition(0,0);
                    return true;
                case R.id.roadMap3item:
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
        roadMapThree = findViewById(R.id.roadMapThree);
        topBarThree = roadMapThree.findViewById(R.id.topBarThree);
        goBackButton = topBarThree.findViewById(R.id.goBackBtn);
        roadMapThreeLessonTrail = roadMapThree.findViewById(R.id.roadMapThreeLessonTrail);

    }

    private void initState(){
        roadMapThreeLessonTrail.setAlpha(.7f);
    }
}