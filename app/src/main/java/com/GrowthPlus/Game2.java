package com.GrowthPlus;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.GrowthPlus.customViews.QuizCircle;
import com.GrowthPlus.customViews.TopBar;
import com.GrowthPlus.dataAccessLayer.Quiz.QuizSchema;
import com.GrowthPlus.dataAccessLayer.QuizContent.QuizContent;
import com.GrowthPlus.dataAccessLayer.ScenarioGame.ScenarioGameSchema;
import com.GrowthPlus.dataAccessLayer.child.ChildSchema;
import com.GrowthPlus.roadMapActivity.RoadMapTwo;

import java.util.ArrayList;
import java.util.Collections;

import io.realm.Realm;
import io.realm.RealmList;

public class Game2 extends AppCompatActivity {
    final int MAX = 10;
    ChildSchema child;
    Realm realm;
    TopBar topBar;
    Button nextContent, introBackBtn;
    String childId, databaseGameId;
    ScenarioGameSchema game;
    int contentLength, counter, score;
    RealmList<QuizContent> contents;
    ArrayList<Integer> twenty = new ArrayList<>(20);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        init();

        introBackBtn.setOnClickListener(view -> {
            Intent lessonIntent = new Intent(Game2.this, RoadMapTwo.class);
            lessonIntent.putExtra("childIdentify", childId);
            startActivity(lessonIntent);
            this.finish();
        });
        setTopBar();
    }

    private void init(){
        Bundle extras = getIntent().getExtras();
        if(extras != null){
            childId = extras.getString("childId");
            databaseGameId = extras.getString("databaseQuizId");
        }

        realm = Realm.getDefaultInstance();
        child = realm.where(ChildSchema.class).equalTo("childId", childId).findFirst();
        game = realm.where(ScenarioGameSchema.class).equalTo("scenarioGameId", databaseGameId).findFirst();
        // contents = game.getContents();
        topBar = findViewById(R.id.topBar);
        introBackBtn = topBar.findViewById(R.id.goBackBtn);
        nextContent = findViewById(R.id.next_button);
        score = 0;

        for(int i = 0; i <= 19; i++)
            twenty.add(i);
        Collections.shuffle(twenty); // Randomize question selection
    }

    private void setTopBar(){
        topBar.setPoints(String.valueOf(child.getScore()));
        topBar.setToStar();
    }
}