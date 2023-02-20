package com.GrowthPlus;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.GrowthPlus.customViews.TopBar;
import com.GrowthPlus.dataAccessLayer.Quiz.QuizSchema;
import com.GrowthPlus.dataAccessLayer.child.ChildSchema;
import com.GrowthPlus.fragment.WordImage;
import com.GrowthPlus.roadMapActivity.RoadMapOne;

import io.realm.Realm;

public class IntroScreen extends AppCompatActivity {
    private Realm realm;
    private ChildSchema child;
    private String childId, data, image, name;
    private TopBar topBar;
    private Button next, back;
    private QuizSchema quizContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro_screen);
        init();
        setTopBar();

        // Load fragment for Intro to Quiz/Game
        FragmentManager fragmentManager = getSupportFragmentManager();
        if (savedInstanceState == null) {
            Bundle bundle = new Bundle();
            bundle.putString("locationIntroText", name);
            bundle.putString("locationIntroImage", image);

            FragmentTransaction transaction = fragmentManager.beginTransaction();
            transaction.setReorderingAllowed(true);
            transaction.replace(R.id.frame_layout_intro_screen, WordImage.class, bundle);
            transaction.commit();
        }

        back.setOnClickListener(v -> {
            Intent intent = new Intent(IntroScreen.this, RoadMapOne.class);
            intent.putExtra("childIdentify", childId);
            startActivity(intent);
        });

        next.setOnClickListener(v -> {
            Intent intent = new Intent(IntroScreen.this, Quiz.class);
            intent.putExtra("childId", childId);
            intent.putExtra("databaseQuizId", data);
            startActivity(intent);
        });
    }

    private void init(){
        Bundle extras = getIntent().getExtras();
        if(extras != null){
            childId = extras.getString("childId");
            data = extras.getString("databaseQuizId");
            // TODO: Need String to determine if quiz or game extras.getString | QuizID or GameID
        }
        realm = Realm.getDefaultInstance();
        child = realm.where(ChildSchema.class).equalTo("childId", childId).findFirst();
        topBar = findViewById(R.id.topBarIntroScreen);
        back = topBar.findViewById(R.id.goBackBtn);
        next = findViewById(R.id.next_button_intro);

        // TODO: Different requirements for Quiz vs Game
        quizContent = realm.where(QuizSchema.class).equalTo("quizId", data).findFirst();
        image = quizContent.getImage();
        name = quizContent.getQuizName();
    }

    private void setTopBar(){
        topBar.setPoints(String.valueOf(child.getScore()));
    }
}