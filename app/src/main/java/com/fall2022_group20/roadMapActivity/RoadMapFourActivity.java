package com.fall2022_group20.roadMapActivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.fall2022_group20.R;
import com.fall2022_group20.customViews.RoadMapLessonTrail;
import com.fall2022_group20.customViews.TopBar;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class RoadMapFourActivity extends AppCompatActivity {

    ImageButton goBackButton;
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
                    startActivity(new Intent(getApplicationContext(), RoadMapOneActivity.class));
                    overridePendingTransition(0,0);
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
                    return true;
            }
            return false;
        });

        goBackButton = findViewById(R.id.goBackBtn);
        goBackButton.setOnClickListener(v -> onBackPressed());

    }

    private void init(){

        bottomNavigationView = findViewById(R.id.roadMapBottomNavigationView);
        roadMapFour = findViewById(R.id.roadMapFour);
        topBarFour = roadMapFour.findViewById(R.id.topBarFour);
        roadMapFourLessonTrail = roadMapFour.findViewById(R.id.roadMapFourLessonTrail);

    }

    private void initState(){
        roadMapFourLessonTrail.setAlpha(.7f);
    }
}