package com.GrowthPlus;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.GrowthPlus.customViews.TopBar;
import com.GrowthPlus.dataAccessLayer.child.ChildSchema;
import com.GrowthPlus.roadMapActivity.RoadMapFour;
import com.GrowthPlus.roadMapActivity.RoadMapOne;
import com.GrowthPlus.roadMapActivity.RoadMapThree;
import com.GrowthPlus.roadMapActivity.RoadMapTwo;

import io.realm.Realm;

public class Results extends AppCompatActivity {
    private TopBar topBar;
    private TextView first, second;
    private ImageView box;
    private Button back, next;
    private String childId, whichOne, whichRoadMap;
    private int points, max, passOrNot;
    private Realm realm;
    private ChildSchema child;
    ColorStateList green, red;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);
        init();
        setTopBar();

        back.setVisibility(View.INVISIBLE);

        first.setText(String.valueOf(points));
        second.setText(String.valueOf(max));

        if(passOrNot == 1){
            box.setBackgroundTintList(green);
        }
        else{
            box.setBackgroundTintList(red);
        }

        next.setOnClickListener(v -> {
            Intent lessonIntent;

            if(whichRoadMap.equals("One")){
                lessonIntent =  new Intent(Results.this, RoadMapOne.class);
            }
            else if(whichRoadMap.equals("Two")){
                lessonIntent =  new Intent(Results.this, RoadMapTwo.class);
            }
            else if(whichRoadMap.equals("Three")){
                lessonIntent =  new Intent(Results.this, RoadMapThree.class);
            }
            else{
                lessonIntent =  new Intent(Results.this, RoadMapFour.class);
            }
            lessonIntent.putExtra("childIdentify", childId);
            startActivity(lessonIntent);
        });
    }

    private void init(){
        Bundle extras = getIntent().getExtras();
        if(extras != null){
            childId = extras.getString("childId");
            whichOne = extras.getString("whichOne");
            points = extras.getInt("points");
            max = extras.getInt("max");
            whichRoadMap = extras.getString("whichRoadMap");
            passOrNot = extras.getInt("passOrNot");
        }
        realm = Realm.getDefaultInstance();
        child = realm.where(ChildSchema.class).equalTo("childId", childId).findFirst();
        topBar = findViewById(R.id.topBar);
        back = topBar.findViewById(R.id.goBackBtn);
        next = findViewById(R.id.nextButton);
        first = findViewById(R.id.firstNumber);
        second = findViewById(R.id.secondNumber);
        box = findViewById(R.id.box);
        green = ColorStateList.valueOf(ContextCompat.getColor(this, R.color.light_green));
        red = ColorStateList.valueOf(ContextCompat.getColor(this, R.color.red));
    }

    private void setTopBar(){
        topBar.setPoints(String.valueOf(child.getScore()));
        if(whichOne.equals("Quiz")){
            topBar.setToCircle();
        }
        else if(whichOne.equals("Game")){
            topBar.setToStar();
        }
        else{
            topBar.setToTriangle();
        }
    }
}