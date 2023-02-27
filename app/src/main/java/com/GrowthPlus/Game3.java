package com.GrowthPlus;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.GrowthPlus.customViews.Soccer;
import com.GrowthPlus.customViews.TopBar;
import com.GrowthPlus.dataAccessLayer.ScenarioGame.ScenarioGameContent;
import com.GrowthPlus.dataAccessLayer.ScenarioGame.ScenarioGameSchema;
import com.GrowthPlus.dataAccessLayer.child.ChildSchema;
import com.GrowthPlus.roadMapActivity.RoadMapOne;
import com.GrowthPlus.roadMapActivity.RoadMapThree;

import java.util.ArrayList;
import java.util.Collections;

import io.realm.Realm;
import io.realm.RealmList;

public class Game3 extends AppCompatActivity {
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
    Soccer ball1, ball2, ball3;
    TextView question;
    Handler handler;
    ObjectAnimator move1a, move1b, move2a, move2b, move3a, move3b, move4a, move4b, move5a, move5b, move6a, move6b;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game3);
        init();

        introBackBtn.setOnClickListener(view -> {
            setCompletedState(gameScore, MIN_TO_PASS);
            Intent lessonIntent = new Intent(Game3.this, RoadMapThree.class);
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
        gameScore = child.getRoadMapThree().getScenarioGame().getCurrentPoints();
        counter = 0;
        question = findViewById(R.id.gameQuestion);
        handler = new Handler();
        ball1 = findViewById(R.id.soccer1);
        ball2 = findViewById(R.id.soccer2);
        ball3 = findViewById(R.id.soccer3);

        // Correct Animation
        move1a = ObjectAnimator.ofFloat(ball1, "translationX", 360f);
        move1b = ObjectAnimator.ofFloat(ball1, "translationY", -850f);
        move2a = ObjectAnimator.ofFloat(ball2, "translationX", 0f);
        move2b = ObjectAnimator.ofFloat(ball2, "translationY", -850f);
        move3a = ObjectAnimator.ofFloat(ball3, "translationX", -360f);
        move3b = ObjectAnimator.ofFloat(ball3, "translationY", -850f);
        move1a.setDuration(1000);
        move1b.setDuration(1000);
        move2a.setDuration(1000);
        move2b.setDuration(1000);
        move3a.setDuration(1000);
        move3b.setDuration(1000);

        // Incorrect Animation
        move4a = ObjectAnimator.ofFloat(ball1, "translationX", 2000f);
        move4b = ObjectAnimator.ofFloat(ball1, "translationY", -2000f);
        move5a = ObjectAnimator.ofFloat(ball2, "translationX", -1000f);
        move5b = ObjectAnimator.ofFloat(ball2, "translationY", -2000f);
        move6a = ObjectAnimator.ofFloat(ball3, "translationX", -2000f);
        move6b = ObjectAnimator.ofFloat(ball3, "translationY", -2000f);
        move4a.setDuration(2000);
        move4b.setDuration(2000);
        move5a.setDuration(2000);
        move5b.setDuration(2000);
        move6a.setDuration(2000);
        move6b.setDuration(2000);

        for(int i = 0; i <= 39; i++)
            forty.add(i);
        Collections.shuffle(forty); // Randomize question selection
    }

    private void setTopBar(){
        gameTopBar.setPoints(String.valueOf(child.getScore()));
        gameTopBar.setToStar();
    }

    private void setContent(){
        question.setText(contents.get(forty.get(counter)).getQuestion());
        ball1.setNumber(contents.get(forty.get(counter)).getOptionOne());
        ball2.setNumber(contents.get(forty.get(counter)).getOptionTwo());
        ball3.setNumber(contents.get(forty.get(counter)).getOptionThree());

        ball1.setOnClickListener(v -> {
            ball2.setVisibility(View.INVISIBLE);
            ball3.setVisibility(View.INVISIBLE);
            deactivate();

            if(ball1.getNumber().equals(contents.get(forty.get(counter)).getAnswer())) { // CORRECT
                if(gameScore < MAX){
                    gameScore++;
                    childScore++;
                    setChildAndGameScoreInRealm(childScore, gameScore);

                    //Update top bar scoring
                    gameTopBar.setPoints(String.valueOf(childScore));
                }
                move1a.start();
                move1b.start();
            }
            else{
                move4a.start();
                move4b.start();
            }
            showNext();
        });

        ball2.setOnClickListener(v -> {
            ball1.setVisibility(View.INVISIBLE);
            ball3.setVisibility(View.INVISIBLE);
            deactivate();

            if(ball2.getNumber().equals(contents.get(forty.get(counter)).getAnswer())) { // CORRECT
                if(gameScore < MAX){
                    gameScore++;
                    childScore++;
                    setChildAndGameScoreInRealm(childScore, gameScore);

                    //Update top bar scoring
                    gameTopBar.setPoints(String.valueOf(childScore));
                }
                move2a.start();
                move2b.start();
            }
            else{
                move5a.start();
                move5b.start();
            }
            showNext();
        });

        ball3.setOnClickListener(v -> {
            ball1.setVisibility(View.INVISIBLE);
            ball2.setVisibility(View.INVISIBLE);
            deactivate();

            if(ball3.getNumber().equals(contents.get(forty.get(counter)).getAnswer())) { // CORRECT
                if(gameScore < MAX){
                    gameScore++;
                    childScore++;
                    setChildAndGameScoreInRealm(childScore, gameScore);

                    //Update top bar scoring
                    gameTopBar.setPoints(String.valueOf(childScore));
                }
                move3a.start();
                move3b.start();
            }
            else{
                move6a.start();
                move6b.start();
            }
            showNext();
        });
    }

    private void deactivate(){
        ball1.setOnClickListener(null);
        ball2.setOnClickListener(null);
        ball3.setOnClickListener(null);
    }

    private void showNext(){
        handler.postDelayed(() -> {
            counter++;
            if(counter >= MAX){
                setCompletedState(gameScore, MIN_TO_PASS);
                Intent intent = new Intent(Game3.this, RoadMapOne.class); // TODO: Dynamically change location address
                intent.putExtra("childIdentify", childId);
                startActivity(intent);
            }
            else{
                ball1.animate().translationX(0);
                ball1.animate().translationY(0);
                ball2.animate().translationX(0);
                ball2.animate().translationY(0);
                ball3.animate().translationX(0);
                ball3.animate().translationY(0);

                ball1.setVisibility(View.VISIBLE);
                ball2.setVisibility(View.VISIBLE);
                ball3.setVisibility(View.VISIBLE);
                setContent();
            }
        }, 3000);
    }

    private void setChildAndGameScoreInRealm(int childScore, int gameScore){
        realm.executeTransactionAsync(realm1 -> {
            ChildSchema child = realm1.where(ChildSchema.class).equalTo("childId", childId).findFirst();
            assert child != null;
            child.setScore(childScore);
            child.getRoadMapThree().getScenarioGame().setCurrentPoints(gameScore);
        });
    }

    private void setCompletedState(int currentScore, int minToPass){
        if(currentScore >= minToPass){
            realm.executeTransactionAsync(realm1 -> {
                ChildSchema child = realm1.where(ChildSchema.class).equalTo("childId", childId).findFirst();
                assert child != null;
                if(!child.getRoadMapThree().getScenarioGame().getCompleted()){
                    child.getRoadMapThree().getScenarioGame().setCompleted(true);
                }
                if(!child.getRoadMapFour().getLocked()){
                    child.getRoadMapFour().setLocked(false);
                }
            });
        }
    }
}