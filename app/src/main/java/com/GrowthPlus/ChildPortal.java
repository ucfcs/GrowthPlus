package com.GrowthPlus;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.GrowthPlus.roadMapActivity.RoadMapOneActivity;

public class ChildPortal extends AppCompatActivity {

    Button learnButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_child_portal);

        Button backBtn = findViewById(R.id.backChild);

        backBtn.setOnClickListener(v -> startActivity(new Intent(ChildPortal.this, MainActivity.class)));

        Button leaderboardBtn = findViewById(R.id.leaderboardBtn);

        leaderboardBtn.setOnClickListener(v -> startActivity(new Intent(ChildPortal.this, Leaderboard.class)));

        learnButton = findViewById(R.id.learnBtn);
        learnButton.setOnClickListener(view -> {
            Intent intent = new Intent(this, RoadMapOneActivity.class);
            startActivity(intent);
        });
    }
}