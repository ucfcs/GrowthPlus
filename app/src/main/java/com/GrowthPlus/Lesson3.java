package com.GrowthPlus;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.GrowthPlus.customViews.TopBar;
import com.GrowthPlus.dataAccessLayer.Language.Translator;
import com.GrowthPlus.dataAccessLayer.Lesson.LessonSchema;
import com.GrowthPlus.dataAccessLayer.LessonContent.LessonContent;
import com.GrowthPlus.dataAccessLayer.child.ChildSchema;
import com.GrowthPlus.fragment.Conversion;
import com.GrowthPlus.fragment.ConversionTable;
import com.GrowthPlus.fragment.Counting;
import com.GrowthPlus.fragment.HorizontalEquation;
import com.GrowthPlus.fragment.ImageWord;
import com.GrowthPlus.fragment.WordGrid;
import com.GrowthPlus.fragment.WordImage;
import com.GrowthPlus.fragment.WordImageEquation;
import com.GrowthPlus.roadMapActivity.RoadMapOne;
import com.GrowthPlus.roadMapActivity.RoadMapThree;
import com.GrowthPlus.roadMapActivity.RoadMapTwo;

import io.realm.Realm;
import io.realm.RealmList;

public class Lesson3 extends AppCompatActivity {
    private String dataBaseLessonId;
    private String childId;
    private ChildSchema child;
    private LessonSchema lesson;
    private Realm realm;
    private RealmList<LessonContent> contents;
    private TopBar topBar;
    private Button introBackBtn;
    private int contentLength;
    private Button nextContent;
    private int counter;
    private String lessonName;
    private String image;
    ConstraintLayout lessonBackground;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson);
        init();

        introBackBtn.setOnClickListener(view -> {
            Intent lessonIntent = new Intent(Lesson3.this, RoadMapThree.class);
            // TODO: Dynamically change return address based on child's progress
            lessonIntent.putExtra("childIdentify", childId);
            startActivity(lessonIntent);
            this.finish();
        });
        setTopBar();
        setLevelColor();

        // Create one fragment that we will dynamically change
        FragmentManager fragmentManager = getSupportFragmentManager();

        // Create instance of shared preferences and save current language id
        SharedPreferences langPrefs = getSharedPreferences("LangPreferences", MODE_PRIVATE);
        String langId = langPrefs.getString("languageId", "frenchZero");
        // Create language translator and set up the Lesson string
        Translator trans = new Translator(langId);
        String lessonTranslated = trans.getString("Lesson") + " "+ lessonName;
        int numOfImages = Integer.valueOf(lessonName);

        // Fragment for Lesson intro
        if (savedInstanceState == null) {
            Bundle bundle = new Bundle();
            bundle.putString("wordMD", lessonTranslated);
            bundle.putString("imageMD", image);
            bundle.putInt("numMD", numOfImages);

            FragmentTransaction transaction = fragmentManager.beginTransaction();
            transaction.setReorderingAllowed(true);
            transaction.replace(R.id.frame_layout_lesson, WordGrid.class, bundle);
            transaction.commit();
        }

        contentLength = contents.size();
        // Use the counter to access the contents of the appropriate Lesson
        counter = 0;

        nextContent.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Reached the end of the contents and need to start looking at flashcards or the Lesson is 10 (which is all flashcards)
                if(counter >= contentLength){
                    Intent flashcardIntent = new Intent(Lesson3.this, Flashcard.class);
                    flashcardIntent.putExtra("dataBaseLessonId", dataBaseLessonId);
                    flashcardIntent.putExtra("childId", childId);
                    flashcardIntent.putExtra("lessonImage", image);
                    startActivity(flashcardIntent);
                    finish();
                }
                else{
                    String category = contents.get(counter).getCategory();

                    // These variables as needed in each switch statement
                    // Same vars as are found in the roadmap.json for lessons
                    String lessonImg, word, firstNumber, firstOperator, secondNumber,
                            secondOperator, thirdNumber, imgOne, imgTwo, imgThree,
                            imgFour, imgFive;

                    switch (category){
                        case "counting": {
                            word = contents.get(counter).getWord();
                            firstNumber = contents.get(counter).getFirstNumber();
                            lessonImg = lesson.getImage();
                            if(!trans.getString(word).equals("empty")){
                                word = trans.getString(word);
                            }

                            if (savedInstanceState == null) {
                                Bundle bundle = new Bundle();
                                bundle.putString("lessonWord", word);
                                bundle.putString("lessonNumber", firstNumber);
                                bundle.putString("lessonImage", lessonImg);

                                FragmentTransaction transaction = fragmentManager.beginTransaction();
                                transaction.setReorderingAllowed(true);
                                transaction.replace(R.id.frame_layout_lesson, Counting.class, bundle);
                                transaction.commit();
                            }
                            break;
                        }

                        case "family": {

                        }

                        case "imageWord" : {
                            imgOne = contents.get(counter).getImgOne();
                            word = contents.get(counter).getWord();
                            if (!trans.getString(word).equals("empty")) {
                                word = trans.getString(word);
                            }

                            if (savedInstanceState == null) {
                                Bundle bundle = new Bundle();
                                bundle.putString("imageWordText", word);
                                bundle.putString("imageWordImage", imgOne);

                                FragmentTransaction transaction = fragmentManager.beginTransaction();
                                transaction.setReorderingAllowed(true);
                                transaction.replace(R.id.frame_layout_lesson, ImageWord.class, bundle);
                                transaction.commit();
                            }
                            break;
                        }

                        case "conversion" :{
                            firstNumber = contents.get(counter).getFirstNumber();
                            firstOperator = contents.get(counter).getFirstOperator();
                            secondNumber = contents.get(counter).getSecondNumber();

                            lessonImg = lesson.getImage();

                            if (savedInstanceState == null) {
                                Bundle bundle = new Bundle();
                                bundle.putString("conversionText1", firstNumber);
                                bundle.putString("operator", firstOperator);
                                bundle.putString("conversionText2", secondNumber);

                                FragmentTransaction transaction = fragmentManager.beginTransaction();
                                transaction.setReorderingAllowed(true);
                                transaction.replace(R.id.frame_layout_lesson, Conversion.class, bundle);
                                transaction.commit();
                            }
                            break;
                        }

                        case "conversionTable": {
                            firstNumber = contents.get(counter).getFirstNumber();
                            firstOperator = contents.get(counter).getFirstOperator();
                            secondNumber = contents.get(counter).getSecondNumber();
                            secondOperator = contents.get(counter).getSecondOperator();

                            lessonImg = lesson.getImage();

                            if (savedInstanceState == null) {
                                Bundle bundle = new Bundle();
                                bundle.putString("conversionTableText1", firstNumber);
                                bundle.putString("conversionTableText2", firstOperator);
                                bundle.putString("conversionTableText3", secondNumber);
                                bundle.putString("conversionTableText4", secondOperator);

                                FragmentTransaction transaction = fragmentManager.beginTransaction();
                                transaction.setReorderingAllowed(true);
                                transaction.replace(R.id.frame_layout_lesson, ConversionTable.class, bundle);
                                transaction.commit();
                            }
                            break;
                        }

//                        case "perimeterArea": {
//                            break;
//                        }
//
//                        case "shape":{
//                            break;
//                        }
                        default:
                    }
                    counter++;
                }
            }
        });
    }

    private void init(){
        Bundle extras = getIntent().getExtras();
        if(extras != null){
            dataBaseLessonId = extras.getString("dataBaseLessonId");
            childId = extras.getString("childId");
        }
        realm = Realm.getDefaultInstance();
        child = realm.where(ChildSchema.class).equalTo("childId", childId).findFirst();
        lesson = realm.where(LessonSchema.class).equalTo("lessonId", dataBaseLessonId).findFirst();
        contents = lesson.getContents();
        topBar = findViewById(R.id.topBar);
        introBackBtn = topBar.findViewById(R.id.goBackBtn);
        nextContent = findViewById(R.id.next_button_lesson);
        lessonName = lesson.getLessonName();
        image = lesson.getImage();
        lessonBackground = findViewById(R.id.lesson2);
    }

    private void setTopBar(){
        topBar.setPoints(String.valueOf(child.getScore()));
        topBar.setToTriangle();
    }
    public void setLevelColor(){
        lessonBackground.setBackgroundColor(Color.rgb(198, 192, 18));

    }
}
