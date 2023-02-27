package com.GrowthPlus;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.GrowthPlus.customViews.TopBar;
import com.GrowthPlus.dataAccessLayer.Flashcard.FlashcardSchema;
import com.GrowthPlus.dataAccessLayer.Language.Translator;
import com.GrowthPlus.dataAccessLayer.Lesson.LessonSchema;
import com.GrowthPlus.dataAccessLayer.child.ChildSchema;
import com.GrowthPlus.fragment.CustomEquation;
import com.GrowthPlus.fragment.CustomImage;
import com.GrowthPlus.fragment.CustomImageOperator;
import com.GrowthPlus.fragment.CustomImageWord;
import com.GrowthPlus.fragment.FlashcardAnswer;
import com.GrowthPlus.roadMapActivity.RoadMapFour;
import com.GrowthPlus.utilities.ImageSrcIdentifier;

import io.realm.Realm;
import io.realm.RealmList;

public class Flashcard4 extends AppCompatActivity {

    private String dataBaseLessonId;
    private String childId;
    private ChildSchema child; // Need this to update score
    private LessonSchema lesson;
    private Realm realm;
    private RealmList<FlashcardSchema> lessonFlashcards;
    private String image;
    private ImageSrcIdentifier imageSrcIdentifier;
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
    private ColorStateList resetInputTint;
    private TopBar flashcardTopBar;
    private final int TEXT_INPUT_ONLY = 1;
    private final int NUMBER_INPUT_ONLY = 2;
    int counter = 0;
    final int MAX = 5;
    String flashcardAnswer;
    String firstNumber;
    String firstOperator;
    String secondNumber;
    String secondOperator;
    String category;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flashcard);
        init();

        flashcardBackBtn.setOnClickListener(view -> {
            Intent lessonIntent = new Intent(Flashcard4.this, RoadMapFour.class);
            lessonIntent.putExtra("childIdentify", childId);
            startActivity(lessonIntent);
            this.finish();
        });
        setTopBar();

        // Create instance of shared preferences and save current language id
        SharedPreferences langPrefs = getSharedPreferences("LangPreferences", MODE_PRIVATE);
        String langId = langPrefs.getString("languageId", "frenchZero");
        // Create language translator and set up the Lesson string
        Translator trans = new Translator(langId);

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
                if (savedInstanceState == null) {
                    Bundle bundle = new Bundle();
                    bundle.putString("image", image);
                    bundle.putString("firstNumber", firstNumber);
                    bundle.putString("firstOperator", firstOperator);
                    bundle.putString("secondNumber", secondNumber);
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

                if (!trans.getString(firstOperator).equals("empty")) {
                    firstOperator = trans.getString(firstOperator);
                }
                if (!trans.getString(secondOperator).equals("empty")) {
                    secondOperator = trans.getString(secondOperator);
                }

                if (savedInstanceState == null) {
                    Bundle bundle = new Bundle();
                    bundle.putString("image", image);
                    bundle.putString("firstNumber", firstNumber);
                    bundle.putString("firstOperator", firstOperator);
                    bundle.putString("secondNumber", secondNumber);
                    bundle.putString("secondOperator", secondOperator);
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
                }
                else {
                    answerColor = wrongAnswerColor;
                }

                flashcardContainer.animate().setDuration(500).rotationYBy(360f).setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        super.onAnimationEnd(animation);

                        if (savedInstanceState == null) {
                            Bundle bundle = new Bundle();
                            bundle.putString("answer", flashcardAnswer);
                            FragmentTransaction transaction = fragmentManager.beginTransaction();
                            transaction.setReorderingAllowed(true);
                            transaction.replace(flashcardContainer.findViewById(R.id.frame_layout_flashcard).getId(), FlashcardAnswer.class, bundle);
                            transaction.commit();
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
                Intent lessonIntent = new Intent(Flashcard4.this, RoadMapFour.class); // TODO: Dynamically change location address
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
                        if (savedInstanceState == null) {
                            Bundle bundle = new Bundle();
                            bundle.putString("image", image);
                            bundle.putString("firstNumber", firstNumber);
                            bundle.putString("firstOperator", firstOperator);
                            bundle.putString("secondNumber", secondNumber);
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

                        if (!trans.getString(firstOperator).equals("empty")) {
                            firstOperator = trans.getString(firstOperator);
                        }
                        if (!trans.getString(secondOperator).equals("empty")) {
                            secondOperator = trans.getString(secondOperator);
                        }

                        if (savedInstanceState == null) {
                            Bundle bundle = new Bundle();
                            bundle.putString("image", image);
                            bundle.putString("firstNumber", firstNumber);
                            bundle.putString("firstOperator", firstOperator);
                            bundle.putString("secondNumber", secondNumber);
                            bundle.putString("secondOperator", secondOperator);
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
        }
        imageSrcIdentifier = new ImageSrcIdentifier();
        flashcardTopBar = findViewById(R.id.flashcardTopBar);
        child = realm.where(ChildSchema.class).equalTo("childId", childId).findFirst();
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
        resetInputTint = ContextCompat.getColorStateList(this, R.color.grey);
    }

    private void setTopBar(){
        flashcardTopBar.setPoints(String.valueOf(child.getScore()));
        flashcardTopBar.setToTriangle();
    }
}