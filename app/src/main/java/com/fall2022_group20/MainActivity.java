package com.fall2022_group20;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.fall2022_group20.roadMapActivity.RoadMapOneActivity;

import io.realm.Realm;

public class MainActivity extends AppCompatActivity {
    private Realm realm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        realm = Realm.getDefaultInstance();

        Button goToRoadMapOne = findViewById(R.id.roadMapOneBtn);
        goToRoadMapOne.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, RoadMapOneActivity.class);
            startActivity(intent);
        });
    }
}