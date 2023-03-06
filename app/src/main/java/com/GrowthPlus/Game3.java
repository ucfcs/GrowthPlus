package com.GrowthPlus;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.media.MediaPlayer;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.GrowthPlus.customViews.Soccer;
import com.GrowthPlus.customViews.TopBar;
import com.GrowthPlus.dataAccessLayer.ChildRoadMap.ChildRoadMap;
import com.GrowthPlus.dataAccessLayer.RoadMapLesson.RoadMapLesson;
import com.GrowthPlus.dataAccessLayer.ScenarioGame.ScenarioGameContent;
import com.GrowthPlus.dataAccessLayer.ScenarioGame.ScenarioGameSchema;
import com.GrowthPlus.dataAccessLayer.child.ChildSchema;
import com.GrowthPlus.roadMapActivity.RoadMapFour;
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
    int gameScore, counter, childScore, numberCorrect;
    RealmList<ScenarioGameContent> contents;
    ArrayList<Integer> forty = new ArrayList<>(40);
    Soccer ball1, ball2, ball3;
    TextView question;
    Handler handler;
    ObjectAnimator move1a, move1b, move2a, move2b, move3a, move3b, move4a, move4b, move5a, move5b, move6a, move6b;
    private MediaPlayer correct, incorrect, background;
    ConstraintLayout topBarBackground;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game3);
        init();
        playBackground();

        introBackBtn.setOnClickListener(view -> {
            setCompletedState(gameScore);
            background.stop();
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
        gameTopBar = findViewById(R.id.game3TopBar);
        topBarBackground = findViewById(R.id.topBar);
        introBackBtn = gameTopBar.findViewById(R.id.goBackBtn);
        gameScore = child.getRoadMapThree().getScenarioGame().getCurrentPoints();
        counter = 0;
        numberCorrect = 0;
        question = findViewById(R.id.gameQuestion);
        handler = new Handler();
        ball1 = findViewById(R.id.soccer1);
        ball2 = findViewById(R.id.soccer2);
        ball3 = findViewById(R.id.soccer3);
        correct = MediaPlayer.create(this, R.raw.correct);
        incorrect = MediaPlayer.create(this, R.raw.incorrect);
        background = MediaPlayer.create(this, R.raw.soccer);

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
        gameTopBar.setShapeColor(Color.rgb(3, 71, 50));
        gameTopBar.setPointIconBackground(Color.rgb(252, 209, 70));
        gameTopBar.setPointsTextColor(Color.rgb(3, 71, 50));
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
                numberCorrect++;
                playCorrect();
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
                playIncorrect();
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
                playCorrect();
                numberCorrect++;
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
                playIncorrect();
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
                playCorrect();
                numberCorrect++;
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
                playIncorrect();
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
                background.stop();
                setCompletedState(gameScore);
                Intent lessonIntent = new Intent(Game3.this, Results.class);
                lessonIntent.putExtra("childId", childId);
                lessonIntent.putExtra("whichOne", "Game");
                lessonIntent.putExtra("points", gameScore);
                lessonIntent.putExtra("max", MAX);
                lessonIntent.putExtra("whichRoadMap", "Three");
                if(gameScore >= MIN_TO_PASS){
                    lessonIntent.putExtra("passOrNot", 1);
                }
                else{
                    lessonIntent.putExtra("passOrNot", 0);
                }
                startActivity(lessonIntent);
            }
            else{
                ball1.clearAnimation();
                ball2.clearAnimation();
                ball3.clearAnimation();
                ball1.animate().translationX(0);
                ball1.animate().translationY(0);
                ball1.animate().setDuration(0);
                ball2.animate().translationX(0);
                ball2.animate().translationY(0);
                ball2.animate().setDuration(0);
                ball3.animate().translationX(0);
                ball3.animate().translationY(0);
                ball3.animate().setDuration(0);

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

    private void setCompletedState(int currentScore){
        if(currentScore >= MIN_TO_PASS){
            realm.executeTransactionAsync(realm1 -> {
                ChildSchema child = realm1.where(ChildSchema.class).equalTo("childId", childId).findFirst();
                assert child != null;
                if(!child.getRoadMapThree().getScenarioGame().getCompleted()){
                    child.getRoadMapThree().getScenarioGame().setCompleted(true);
                    child.getRoadMapThree().getScenarioGame().setCurrent(false);
                }
                if(child.getRoadMapFour().getLocked()){
                    ChildRoadMap nextRoadMap = child.getRoadMapFour();
                    nextRoadMap.setLocked(false);
                    nextRoadMap.setCurrent(true);
                    RealmList<RoadMapLesson> nextLessons = nextRoadMap.getRoadMapLessons();
                    RoadMapLesson firstLesson = nextLessons.get(0);
                    assert firstLesson != null;
                    firstLesson.setCurrent(true);
                    firstLesson.setCompleted(false);
                }
            });
            goToNextRoadMap();
        }else {
            stayCurrentRoadMap();
        }
    }

    private void goToNextRoadMap(){
        Intent intent = new Intent(Game3.this, RoadMapFour.class);
        intent.putExtra("childIdentify", childId);
        startActivity(intent);
        this.finish();
    }

    private void stayCurrentRoadMap(){
        Intent intent = new Intent(Game3.this, RoadMapThree.class);
        intent.putExtra("childIdentify", childId);
        startActivity(intent);
        this.finish();
    }

    @Override
    protected void onDestroy() {
        if (!realm.isClosed()) realm.close();
        super.onDestroy();
    }
}