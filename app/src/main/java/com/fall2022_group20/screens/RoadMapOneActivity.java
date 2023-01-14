package com.fall2022_group20.screens;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import com.fall2022_group20.R;

public class RoadMapOneActivity extends AppCompatActivity {

    ImageButton goBackButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_roadmap_one);

        goBackButton = findViewById(R.id.goBackBtn);
        goBackButton.setOnClickListener(v -> onBackPressed());

    }
}
