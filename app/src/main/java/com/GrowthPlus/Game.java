package com.GrowthPlus;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.media.MediaPlayer;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.Button;
import android.widget.TextView;

import com.GrowthPlus.customViews.Fish;
import com.GrowthPlus.customViews.FishMirror;
import com.GrowthPlus.customViews.TopBar;
import com.GrowthPlus.dataAccessLayer.ChildRoadMap.ChildRoadMap;
import com.GrowthPlus.dataAccessLayer.RoadMapLesson.RoadMapLesson;
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

public class Game extends AppCompatActivity {
    final int MAX = 20;
    final int MIN_TO_PASS = 14;
    ChildSchema child;
    Realm realm;
    TopBar gameTopBar;
    Button introBackBtn;
    String childId, databaseGameId;
    ScenarioGameSchema game;
    int gameScore, counter, childScore;
    private boolean selectedAnswer;
    int numberCorrect;
    RealmList<ScenarioGameContent> contents;
    ArrayList<Integer> forty = new ArrayList<>(40);
    Fish fish1, fish3, correctFish;
    FishMirror fish2;
    TextView question;
    ObjectAnimator move1, move2, move4, move5, move7, move9;
    Random rand;
    Handler handler;
    private MediaPlayer correct, incorrect, background;
    private final AlphaAnimation buttonClick = new AlphaAnimation(1F, 0.8F);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        init();
        playBackground();

        introBackBtn.setOnClickListener(view -> {
            view.startAnimation(buttonClick);
            handler.removeCallbacksAndMessages(null);
            setCompletedState(gameScore);
            background.stop();
        });
        correctFish.setVisibility(View.INVISIBLE);
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
        gameTopBar = findViewById(R.id.gameTopBar);
        introBackBtn = gameTopBar.findViewById(R.id.goBackBtn);
        gameScore = child.getRoadMapOne().getScenarioGame().getCurrentPoints();
        counter = 0;
        numberCorrect = 0;
        fish1 = findViewById(R.id.fish1);
        fish2 = findViewById(R.id.fish2);
        fish3 = findViewById(R.id.fish3);
        correctFish = findViewById(R.id.correctFish);
        question = findViewById(R.id.gameQuestion);
        rand = new Random();
        handler = new Handler();
        correct = MediaPlayer.create(this, R.raw.correct);
        correct.setVolume((float)3, (float)3);
        incorrect = MediaPlayer.create(this, R.raw.incorrect);
        incorrect.setVolume((float)4, (float)4);
        background = MediaPlayer.create(this, R.raw.sea);

        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int height = displayMetrics.heightPixels;
        int width = displayMetrics.widthPixels;

        float wid = (float) width;
        float heightF = (float) height;

        // Fish 1
        move1 = ObjectAnimator.ofFloat(fish1, "translationX", (float) (wid * 1.3));
        move1.setDuration(10000);

        // Fish 2
        move2 = ObjectAnimator.ofFloat(fish2, "translationX", (float) (-wid * 1.22));
        move2.setDuration(10000);
        move7 = ObjectAnimator.ofFloat(fish2, "translationY", (float) (heightF * .1));
        move7.setDuration(10000);

        // Fish 3
        move4 = ObjectAnimator.ofFloat(fish3, "translationX", (float) (wid * 1.22));
        move5 = ObjectAnimator.ofFloat(fish3, "translationY", (float) (heightF * .21));
        move4.setDuration(10000);
        move5.setDuration(10000);

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
        gameTopBar.setShapeColor(Color.rgb(252, 209, 70));
    }

    private void setContent(){
        selectedAnswer = false;
        correctFish.setNumber(contents.get(forty.get(counter)).getAnswer());
        question.setText(contents.get(forty.get(counter)).getQuestion());
        fish1.setNumber(contents.get(forty.get(counter)).getOptionOne());
        fish2.setNumber(contents.get(forty.get(counter)).getOptionTwo());
        fish3.setNumber(contents.get(forty.get(counter)).getOptionThree());

        // Fish 1 Movement
        move1.start();

        // Fish 2 Movement
        move2.start();
        move7.start();

        // Fish 3 Movement
        move4.start();
        move5.start();

        fish1.setOnClickListener(v -> {
            selectedAnswer = true;
            if(fish1.getNumber().equals(contents.get(forty.get(counter)).getAnswer())) { // CORRECT
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
            else{
                deactivate();
                playIncorrect();
                drop(fish1);
            }
            showNextDelayed();
        });

        fish2.setOnClickListener(v -> {
            selectedAnswer = true;
            if(fish2.getNumber().equals(contents.get(forty.get(counter)).getAnswer())) { // CORRECT
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
            else{
                deactivate();
                playIncorrect();
                drop(fish2);
            }
            showNextDelayed();
        });

        fish3.setOnClickListener(v -> {
            selectedAnswer = true;
            if(fish3.getNumber().equals(contents.get(forty.get(counter)).getAnswer())) { // CORRECT
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
            else{
                deactivate();
                playIncorrect();
                drop(fish3);
            }
            showNextDelayed();
        });

        // No answer selected in 10 seconds
        handler.postDelayed(() -> {
            if(!selectedAnswer){
                showNext();
            }
        }, 10000);
    }

    private void deactivate(){
        fish1.setOnClickListener(null);
        fish2.setOnClickListener(null);
        fish3.setOnClickListener(null);
    }

    private void resetAnimation(){
        fish1.clearAnimation();
        fish2.clearAnimation();
        fish3.clearAnimation();
        fish1.animate().translationX(0);
        fish1.animate().translationY(0);
        fish1.animate().setDuration(0);
        fish2.animate().translationX(0);
        fish2.animate().translationY(0);
        fish2.animate().setDuration(0);
        fish3.animate().translationX(0);
        fish3.animate().translationY(0);
        fish3.animate().setDuration(0);
    }

    private void showCorrect(){
        correctFish.setVisibility(View.VISIBLE);
        fish1.setVisibility(View.INVISIBLE);
        fish2.setVisibility(View.INVISIBLE);
        fish3.setVisibility(View.INVISIBLE);
    }

    private void showNextDelayed(){
        handler.postDelayed(() -> {
            counter++;
            if(counter >= MAX){
                background.stop();
                Intent lessonIntent = new Intent(Game.this, Results.class);
                lessonIntent.putExtra("childId", childId);
                lessonIntent.putExtra("whichOne", "Game");
                lessonIntent.putExtra("points", numberCorrect);
                lessonIntent.putExtra("max", MAX);
                lessonIntent.putExtra("whichRoadMap", "One");
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
                fish1.setVisibility(View.VISIBLE);
                fish2.setVisibility(View.VISIBLE);
                fish3.setVisibility(View.VISIBLE);
                correctFish.setVisibility(View.INVISIBLE);
                resetAnimation();
                handler.removeCallbacksAndMessages(null);
                setContent();
            }
        }, 2500);
    }

    private void showNext() {
        counter++;
        if(counter >= MAX) {
            background.stop();
            Intent lessonIntent = new Intent(Game.this, Results.class);
            lessonIntent.putExtra("childId", childId);
            lessonIntent.putExtra("whichOne", "Game");
            lessonIntent.putExtra("points", numberCorrect);
            lessonIntent.putExtra("max", MAX);
            lessonIntent.putExtra("whichRoadMap", "One");
            if (gameScore >= MIN_TO_PASS) {
                updateGameAndRoadMapState();
                lessonIntent.putExtra("passOrNot", 1);
            } else {
                lessonIntent.putExtra("passOrNot", 0);
            }
            startActivity(lessonIntent);
            this.finish();
        }
        else{
            resetAnimation();
            handler.removeCallbacksAndMessages(null);
            setContent();
        }
    }

    private void drop(View target){
        move9 = ObjectAnimator.ofFloat(target, "translationY", 2500f);
        move9.setDuration(2500);
        move9.start();
    }

    private void setChildAndGameScoreInRealm(int childScore, int gameScore){
        realm.executeTransactionAsync(realm1 -> {
            ChildSchema child = realm1.where(ChildSchema.class).equalTo("childId", childId).findFirst();
            assert child != null;
            child.setScore(childScore);
            child.getRoadMapOne().getScenarioGame().setCurrentPoints(gameScore);
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
        if(child.getRoadMapTwo().getLocked()){
            ChildRoadMap nextRoadMap = child.getRoadMapTwo();
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

            if(!child.getRoadMapOne().getCompleted()){
                child.getRoadMapOne().setCurrent(false);
                child.getRoadMapOne().setCompleted(true);
            }
            if(!child.getRoadMapOne().getScenarioGame().getCompleted()){
                child.getRoadMapOne().getScenarioGame().setCompleted(true);
                child.getRoadMapOne().getScenarioGame().setCurrent(false);

            }
            setNextRoadMap(child);
        });
    }

    private void goToNextRoadMap(){
        Intent intent = new Intent(Game.this, RoadMapTwo.class);
        intent.putExtra("childIdentify", childId);
        startActivity(intent);
        this.finish();
    }

    private void stayCurrentRoadMap(){
        Intent intent = new Intent(Game.this, RoadMapOne.class);
        intent.putExtra("childIdentify", childId);
        startActivity(intent);
        this.finish();
    }

    @Override
    protected void onDestroy() {
        if (!realm.isClosed()) realm.close();
        handler.removeCallbacksAndMessages(null);
        background.stop();
        super.onDestroy();
    }
}