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
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Interpolator;
import android.widget.Button;
import android.widget.TextView;

import com.GrowthPlus.customViews.Banana;
import com.GrowthPlus.customViews.CircleTimer;
import com.GrowthPlus.customViews.TopBar;
import com.GrowthPlus.dataAccessLayer.ChildRoadMap.ChildRoadMap;
import com.GrowthPlus.dataAccessLayer.RoadMapLesson.RoadMapLesson;
import com.GrowthPlus.dataAccessLayer.ScenarioGame.ScenarioGameContent;
import com.GrowthPlus.dataAccessLayer.ScenarioGame.ScenarioGameSchema;
import com.GrowthPlus.dataAccessLayer.child.ChildSchema;
import com.GrowthPlus.roadMapActivity.RoadMapThree;
import com.GrowthPlus.roadMapActivity.RoadMapTwo;

import java.util.ArrayList;
import java.util.Collections;

import io.realm.Realm;
import io.realm.RealmList;

public class Game2 extends AppCompatActivity {
    final int MAX = 20;
    final int MIN_TO_PASS = 14;
    final int TOTALTIMER = 21000;
    final int INTERVAL = 100;
    ChildSchema child;
    Realm realm;
    TopBar gameTopBar;
    Button introBackBtn;
    String childId, databaseGameId;
    ScenarioGameSchema game;
    int gameScore, counter, childScore, numberCorrect;
    RealmList<ScenarioGameContent> contents;
    ArrayList<Integer> forty = new ArrayList<>(40);
    Banana b1, b2, b3, correctB;
    TextView question;
    Handler handler;
    ObjectAnimator animator1, animator2, animator3;
    private MediaPlayer correct, incorrect, background;
    ConstraintLayout topBarBackground;
    private CountDownTimer countDownTimer;
    private CircleTimer circleTimer;
    private final AlphaAnimation buttonClick = new AlphaAnimation(1F, 0.8F);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game2);
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
        gameTopBar = findViewById(R.id.game2TopBar);
        topBarBackground = findViewById(R.id.topBar);
        introBackBtn = gameTopBar.findViewById(R.id.goBackBtn);
        gameScore = child.getRoadMapTwo().getScenarioGame().getCurrentPoints();
        counter = 0;
        numberCorrect = 0;
        question = findViewById(R.id.gameQuestion);
        handler = new Handler();
        b1 = findViewById(R.id.banana1);
        b2 = findViewById(R.id.banana2);
        b3 = findViewById(R.id.banana3);
        correctB = findViewById(R.id.correctBanana);
        correct = MediaPlayer.create(this, R.raw.correct);
        correct.setVolume((float)3, (float)3);
        incorrect = MediaPlayer.create(this, R.raw.incorrect);
        incorrect.setVolume((float)4, (float)4);
        background = MediaPlayer.create(this, R.raw.market);
        circleTimer = findViewById(R.id.circleTimer);

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
        topBarBackground.setBackgroundColor(Color.rgb(252, 209, 70));
        gameTopBar.setShapeColor(Color.rgb(96, 163, 200));
        gameTopBar.setPointIconBackground(Color.rgb(252, 209, 70));
        gameTopBar.setPointsTextColor(Color.rgb(96, 163, 200));
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
            countDownTimer.cancel(); //the user selected an answer so we can stop the timer

            if(b1.getNumber().equals(contents.get(forty.get(counter)).getAnswer())) { // CORRECT
                playCorrect();
                showCorrect();
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
                wrongAnimation(b1, b2, b3);
            }
            deactivate();
            showNext();
        });

        b2.setOnClickListener(v -> {
            countDownTimer.cancel(); //the user selected an answer so we can stop the timer

            if(b2.getNumber().equals(contents.get(forty.get(counter)).getAnswer())) { // CORRECT
                numberCorrect++;
                playCorrect();
                showCorrect();
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
                wrongAnimation(b1, b2, b3);
            }
            deactivate();
            showNext();
        });

        b3.setOnClickListener(v -> {
            countDownTimer.cancel(); //the user selected an answer so we can stop the timer

            if(b3.getNumber().equals(contents.get(forty.get(counter)).getAnswer())) { // CORRECT
                numberCorrect++;
                playCorrect();
                showCorrect();
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
                wrongAnimation(b1, b2, b3);
            }
            deactivate();
            showNext();
        });
    }

    private void deactivate(){
        b1.setOnClickListener(null);
        b2.setOnClickListener(null);
        b3.setOnClickListener(null);
    }

    private void showCorrect(){
        correctB.setVisibility(View.VISIBLE);
        b1.setVisibility(View.INVISIBLE);
        b2.setVisibility(View.INVISIBLE);
        b3.setVisibility(View.INVISIBLE);
    }

    private void showNext(){
        handler.postDelayed(() -> {
            counter++;
            if(counter >= MAX){
                background.stop();
                Intent lessonIntent = new Intent(Game2.this, Results.class);
                lessonIntent.putExtra("childId", childId);
                lessonIntent.putExtra("whichOne", "Game");
                lessonIntent.putExtra("points", numberCorrect);
                lessonIntent.putExtra("max", MAX);
                lessonIntent.putExtra("whichRoadMap", "Two");
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
                b1.clearAnimation();
                b2.clearAnimation();
                b3.clearAnimation();
                b1.animate().translationX(0);
                b1.animate().setDuration(0);
                b2.animate().translationX(0);
                b2.animate().setDuration(0);
                b3.animate().translationX(0);
                b3.animate().setDuration(0);
                b1.setVisibility(View.VISIBLE);
                b2.setVisibility(View.VISIBLE);
                b3.setVisibility(View.VISIBLE);
                correctB.setVisibility(View.INVISIBLE);
                setContent();
                setTimer();
            }
        }, 2500);
    }

    private void wrongAnimation(View target1, View target2, View target3){
        animator1 = ObjectAnimator.ofFloat(target1, "translationX", 2000f);
        animator2 = ObjectAnimator.ofFloat(target2, "translationX", 1000f);
        animator3 = ObjectAnimator.ofFloat(target3, "translationX", 2000f);
        animator1.setDuration(1500);
        animator2.setDuration(1500);
        animator3.setDuration(1500);
        animator1.start();
        animator2.start();
        animator3.start();
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
        animator2.setRepeatCount(15);
        animator3.setRepeatCount(20);
        animator1.start();
        animator2.start();
        animator3.start();
    }

    private float getPowOut(float time, double pow){
        return (float)((float)1 - Math.pow(1 - time, pow));
    }

    private void setChildAndGameScoreInRealm(int childScore, int gameScore){
        realm.executeTransactionAsync(realm1 -> {
            ChildSchema child = realm1.where(ChildSchema.class).equalTo("childId", childId).findFirst();
            assert child != null;
            child.setScore(childScore);
            child.getRoadMapTwo().getScenarioGame().setCurrentPoints(gameScore);
        });
    }

    private void setCompletedState(int currentScore){
        if(currentScore >= MIN_TO_PASS){
            updateGameAndRoadMapState();
            goToNextRoadMap();
        }else {
            stayCurrentRoadMap();
        }
    }

    private void setNextRoadMap(ChildSchema child){
        if(child.getRoadMapThree().getLocked()){
            ChildRoadMap nextRoadMap = child.getRoadMapThree();
            nextRoadMap.setLocked(false);
            nextRoadMap.setCurrent(true);
            RealmList<RoadMapLesson> nextLessons = nextRoadMap.getRoadMapLessons();
            RoadMapLesson firstLesson = nextLessons.get(0);
            assert firstLesson != null;
            firstLesson.setCurrent(true);
            firstLesson.setCompleted(false);
        }
    }

    private void updateGameAndRoadMapState(){
        realm.executeTransactionAsync(realm1 -> {
            ChildSchema child = realm1.where(ChildSchema.class).equalTo("childId", childId).findFirst();
            assert child != null;

            if(!child.getRoadMapTwo().getCompleted()){
                child.getRoadMapTwo().setCurrent(false);
                child.getRoadMapTwo().setCompleted(true);
            }
            if(!child.getRoadMapTwo().getScenarioGame().getCompleted()){
                child.getRoadMapTwo().getScenarioGame().setCompleted(true);
                child.getRoadMapTwo().getScenarioGame().setCurrent(false);
            }
            setNextRoadMap(child);
        });
    }

    private void goToNextRoadMap(){
        Intent intent = new Intent(Game2.this, RoadMapThree.class);
        intent.putExtra("childIdentify", childId);
        startActivity(intent);
        this.finish();
    }

    private void stayCurrentRoadMap(){
        Intent intent = new Intent(Game2.this, RoadMapTwo.class);
        intent.putExtra("childIdentify", childId);
        startActivity(intent);
        this.finish();
    }

    @Override
    protected void onDestroy() {
        if (!realm.isClosed()) realm.close();
        handler.removeCallbacksAndMessages(null);
        background.stop();
        countDownTimer.cancel();
        super.onDestroy();
    }

    //sets a timer that counts down from 30 and moves on if the user doesn't choose an answer in time
    private void setTimer() {
        countDownTimer = new CountDownTimer(TOTALTIMER, INTERVAL){
            @Override
            public void onTick(long millisUntilFinished) {
                float progress = (float) millisUntilFinished / TOTALTIMER;
                circleTimer.setProgress(progress);
            }
            public void onFinish(){
                playIncorrect();
                circleTimer.setProgress(0);
                wrongAnimation(b1, b2, b3);
                deactivate();
                showNext();
            }
        }.start();
    }
}