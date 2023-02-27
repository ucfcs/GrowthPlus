package com.GrowthPlus;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.GrowthPlus.customViews.Fish;
import com.GrowthPlus.customViews.TopBar;
import com.GrowthPlus.dataAccessLayer.RoadMapLesson.RoadMapLesson;
import com.GrowthPlus.dataAccessLayer.RoadMapQuiz.RoadMapQuiz;
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
    RealmList<ScenarioGameContent> contents;
    ArrayList<Integer> forty = new ArrayList<>(40);
    Fish fish1, fish2, fish3, correctFish;
    TextView question;
    ObjectAnimator move1, move2, move3, move4, move5, move6, move7, move8;
    Random rand;
    Handler handler;

    // Same scoring logic as quiz
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        init();

        Log.i("gameScore", String.valueOf(gameScore));
        Log.i("gameChildScore", String.valueOf(childScore));

        introBackBtn.setOnClickListener(view -> {
            Intent lessonIntent = new Intent(Game.this, RoadMapOne.class);
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

        for(int i = 0; i <= 39; i++)
            forty.add(i);
        Collections.shuffle(forty); // Randomize question selection
    }

    private void setTopBar(){
        gameTopBar.setPoints(String.valueOf(child.getScore()));
        gameTopBar.setToStar();
    }

    private void setContent(){
        correctFish.setVisibility(View.INVISIBLE);
        correctFish.setNumber(contents.get(forty.get(counter)).getAnswer());
        question.setText(contents.get(forty.get(counter)).getQuestion());
        fish1.setNumber(contents.get(forty.get(counter)).getOptionOne());
        fish2.setNumber(contents.get(forty.get(counter)).getOptionTwo());
        fish3.setNumber(contents.get(forty.get(counter)).getOptionThree());

        // Fish 1
        move1 = ObjectAnimator.ofFloat(fish1, "translationX", 600f);
        move1.setDuration(25000);
        move6 = ObjectAnimator.ofFloat(fish1, "translationY", 150f);
        move6.setDuration(25000);

        if(rand.nextInt(2) == 0){
            move1.start();
        }
        else{
            move1.start();
            move6.start();
        }

        // Fish 2
        move2 = ObjectAnimator.ofFloat(fish2, "translationX", -225f);
        move3 = ObjectAnimator.ofFloat(fish2, "translationY", -100f);
        move2.setDuration(25000);
        move3.setDuration(25000);
        move7 = ObjectAnimator.ofFloat(fish2, "translationY", 100f);
        move7.setDuration(25000);

        if(rand.nextInt(2) == 0){
            move2.start();
            move3.start();
        }
        else{
            move2.start();
            move7.start();
        }

        // Fish 3
        move4 = ObjectAnimator.ofFloat(fish3, "translationX", 400f);
        move5 = ObjectAnimator.ofFloat(fish3, "translationY", 250f);
        move4.setDuration(25000);
        move5.setDuration(25000);
        move8 = ObjectAnimator.ofFloat(fish3, "translationY", -10f);
        move8.setDuration(25000);

        if(rand.nextInt(2) == 0){
            move4.start();
            move5.start();
        }
        else{
            move4.start();
            move8.start();
        }

        fish1.setOnClickListener(v -> {
            if(fish1.getNumber().equals(contents.get(forty.get(counter)).getAnswer())) { // CORRECT
                if(gameScore < MAX){
                    gameScore++;
                    childScore++;
                    setChildAndGameScoreInRealm(childScore, gameScore);

                    //Update top bar scoring
                    gameTopBar.setPoints(String.valueOf(childScore));
                }
            }
            resetAnimation();
            deactivate();
            showCorrect();
        });

        fish2.setOnClickListener(v -> {
            if(fish2.getNumber().equals(contents.get(forty.get(counter)).getAnswer())) { // CORRECT
                if(gameScore < MAX){
                    gameScore++;
                    childScore++;
                    setChildAndGameScoreInRealm(childScore, gameScore);

                    //Update top bar scoring
                    gameTopBar.setPoints(String.valueOf(childScore));
                }
            }
            resetAnimation();
            deactivate();
            showCorrect();
        });

        fish3.setOnClickListener(v -> {
            if(fish3.getNumber().equals(contents.get(forty.get(counter)).getAnswer())) { // CORRECT
                if(gameScore < MAX){
                    gameScore++;
                    childScore++;
                    setChildAndGameScoreInRealm(childScore, gameScore);

                    //Update top bar scoring
                    gameTopBar.setPoints(String.valueOf(childScore));
                }
            }
            resetAnimation();
            deactivate();
            showCorrect();
        });
    }

    public void deactivate(){
        fish1.setOnClickListener(null);
        fish2.setOnClickListener(null);
        fish3.setOnClickListener(null);
    }

    public void resetAnimation(){
        move1.end();
        move6.end();
        move7.end();
        fish1.animate().translationX(0);
        fish1.animate().translationY(0);
        move2.end();
        move3.end();
        fish2.animate().translationX(0);
        fish2.animate().translationY(0);
        move4.end();
        move5.end();
        move8.end();
        fish3.animate().translationX(0);
        fish3.animate().translationY(0);
    }

    public void showCorrect(){
        correctFish.setVisibility(View.VISIBLE);
        fish1.setVisibility(View.INVISIBLE);
        fish2.setVisibility(View.INVISIBLE);
        fish3.setVisibility(View.INVISIBLE);
        handler.postDelayed(() -> {
            counter++;
            if(counter >= MAX){
                setCompletedState(gameScore, MIN_TO_PASS);
                Intent intent = new Intent(Game.this, RoadMapOne.class); // TODO: Dynamically change location address
                intent.putExtra("childIdentify", childId);
                startActivity(intent);
            }
            else{
                fish1.setVisibility(View.VISIBLE);
                fish2.setVisibility(View.VISIBLE);
                fish3.setVisibility(View.VISIBLE);
                correctFish.setVisibility(View.INVISIBLE);
                setContent();
            }
        }, 5000);
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