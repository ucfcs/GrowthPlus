package com.GrowthPlus;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.GrowthPlus.customViews.Fish;
import com.GrowthPlus.customViews.FishMirror;
import com.GrowthPlus.customViews.TopBar;
import com.GrowthPlus.dataAccessLayer.ScenarioGame.ScenarioGameContent;
import com.GrowthPlus.dataAccessLayer.ScenarioGame.ScenarioGameSchema;
import com.GrowthPlus.dataAccessLayer.child.ChildSchema;
import com.GrowthPlus.roadMapActivity.RoadMapOne;

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
    RealmList<ScenarioGameContent> contents;
    ArrayList<Integer> forty = new ArrayList<>(40);
    Fish fish1, fish3, correctFish;
    FishMirror fish2;
    TextView question;
    ObjectAnimator move1, move2, move3, move4, move5, move6, move7, move8, move9;
    Random rand;
    Handler handler;
    private MediaPlayer correct, incorrect, background;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        init();
        playBackground();

        introBackBtn.setOnClickListener(view -> {
            background.stop();
            setCompletedState(gameScore, MIN_TO_PASS);
            Intent lessonIntent = new Intent(Game.this, RoadMapOne.class);
            lessonIntent.putExtra("childIdentify", childId);
            startActivity(lessonIntent);
            this.finish();
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
        fish1 = findViewById(R.id.fish1);
        fish2 = findViewById(R.id.fish2);
        fish3 = findViewById(R.id.fish3);
        correctFish = findViewById(R.id.correctFish);
        question = findViewById(R.id.gameQuestion);
        rand = new Random();
        handler = new Handler();
        correct = MediaPlayer.create(this, R.raw.correct);
        incorrect = MediaPlayer.create(this, R.raw.incorrect);
        background = MediaPlayer.create(this, R.raw.sea);

        // Fish 1
        move1 = ObjectAnimator.ofFloat(fish1, "translationX", 1300f);
        move1.setDuration(10000);
        move6 = ObjectAnimator.ofFloat(fish1, "translationY", -150f);
        move6.setDuration(10000);

        // Fish 2
        move2 = ObjectAnimator.ofFloat(fish2, "translationX", -1300f);
        move3 = ObjectAnimator.ofFloat(fish2, "translationY", -200f);
        move2.setDuration(10000);
        move3.setDuration(10000);
        move7 = ObjectAnimator.ofFloat(fish2, "translationY", 200f);
        move7.setDuration(10000);

        // Fish 3
        move4 = ObjectAnimator.ofFloat(fish3, "translationX", 1300f);
        move5 = ObjectAnimator.ofFloat(fish3, "translationY", 500f);
        move4.setDuration(10000);
        move5.setDuration(10000);
        move8 = ObjectAnimator.ofFloat(fish3, "translationY", 300f);
        move8.setDuration(10000);

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
        selectedAnswer = false;
        correctFish.setNumber(contents.get(forty.get(counter)).getAnswer());
        question.setText(contents.get(forty.get(counter)).getQuestion());
        fish1.setNumber(contents.get(forty.get(counter)).getOptionOne());
        fish2.setNumber(contents.get(forty.get(counter)).getOptionTwo());
        fish3.setNumber(contents.get(forty.get(counter)).getOptionThree());

        // Fish 1 Movement
        if(rand.nextInt(2) == 0){
            move1.start();
        }
        else{
            move1.start();
            move6.start();
        }

        // Fish 2 Movement
        if(rand.nextInt(2) == 0){
            move2.start();
            move3.start();
        }
        else{
            move2.start();
            move7.start();
        }

        // Fish 3 Movement
        if(rand.nextInt(2) == 0){
            move4.start();
            move5.start();
        }
        else{
            move4.start();
            move8.start();
        }

        fish1.setOnClickListener(v -> {
            selectedAnswer = true;
            if(fish1.getNumber().equals(contents.get(forty.get(counter)).getAnswer())) { // CORRECT
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
                drop(fish1);
            }
            showNextDelayed();
        });

        fish2.setOnClickListener(v -> {
            selectedAnswer = true;
            if(fish2.getNumber().equals(contents.get(forty.get(counter)).getAnswer())) { // CORRECT
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
                drop(fish2);
            }
            showNextDelayed();
        });

        fish3.setOnClickListener(v -> {
            selectedAnswer = true;
            if(fish3.getNumber().equals(contents.get(forty.get(counter)).getAnswer())) { // CORRECT
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

        // No answer selected in 8 seconds
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
        fish2.animate().translationX(0);
        fish1.animate().translationY(0);
        fish3.animate().translationX(0);
        fish1.animate().translationY(0);
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
                setCompletedState(gameScore, MIN_TO_PASS);
                Intent lessonIntent = new Intent(Game.this, Results.class);
                lessonIntent.putExtra("childId", childId);
                lessonIntent.putExtra("whichOne", "Game");
                lessonIntent.putExtra("points", gameScore);
                lessonIntent.putExtra("max", MAX);
                lessonIntent.putExtra("whichRoadMap", "One");
                if(gameScore >= MIN_TO_PASS){
                    lessonIntent.putExtra("passOrNot", 1);
                }
                else{
                    lessonIntent.putExtra("passOrNot", 0);
                }
                startActivity(lessonIntent);
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
            setCompletedState(gameScore, MIN_TO_PASS);
            Intent lessonIntent = new Intent(Game.this, Results.class);
            lessonIntent.putExtra("childId", childId);
            lessonIntent.putExtra("whichOne", "Game");
            lessonIntent.putExtra("points", gameScore);
            lessonIntent.putExtra("max", MAX);
            lessonIntent.putExtra("whichRoadMap", "One");
            if (gameScore >= MIN_TO_PASS) {
                lessonIntent.putExtra("passOrNot", 1);
            } else {
                lessonIntent.putExtra("passOrNot", 0);
            }
            startActivity(lessonIntent);
        }
        else{
            resetAnimation();
            handler.removeCallbacksAndMessages(null);
            setContent();
        }
    }

    private void drop(View target){
        move9 = ObjectAnimator.ofFloat(target, "translationY", 2500f);
        move9.setDuration(2000);
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

    private void setCompletedState(int currentScore, int minToPass){
        if(currentScore >= minToPass){
            realm.executeTransactionAsync(realm1 -> {
                ChildSchema child = realm1.where(ChildSchema.class).equalTo("childId", childId).findFirst();
                assert child != null;
                if(!child.getRoadMapOne().getScenarioGame().getCompleted()){
                    child.getRoadMapOne().getScenarioGame().setCompleted(true);
                }
                if(!child.getRoadMapTwo().getLocked()){
                    child.getRoadMapTwo().setLocked(false);
                }
            });
        }
    }
}