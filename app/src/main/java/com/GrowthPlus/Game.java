package com.GrowthPlus;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.GrowthPlus.customViews.TopBar;
import com.GrowthPlus.dataAccessLayer.child.ChildSchema;

import io.realm.Realm;

public class Game extends AppCompatActivity {

    ChildSchema child;
    Realm realm;
    TopBar topBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        init();
        setTopBar();
    }

    private void init(){
        topBar = findViewById(R.id.topBar);
    }

    private void setTopBar(){
        //topBar.setPoints(String.valueOf(child.getScore()));
        topBar.setToStar();
    }
}