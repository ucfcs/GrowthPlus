package com.GrowthPlus;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Interpolator;
import android.widget.Button;
import android.widget.TextView;

import com.GrowthPlus.customViews.Banana;
import com.GrowthPlus.customViews.Fish;
import com.GrowthPlus.customViews.TopBar;
import com.GrowthPlus.dataAccessLayer.ScenarioGame.ScenarioGameContent;
import com.GrowthPlus.dataAccessLayer.ScenarioGame.ScenarioGameSchema;
import com.GrowthPlus.dataAccessLayer.child.ChildSchema;
import com.GrowthPlus.roadMapActivity.RoadMapOne;
import com.GrowthPlus.roadMapActivity.RoadMapTwo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import io.realm.Realm;
import io.realm.RealmList;

public class Game2 extends AppCompatActivity {
    final int MAX = 20;
    ChildSchema child;
    Realm realm;
    TopBar topBar;
    Button introBackBtn;
    String childId, databaseGameId;
    ScenarioGameSchema game;
    int score, counter;
    RealmList<ScenarioGameContent> contents;
    ArrayList<Integer> forty = new ArrayList<>(40);
    Banana b1, b2, b3, correctB;
    TextView question;
    Random rand;
    Handler handler;
    ObjectAnimator animator1, animator2, animator3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game2);
        init();

        introBackBtn.setOnClickListener(view -> {
            Intent lessonIntent = new Intent(Game2.this, RoadMapTwo.class);
            lessonIntent.putExtra("childIdentify", childId);
            startActivity(lessonIntent);
            this.finish();
        });
        setTopBar();
        setContent();
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
        contents = game.getQuestions();
        topBar = findViewById(R.id.topBar);
        introBackBtn = topBar.findViewById(R.id.goBackBtn);
        score = 0;
        counter = 0;
        question = findViewById(R.id.gameQuestion);
        rand = new Random();
        handler = new Handler();
        b1 = findViewById(R.id.banana1);
        b2 = findViewById(R.id.banana2);
        b3 = findViewById(R.id.banana3);
        correctB = findViewById(R.id.correctBanana);

        for(int i = 0; i <= 39; i++)
            forty.add(i);
        Collections.shuffle(forty); // Randomize question selection
    }

    private void setTopBar(){
        topBar.setPoints(String.valueOf(child.getScore()));
        topBar.setToStar();
    }

    private void setContent(){
        correctB.setVisibility(View.INVISIBLE);
        correctB.setNumber(contents.get(forty.get(counter)).getAnswer());
        question.setText(contents.get(forty.get(counter)).getQuestion());
        b1.setNumber(contents.get(forty.get(counter)).getOptionOne());
        b2.setNumber(contents.get(forty.get(counter)).getOptionTwo());
        b3.setNumber(contents.get(forty.get(counter)).getOptionThree());

        bounceAnimation(b1, b2, b3);

        b1.setOnClickListener(v -> {
            if(b1.getNumber().equals(contents.get(forty.get(counter)).getAnswer())) { // CORRECT
                score++;
            }
            deactivate();
            showCorrect();
        });

        b2.setOnClickListener(v -> {
            if(b2.getNumber().equals(contents.get(forty.get(counter)).getAnswer())) { // CORRECT
                score++;
            }
            deactivate();
            showCorrect();
        });

        b3.setOnClickListener(v -> {
            if(b3.getNumber().equals(contents.get(forty.get(counter)).getAnswer())) { // CORRECT
                score++;
            }
            deactivate();
            showCorrect();
        });
    }

    private void deactivate(){
        b1.setOnClickListener(null);
        b2.setOnClickListener(null);
        b3.setOnClickListener(null);
    }

    private void showCorrect(){
        animator1.end();
        animator2.end();
        animator3.end();
        correctB.setVisibility(View.VISIBLE);
        b1.setVisibility(View.INVISIBLE);
        b2.setVisibility(View.INVISIBLE);
        b3.setVisibility(View.INVISIBLE);
        handler.postDelayed(() -> {
            counter++;
            if(counter >= MAX){
                if(score >= 14){
                    // TODO: Make Game isCompleted(true), load next RoadMap, and update ChildScore
                }

                Intent intent = new Intent(Game2.this, RoadMapOne.class); // TODO: Dynamically change location address
                intent.putExtra("childIdentify", childId);
                startActivity(intent);
            }
            else{
                b1.setVisibility(View.VISIBLE);
                b2.setVisibility(View.VISIBLE);
                b3.setVisibility(View.VISIBLE);
                correctB.setVisibility(View.INVISIBLE);
                setContent();
            }
        }, 5000);
    }

    private void bounceAnimation(View target1, View target2,  View target3){
        Interpolator interpolator = input -> getPowOut(input, 3);
        animator1 = ObjectAnimator.ofFloat(target1, "translationY", 0, 35, 0);
        animator2 = ObjectAnimator.ofFloat(target2, "translationY", 0, 35, 0);
        animator3 = ObjectAnimator.ofFloat(target3, "translationY", 0, 35, 0);
        animator1.setInterpolator(interpolator);
        animator2.setInterpolator(interpolator);
        animator3.setInterpolator(interpolator);
        animator1.setStartDelay(200);
        animator2.setStartDelay(200);
        animator3.setStartDelay(200);
        animator1.setDuration(1000);
        animator2.setDuration(2000);
        animator3.setDuration(1500);
        animator1.setRepeatCount(30);
        animator2.setRepeatCount(30);
        animator3.setRepeatCount(30);
        animator1.start();
        animator2.start();
        animator3.start();
    }

    private float getPowOut(float time, double pow){
        return (float)((float)1 - Math.pow(1 - time, pow));
    }
}