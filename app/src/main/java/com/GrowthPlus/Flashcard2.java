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
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.GrowthPlus.customViews.TopBar;
import com.GrowthPlus.dataAccessLayer.Flashcard.FlashcardSchema;
import com.GrowthPlus.dataAccessLayer.Lesson.LessonSchema;
import com.GrowthPlus.dataAccessLayer.RoadMapLesson.RoadMapLesson;
import com.GrowthPlus.dataAccessLayer.RoadMapQuiz.RoadMapQuiz;
import com.GrowthPlus.dataAccessLayer.child.ChildSchema;
import com.GrowthPlus.fragment.CustomEquation;
import com.GrowthPlus.fragment.CustomImage;
import com.GrowthPlus.fragment.CustomImageOperator;
import com.GrowthPlus.fragment.CustomImageWord;
import com.GrowthPlus.fragment.FlashcardAnswer;
import com.GrowthPlus.roadMapActivity.RoadMapTwo;
import com.GrowthPlus.utilities.ImageSrcIdentifier;

import io.realm.Realm;
import io.realm.RealmList;

public class Flashcard2 extends AppCompatActivity {
    private String dataBaseLessonId;
    private String childId;
    private ChildSchema child; // Need this to update score
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
    private int minToPass;
    private int minScoreToPass;
    private int MAX_LESSON_SCORE;
    private int currentLessonScore;
    ConstraintLayout flashcardBackground;
    ConstraintLayout topBarBackground;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flashcard);
        init();

        flashcardBackBtn.setOnClickListener(view -> {
            setPointSystem(currentLessonScore, minScoreToPass);
            Intent lessonIntent = new Intent(Flashcard2.this, RoadMapTwo.class);
            lessonIntent.putExtra("childIdentify", childId);
            startActivity(lessonIntent);
            this.finish();
        });
        setTopBar();
        setFlashcardBackgroundColor();

        /*
         * Switch statement for first flashcard, we don't have an intro so we start at index 0
         * */
        assert lessonFlashcards.get(counter) != null;
        category = lessonFlashcards.get(counter).getCategory();
        flashcard = lessonFlashcards.get(counter);

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
                    answerColor = correctAnswerColor;
                    numberCorrect ++ ;
                    if(currentLessonScore < MAX_LESSON_SCORE){
                        currentLessonScore+= 2;
                        currentChildScore+= 2;

                        realm.executeTransactionAsync(realm1 -> {
                            ChildSchema child = realm1.where(ChildSchema.class).equalTo("childId", childId).findFirst();
                            assert child != null;
                            child.setScore(currentChildScore);
                            child.getRoadMapTwo().getRoadMapLessons().get(lessonIndex).setCurrentScore(currentLessonScore);
                        });
                    }
                }
                else {
                    answerColor = wrongAnswerColor;
                }

                flashcardContainer.animate().setDuration(500).rotationYBy(360f).setListener(new AnimatorListenerAdapter() {
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
            counter++;
            if(counter >= MAX){
                // Passing condition number of correct flashcards
                setPointSystem(numberCorrect, minToPass);
                Intent lessonIntent = new Intent(Flashcard2.this, RoadMapTwo.class); // TODO: Dynamically change location address
                lessonIntent.putExtra("childIdentify", childId);
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

                assert lessonFlashcards.get(counter) != null;
                flashcard = lessonFlashcards.get(counter);

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
                        Log.i("default", "The category does not fit the case, check the return value");
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
        flashcardContainer = findViewById(R.id.flashcardContainer);
        nextFlashcard = findViewById(R.id.next_button_flashcard);
        flashcardBackBtn = flashcardTopBar.findViewById(R.id.goBackBtn);
        fragmentManager = getSupportFragmentManager();
        correctAnswerColor = ContextCompat.getColorStateList(this, R.color.light_green);
        wrongAnswerColor = ContextCompat.getColorStateList(this, R.color.red);
        resetColor = ContextCompat.getColorStateList(this, R.color.blue);
        childLessonsCompleted = child.getRoadMapTwo().getLessonsCompleted();
        currentLessonScore = child.getRoadMapTwo().getRoadMapLessons().get(lessonIndex).getCurrentScore();

        if(lessonIndex == 9){
            MAX = 10;
            MAX_LESSON_SCORE = 20;
            minToPass = 7;
            minScoreToPass = 14;
        }else{
            MAX = 5;
            MAX_LESSON_SCORE = 10;
            minToPass = 4;
            minScoreToPass = 7;
        }
    }

    private void setTopBar(){
        flashcardTopBar.setPoints(String.valueOf(child.getScore()));
        flashcardTopBar.setToTriangle();
        topBarBackground.setBackgroundColor(Color.rgb(252, 209, 70));
        flashcardTopBar.setShapeColor(Color.rgb(96, 163, 200));
        flashcardTopBar.setPointIconBackground(Color.rgb(252, 209, 70));
        flashcardTopBar.setPointsTextColor(Color.rgb(96, 163, 200));
    }

    public void setFlashcardBackgroundColor(){
        flashcardBackground.setBackgroundColor(Color.rgb(232, 160, 78));
    }

    private void setPointSystem(int currentScore, int minToPass){
        if(currentScore >= minToPass){
            // This is the case if lesson is completed and child came back to play it again
            // Don't increase the lessonCompleted count
            if(child.getRoadMapTwo().getRoadMapLessons().get(lessonIndex).getCompleted()){
                realm.executeTransactionAsync(realm1 -> {
                    ChildSchema child = realm1.where(ChildSchema.class).equalTo("childId", childId).findFirst();
                    assert child != null;
                });
            }else{
                realm.executeTransactionAsync(realm1 -> {
                    ChildSchema child = realm1.where(ChildSchema.class).equalTo("childId", childId).findFirst();

                    assert child != null;
                    RoadMapLesson currentLesson = child.getRoadMapTwo().getRoadMapLessons().get(childLessonsCompleted);
                    assert currentLesson != null;
                    currentLesson.setCurrent(false);
                    currentLesson.setCompleted(true);

                    if (childLessonsCompleted < 9){
                        childLessonsCompleted ++;

                        if(childLessonsCompleted == 3){
                            // Set the quiz state
                            RoadMapQuiz quizOne = child.getRoadMapTwo().getRoadMapQuizzes().get(0);
                            assert quizOne != null;
                            quizOne.setCompleted(false);
                            quizOne.setCurrent(true);
                        }else if(childLessonsCompleted == 7){
                            RoadMapQuiz quizTwo = child.getRoadMapTwo().getRoadMapQuizzes().get(1);
                            assert quizTwo != null;
                            quizTwo.setCompleted(false);
                            quizTwo.setCurrent(true);
                        }else{
                            child.getRoadMapTwo().setLessonsCompleted(childLessonsCompleted);
                            RoadMapLesson nextLesson = child.getRoadMapTwo().getRoadMapLessons().get(childLessonsCompleted);
                            assert nextLesson != null;
                            nextLesson.setCurrent(true);
                            nextLesson.setCompleted(false);
                        }
                    } /*TODO: Handle childLessonCompleted == 9,
                                If lessons completed is 9, then all lessons are completed
                                the count starts at zero, hence 9 and if so, enable roadmap game
                                Roadmap game is not implemented yet so it is open for now. */
                });
            }
        }
    }
}