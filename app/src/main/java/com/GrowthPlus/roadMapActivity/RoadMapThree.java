package com.GrowthPlus.roadMapActivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.GrowthPlus.R;
import com.GrowthPlus.customViews.RoadMapLessonTrail;
import com.GrowthPlus.customViews.RoadMapTile;
import com.GrowthPlus.customViews.TopBar;
import com.GrowthPlus.dataAccessLayer.ChildRoadMap.ChildRoadMap;
import com.GrowthPlus.dataAccessLayer.RoadMapLesson.RoadMapLesson;
import com.GrowthPlus.dataAccessLayer.RoadMapQuiz.RoadMapQuiz;
import com.GrowthPlus.dataAccessLayer.RoadMapScenarioGame.RoadMapScenarioGame;
import com.GrowthPlus.dataAccessLayer.child.ChildSchema;
import com.GrowthPlus.dataAccessLayer.child.ChildSchemaService;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.HashMap;

import io.realm.Realm;
import io.realm.RealmList;

public class RoadMapThree extends AppCompatActivity {

    Button goBackButton;
    BottomNavigationView bottomNavigationView;
    RoadMapLessonTrail roadMapThreeLessonTrail;
    ConstraintLayout roadMapThree;
    RoadMapTile tile1, tile2, tile3, tile4, tile5, tile6, tile7, tile8, tile9, tile10, tile11, tile12, tile13;
    TopBar topBarThree;
    Intent IntentIntro; // Leads to Lesson page
    String childID;
    ChildSchemaService childSchemaService;
    Realm realm;
    ChildRoadMap childRoadMapThree;
    HashMap<Integer, RoadMapTile> mapTiles;
    Integer lessonCompleted;
    RealmList<RoadMapLesson> roadMapLessons;
    RealmList<RoadMapQuiz> roadMapQuizes;
    RoadMapScenarioGame game;
    HashMap<Integer, String> mapLessonId;

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
