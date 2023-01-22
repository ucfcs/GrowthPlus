package com.fall2022_group20.roadMapActivity;


import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.fall2022_group20.R;
import com.fall2022_group20.customViews.RoadMapLessonTrail;
import com.fall2022_group20.customViews.RoadMapTile;
import com.google.android.material.bottomnavigation.BottomNavigationView;


public class RoadMapOneActivity extends AppCompatActivity {

    ImageButton goBackButton;
    BottomNavigationView bottomNavigationView;
    RoadMapLessonTrail roadMapOneLessonTrail;
    RoadMapTile roadMapTileOne;
    ImageView tile;
    ImageView avatar;
    TextView triangle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_road_map_one);

        bottomNavigationView = findViewById(R.id.roadMapBottomNavigationView);
        bottomNavigationView.setSelectedItemId(R.id.roadMap1item);

        roadMapOneLessonTrail = findViewById(R.id.roadMapOneLessonTrail);
        roadMapTileOne = roadMapOneLessonTrail.findViewById(R.id.roadMapTileOne);

        tile = roadMapTileOne.findViewById(R.id.tile);
        avatar = roadMapTileOne.findViewById(R.id.avatar);
        triangle = roadMapTileOne.findViewById(R.id.triangle);

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
}
