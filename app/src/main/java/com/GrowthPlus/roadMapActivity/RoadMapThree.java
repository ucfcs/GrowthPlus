package com.GrowthPlus.roadMapActivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.GrowthPlus.ChildPortal;
import com.GrowthPlus.IntroScreen;
import com.GrowthPlus.Lesson3;
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
import java.util.Objects;

import io.realm.Realm;
import io.realm.RealmList;

public class RoadMapThree extends AppCompatActivity implements View.OnClickListener{

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

        Log.i("childName:", child.getName());
        Log.i("rooadmapCompleted:", String.valueOf(child.getRoadMapThree()));
        init(child);

//        initState();
        // TODO: Check the isLocked in roadmapmap object if lock, don't set the tiles
        roadMapThreeLessonTrail.unLockRoadMap();
        setLessonTiles(child);

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


        childRoadMapThree = child.getRoadMapThree();
        lessonCompleted = childRoadMapThree.getLessonsCompleted();
        roadMapLessons = childRoadMapThree.getRoadMapLessons();
        roadMapQuizes = childRoadMapThree.getRoadMapQuizzes();
        game = childRoadMapThree.getScenarioGame();

        tile1 = roadMapThreeLessonTrail.getRoadMapTile1();
        tile2 = roadMapThreeLessonTrail.getRoadMapTile2();
        tile3 = roadMapThreeLessonTrail.getRoadMapTile3();
        tile4 = roadMapThreeLessonTrail.getRoadMapTile4();
        tile5 = roadMapThreeLessonTrail.getRoadMapTile5();
        tile6 = roadMapThreeLessonTrail.getRoadMapTile6();
        tile7 = roadMapThreeLessonTrail.getRoadMapTile7();
        tile8 = roadMapThreeLessonTrail.getRoadMapTile8();
        tile9 = roadMapThreeLessonTrail.getRoadMapTile9();
        tile10 = roadMapThreeLessonTrail.getRoadMapTile10();
        tile11 = roadMapThreeLessonTrail.getRoadMapTile11();
        tile12 = roadMapThreeLessonTrail.getRoadMapTile12();
        tile13 = roadMapThreeLessonTrail.getRoadMapTile13();

        mapTiles = new HashMap<>();
        mapLessonId = new HashMap<>();
        mapRoadMapTiles();
    }

    private void initState(){
        roadMapThreeLessonTrail.setAlpha(.7f);
    }

    private void mapRoadMapTiles(){
        mapTiles.put(0, tile1);
        mapTiles.put(1, tile2);
        mapTiles.put(2, tile3);
        mapTiles.put(3, tile5);
        mapTiles.put(4, tile6);
        mapTiles.put(5, tile7);
        mapTiles.put(6, tile8);
        mapTiles.put(7, tile10);
        mapTiles.put(8, tile11);
        mapTiles.put(9, tile12);
    }

    private void setLessonTiles(ChildSchema child){

        // Loop thru the lessons completed
        for(int i = 0; i <= lessonCompleted; i++){
            RoadMapLesson roadMapLessonTemp = roadMapLessons.get(i);
            assert roadMapLessonTemp != null;
            String dataBaseLessonId = roadMapLessonTemp.getDatabaseLessonId();
            Log.i("roadmapCompleted:", dataBaseLessonId);
            Integer tileIdTemp = Objects.requireNonNull(mapTiles.get(i)).getId();

            if(roadMapLessonTemp.getCompleted()){
                Objects.requireNonNull(mapTiles.get(i)).setCompletedState();
            }

            if(roadMapLessonTemp.getCurrent()){
                roadMapThreeLessonTrail.setSelectedState(Objects.requireNonNull(mapTiles.get(i)), child);
            }

            Objects.requireNonNull(mapTiles.get(i)).setOnClickListener(this);
            mapLessonId.put(tileIdTemp, dataBaseLessonId);
        }
        //this was previously commented------------
        tile4.setOnClickListener(this);
        if(roadMapQuizes.get(0).getCompleted()){
            tile4.setCompletedState();
        }
        if(roadMapQuizes.get(0).getCurrent()){
            roadMapThreeLessonTrail.setSelectedState(tile4, child);
        }

        tile9.setOnClickListener(this);
        if(roadMapQuizes.get(1).getCompleted()){
            tile9.setCompletedState();
        }
        if(roadMapQuizes.get(1).getCurrent()){
            roadMapThreeLessonTrail.setSelectedState(tile9, child);
        }

        tile13.setOnClickListener(this);
        if(game.getCompleted()){
            tile13.setCompletedState();
        }
        //-------------------------------------------
    }

    /*
     * These may all look the same, but the big difference is the id of the tile component
     * Each tile component id maps to its corresponding Lesson id
     * The Lesson ids are populated in a hashmap when looping through the completed Lesson in the roadmap
     * */
    @Override
    public void onClick(View view) {
        int viewId = view.getId();

        if(viewId == goBackButton.getId()){
            IntentIntro = new Intent(RoadMapThree.this, Lesson3.class);
            Intent lessonIntent = new Intent(RoadMapThree.this, ChildPortal.class);
            lessonIntent.putExtra("childIdLandingPage", childID);
            startActivity(lessonIntent);
        }

        else if(viewId == tile1.getId()){
            IntentIntro = new Intent(RoadMapThree.this, Lesson3.class);
            IntentIntro.putExtra("childId", childID);
            IntentIntro.putExtra("dataBaseLessonId", mapLessonId.get(viewId));
            startActivity(IntentIntro);
        }

        else if(viewId == tile2.getId()){
            IntentIntro = new Intent(RoadMapThree.this, Lesson3.class);
            IntentIntro.putExtra("childId", childID);
            IntentIntro.putExtra("dataBaseLessonId", mapLessonId.get(viewId));
            startActivity(IntentIntro);
        }

        else if(viewId == tile3.getId()){
            IntentIntro = new Intent(RoadMapThree.this, Lesson3.class);
            IntentIntro.putExtra("childId", childID);
            IntentIntro.putExtra("dataBaseLessonId", mapLessonId.get(viewId));
            startActivity(IntentIntro);
        }

        else if(viewId == tile4.getId()){
            IntentIntro = new Intent(RoadMapThree.this, IntroScreen.class);
            IntentIntro.putExtra("childId", childID);
            IntentIntro.putExtra("databaseQuizId", roadMapQuizes.get(0).getDatabaseQuizId());
            IntentIntro.putExtra("whichOne", "Quiz");
            IntentIntro.putExtra("whichRoadMap", "3");
            startActivity(IntentIntro);
        }

        else if(viewId == tile5.getId()){
            IntentIntro = new Intent(RoadMapThree.this, Lesson3.class);
            IntentIntro.putExtra("childId", childID);
            IntentIntro.putExtra("dataBaseLessonId", mapLessonId.get(viewId));
            startActivity(IntentIntro);
        }

        else if(viewId == tile6.getId()){
            IntentIntro = new Intent(RoadMapThree.this, Lesson3.class);
            IntentIntro.putExtra("childId", childID);
            IntentIntro.putExtra("dataBaseLessonId", mapLessonId.get(viewId));
            startActivity(IntentIntro);
        }

        else if(viewId == tile7.getId()){
            IntentIntro = new Intent(RoadMapThree.this, Lesson3.class);
            IntentIntro.putExtra("childId", childID);
            IntentIntro.putExtra("dataBaseLessonId", mapLessonId.get(viewId));
            startActivity(IntentIntro);
        }

        else if(viewId == tile8.getId()){
            IntentIntro = new Intent(RoadMapThree.this, Lesson3.class);
            IntentIntro.putExtra("childId", childID);
            IntentIntro.putExtra("dataBaseLessonId", mapLessonId.get(viewId));
            startActivity(IntentIntro);
        }

        else if(viewId == tile9.getId()){
            IntentIntro = new Intent(RoadMapThree.this, IntroScreen.class);
            IntentIntro.putExtra("childId", childID);
            IntentIntro.putExtra("databaseQuizId", roadMapQuizes.get(1).getDatabaseQuizId());
            IntentIntro.putExtra("whichOne", "Quiz");
            IntentIntro.putExtra("whichRoadMap", "3");
            startActivity(IntentIntro);
        }

        else if(viewId == tile10.getId()){
            IntentIntro = new Intent(RoadMapThree.this, Lesson3.class);
            IntentIntro.putExtra("childId", childID);
            IntentIntro.putExtra("dataBaseLessonId", mapLessonId.get(viewId));
            startActivity(IntentIntro);
        }

        else if(viewId == tile11.getId()){
            IntentIntro = new Intent(RoadMapThree.this, Lesson3.class);
            IntentIntro.putExtra("childId", childID);
            IntentIntro.putExtra("dataBaseLessonId", mapLessonId.get(viewId));
            startActivity(IntentIntro);
        }

        else if(viewId == tile12.getId()){
            IntentIntro = new Intent(RoadMapThree.this, Lesson3.class);
            IntentIntro.putExtra("childId", childID);
            IntentIntro.putExtra("dataBaseLessonId", mapLessonId.get(viewId));
            startActivity(IntentIntro);
        }

        else if(viewId == tile13.getId()){
            IntentIntro = new Intent(RoadMapThree.this, IntroScreen.class);
            IntentIntro.putExtra("childId", childID);
            IntentIntro.putExtra("databaseQuizId", game.getDatabaseScenarioGameId());
            IntentIntro.putExtra("whichOne", "Game");
            IntentIntro.putExtra("whichRoadMap", "3");
            startActivity(IntentIntro);
        }
    }
}
