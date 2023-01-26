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

public class RoadMapFour extends AppCompatActivity {

    Button goBackButton;
    BottomNavigationView bottomNavigationView;
    ConstraintLayout roadMapFour;
    RoadMapLessonTrail roadMapFourLessonTrail;
    TopBar topBarFour;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_road_map_four);

        init();
        initState();

        bottomNavigationView.setSelectedItemId(R.id.roadMap4item);
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
                    startActivity(new Intent(getApplicationContext(), RoadMapThree.class));
                    overridePendingTransition(0,0);
                    return true;
                case R.id.roadMap4item:
                    return true;
            }
            return false;
        });

        goBackButton.setOnClickListener(v -> onBackPressed());

    }

    private void init(){

        bottomNavigationView = findViewById(R.id.roadMapBottomNavigationView);
        roadMapFour = findViewById(R.id.roadMapFour);
        topBarFour = roadMapFour.findViewById(R.id.topBarFour);
        goBackButton = topBarFour.findViewById(R.id.goBackBtn);
        roadMapFourLessonTrail = roadMapFour.findViewById(R.id.roadMapFourLessonTrail);

    }

    private void initState(){
        roadMapFourLessonTrail.setAlpha(.7f);
    }
}