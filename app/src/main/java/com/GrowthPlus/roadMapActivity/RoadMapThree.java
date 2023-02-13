package com.GrowthPlus.roadMapActivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.GrowthPlus.R;
import com.GrowthPlus.customViews.RoadMapLessonTrail;
import com.GrowthPlus.customViews.TopBar;
import com.GrowthPlus.dataAccessLayer.child.ChildSchema;
import com.GrowthPlus.dataAccessLayer.child.ChildSchemaService;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import io.realm.Realm;

public class RoadMapThree extends AppCompatActivity {

    Button goBackButton;
    BottomNavigationView bottomNavigationView;
    ConstraintLayout roadMapThree;
    RoadMapLessonTrail roadMapThreeLessonTrail;
    TopBar topBarThree;
    String childID;
    ChildSchemaService childSchemaService;
    Realm realm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_road_map_three);

        realm = Realm.getDefaultInstance();
        childSchemaService = new ChildSchemaService(realm);

        Bundle extras = getIntent().getExtras();
        if(extras != null){
            childID = extras.getString("childIdentify");
        }
        ChildSchema child = childSchemaService.getChildSchemaById(childID);
        init(child);
        initState();

        bottomNavigationView.setSelectedItemId(R.id.roadMap3item);
        bottomNavigationView.setOnItemSelectedListener(item -> {
            switch (item.getItemId()){
                case R.id.roadMap1item:{
                    Intent intent = new Intent(getApplicationContext(), RoadMapOne.class);
                    intent.putExtra("childIdentify", childID);
                    startActivity(intent);
                    overridePendingTransition(0,0);
                    return true;

                }
                case R.id.roadMap2item:{
                    Intent intent = new Intent(getApplicationContext(), RoadMapTwo.class);
                    intent.putExtra("childIdentify", childID);
                    startActivity(intent);
                    overridePendingTransition(0,0);
                    return true;
                }
                case R.id.roadMap3item:{
                    return true;
                }

                case R.id.roadMap4item:{
                    Intent intent = new Intent(getApplicationContext(), RoadMapFour.class);
                    intent.putExtra("childIdentify", childID);
                    startActivity(intent);
                    overridePendingTransition(0,0);
                    return true;
                }
            }
            return false;
        });

        goBackButton.setOnClickListener(v -> onBackPressed());

    }

    private void init(ChildSchema child){
        bottomNavigationView = findViewById(R.id.roadMapBottomNavigationView);
        roadMapThree = findViewById(R.id.roadMapThree);
        topBarThree = roadMapThree.findViewById(R.id.topBarThree);
        topBarThree.setPoints(String.valueOf(child.getScore()));
        goBackButton = topBarThree.findViewById(R.id.goBackBtn);
        roadMapThreeLessonTrail = roadMapThree.findViewById(R.id.roadMapThreeLessonTrail);

    }

    private void initState(){
        roadMapThreeLessonTrail.setAlpha(.7f);
    }
}