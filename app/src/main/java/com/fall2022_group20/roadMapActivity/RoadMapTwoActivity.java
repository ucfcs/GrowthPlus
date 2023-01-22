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

public class RoadMapTwoActivity extends AppCompatActivity {

    ImageButton goBackButton;
    BottomNavigationView bottomNavigationView;
    ConstraintLayout roadMapTwo;
    RoadMapLessonTrail roadMapLessonTwoTrail;
    TopBar topBarTwo;
    ImageView lockTwo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_road_map_two);

        init();
        initState();

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
        roadMapLessonTwoTrail = roadMapTwo.findViewById(R.id.roadMapTwoLessonTrail);
        lockTwo = roadMapLessonTwoTrail.findViewById(R.id.lockTwo);

    }

    private void initState(){
        roadMapLessonTwoTrail.setAlpha(.7f);

    }
}