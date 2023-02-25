package com.GrowthPlus;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.GrowthPlus.customViews.CustomTimerComponent;
import com.GrowthPlus.customViews.QuizCircle;
import com.GrowthPlus.customViews.TopBar;
import com.GrowthPlus.dataAccessLayer.Language.Translator;
import com.GrowthPlus.dataAccessLayer.Quiz.QuizSchema;
import com.GrowthPlus.dataAccessLayer.QuizContent.QuizContent;
import com.GrowthPlus.dataAccessLayer.child.ChildSchema;
import com.GrowthPlus.fragment.QuizImage;
import com.GrowthPlus.fragment.QuizText;
import com.GrowthPlus.roadMapActivity.RoadMapFour;

import java.util.ArrayList;
import java.util.Collections;

import io.realm.Realm;
import io.realm.RealmList;

public class Quiz4 extends AppCompatActivity {
    final int MAX = 10;
    ChildSchema child;
    Realm realm;
    TopBar topBar;
    Button nextContent, introBackBtn;
    String childId, databaseQuizId;
    QuizSchema quiz;
    int contentLength, counter, score;
    RealmList<QuizContent> contents;
    QuizCircle cir1, cir2, cir3, cir4;
    ArrayList<Integer> twenty = new ArrayList<>(20);

    private CountDownTimer countDownTimer;
    private CustomTimerComponent customTimerComponent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        init();

        introBackBtn.setOnClickListener(view -> {
            countDownTimer.cancel(); //since the user is exiting the quiz we need to stop the timer
            Intent lessonIntent = new Intent(Quiz4.this, RoadMapFour.class);
            lessonIntent.putExtra("childIdentify", childId);
            startActivity(lessonIntent);
            this.finish();
        });
        setTopBar();
        setTimer();

        FragmentManager fragmentManager = getSupportFragmentManager();

        // Create instance of shared preferences and save current language id
        SharedPreferences langPrefs = getSharedPreferences("LangPreferences", MODE_PRIVATE);
        String langId = langPrefs.getString("languageId", "frenchZero");
        // Create language translator and set up the Lesson string
        Translator trans = new Translator(langId);

        contentLength = contents.size();
        counter = 0;

        // This loads the first question only before needing to click the next button ---------------------------------
        String category = contents.get(twenty.get(counter)).getQuizCategory();
        switch(category){
            case "String": {
                String word = contents.get(twenty.get(counter)).getQuestion();
                setAnswers();
                if(!trans.getString(word).equals("empty")){
                    word = trans.getString(word);
                }

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
                String num = contents.get(twenty.get(counter)).getQuestion();
                int numOfImg = Integer.valueOf(num);
                setAnswers();
                Log.i("num", String.valueOf(numOfImg));
                Log.i("image", picture);

                if (savedInstanceState == null) {
                    Bundle bundle = new Bundle();
                    bundle.putString("image", picture);
                    bundle.putInt("imgNum", numOfImg);

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
            countDownTimer.cancel();
            setTimer();

            counter++; // Display 10 questions then exit activity
            if(counter >= MAX){
                if(score >= 7){
                    // TODO: make quiz completed and is NOT current
                }
                if(score > 7) {
                    // TODO: update highest score achieved on quiz if > currentPoints
                }

                countDownTimer.cancel();//since we are exiting the activity we need to stop the timer

                Intent lessonIntent = new Intent(Quiz4.this, RoadMapFour.class); // TODO: Dynamically change location address
                lessonIntent.putExtra("childIdentify", childId);
                startActivity(lessonIntent);
                this.finish();
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
                        if(!trans.getString(word).equals("empty")){
                            word = trans.getString(word);
                        }

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
                        String num = contents.get(twenty.get(counter)).getQuestion();
                        int numOfImg = Integer.valueOf(num);
                        setAnswers();

                        if (savedInstanceState == null) {
                            Bundle bundle = new Bundle();
                            bundle.putString("image", picture);
                            bundle.putInt("imgNum", numOfImg);

                            FragmentTransaction transaction = fragmentManager.beginTransaction();
                            transaction.setReorderingAllowed(true);
                            transaction.replace(R.id.frame_layout, QuizImage.class, bundle);
                            transaction.commit();
                        }
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
        score = 0;

        for(int i = 0; i <= 19; i++)
            twenty.add(i);
        Collections.shuffle(twenty); // Randomize question selection
    }

    private void setTopBar(){
        topBar.setPoints(String.valueOf(child.getScore()));
        topBar.setToCircle();
    }

    private void setAnswers(){
        cir1.setAnswer(contents.get(twenty.get(counter)).getAnswerOne());
        cir1.setOnClickListener(v -> {
            countDownTimer.cancel(); //the user selected an answer so we can stop the timer

            if(cir1.getAnswer().equals(contents.get(twenty.get(counter)).getAnswer())){ // If circle is correct
                cir1.correct();
                score++;
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
            countDownTimer.cancel(); //the user selected an answer so we can stop the timer

            if(cir2.getAnswer().equals(contents.get(twenty.get(counter)).getAnswer())){ // If circle is correct
                cir2.correct();
                score++;
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
            countDownTimer.cancel(); //the user selected an answer so we can stop the timer

            if(cir3.getAnswer().equals(contents.get(twenty.get(counter)).getAnswer())){ // If circle is correct
                cir3.correct();
                score++;
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
            countDownTimer.cancel(); //the user selected an answer so we can stop the timer

            if(cir4.getAnswer().equals(contents.get(twenty.get(counter)).getAnswer())){ // If circle is correct
                cir4.correct();
                score++;
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

    //sets a timer that counts down from 30 and moves on if the user doesn't choose an answer in time
    private void setTimer() {
        customTimerComponent = findViewById(R.id.countdownTimer);
        countDownTimer = new CountDownTimer(31000, 1000) {

            public void onTick(long millisUntilFinished) {
                customTimerComponent.setTimerText(""+millisUntilFinished / 1000);
            }
            public void onFinish() {
                countDownTimer.cancel();
                nextContent.setVisibility(View.VISIBLE); //make the next button visible
                nextContent.performClick(); //and programmatically click it
            }
        }.start();
    }
}