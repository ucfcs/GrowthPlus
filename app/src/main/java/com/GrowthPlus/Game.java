package com.GrowthPlus;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.GrowthPlus.customViews.Fish;
import com.GrowthPlus.customViews.TopBar;
import com.GrowthPlus.dataAccessLayer.ScenarioGame.ScenarioGameContent;
import com.GrowthPlus.dataAccessLayer.ScenarioGame.ScenarioGameSchema;
import com.GrowthPlus.dataAccessLayer.child.ChildSchema;
import com.GrowthPlus.roadMapActivity.RoadMapOne;

import java.util.ArrayList;
import java.util.Collections;

import io.realm.Realm;
import io.realm.RealmList;

public class Game extends AppCompatActivity {
    final int MAX = 20; // TODO: Change this back to 20
    ChildSchema child;
    Realm realm;
    TopBar topBar;
    Button nextContent, introBackBtn;
    String childId, databaseGameId;
    ScenarioGameSchema game;
    int score, counter;
    RealmList<ScenarioGameContent> contents;
    ArrayList<Integer> forty = new ArrayList<>(40);
    Fish fish1, fish2, fish3;
    TextView question;
    ObjectAnimator move1, move2, move3, move4, move5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        init();

        introBackBtn.setOnClickListener(view -> {
            Intent lessonIntent = new Intent(Game.this, RoadMapOne.class);
            // TODO: Dynamically change return address based on child's progress
            lessonIntent.putExtra("childIdentify", childId);
            startActivity(lessonIntent);
        });
        setTopBar();
        setContent();

        nextContent.setOnClickListener(v -> {
            counter++; // Show 20 questions then leave Game

            if(counter >= MAX){
                if(score >= 14){
                    // TODO: Make Game completed and load next RoadMap
                }

                Intent intent = new Intent(Game.this, RoadMapOne.class); // TODO: Dynamically change location address
                intent.putExtra("childIdentify", childId);
                startActivity(intent);
                this.finish();
            }
            else{
                fish1.setVisibility(View.VISIBLE);
                fish2.setVisibility(View.VISIBLE);
                fish3.setVisibility(View.VISIBLE);

                setContent();
            }
        });
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
        nextContent = findViewById(R.id.nextQuestion);
        score = 0;
        counter = 0;
        fish1 = findViewById(R.id.fish1);
        fish2 = findViewById(R.id.fish2);
        fish3 = findViewById(R.id.fish3);
        question = findViewById(R.id.gameQuestion);

        for(int i = 0; i <= 39; i++)
            forty.add(i);
        Collections.shuffle(forty); // Randomize question selection
    }

    private void setTopBar(){
        topBar.setPoints(String.valueOf(child.getScore()));
        topBar.setToStar();
    }

    private void setContent(){
        nextContent.setVisibility(View.INVISIBLE);
        question.setText(contents.get(forty.get(counter)).getQuestion());
        fish1.setNumber(contents.get(forty.get(counter)).getOptionOne());
        fish2.setNumber(contents.get(forty.get(counter)).getOptionTwo());
        fish3.setNumber(contents.get(forty.get(counter)).getOptionThree());

        // Fish 1
        move1 = ObjectAnimator.ofFloat(fish1, "translationX", 650f);
        move1.setDuration(10000);
        move1.start();

        // Fish 2
        move2 = ObjectAnimator.ofFloat(fish2, "translationX", -130f);
        move3 = ObjectAnimator.ofFloat(fish2, "translationY", -50f);
        move2.setDuration(10000);
        move3.setDuration(10000);
        move2.start();
        move3.start();

        // Fish 3
        move4 = ObjectAnimator.ofFloat(fish3, "translationX", 350f);
        move5 = ObjectAnimator.ofFloat(fish3, "translationY", 250f);
        move4.setDuration(10000);
        move5.setDuration(10000);
        move4.start();
        move5.start();

        fish1.setOnClickListener(v -> {
            if(fish1.getNumber().equals(contents.get(forty.get(counter)).getAnswer())){ // CORRECT
                score++;
                fish2.setVisibility(View.INVISIBLE);
                fish3.setVisibility(View.INVISIBLE);
            }
            else{ // INCORRECT
                if(fish2.getNumber().equals(contents.get(forty.get(counter)).getAnswer())){
                    fish1.setVisibility(View.INVISIBLE);
                    fish3.setVisibility(View.INVISIBLE);
                }
                else{
                    fish1.setVisibility(View.INVISIBLE);
                    fish2.setVisibility(View.INVISIBLE);
                }
            }
            resetAnimation();
            deactivate();
            nextContent.setVisibility(View.VISIBLE);
        });

        fish2.setOnClickListener(v -> {
            if(fish2.getNumber().equals(contents.get(forty.get(counter)).getAnswer())){ // CORRECT
                score++;
                fish1.setVisibility(View.INVISIBLE);
                fish3.setVisibility(View.INVISIBLE);
            }
            else{ // INCORRECT
                if(fish1.getNumber().equals(contents.get(forty.get(counter)).getAnswer())){
                    fish2.setVisibility(View.INVISIBLE);
                    fish3.setVisibility(View.INVISIBLE);
                }
                else{
                    fish1.setVisibility(View.INVISIBLE);
                    fish2.setVisibility(View.INVISIBLE);
                }
            }
            resetAnimation();
            deactivate();
            nextContent.setVisibility(View.VISIBLE);
        });

        fish3.setOnClickListener(v -> {
            if(fish3.getNumber().equals(contents.get(forty.get(counter)).getAnswer())){ // CORRECT
                score++;
                fish1.setVisibility(View.INVISIBLE);
                fish2.setVisibility(View.INVISIBLE);
            }
            else{ // INCORRECT
                if(fish1.getNumber().equals(contents.get(forty.get(counter)).getAnswer())){
                    fish2.setVisibility(View.INVISIBLE);
                    fish3.setVisibility(View.INVISIBLE);
                }
                else{
                    fish1.setVisibility(View.INVISIBLE);
                    fish3.setVisibility(View.INVISIBLE);
                }
            }
            resetAnimation();
            deactivate();
            nextContent.setVisibility(View.VISIBLE);
        });
    }

    public void deactivate(){
        fish1.setOnClickListener(null);
        fish2.setOnClickListener(null);
        fish3.setOnClickListener(null);
    }

    public void resetAnimation(){
        move1.end();
        fish1.animate().translationX(0);
        move2.end();
        move3.end();
        fish2.animate().translationX(0);
        fish2.animate().translationY(0);
        move4.end();
        move5.end();
        fish3.animate().translationX(0);
        fish3.animate().translationY(0);

    }
}