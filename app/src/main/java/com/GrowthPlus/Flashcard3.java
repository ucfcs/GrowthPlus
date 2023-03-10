package com.GrowthPlus;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.media.MediaPlayer;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.Button;

import com.GrowthPlus.customViews.TopBar;
import com.GrowthPlus.dataAccessLayer.Flashcard.FlashcardSchema;
import com.GrowthPlus.dataAccessLayer.Lesson.LessonSchema;
import com.GrowthPlus.dataAccessLayer.RoadMapLesson.RoadMapLesson;
import com.GrowthPlus.dataAccessLayer.RoadMapQuiz.RoadMapQuiz;
import com.GrowthPlus.dataAccessLayer.RoadMapScenarioGame.RoadMapScenarioGame;
import com.GrowthPlus.dataAccessLayer.child.ChildSchema;
import com.GrowthPlus.fragment.CustomEquation;
import com.GrowthPlus.fragment.CustomImage;
import com.GrowthPlus.fragment.CustomImageOperator;
import com.GrowthPlus.fragment.CustomImageWord;
import com.GrowthPlus.fragment.FlashcardAnswer;
import com.GrowthPlus.roadMapActivity.RoadMapThree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;

import io.realm.Realm;
import io.realm.RealmChangeListener;
import io.realm.RealmList;

public class Flashcard3 extends AppCompatActivity {
    private String dataBaseLessonId;
    private String childId;
    private ChildSchema child;
    private LessonSchema lesson;
    private Realm realm;
    private RealmList<FlashcardSchema> lessonFlashcards;
    private String image;
    private com.GrowthPlus.customViews.Flashcard flashcardContainer;
    private Button nextFlashcard;
    private FlashcardSchema flashcard;
    private FragmentManager fragmentManager;
    private String childAnswer;
    private ColorStateList answerColor;
    private ColorStateList correctAnswerColor;
    private ColorStateList wrongAnswerColor;
    private ColorStateList resetColor;
    private Button flashcardBackBtn;
    private TopBar flashcardTopBar;
    private final int NUMBER_INPUT_ONLY = 2;
    private int counter = 0;
    private int MAX;
    private int currentChildScore;
    private int numberCorrect;
    private String flashcardAnswer;
    private String firstNumber;
    private String firstOperator;
    private String secondNumber;
    private String secondOperator;
    private String category;
    private int childLessonsCompleted;
    private int lessonIndex;
    private int minScoreToPass;
    private int minNumToPass;
    private int MAX_LESSON_SCORE;
    private int currentLessonScore, howMany;
    private MediaPlayer correct, incorrect;
    ArrayList<Integer> randomizer;
    ConstraintLayout flashcardBackground;
    ConstraintLayout topBarBackground;
    private boolean isCompleted;
    private String lessonCategory;
    private RealmChangeListener<ChildSchema> realmListener;
    private final AlphaAnimation buttonClick = new AlphaAnimation(1F, 0.8F);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flashcard);
        init();

        flashcardBackBtn.setOnClickListener(view -> {
            view.startAnimation(buttonClick);
            if (currentLessonScore >= minScoreToPass && !isCompleted){
                Objects.requireNonNull(
                                realm.where(ChildSchema.class).equalTo("childId", childId).findFirst())
                        .addChangeListener(realmListener);
                setLessonState();
            }else{
                backToRoadMap();
            }
        });
        setTopBar();
        setFlashcardBackgroundColor();

        /*
         * Switch statement for first flashcard, we don't have an intro so we start at index 0
         * */
        category = Objects.requireNonNull(lessonFlashcards.get(randomizer.get(counter))).getCategory();
        flashcard = lessonFlashcards.get(randomizer.get(counter));

        assert flashcard != null;
        flashcardAnswer = flashcard.getAnswer();
        image = flashcard.getImage();
        nextFlashcard.setVisibility(View.INVISIBLE);

        flashcardContainer.setRawInputType(NUMBER_INPUT_ONLY);
        switch (category){
            case "customImage":{
                firstNumber = flashcard.getFirstNumber();
                if (savedInstanceState == null) {
                    Bundle bundle = new Bundle();
                    bundle.putString("image", image);
                    bundle.putString("answer", flashcardAnswer);
                    FragmentTransaction transaction = fragmentManager.beginTransaction();
                    transaction.setReorderingAllowed(true);
                    transaction.replace(flashcardContainer.findViewById(R.id.frame_layout_flashcard).getId(), CustomImage.class, bundle);
                    transaction.commit();
                }
                break;
            }

            case "customImageOperator":{
                firstNumber = flashcard.getFirstNumber();
                firstOperator = flashcard.getFirstOperator();
                secondNumber = flashcard.getSecondNumber();
                if (savedInstanceState == null) {
                    Bundle bundle = new Bundle();
                    bundle.putString("image", image);
                    bundle.putString("firstNumber", firstNumber);
                    bundle.putString("firstOperator", firstOperator);
                    bundle.putString("secondNumber", secondNumber);
                    bundle.putInt("textColor", Color.rgb(252, 209, 70));
                    FragmentTransaction transaction = fragmentManager.beginTransaction();
                    transaction.setReorderingAllowed(true);
                    transaction.replace(flashcardContainer.findViewById(R.id.frame_layout_flashcard).getId(), CustomImageOperator.class, bundle);
                    transaction.commit();
                }
                break;
            }

            case "customEquation":{
                firstNumber = flashcard.getFirstNumber();
                firstOperator = flashcard.getFirstOperator();
                secondNumber = flashcard.getSecondNumber();
                secondOperator = flashcard.getSecondOperator();
                if (savedInstanceState == null) {
                    Bundle bundle = new Bundle();
                    bundle.putString("image", image);
                    bundle.putString("firstNumber", firstNumber);
                    bundle.putString("firstOperator", firstOperator);
                    bundle.putString("secondNumber", secondNumber);
                    bundle.putString("carryInput", secondOperator);
                    bundle.putInt("textColor", Color.rgb(252, 209, 70));
                    FragmentTransaction transaction = fragmentManager.beginTransaction();
                    transaction.setReorderingAllowed(true);
                    transaction.replace(flashcardContainer.findViewById(R.id.frame_layout_flashcard).getId(), CustomEquation.class, bundle);
                    transaction.commit();
                }
                break;
            }

            case "customImageWord":{
                firstNumber = flashcard.getFirstNumber();
                firstOperator = flashcard.getFirstOperator();
                secondNumber = flashcard.getSecondNumber();
                secondOperator = flashcard.getSecondOperator();
                if (savedInstanceState == null) {
                    Bundle bundle = new Bundle();
                    bundle.putString("image", image);
                    bundle.putString("firstNumber", firstNumber);
                    bundle.putString("firstOperator", firstOperator);
                    bundle.putString("secondNumber", secondNumber);
                    bundle.putString("secondOperator", secondOperator);
                    bundle.putInt("textColor", Color.rgb(252, 209, 70));
                    FragmentTransaction transaction = fragmentManager.beginTransaction();
                    transaction.setReorderingAllowed(true);
                    transaction.replace(flashcardContainer.findViewById(R.id.frame_layout_flashcard).getId(), CustomImageWord.class, bundle);
                    transaction.commit();
                }
                break;
            }

            default:{
                try {
                    throw new Exception("The category does not fit a case, check the return value");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        /*
         * Handles the answer verification logic.
         * If child taps the flashcard the animate() method fires up and rotates the flashcard 360 degrees.
         * Then, the onAnimationEnd sets the corresponding logic after the animation is done.
         * Refer to figma for clarification.
         * */
        flashcardContainer.setOnClickListener(view -> {
            childAnswer = flashcardContainer.getAnswer();
            // Don't take empty input
            if((childAnswer == null) || childAnswer.equals("")){
                // Maybe have a red field around it to indicate it needs input
                flashcardContainer.setAnswerOpacity(1f); // Doesn't do anything, it's just so that there is no empty field.
            }else{
                if(childAnswer.equals(flashcardAnswer)){
                    playCorrect();
                    answerColor = correctAnswerColor;
                    numberCorrect ++ ;
                    if(currentLessonScore < MAX_LESSON_SCORE){
                        currentLessonScore+= 2;
                        currentChildScore+= 2;

                        realm.executeTransactionAsync(realm1 -> {
                            ChildSchema child = realm1.where(ChildSchema.class).equalTo("childId", childId).findFirst();
                            assert child != null;
                            child.setScore(currentChildScore);
                            Objects.requireNonNull(child.getRoadMapThree().getRoadMapLessons().get(lessonIndex)).setCurrentScore(currentLessonScore);
                        });
                    }
                }
                else {
                    playIncorrect();
                    answerColor = wrongAnswerColor;
                }

                flashcardContainer.animate().setDuration(1000).rotationYBy(360f).setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationStart (Animator animation){
                        super.onAnimationStart(animation);
                        flashcardContainer.setEnabled(false);
                        flashcardContainer.setAnswerEnabled(false);
                    }
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        super.onAnimationEnd(animation);

                        if (savedInstanceState == null) {
                            Bundle bundle = new Bundle();
                            bundle.putString("answer", flashcardAnswer);
                            bundle.putInt("answerTextColor", Color.rgb(252, 209, 70));
                            FragmentTransaction transaction = fragmentManager.beginTransaction();
                            transaction.setReorderingAllowed(true);
                            transaction.replace(flashcardContainer.findViewById(R.id.frame_layout_flashcard).getId(), FlashcardAnswer.class, bundle);
                            transaction.commit();
                            flashcardTopBar.setPoints(String.valueOf(currentChildScore));
                        }

                        flashcardContainer.setFlashcardColor(answerColor);
                        flashcardContainer.setAnswerOpacity(0.7f); // opacity
                        flashcardContainer.setEnabled(false);
                        flashcardContainer.setAnswerEnabled(false);
                        nextFlashcard.setVisibility(View.VISIBLE);
                    }
                });
            }
        });

        // Now handle the next flashcard onClick, increment the counter to go to next flashcard
        // Make sure to reset the flashcardContainer state
        nextFlashcard.setOnClickListener(view -> {
            view.startAnimation(buttonClick);
            counter++;
            if(counter >= MAX){
                // Passing condition number of correct flashcards
                if (currentLessonScore >= minScoreToPass && !isCompleted){
                    setLessonState();
                }
                Intent lessonIntent = new Intent(Flashcard3.this, Results.class);
                lessonIntent.putExtra("childId", childId);
                lessonIntent.putExtra("whichOne", "Flash");
                lessonIntent.putExtra("points", numberCorrect);
                lessonIntent.putExtra("max", MAX);
                lessonIntent.putExtra("whichRoadMap", "Three");
                if(numberCorrect >= minNumToPass){
                    lessonIntent.putExtra("passOrNot", 1);
                }
                else{
                    lessonIntent.putExtra("passOrNot", 0);
                }
                startActivity(lessonIntent);
                this.finish();

            }else {

                // Resetting state of flashcard
                flashcardContainer.setText(null);
                flashcardContainer.setFlashcardColor(resetColor);
                flashcardContainer.setAnswerOpacity(1f);
                flashcardContainer.setEnabled(true);
                flashcardContainer.setAnswerEnabled(true);
                flashcardContainer.setRawInputType(NUMBER_INPUT_ONLY);

                flashcard = lessonFlashcards.get(randomizer.get(counter));

                assert flashcard != null;
                category = flashcard.getCategory();
                flashcardAnswer = flashcard.getAnswer();
                image = flashcard.getImage();

                nextFlashcard.setVisibility(View.INVISIBLE);

                switch (category){
                    case "customImage":{
                        firstNumber = flashcard.getFirstNumber();
                        if (savedInstanceState == null) {
                            Bundle bundle = new Bundle();
                            bundle.putString("image", image);
                            bundle.putString("firstNumber", firstNumber);
                            bundle.putString("answer", flashcardAnswer);
                            FragmentTransaction transaction = fragmentManager.beginTransaction();
                            transaction.setReorderingAllowed(true);
                            transaction.replace(flashcardContainer.findViewById(R.id.frame_layout_flashcard).getId(), CustomImage.class, bundle);
                            transaction.commit();
                        }
                        break;
                    }

                    case "customImageOperator":{
                        firstNumber = flashcard.getFirstNumber();
                        firstOperator = flashcard.getFirstOperator();
                        secondNumber = flashcard.getSecondNumber();
                        if (savedInstanceState == null) {
                            Bundle bundle = new Bundle();
                            bundle.putString("image", image);
                            bundle.putString("firstNumber", firstNumber);
                            bundle.putString("firstOperator", firstOperator);
                            bundle.putString("secondNumber", secondNumber);
                            bundle.putString("answer", flashcardAnswer);
                            bundle.putBoolean("isAnimationDone", false);
                            bundle.putInt("textColor", Color.rgb(252, 209, 70));
                            FragmentTransaction transaction = fragmentManager.beginTransaction();
                            transaction.setReorderingAllowed(true);
                            transaction.replace(flashcardContainer.findViewById(R.id.frame_layout_flashcard).getId(), CustomImageOperator.class, bundle);
                            transaction.commit();
                        }
                        break;
                    }

                    case "customEquation":{
                        firstNumber = flashcard.getFirstNumber();
                        firstOperator = flashcard.getFirstOperator();
                        secondNumber = flashcard.getSecondNumber();
                        secondOperator = flashcard.getSecondOperator();
                        if (savedInstanceState == null) {
                            Bundle bundle = new Bundle();
                            bundle.putString("image", image);
                            bundle.putString("firstNumber", firstNumber);
                            bundle.putString("firstOperator", firstOperator);
                            bundle.putString("secondNumber", secondNumber);
                            bundle.putString("carryInput", secondOperator);
                            bundle.putInt("textColor", Color.rgb(252, 209, 70));
                            FragmentTransaction transaction = fragmentManager.beginTransaction();
                            transaction.setReorderingAllowed(true);
                            transaction.replace(flashcardContainer.findViewById(R.id.frame_layout_flashcard).getId(), CustomEquation.class, bundle);
                            transaction.commit();
                        }
                        break;
                    }

                    case "customImageWord":{
                        firstNumber = flashcard.getFirstNumber();
                        firstOperator = flashcard.getFirstOperator();
                        secondNumber = flashcard.getSecondNumber();
                        secondOperator = flashcard.getSecondOperator();
                        if (savedInstanceState == null) {
                            Bundle bundle = new Bundle();
                            bundle.putString("image", image);
                            bundle.putString("firstNumber", firstNumber);
                            bundle.putString("firstOperator", firstOperator);
                            bundle.putString("secondNumber", secondNumber);
                            bundle.putString("secondOperator", secondOperator);
                            bundle.putInt("textColor", Color.rgb(252, 209, 70));
                            FragmentTransaction transaction = fragmentManager.beginTransaction();
                            transaction.setReorderingAllowed(true);
                            transaction.replace(flashcardContainer.findViewById(R.id.frame_layout_flashcard).getId(), CustomImageWord.class, bundle);
                            transaction.commit();
                        }
                        break;
                    }

                    default:{
                        try {
                            throw new Exception("The category does not fit a case, check the return value");
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        });
    }

    private void init(){
        realm = Realm.getDefaultInstance();
        Bundle extras = getIntent().getExtras();
        if(extras != null){
            dataBaseLessonId = extras.getString("dataBaseLessonId");
            childId = extras.getString("childId");
            image = extras.getString("lessonImage");
            lessonIndex = extras.getInt("lessonIndex");
        }
        flashcardTopBar = findViewById(R.id.flashcardTopBar);
        flashcardBackground = findViewById(R.id.flashcardLayout);
        topBarBackground = findViewById(R.id.topBar);
        child = realm.where(ChildSchema.class).equalTo("childId", childId).findFirst();
        assert child != null;
        currentChildScore = child.getScore();
        numberCorrect = 0;
        lesson = realm.where(LessonSchema.class).equalTo("lessonId", dataBaseLessonId).findFirst();
        assert lesson != null;
        lessonFlashcards = lesson.getFlashcards();
        lessonCategory = lesson.getCategory();
        flashcardContainer = findViewById(R.id.flashcardContainer);
        nextFlashcard = findViewById(R.id.next_button_flashcard);
        flashcardBackBtn = flashcardTopBar.findViewById(R.id.goBackBtn);
        fragmentManager = getSupportFragmentManager();
        correctAnswerColor = ContextCompat.getColorStateList(this, R.color.dark_green);
        wrongAnswerColor = ContextCompat.getColorStateList(this, R.color.red);
        resetColor = ContextCompat.getColorStateList(this, R.color.blue);
        childLessonsCompleted = child.getRoadMapThree().getLessonsCompleted();
        currentLessonScore = child.getRoadMapThree().getRoadMapLessons().get(lessonIndex).getCurrentScore();
        correct = MediaPlayer.create(this, R.raw.correct);
        incorrect = MediaPlayer.create(this, R.raw.incorrect);
        howMany = lessonFlashcards.size();
        randomizer = new ArrayList<>(howMany);
        for(int i = 0; i < howMany; i++)
            randomizer.add(i);
        Collections.shuffle(randomizer); // Randomize question selection
        currentLessonScore = Objects.requireNonNull(child.getRoadMapThree().getRoadMapLessons().get(lessonIndex)).getCurrentScore();
        isCompleted = Objects.requireNonNull(child.getRoadMapThree().getRoadMapLessons().get(lessonIndex)).getCompleted();
        realmListener = realmChildSchema -> {
            // Navigate back to RoadMap after realm is finished performing tasks in the background thread
            backToRoadMap();
        };

        if(lessonIndex == 9){
            MAX = 10;
            MAX_LESSON_SCORE = 20;
            minScoreToPass = 14;
            minNumToPass = 7;
        }else{
            MAX = 5;
            MAX_LESSON_SCORE = 10;
            minScoreToPass = 7;
            minNumToPass = 4;
        }
    }

    private void playCorrect(){
        correct.start();
    }

    private void playIncorrect(){
        incorrect.start();
    }

    private void setTopBar(){
        flashcardTopBar.setPoints(String.valueOf(child.getScore()));
        flashcardTopBar.setToTriangle();
        topBarBackground.setBackgroundColor(Color.rgb(252, 209, 70));
        flashcardTopBar.setShapeColor(Color.rgb(3, 71, 50));
        flashcardTopBar.setPointIconBackground(Color.rgb(252, 209, 70));
        flashcardTopBar.setPointsTextColor(Color.rgb(3, 71, 50));
    }

    public void setFlashcardBackgroundColor(){
        flashcardBackground.setBackgroundColor(Color.rgb(198, 192, 18));
    }

    private void setLessonState() {
        realm.executeTransactionAsync(realm1 -> {
            ChildSchema child = realm1.where(ChildSchema.class).equalTo("childId", childId).findFirst();

            assert child != null;
            RoadMapLesson currentLesson = child.getRoadMapThree().getRoadMapLessons().get(childLessonsCompleted);
            assert currentLesson != null;
            currentLesson.setCurrent(false);
            currentLesson.setCompleted(true);
            updateCatCount(child);
            updateTotalLessonsCompleted(child);

            if (childLessonsCompleted < 9) {
                childLessonsCompleted++;

                if (childLessonsCompleted == 3) {
                    // Set the quiz state
                    RoadMapQuiz quizOne = child.getRoadMapThree().getRoadMapQuizzes().get(0);
                    assert quizOne != null;
                    quizOne.setCompleted(false);
                    quizOne.setCurrent(true);
                } else if (childLessonsCompleted == 7) {
                    RoadMapQuiz quizTwo = child.getRoadMapThree().getRoadMapQuizzes().get(1);
                    assert quizTwo != null;
                    quizTwo.setCompleted(false);
                    quizTwo.setCurrent(true);
                } else {
                    child.getRoadMapThree().setLessonsCompleted(childLessonsCompleted);
                    RoadMapLesson nextLesson = child.getRoadMapThree().getRoadMapLessons().get(childLessonsCompleted);
                    assert nextLesson != null;
                    nextLesson.setCurrent(true);
                    nextLesson.setCompleted(false);
                }
            } else {
                setGameState(child);
            }
        });
    }

    private void setGameState(ChildSchema child){
        RoadMapScenarioGame game = child.getRoadMapThree().getScenarioGame();
        if(!game.getCompleted()){
            game.setCompleted(false);
            game.setCurrent(true);
        }
    }

    private void backToRoadMap(){
        Intent lessonIntent = new Intent(Flashcard3.this, RoadMapThree.class);
        lessonIntent.putExtra("childIdentify", childId);
        startActivity(lessonIntent);
        this.finish();
    }

    private void updateTotalLessonsCompleted(ChildSchema child){
        Integer totalLessonsCompleted = child.getTotalLessonsCompleted();
        totalLessonsCompleted++;
        child.setTotalLessonsCompleted(totalLessonsCompleted);
    }

    private void updateCatCount(ChildSchema child) {
        if (lessonCategory != null) {
            switch (lessonCategory) {
                case "numbers": {
                    int catCount = child.getCatCountNumbers();
                    catCount++;
                    child.setCatCountNumbers(catCount);
                    break;
                }
                case "units": {
                    int catCount = child.getCatCountUnits();
                    catCount++;
                    child.setCatCountUnits(catCount);
                    break;
                }
                case "addition": {
                    int catCount = child.getCatCountAddition();
                    catCount++;
                    child.setCatCountAddition(catCount);
                    break;
                }
                case "subtraction": {
                    int catCount = child.getCatCountSubtraction();
                    catCount++;
                    child.setCatCountSubtraction(catCount);
                    break;
                }
                case "multiplication": {
                    int catCount = child.getCatCountMultiplication();
                    catCount++;
                    child.setCatCountMultiplication(catCount);
                    break;
                }
                case "division": {
                    int catCount = child.getCatCountDivision();
                    catCount++;
                    child.setCatCountDivision(catCount);
                    break;
                }
                case "length": {
                    int catCount = child.getCatCountLength();
                    catCount++;
                    child.setCatCountLength(catCount);
                    break;
                }
                case "weight": {
                    int catCount = child.getCatCountWeightVolume();
                    catCount++;
                    child.setCatCountWeightVolume(catCount);
                    break;
                }
                case "money": {
                    int catCount = child.getCatCountMoney();
                    catCount++;
                    child.setCatCountMoney(catCount);
                    break;
                }
                case "time": {
                    int catCount = child.getCatCountTime();
                    catCount++;
                    child.setCatCountTime(catCount);
                    break;
                }
                case "shapes": {
                    int catCount = child.getCatCountShapes();
                    catCount++;
                    child.setCatCountShapes(catCount);
                    break;
                }
                case "angles": {
                    int catCount = child.getCatCountAngles();
                    catCount++;
                    child.setCatCountAngles(catCount);
                    break;
                }
                default: {
                    try {
                        throw new Exception("Category does not exist, check return value");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        else{
            int catCount = child.getCatCountReview();
            catCount++;
            child.setCatCountReview(catCount);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Remove the listener.
        Objects.requireNonNull(
                        realm.where(ChildSchema.class).equalTo("childId", childId).findFirst())
                .removeChangeListener(realmListener);
        // Close the Realm instance.
        realm.removeAllChangeListeners();
        realm.close();

    }


}