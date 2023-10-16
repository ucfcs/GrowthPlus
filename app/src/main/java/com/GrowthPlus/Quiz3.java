package com.GrowthPlus;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.Button;

import com.GrowthPlus.customViews.CircleTimer;
import com.GrowthPlus.customViews.CustomTimerComponent;
import com.GrowthPlus.customViews.QuizCircle;
import com.GrowthPlus.customViews.TopBar;
import com.GrowthPlus.dataAccessLayer.Language.Translator;
import com.GrowthPlus.dataAccessLayer.Quiz.QuizSchema;
import com.GrowthPlus.dataAccessLayer.QuizContent.QuizContent;
import com.GrowthPlus.dataAccessLayer.RoadMapLesson.RoadMapLesson;
import com.GrowthPlus.dataAccessLayer.RoadMapQuiz.RoadMapQuiz;
import com.GrowthPlus.dataAccessLayer.child.ChildSchema;
import com.GrowthPlus.fragment.QuizImage;
import com.GrowthPlus.fragment.QuizText;
import com.GrowthPlus.roadMapActivity.RoadMapThree;

import java.util.ArrayList;
import java.util.Collections;

import io.realm.Realm;
import io.realm.RealmList;

public class Quiz3 extends AppCompatActivity {
    final int MAX = 10;
    final int TOTALTIMER = 21000;
    final int INTERVAL = 100;
    ChildSchema child;
    Realm realm;
    TopBar quizTopBar;
    Button nextContent, introBackBtn;
    String childId, databaseQuizId;
    QuizSchema quiz;
    int counter, thisScore, childScore, quizIndex, childLessonsCompleted, minScoreToPass, numberCorrect;
    RealmList<QuizContent> contents;
    QuizCircle cir1, cir2, cir3, cir4;
    ArrayList<Integer> twenty = new ArrayList<>(20);
    private CountDownTimer countDownTimer;
    private CustomTimerComponent customTimerComponent;
    private MediaPlayer correct, incorrect;
    ConstraintLayout quizBackground;
    ConstraintLayout topBarBackground;
    private final AlphaAnimation buttonClick = new AlphaAnimation(1F, 0.8F);
    private CircleTimer circleTimer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        init();

        introBackBtn.setOnClickListener(view -> {
            view.startAnimation(buttonClick);
            // Child passes the quiz
            setPointSystem(thisScore, minScoreToPass);
            countDownTimer.cancel(); //since the user is exiting the quiz we need to stop the timer
            Intent lessonIntent = new Intent(Quiz3.this, RoadMapThree.class);
            lessonIntent.putExtra("childIdentify", childId);
            startActivity(lessonIntent);
            this.finish();
        });
        setTopBar();
        setQuizColor();
        setTimer();

        FragmentManager fragmentManager = getSupportFragmentManager();

        // Create instance of shared preferences and save current language id
        SharedPreferences langPrefs = getSharedPreferences("LangPreferences", MODE_PRIVATE);
        String langId = langPrefs.getString("languageId", "frenchZero");
        // Create language translator and set up the Lesson string
        Translator trans = new Translator(langId);

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
                    bundle.putInt("textColor", Color.rgb(3,71,58));
                    bundle.putString("langId", langId);

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
                break;
            }

            default:
        }
        // First question is loaded ------------------------------------------------------------------------------------

        nextContent.setVisibility(View.INVISIBLE); // Hide nextQuestion until a circle is selected

        nextContent.setOnClickListener(v -> {
            v.startAnimation(buttonClick);
            countDownTimer.cancel();
            setTimer();

            counter++; // Display 10 questions then exit activity
            if(counter >= MAX){
                // Child passes the quiz
                setPointSystem(thisScore, minScoreToPass);
                countDownTimer.cancel();//since we are exiting the activity we need to stop the timer
                Intent lessonIntent = new Intent(Quiz3.this, Results.class);
                lessonIntent.putExtra("childId", childId);
                lessonIntent.putExtra("whichOne", "Quiz");
                lessonIntent.putExtra("points", numberCorrect);
                lessonIntent.putExtra("max", MAX);
                lessonIntent.putExtra("whichRoadMap", "Three");
                if(thisScore >= minScoreToPass){
                    lessonIntent.putExtra("passOrNot", 1);
                }
                else{
                    lessonIntent.putExtra("passOrNot", 0);
                }
                startActivity(lessonIntent);
                this.finish();
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
                            bundle.putInt("textColor", Color.rgb(3,71,58));
                            bundle.putString("langId", langId);

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
            quizIndex = extras.getInt("quizIndex");
        }

        cir1 = findViewById(R.id.circleOne);
        cir2 = findViewById(R.id.circleTwo);
        cir3 = findViewById(R.id.circleThree);
        cir4 = findViewById(R.id.circleFour);
        realm = Realm.getDefaultInstance();
        child = realm.where(ChildSchema.class).equalTo("childId", childId).findFirst();
        quiz = realm.where(QuizSchema.class).equalTo("quizId", databaseQuizId).findFirst();
        contents = quiz.getContents();
        quizTopBar = findViewById(R.id.quizTopBar);
        introBackBtn = quizTopBar.findViewById(R.id.goBackBtn);
        nextContent = findViewById(R.id.next_button);
        quizBackground = findViewById(R.id.quiz);
        topBarBackground = findViewById(R.id.topBar);
        childScore = child.getScore();
        childLessonsCompleted = child.getRoadMapThree().getLessonsCompleted();
        thisScore = child.getRoadMapThree().getRoadMapQuizzes().get(quizIndex).getCurrentPoints();
        numberCorrect = 0;
        minScoreToPass = 7;
        correct = MediaPlayer.create(this, R.raw.correct);
        incorrect = MediaPlayer.create(this, R.raw.incorrect);
        circleTimer = findViewById(R.id.circleTimer);

        for(int i = 0; i <= 19; i++)
            twenty.add(i);
        Collections.shuffle(twenty); // Randomize question selection
    }

    private void playCorrect(){
        correct.start();
    }

    private void playIncorrect(){
        incorrect.start();
    }

    private void setTopBar(){
        quizTopBar.setPoints(String.valueOf(child.getScore()));
        quizTopBar.setToCircle();
        topBarBackground.setBackgroundColor(Color.rgb(252, 209, 70));
        quizTopBar.setShapeColor(Color.rgb(3, 71, 50));
        quizTopBar.setPointIconBackground(Color.rgb(252, 209, 70));
        quizTopBar.setPointsTextColor(Color.rgb(3, 71, 50));
    }

    public void setQuizColor(){
        quizBackground.setBackgroundColor(Color.rgb(198, 192, 18));
    }

    private void setAnswers(){
        cir1.setAnswer(contents.get(twenty.get(counter)).getAnswerOne());
        cir1.setOnClickListener(v -> {
            countDownTimer.cancel(); //the user selected an answer so we can stop the timer

            if(cir1.getAnswer().equals(contents.get(twenty.get(counter)).getAnswer())){ // If circle is correct
                playCorrect();
                cir1.correct();
                cir1.correctQuiz3();
                numberCorrect++;
                if(thisScore < MAX){
                    thisScore++;
                    childScore++;
                    setChildAndQuizScoreInRealm(childScore, thisScore);

                    //Update top bar scoring
                    quizTopBar.setPoints(String.valueOf(childScore));
                }
            }
            else{
                playIncorrect();
                cir1.incorrect();

                // Now show correct answer
                if(cir2.getAnswer().equals(contents.get(twenty.get(counter)).getAnswer())){
                    cir2.correctQuiz3();
                }
                else if(cir3.getAnswer().equals(contents.get(twenty.get(counter)).getAnswer())){
                    cir3.correctQuiz3();
                }
                else{
                    cir4.correctQuiz3();
                }
            }
            deactivate();
            nextContent.setVisibility(View.VISIBLE); // Now show the nextQuestion button
        });

        cir2.setAnswer(contents.get(twenty.get(counter)).getAnswerTwo());
        cir2.setOnClickListener(v -> {
            countDownTimer.cancel(); //the user selected an answer so we can stop the timer

            if(cir2.getAnswer().equals(contents.get(twenty.get(counter)).getAnswer())){ // If circle is correct
                playCorrect();
                cir2.correct();
                cir2.correctQuiz3();
                //cir2.setBackgroundTintList(ColorStateList.valueOf(Color.rgb(3, 71, 50)));
                numberCorrect++;
                if(thisScore < MAX){
                    thisScore++;
                    childScore++;
                    setChildAndQuizScoreInRealm(childScore, thisScore);

                    //Update top bar scoring
                    quizTopBar.setPoints(String.valueOf(childScore));
                }
            }
            else{
                playIncorrect();
                cir2.incorrect();

                // Now show correct answer
                if(cir1.getAnswer().equals(contents.get(twenty.get(counter)).getAnswer())){
                    cir1.correctQuiz3();
                }
                else if(cir3.getAnswer().equals(contents.get(twenty.get(counter)).getAnswer())){
                    cir3.correctQuiz3();
                }
                else{
                    cir4.correctQuiz3();
                }
            }
            deactivate();
            nextContent.setVisibility(View.VISIBLE); // Now show the nextQuestion button
        });

        cir3.setAnswer(contents.get(twenty.get(counter)).getAnswerThree());
        cir3.setOnClickListener(v -> {
            countDownTimer.cancel(); //the user selected an answer so we can stop the timer

            if(cir3.getAnswer().equals(contents.get(twenty.get(counter)).getAnswer())){ // If circle is correct
                playCorrect();
                cir3.correct();
                cir3.correctQuiz3();
                //cir3.setBackgroundTintList(ColorStateList.valueOf(Color.rgb(3, 71, 50)));
                numberCorrect++;
                if(thisScore < MAX){
                    thisScore++;
                    childScore++;
                    setChildAndQuizScoreInRealm(childScore, thisScore);

                    //Update top bar scoring
                    quizTopBar.setPoints(String.valueOf(childScore));
                }
            }
            else{
                playIncorrect();
                cir3.incorrect();

                // Now show correct answer
                if(cir1.getAnswer().equals(contents.get(twenty.get(counter)).getAnswer())){
                    cir1.correctQuiz3();
                }
                else if(cir2.getAnswer().equals(contents.get(twenty.get(counter)).getAnswer())){
                    cir2.correctQuiz3();
                }
                else{
                    cir4.correctQuiz3();
                }
            }
            deactivate();
            nextContent.setVisibility(View.VISIBLE); // Now show the nextQuestion button
        });

        cir4.setAnswer(contents.get(twenty.get(counter)).getAnswerFour());
        cir4.setOnClickListener(v -> {
            countDownTimer.cancel(); //the user selected an answer so we can stop the timer

            if(cir4.getAnswer().equals(contents.get(twenty.get(counter)).getAnswer())){ // If circle is correct
                playCorrect();
                cir4.correct();
                cir4.correctQuiz3();
                //cir4.setBackgroundTintList(ColorStateList.valueOf(Color.rgb(3, 71, 50)));
                numberCorrect++;
                if(thisScore < MAX){
                    thisScore++;
                    childScore++;
                    setChildAndQuizScoreInRealm(childScore, thisScore);

                    //Update top bar scoring
                    quizTopBar.setPoints(String.valueOf(childScore));
                }
            }
            else{
                playIncorrect();
                cir4.incorrect();

                // Now show correct answer
                if(cir1.getAnswer().equals(contents.get(twenty.get(counter)).getAnswer())){
                    cir1.correctQuiz3();
                }
                else if(cir2.getAnswer().equals(contents.get(twenty.get(counter)).getAnswer())){
                    cir2.correctQuiz3();
                }
                else{
                    cir3.correctQuiz3();
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
        countDownTimer = new CountDownTimer(TOTALTIMER, INTERVAL){
            @Override
            public void onTick(long millisUntilFinished) {
                float progress = (float) millisUntilFinished / TOTALTIMER;
                circleTimer.setProgress(progress);
            }

            @Override
            public void onFinish() {
                playIncorrect();
                circleTimer.setProgress(0);

                // show correct answer
                if(cir1.getAnswer().equals(contents.get(twenty.get(counter)).getAnswer())){
                    cir1.correct();
                }
                else if(cir2.getAnswer().equals(contents.get(twenty.get(counter)).getAnswer())){
                    cir2.correct();
                }
                else if(cir3.getAnswer().equals(contents.get(twenty.get(counter)).getAnswer())){
                    cir3.correct();
                }
                else{
                    cir4.correct();
                }

                deactivate();
                nextContent.setVisibility(View.VISIBLE);
            }
        }.start();
    }

    private void setPointSystem(int currentScore, int minToPass){
        if(currentScore >= minToPass){ //minToPass = 7
            realm.executeTransactionAsync(realm1 -> {
                ChildSchema child = realm1.where(ChildSchema.class).equalTo("childId", childId).findFirst();
                assert child != null;
                RoadMapQuiz currentQuiz = child.getRoadMapThree().getRoadMapQuizzes().get(quizIndex);
                assert currentQuiz != null;
                // If quiz was not previously completed, set the state, otherwise just update score
                // Update the completed lessons counter to move to next tile
                if(!currentQuiz.getCompleted()){
                    currentQuiz.setCurrent(false);
                    currentQuiz.setCompleted(true);
                    childLessonsCompleted ++;
                    child.getRoadMapThree().setLessonsCompleted(childLessonsCompleted);
                    RoadMapLesson nextLesson = child.getRoadMapThree().getRoadMapLessons().get(childLessonsCompleted);
                    assert nextLesson != null;
                    nextLesson.setCurrent(true);
                    nextLesson.setCompleted(false);
                }
            });
        }
    }

    private void setChildAndQuizScoreInRealm(int childScore, int quizScore){
        realm.executeTransactionAsync(realm1 -> {
            ChildSchema child = realm1.where(ChildSchema.class).equalTo("childId", childId).findFirst();
            assert child != null;
            child.setScore(childScore);
            child.getRoadMapThree().getRoadMapQuizzes().get(quizIndex).setCurrentPoints(quizScore);
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Close the Realm instance.
        realm.close();
    }
}