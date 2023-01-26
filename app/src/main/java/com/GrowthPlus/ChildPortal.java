package com.GrowthPlus;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.GrowthPlus.roadMapActivity.RoadMapOne;

public class ChildPortal extends AppCompatActivity {

    Button learnButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_child_portal);

        Button backBtn = (Button) findViewById(R.id.backChild);

        backBtn.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                startActivity(new Intent(ChildPortal.this, MainActivity.class));
            }
        });

        Button leaderboardBtn = (Button) findViewById(R.id.leaderboardBtn);

        leaderboardBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ChildPortal.this, Leaderboard.class));
            }
        });

        learnButton = findViewById(R.id.learnBtn);
        learnButton.setOnClickListener(view -> {
            Intent intent = new Intent(this, RoadMapOne.class);
            startActivity(intent);
        });
    }
}