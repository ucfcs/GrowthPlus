package com.GrowthPlus;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;

import com.GrowthPlus.dataAccessLayer.Flashcard.FlashcardSchema;
import com.GrowthPlus.dataAccessLayer.Lesson.LessonSchema;
import com.GrowthPlus.dataAccessLayer.child.ChildSchema;
import com.GrowthPlus.fragment.CustomImage;
import com.GrowthPlus.fragment.WordImage;
import com.GrowthPlus.utilities.ColorIdentifier;
import com.GrowthPlus.utilities.ImageSrcIdentifier;

import io.realm.Realm;
import io.realm.RealmList;

public class Flashcard extends AppCompatActivity {

    private String dataBaseLessonId;
    private String childId;
    private ChildSchema child;
    private LessonSchema lesson;
    private Realm realm;
    private RealmList<FlashcardSchema> lessonFlashcards;
    private String image;
    private ImageSrcIdentifier imageSrcIdentifier;
    private ColorIdentifier colorIdentifier;
    private com.GrowthPlus.customViews.Flashcard flashcardContainer;
    private FlashcardSchema flashcard;
    private FragmentManager fragmentManager;
    private String childAnswer;
    private ColorStateList answerColor;
    private ColorStateList correctAnswerColor;
    private ColorStateList wrongAnswerColor;
    private int TEXT_INPUT_ONLY = 1;
    private int NUMBER_INPUT_ONLY = 2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flashcard);
        init();

        String category, image, firstNumber, firstOperator, secondNumber, secondOperator, answer;
        int size = lessonFlashcards.size();
        int counter = 0;
        flashcard = lessonFlashcards.get(counter);

        assert flashcard != null;
        category = flashcard.getCategory();

        /*
        * Switch statement for first flashcard, we don't have an intro so we start at index 0
        *
        * */
        switch (category){
            case "customImage":{
                image = flashcard.getImage();
                firstNumber = flashcard.getFirstNumber();
                answer = flashcard.getAnswer();
                flashcardContainer.setRawInputType(NUMBER_INPUT_ONLY);
                if (savedInstanceState == null) {
                    Bundle bundle = new Bundle();
                    bundle.putString("image", image);
                    bundle.putString("firstNumber", firstNumber);
                    bundle.putString("answer", answer);
                    FragmentTransaction transaction = fragmentManager.beginTransaction();
                    transaction.setReorderingAllowed(true);
                    transaction.replace(flashcardContainer.findViewById(R.id.frame_layout_flashcard).getId(), CustomImage.class, bundle);
                    transaction.commit();
                }

                /*
                * Handles the answer verification logic, if child taps the flashcard
                * the animate() method fires up and rotates the flashcard 360 degrees.
                * then the onAnimationEnd sets the corresponding color.
                * */
                flashcardContainer.setOnClickListener(view -> {
                    childAnswer = flashcardContainer.getAnswer();
                    if(childAnswer.equals(answer)){
                       answerColor = correctAnswerColor;
                    }
                    else {
                        answerColor = wrongAnswerColor;
                    }
                    flashcardContainer.animate().setDuration(500).rotationYBy(360f).setListener(new AnimatorListenerAdapter() {
                        @Override
                        public void onAnimationEnd(Animator animation) {
                            super.onAnimationEnd(animation);
                            flashcardContainer.setFlashcardColor(answerColor);
                        }
                    });
                });

            }
        }

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
        colorIdentifier = new ColorIdentifier();
        child = realm.where(ChildSchema.class).equalTo("childId", childId).findFirst();
        lesson = realm.where(LessonSchema.class).equalTo("lessonId", dataBaseLessonId).findFirst();
        assert lesson != null;
        lessonFlashcards = lesson.getFlashcards();
        flashcardContainer = findViewById(R.id.flashcardContainer);
        fragmentManager = getSupportFragmentManager();
        correctAnswerColor = ContextCompat.getColorStateList(this, R.color.light_green);
        wrongAnswerColor = ContextCompat.getColorStateList(this, R.color.red);

    }
}