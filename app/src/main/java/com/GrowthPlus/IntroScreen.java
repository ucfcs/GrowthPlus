package com.GrowthPlus;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import com.GrowthPlus.customViews.TopBar;
import com.GrowthPlus.dataAccessLayer.Quiz.QuizSchema;
import com.GrowthPlus.dataAccessLayer.ScenarioGame.ScenarioGameSchema;
import com.GrowthPlus.dataAccessLayer.child.ChildSchema;
import com.GrowthPlus.fragment.WordImage;
import com.GrowthPlus.roadMapActivity.RoadMapOne;

import io.realm.Realm;

public class IntroScreen extends AppCompatActivity {
    private Realm realm;
    private ChildSchema child;
    private String childId, data, image, name, whichOne, whichRoadMap;
    private TopBar topBar;
    private Button next, back;
    private QuizSchema quizContent;
    private ScenarioGameSchema gameContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro_screen);
        init();
        setTopBar();

        // Load fragment for Intro to Quiz/Game
        FragmentManager fragmentManager = getSupportFragmentManager();
        if (savedInstanceState == null) {
            Bundle bundle = new Bundle();
            bundle.putString("locationIntroText", name);
            bundle.putString("locationIntroImage", image);

            FragmentTransaction transaction = fragmentManager.beginTransaction();
            transaction.setReorderingAllowed(true);
            transaction.replace(R.id.frame_layout_intro_screen, WordImage.class, bundle);
            transaction.commit();
        }

        back.setOnClickListener(v -> {
            Intent intent = new Intent(IntroScreen.this, RoadMapOne.class);
            intent.putExtra("childIdentify", childId);
            startActivity(intent);
        });

        next.setOnClickListener(v -> {
            Intent intent = null;
            if(whichOne.equals("Quiz")){
                //for each quiz we want to check which roadMap we're on to set the correct
                //quiz class
                if(whichRoadMap.equals("1")){
                    intent = new Intent(IntroScreen.this, Quiz.class);
                }
                else if(whichRoadMap.equals("2")){
                    intent = new Intent(IntroScreen.this, Quiz2.class);
                }
                else if(whichRoadMap.equals("3")){
                    intent = new Intent(IntroScreen.this, Quiz3.class);
                }
                else if(whichRoadMap.equals("4")){
                    intent = new Intent(IntroScreen.this, Quiz4.class);
                }
            }
            else{
                //for each game we want to check which roadMap we're on to set
                //the correct Game class
                if(whichRoadMap.equals("1")){
                    intent = new Intent(IntroScreen.this, Game.class);
                }
                else if(whichRoadMap.equals("2")){
                    intent = new Intent(IntroScreen.this, Game2.class);
                }
                else if(whichRoadMap.equals("3")){
                    intent = new Intent(IntroScreen.this, Game3.class);
                }
                else if(whichRoadMap.equals("4")){
                    intent = new Intent(IntroScreen.this, Game4.class);
                }
            }
            intent.putExtra("childId", childId);
            intent.putExtra("databaseQuizId", data);
            startActivity(intent);
        });
    }

    private void init(){
        Bundle extras = getIntent().getExtras();
        if(extras != null){
            childId = extras.getString("childId");
            data = extras.getString("databaseQuizId");
            whichOne = extras.getString("whichOne");
            whichRoadMap = extras.getString("whichRoadMap");
        }
        realm = Realm.getDefaultInstance();
        child = realm.where(ChildSchema.class).equalTo("childId", childId).findFirst();
        topBar = findViewById(R.id.topBarIntroScreen);
        back = topBar.findViewById(R.id.goBackBtn);
        next = findViewById(R.id.next_button_intro);

        // Quiz or Game database
        if(whichOne.equals("Quiz")){
            quizContent = realm.where(QuizSchema.class).equalTo("quizId", data).findFirst();
            image = quizContent.getImage();
            name = quizContent.getQuizName();
        }
        else if(whichOne.equals("Game")){
            gameContent = realm.where(ScenarioGameSchema.class).equalTo("scenarioGameId", data).findFirst();
            image = gameContent.getImage();
            name = gameContent.getScenarioGameName();
        }
        else{
            Log.i("ERROR", "NOT QUIZ OR GAME");
        }
    }

    private void setTopBar(){
        topBar.setPoints(String.valueOf(child.getScore()));
        if(whichOne.equals("Quiz")){
            topBar.setToCircle();
        }
        else if(whichOne.equals("Game")){
            topBar.setToStar();
        }
    }
}