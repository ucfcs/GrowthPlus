package com.fall2022_group20.roadMapActivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;

import com.fall2022_group20.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class RoadMapTwoActivity extends AppCompatActivity {

    ImageButton goBackButton;
    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_road_map_two);

        bottomNavigationView = findViewById(R.id.roadMapBottomNavigationView);

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
}