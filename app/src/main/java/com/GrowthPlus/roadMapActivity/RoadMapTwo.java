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

public class RoadMapTwo extends AppCompatActivity {
    Button goBackButton;
    BottomNavigationView bottomNavigationView;
    ConstraintLayout roadMapTwo;
    RoadMapLessonTrail roadMapTwoLessonTrail;
    TopBar topBarTwo;
    String childID;
    ChildSchemaService childSchemaService;
    Realm realm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_road_map_two);

        realm = Realm.getDefaultInstance();
        childSchemaService = new ChildSchemaService(realm);

        Bundle extras = getIntent().getExtras();
        if(extras != null){
            childID = extras.getString("childIdentify");
        }
        ChildSchema child = childSchemaService.getChildSchemaById(childID);
        init(child);
        initState();

        bottomNavigationView.setSelectedItemId(R.id.roadMap2item);
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
                    return true;
                }
                case R.id.roadMap3item:{
                    Intent intent = new Intent(getApplicationContext(), RoadMapThree.class);
                    intent.putExtra("childIdentify", childID);
                    startActivity(intent);
                    overridePendingTransition(0,0);
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
        roadMapTwo = findViewById(R.id.roadMapTwo);
        topBarTwo = roadMapTwo.findViewById(R.id.topBarTwo);
        topBarTwo.setPoints(String.valueOf(child.getScore()));
        goBackButton = topBarTwo.findViewById(R.id.goBackBtn);
        roadMapTwoLessonTrail = roadMapTwo.findViewById(R.id.roadMapTwoLessonTrail);

    }

    private void initState(){
        roadMapTwoLessonTrail.setAlpha(.7f);
    }
}
