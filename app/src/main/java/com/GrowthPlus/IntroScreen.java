package com.GrowthPlus;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import com.GrowthPlus.customViews.TopBar;
import com.GrowthPlus.dataAccessLayer.Language.Translator;
import com.GrowthPlus.dataAccessLayer.Quiz.QuizSchema;
import com.GrowthPlus.dataAccessLayer.ScenarioGame.ScenarioGameSchema;
import com.GrowthPlus.dataAccessLayer.child.ChildSchema;
import com.GrowthPlus.fragment.WordImage;
import com.GrowthPlus.roadMapActivity.RoadMapFour;
import com.GrowthPlus.roadMapActivity.RoadMapOne;
import com.GrowthPlus.roadMapActivity.RoadMapThree;
import com.GrowthPlus.roadMapActivity.RoadMapTwo;

import io.realm.Realm;

public class IntroScreen extends AppCompatActivity {
    private Realm realm;
    private ChildSchema child;
    private String childId, data, image, name, whichOne, whichRoadMap;
    private TopBar topBar;
    private Button next, back;
    private QuizSchema quizContent;
    private ScenarioGameSchema gameContent;
    private int quizIndex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro_screen);
        init();
        setTopBar();

        // Create instance of shared preferences and save current language id
        SharedPreferences langPrefs = getSharedPreferences("LangPreferences", MODE_PRIVATE);
        String langId = langPrefs.getString("languageId", "frenchZero");
        // Create language translator and set up the Lesson string
        Translator trans = new Translator(langId);
        if(whichOne.equals("Quiz")){
            name = trans.getString("quiz") + " "+name;
        }
        else{
            name = trans.getString("game") + " "+name;
        }

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
            Intent intent = null;
            if(whichRoadMap.equals("1")){
                intent = new Intent(IntroScreen.this, RoadMapOne.class);
            }
            else if(whichRoadMap.equals("2")){
                intent = new Intent(IntroScreen.this, RoadMapTwo.class);
            }
            else if(whichRoadMap.equals("3")){
                intent = new Intent(IntroScreen.this, RoadMapThree.class);
            }
            else if(whichRoadMap.equals("4")){
                intent = new Intent(IntroScreen.this, RoadMapFour.class);
            }
            intent.putExtra("childIdentify", childId);
            startActivity(intent);
            this.finish();
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
            intent.putExtra("quizIndex", quizIndex);
            startActivity(intent);
            finish();
        });
    }

    private void init(){
        Bundle extras = getIntent().getExtras();
        if(extras != null){
            childId = extras.getString("childId");
            data = extras.getString("databaseQuizId");
            whichOne = extras.getString("whichOne");
            whichRoadMap = extras.getString("whichRoadMap");
            quizIndex = extras.getInt("quizIndex");
            // TODO: Need String to determine if quiz or game extras.getString | QuizID or GameID
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