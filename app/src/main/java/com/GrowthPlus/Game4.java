package com.GrowthPlus;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.media.MediaPlayer;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Interpolator;
import android.widget.Button;
import android.widget.TextView;

import com.GrowthPlus.customViews.Coconut;
import com.GrowthPlus.customViews.CustomTimerComponent;
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
    int gameScore, counter, childScore, numberCorrect;
    RealmList<ScenarioGameContent> contents;
    ArrayList<Integer> forty = new ArrayList<>(40);
    Coconut c1, c2, c3;
    TextView question;
    Handler handler;
    ObjectAnimator animator1, animator2, animator3, animator4;
    private MediaPlayer correct, incorrect, background;
    ConstraintLayout topBarBackground;
    float height1;
    private CountDownTimer countDownTimer;
    private CustomTimerComponent customTimerComponent;
    private final AlphaAnimation buttonClick = new AlphaAnimation(1F, 0.8F);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game4);
        init();
        playBackground();

        introBackBtn.setOnClickListener(view -> {
            view.startAnimation(buttonClick);
            handler.removeCallbacksAndMessages(null);
            setCompletedState(gameScore);
            background.stop();
            countDownTimer.cancel(); //since the user is exiting the game we need to stop the timer
        });
        setTopBar();
        setContent();
        setTimer();
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
        gameTopBar = findViewById(R.id.game4TopBar);
        topBarBackground = findViewById(R.id.topBar);
        introBackBtn = gameTopBar.findViewById(R.id.goBackBtn);
        gameScore = child.getRoadMapFour().getScenarioGame().getCurrentPoints();
        counter = 0;
        numberCorrect = 0;
        question = findViewById(R.id.gameQuestion);
        handler = new Handler();
        c1 = findViewById(R.id.coconut1);
        c2 = findViewById(R.id.coconut2);
        c3 = findViewById(R.id.coconut3);
        correct = MediaPlayer.create(this, R.raw.correct);
        correct.setVolume((float)3, (float)3);
        incorrect = MediaPlayer.create(this, R.raw.incorrect);
        incorrect.setVolume((float)4, (float)4);
        background = MediaPlayer.create(this, R.raw.wind);

        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int height = displayMetrics.heightPixels;
        height1 = (float)height;

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
        topBarBackground.setBackgroundColor(Color.rgb(232, 160, 78));
        gameTopBar.setShapeColor(Color.rgb(96, 163, 200));
        gameTopBar.setPointIconBackground(Color.rgb( 232, 160, 78));
        gameTopBar.setPointsTextColor(Color.rgb(96, 163, 200));
    }

    private void setContent(){
        question.setText(contents.get(forty.get(counter)).getQuestion());
        c1.setNumber(contents.get(forty.get(counter)).getOptionOne());
        c2.setNumber(contents.get(forty.get(counter)).getOptionTwo());
        c3.setNumber(contents.get(forty.get(counter)).getOptionThree());
        keepBouncing(c1, c2, c3);

        c1.setOnClickListener(v -> {
            countDownTimer.cancel(); //the user selected an answer so we can stop the timer

            if(c1.getNumber().equals(contents.get(forty.get(counter)).getAnswer())) { // CORRECT
                fallAnimation(c1);
                playCorrect();
                numberCorrect++;
                if(gameScore < MAX){
                    gameScore++;
                    childScore++;
                    setChildAndGameScoreInRealm(childScore, gameScore);

                    //Update top bar scoring
                    gameTopBar.setPoints(String.valueOf(childScore));
                }
            }
            else {
                playIncorrect();
                wrongAnimation(c1, c2, c3);
            }
            deactivate();
            showCorrect();
        });

        c2.setOnClickListener(v -> {
            countDownTimer.cancel(); //the user selected an answer so we can stop the timer

            if(c2.getNumber().equals(contents.get(forty.get(counter)).getAnswer())) { // CORRECT
                numberCorrect++;
                fallAnimation(c2);
                playCorrect();
                if(gameScore < MAX){
                    gameScore++;
                    childScore++;
                    setChildAndGameScoreInRealm(childScore, gameScore);

                    //Update top bar scoring
                    gameTopBar.setPoints(String.valueOf(childScore));
                }
            }
            else {
                playIncorrect();
                wrongAnimation(c1, c2, c3);
            }
            deactivate();
            showCorrect();
        });

        c3.setOnClickListener(v -> {
            countDownTimer.cancel(); //the user selected an answer so we can stop the timer

            if(c3.getNumber().equals(contents.get(forty.get(counter)).getAnswer())) { // CORRECT
                numberCorrect++;
                fallAnimation(c3);
                playCorrect();
                if(gameScore < MAX){
                    gameScore++;
                    childScore++;
                    setChildAndGameScoreInRealm(childScore, gameScore);

                    //Update top bar scoring
                    gameTopBar.setPoints(String.valueOf(childScore));
                }
            }
            else {
                playIncorrect();
                wrongAnimation(c1, c2, c3);
            }
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
        handler.postDelayed(() -> {
            counter++;
            if(counter >= MAX){
                background.stop();
                //setCompletedState(gameScore);
                Intent lessonIntent = new Intent(Game4.this, Results.class);
                lessonIntent.putExtra("childId", childId);
                lessonIntent.putExtra("whichOne", "Game");
                lessonIntent.putExtra("points", gameScore);
                lessonIntent.putExtra("max", MAX);
                lessonIntent.putExtra("whichRoadMap", "Four");
                if(gameScore >= MIN_TO_PASS){
                    updateGameAndRoadMapState();
                    lessonIntent.putExtra("passOrNot", 1);
                }
                else{
                    lessonIntent.putExtra("passOrNot", 0);
                }
                startActivity(lessonIntent);
                this.finish();
            }
            else{
                c1.clearAnimation();
                c2.clearAnimation();
                c3.clearAnimation();
                c1.animate().translationX(0);
                c1.animate().translationY(0);
                c1.animate().setDuration(500);
                c2.animate().translationX(0);
                c2.animate().translationY(0);
                c2.animate().setDuration(500);
                c3.animate().translationX(0);
                c3.animate().translationY(0);
                c3.animate().setDuration(500);
                setContent();
                setTimer();
            }
        }, 2500);
    }

    private void fallAnimation(View target){
        animator2.end();
        animator3.end();
        animator4.end();

        animator1 = ObjectAnimator.ofFloat(target, "translationY", (float)(0.4 * height1));
        animator1.setDuration(1500);
        animator1.start();
    }

    private void keepBouncing(View target1, View target2,  View target3){
        Interpolator interpolator = input -> getPowOut(input, 2);
        animator2 = ObjectAnimator.ofFloat(target1, "translationY", 0, 10, 0);
        animator3 = ObjectAnimator.ofFloat(target2, "translationY", 0, 10, 0);
        animator4 = ObjectAnimator.ofFloat(target3, "translationY", 0, 10, 0);
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

    private void wrongAnimation(View target1, View target2, View target3){
        animator2 = ObjectAnimator.ofFloat(target1, "translationX", -1000f);
        animator3 = ObjectAnimator.ofFloat(target2, "translationX", -1500f);
        animator4 = ObjectAnimator.ofFloat(target3, "translationX", -2000f);
        animator2.setDuration(1500);
        animator3.setDuration(1500);
        animator4.setDuration(1500);
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

    private void setCompletedState(int currentScore){
        if(currentScore >= MIN_TO_PASS){
           updateGameAndRoadMapState();
        }
           stayCurrentRoadMap();
    }

    private void updateGameAndRoadMapState(){
        realm.executeTransactionAsync(realm1 -> {
            ChildSchema child = realm1.where(ChildSchema.class).equalTo("childId", childId).findFirst();
            assert child != null;

            if(!child.getRoadMapFour().getCompleted()){
                child.getRoadMapFour().setCurrent(false);
                child.getRoadMapFour().setCompleted(true);
            }
            if(!child.getRoadMapFour().getScenarioGame().getCompleted()){
                child.getRoadMapFour().getScenarioGame().setCompleted(true);
                child.getRoadMapFour().getScenarioGame().setCurrent(false);
            }
        });
    }

    private void stayCurrentRoadMap(){
        Intent intent = new Intent(Game4.this, RoadMapFour.class);
        intent.putExtra("childIdentify", childId);
        startActivity(intent);
        this.finish();
    }

    @Override
    protected void onDestroy() {
        if (!realm.isClosed()) realm.close();
        super.onDestroy();
    }

    //sets a timer that counts down from 30 and moves on if the user doesn't choose an answer in time
    private void setTimer() {
        customTimerComponent = findViewById(R.id.countdownTimer);
        countDownTimer = new CountDownTimer(21000, 1000) {
            public void onTick(long millisUntilFinished) {
                customTimerComponent.setTimerText(""+millisUntilFinished / 1000);
            }
            public void onFinish() {
                countDownTimer.cancel();
                playIncorrect();
                wrongAnimation(c1, c2, c3);
                deactivate();
                showCorrect();
            }
        }.start();
    }
}