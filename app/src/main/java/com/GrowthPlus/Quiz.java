package com.GrowthPlus;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.GrowthPlus.customViews.QuizCircle;
import com.GrowthPlus.customViews.TopBar;
import com.GrowthPlus.dataAccessLayer.Quiz.QuizSchema;
import com.GrowthPlus.dataAccessLayer.QuizContent.QuizContent;
import com.GrowthPlus.dataAccessLayer.child.ChildSchema;
import com.GrowthPlus.fragment.QuizImage;
import com.GrowthPlus.fragment.QuizText;
import com.GrowthPlus.roadMapActivity.RoadMapOne;

import java.util.ArrayList;
import java.util.Collections;

import io.realm.Realm;
import io.realm.RealmList;

public class Quiz extends AppCompatActivity {
    final int MAX = 10;
    ChildSchema child;
    Realm realm;
    TopBar topBar;
    Button nextContent, introBackBtn;
    String childId, quizName, image, databaseQuizId;
    QuizSchema quiz;
    int contentLength, counter;
    RealmList<QuizContent> contents;
    QuizCircle cir1, cir2, cir3, cir4;
    ArrayList<Integer> twenty = new ArrayList<>(20);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        init();

        introBackBtn.setOnClickListener(view -> {
            Intent lessonIntent = new Intent(Quiz.this, RoadMapOne.class);
            // TODO: Dynamically change return address based on child's progress
            lessonIntent.putExtra("childIdentify", childId);
            startActivity(lessonIntent);
        });
        setTopBar();

        FragmentManager fragmentManager = getSupportFragmentManager();

        contentLength = contents.size();
        counter = 0;

        // This loads the first question only before needing to click the next button ---------------------------------
        String category = contents.get(twenty.get(counter)).getQuizCategory();
        switch(category){
            case "String": {
                String word = contents.get(twenty.get(counter)).getQuestion();
                setAnswers();

                if (savedInstanceState == null) {
                    Bundle bundle = new Bundle();
                    bundle.putString("text", word);

                    FragmentTransaction transaction = fragmentManager.beginTransaction();
                    transaction.setReorderingAllowed(true);
                    transaction.replace(R.id.frame_layout, QuizText.class, bundle);
                    transaction.commit();
                }
                break;
            }

            case "Image": {
                String picture = contents.get(twenty.get(counter)).getImage();
                setAnswers();
                Log.i(
                        "Picture", picture
                );

                if (savedInstanceState == null) {
                    Bundle bundle = new Bundle();
                    bundle.putString("image", picture);

                    FragmentTransaction transaction = fragmentManager.beginTransaction();
                    transaction.setReorderingAllowed(true);
                    transaction.replace(R.id.frame_layout, QuizImage.class, bundle);
                    transaction.commit();
                }
                break;
            }

            default:
        }
        // First question is loaded ------------------------------------------------------------------------------------

        nextContent.setVisibility(View.INVISIBLE); // Hide nextQuestion until a circle is selected

        nextContent.setOnClickListener(v -> {
            counter++;
            if(counter >= MAX){
                Intent lessonIntent = new Intent(Quiz.this, RoadMapOne.class); // TODO: Dynamically change location address
                lessonIntent.putExtra("childIdentify", childId);
                startActivity(lessonIntent);
                // TODO: Must pass with at least 7/10 correct otherwise has to do it again
            }
            else{
                // Reset all circles to neutral color
                cir1.setBack();
                cir2.setBack();
                cir3.setBack();
                cir4.setBack();

                String category1 = contents.get(twenty.get(counter)).getQuizCategory();
                switch(category1){
                    case "String": {
                        String word = contents.get(twenty.get(counter)).getQuestion();
                        setAnswers();

                        if (savedInstanceState == null) {
                            Bundle bundle = new Bundle();
                            bundle.putString("text", word);

                            FragmentTransaction transaction = fragmentManager.beginTransaction();
                            transaction.setReorderingAllowed(true);
                            transaction.replace(R.id.frame_layout, QuizText.class, bundle);
                            transaction.commit();
                        }
                        break;
                    }

                    case "Image": {
                        String picture = contents.get(twenty.get(counter)).getImage();
                        setAnswers();
                        Log.i(
                                "Picture", picture
                        );

                        if (savedInstanceState == null) {
                            Bundle bundle = new Bundle();
                            bundle.putString("image", picture);

                            FragmentTransaction transaction = fragmentManager.beginTransaction();
                            transaction.setReorderingAllowed(true);
                            transaction.replace(R.id.frame_layout, QuizImage.class, bundle);
                            transaction.commit();
                        }
                        break;
                    }

                    default:
                }
            }
            nextContent.setVisibility(View.INVISIBLE); // Hide nextQuestion until a circle is selected
        });
    }

    private void init(){
        Bundle extras = getIntent().getExtras();
        if(extras != null){
            // TODO: Need quiz database ID
            childId = extras.getString("childId");
            databaseQuizId = extras.getString("databaseQuizId");
        }

        cir1 = findViewById(R.id.circleOne);
        cir2 = findViewById(R.id.circleTwo);
        cir3 = findViewById(R.id.circleThree);
        cir4 = findViewById(R.id.circleFour);
        realm = Realm.getDefaultInstance();
        child = realm.where(ChildSchema.class).equalTo("childId", childId).findFirst();
        quiz = realm.where(QuizSchema.class).equalTo("quizId", databaseQuizId).findFirst();
        contents = quiz.getContents();
        topBar = findViewById(R.id.topBar);
        introBackBtn = topBar.findViewById(R.id.goBackBtn);
        nextContent = findViewById(R.id.next_button);

        for(int i = 0; i <= 19; i++)
            twenty.add(i);
        Collections.shuffle(twenty); // Randomize question selection

        // quizName = quiz.getQuizName(); // TODO: Should go to location_intro before this
    }

    private void setTopBar(){
        topBar.setPoints(String.valueOf(child.getScore()));
    }

    private void setAnswers(){
        cir1.setAnswer(contents.get(twenty.get(counter)).getAnswerOne());
        cir1.setOnClickListener(v -> {
            if(cir1.getAnswer().equals(contents.get(twenty.get(counter)).getAnswer())){ // If circle is correct
                cir1.correct();
            }
            else{
                cir1.incorrect();

                // Now show correct answer
                if(cir2.getAnswer().equals(contents.get(twenty.get(counter)).getAnswer())){
                    cir2.correct();
                }
                else if(cir3.getAnswer().equals(contents.get(twenty.get(counter)).getAnswer())){
                    cir3.correct();
                }
                else{
                    cir4.correct();
                }
            }
            deactivate();
            nextContent.setVisibility(View.VISIBLE); // Now show the nextQuestion button
        });

        cir2.setAnswer(contents.get(twenty.get(counter)).getAnswerTwo());
        cir2.setOnClickListener(v -> {
            if(cir2.getAnswer().equals(contents.get(twenty.get(counter)).getAnswer())){ // If circle is correct
                cir2.correct();
            }
            else{
                cir2.incorrect();

                // Now show correct answer
                if(cir1.getAnswer().equals(contents.get(twenty.get(counter)).getAnswer())){
                    cir1.correct();
                }
                else if(cir3.getAnswer().equals(contents.get(twenty.get(counter)).getAnswer())){
                    cir3.correct();
                }
                else{
                    cir4.correct();
                }
            }
            deactivate();
            nextContent.setVisibility(View.VISIBLE); // Now show the nextQuestion button
        });

        cir3.setAnswer(contents.get(twenty.get(counter)).getAnswerThree());
        cir3.setOnClickListener(v -> {
            if(cir3.getAnswer().equals(contents.get(twenty.get(counter)).getAnswer())){ // If circle is correct
                cir3.correct();
            }
            else{
                cir3.incorrect();

                // Now show correct answer
                if(cir1.getAnswer().equals(contents.get(twenty.get(counter)).getAnswer())){
                    cir1.correct();
                }
                else if(cir2.getAnswer().equals(contents.get(twenty.get(counter)).getAnswer())){
                    cir2.correct();
                }
                else{
                    cir4.correct();
                }
            }
            deactivate();
            nextContent.setVisibility(View.VISIBLE); // Now show the nextQuestion button
        });

        cir4.setAnswer(contents.get(twenty.get(counter)).getAnswerFour());
        cir4.setOnClickListener(v -> {
            if(cir4.getAnswer().equals(contents.get(twenty.get(counter)).getAnswer())){ // If circle is correct
                cir4.correct();
            }
            else{
                cir4.incorrect();

                // Now show correct answer
                if(cir1.getAnswer().equals(contents.get(twenty.get(counter)).getAnswer())){
                    cir1.correct();
                }
                else if(cir2.getAnswer().equals(contents.get(twenty.get(counter)).getAnswer())){
                    cir2.correct();
                }
                else{
                    cir3.correct();
                }
            }
            deactivate();
            nextContent.setVisibility(View.VISIBLE); // Now show the nextQuestion button
        });
    }

    private void deactivate(){
        cir1.setOnClickListener(null);
        cir2.setOnClickListener(null);
        cir3.setOnClickListener(null);
        cir4.setOnClickListener(null);
    }
}