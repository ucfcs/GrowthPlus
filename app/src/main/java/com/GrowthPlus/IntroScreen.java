package com.GrowthPlus;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;

import com.GrowthPlus.customViews.TopBar;
import com.GrowthPlus.dataAccessLayer.Lesson.LessonSchema;
import com.GrowthPlus.dataAccessLayer.child.ChildSchema;

import io.realm.Realm;

public class IntroScreen extends AppCompatActivity {

    private Realm realm;
    private ChildSchema child;
    private String childId;
    private TopBar topBar;
    private Button introScreenBackBtn;
    private Button nextContent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro_screen);
        setTopBar();
    }

    private void init(){
        Bundle extras = getIntent().getExtras();
        if(extras != null){
            childId = extras.getString("childId");
            // Need String to determine if quiz or game extras.getString
            // QuizID or GameID
        }
        realm = Realm.getDefaultInstance();
        child = realm.where(ChildSchema.class).equalTo("childId", childId).findFirst();
        topBar = findViewById(R.id.topBarIntroScreen);
        introScreenBackBtn = topBar.findViewById(R.id.goBackBtn);
        nextContent = findViewById(R.id.next_button_intro);
    }


    private void setTopBar(){
        topBar.setPoints(String.valueOf(child.getScore()));
    }
}