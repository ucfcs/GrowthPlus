package com.GrowthPlus;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Interpolator;
import android.widget.Button;
import android.widget.TextView;

import com.GrowthPlus.customViews.Coconut;
import com.GrowthPlus.customViews.TopBar;
import com.GrowthPlus.dataAccessLayer.ScenarioGame.ScenarioGameContent;
import com.GrowthPlus.dataAccessLayer.ScenarioGame.ScenarioGameSchema;
import com.GrowthPlus.dataAccessLayer.child.ChildSchema;
import com.GrowthPlus.roadMapActivity.RoadMapFour;

import java.util.ArrayList;
import java.util.Collections;

import io.realm.Realm;
import io.realm.RealmList;

public class Game4 extends AppCompatActivity {
    final int MAX = 20;
    final int MIN_TO_PASS = 14;
    ChildSchema child;
    Realm realm;
    TopBar gameTopBar;
    Button introBackBtn;
    String childId, databaseGameId;
    ScenarioGameSchema game;
    int gameScore, counter, childScore;
    RealmList<ScenarioGameContent> contents;
    ArrayList<Integer> forty = new ArrayList<>(40);
    Coconut c1, c2, c3;
    TextView question;
    Handler handler;
    ObjectAnimator animator1, animator2, animator3, animator4;
    private MediaPlayer correct, incorrect, background;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game4);
        init();
        playBackground();

        introBackBtn.setOnClickListener(view -> {
            background.stop();
            setCompletedState(gameScore, MIN_TO_PASS);
            Intent lessonIntent = new Intent(Game4.this, RoadMapFour.class);
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
        childScore = child.getScore();
        game = realm.where(ScenarioGameSchema.class).equalTo("scenarioGameId", databaseGameId).findFirst();
        contents = game.getQuestions();
        gameTopBar = findViewById(R.id.topBar);
        introBackBtn = gameTopBar.findViewById(R.id.goBackBtn);
        gameScore = child.getRoadMapFour().getScenarioGame().getCurrentPoints();
        counter = 0;
        question = findViewById(R.id.gameQuestion);
        handler = new Handler();
        c1 = findViewById(R.id.coconut1);
        c2 = findViewById(R.id.coconut2);
        c3 = findViewById(R.id.coconut3);
        correct = MediaPlayer.create(this, R.raw.correct);
        incorrect = MediaPlayer.create(this, R.raw.incorrect);
        background = MediaPlayer.create(this, R.raw.wind);

        for(int i = 0; i <= 39; i++)
            forty.add(i);
        Collections.shuffle(forty); // Randomize question selection
    }

    private void playCorrect(){
        correct.start();
    }

    private void playIncorrect(){
        incorrect.start();
    }

    private void playBackground(){
        background.start();
        background.setLooping(true);
    }

    private void setTopBar(){
        gameTopBar.setPoints(String.valueOf(child.getScore()));
        gameTopBar.setToStar();
    }

    private void setContent(){
        question.setText(contents.get(forty.get(counter)).getQuestion());
        c1.setNumber(contents.get(forty.get(counter)).getOptionOne());
        c2.setNumber(contents.get(forty.get(counter)).getOptionTwo());
        c3.setNumber(contents.get(forty.get(counter)).getOptionThree());
        keepBouncing(c1, c2, c3);

        c1.setOnClickListener(v -> {
            if(c1.getNumber().equals(contents.get(forty.get(counter)).getAnswer())) { // CORRECT
                playCorrect();
                if(gameScore < MAX){
                    gameScore++;
                    childScore++;
                    setChildAndGameScoreInRealm(childScore, gameScore);

                    //Update top bar scoring
                    gameTopBar.setPoints(String.valueOf(childScore));
                }
            }
            else
                playIncorrect();
            deactivate();
            showCorrect();
        });

        c2.setOnClickListener(v -> {
            if(c2.getNumber().equals(contents.get(forty.get(counter)).getAnswer())) { // CORRECT
                playCorrect();
                if(gameScore < MAX){
                    gameScore++;
                    childScore++;
                    setChildAndGameScoreInRealm(childScore, gameScore);

                    //Update top bar scoring
                    gameTopBar.setPoints(String.valueOf(childScore));
                }
            }
            else
                playIncorrect();
            deactivate();
            showCorrect();
        });

        c3.setOnClickListener(v -> {
            if(c3.getNumber().equals(contents.get(forty.get(counter)).getAnswer())) { // CORRECT
                playCorrect();
                if(gameScore < MAX){
                    gameScore++;
                    childScore++;
                    setChildAndGameScoreInRealm(childScore, gameScore);

                    //Update top bar scoring
                    gameTopBar.setPoints(String.valueOf(childScore));
                }
            }
            else
                playIncorrect();
            deactivate();
            showCorrect();
        });
    }

    private void deactivate(){
        c1.setOnClickListener(null);
        c2.setOnClickListener(null);
        c3.setOnClickListener(null);
    }

    private void showCorrect(){
        animator2.end();
        animator3.end();
        animator4.end();

        if(c1.getNumber().equals(contents.get(forty.get(counter)).getAnswer())){
            bounceAnimation(c1);
            c2.setVisibility(View.INVISIBLE);
            c3.setVisibility(View.INVISIBLE);
        }
        else if(c2.getNumber().equals(contents.get(forty.get(counter)).getAnswer())){
            bounceAnimation(c2);
            c1.setVisibility(View.INVISIBLE);
            c3.setVisibility(View.INVISIBLE);
        }
        else{
            bounceAnimation(c3);
            c1.setVisibility(View.INVISIBLE);
            c2.setVisibility(View.INVISIBLE);
        }

        // Go to next question or Leave activity
        handler.postDelayed(() -> {
            counter++;
            if(counter >= MAX){
                background.stop();
                setCompletedState(gameScore, MIN_TO_PASS);
                Intent intent = new Intent(Game4.this, RoadMapFour.class); // TODO: Dynamically change location address
                intent.putExtra("childIdentify", childId);
                startActivity(intent);
            }
            else{
                animator1.end();
                c1.setVisibility(View.VISIBLE);
                c2.setVisibility(View.VISIBLE);
                c3.setVisibility(View.VISIBLE);
                setContent();
            }
        }, 4000);
    }

    private void bounceAnimation(View target){
        Interpolator interpolator = input -> getPowOut(input, 2);
        animator1 = ObjectAnimator.ofFloat(target, "translationY", 0, -600, 0);
        animator1.setInterpolator(interpolator);
        animator1.setStartDelay(50);
        animator1.setDuration(2000);
        animator1.setRepeatCount(2);
        animator1.start();
    }

    private void keepBouncing(View target1, View target2,  View target3){
        Interpolator interpolator = input -> getPowOut(input, 3);
        animator2 = ObjectAnimator.ofFloat(target1, "translationY", 0, 30, 0);
        animator3 = ObjectAnimator.ofFloat(target2, "translationY", 0, 20, 0);
        animator4 = ObjectAnimator.ofFloat(target3, "translationY", 0, 25, 0);
        animator2.setInterpolator(interpolator);
        animator3.setInterpolator(interpolator);
        animator4.setInterpolator(interpolator);
        animator2.setDuration(1000);
        animator3.setDuration(2000);
        animator4.setDuration(1500);
        animator2.setRepeatCount(30);
        animator3.setRepeatCount(15);
        animator4.setRepeatCount(20);
        animator2.start();
        animator3.start();
        animator4.start();
    }

    private float getPowOut(float time, double pow){
        return (float)((float)1 - Math.pow(1 - time, pow));
    }

    private void setChildAndGameScoreInRealm(int childScore, int gameScore){
        realm.executeTransactionAsync(realm1 -> {
            ChildSchema child = realm1.where(ChildSchema.class).equalTo("childId", childId).findFirst();
            assert child != null;
            child.setScore(childScore);
            child.getRoadMapFour().getScenarioGame().setCurrentPoints(gameScore);
        });
    }

    private void setCompletedState(int currentScore, int minToPass){
        if(currentScore >= minToPass){
            realm.executeTransactionAsync(realm1 -> {
                ChildSchema child = realm1.where(ChildSchema.class).equalTo("childId", childId).findFirst();
                assert child != null;
                if(!child.getRoadMapFour().getScenarioGame().getCompleted()){
                    child.getRoadMapFour().getScenarioGame().setCompleted(true);
                }
            });
        }
    }
}