package com.GrowthPlus;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.GrowthPlus.customViews.TopBar;
import com.GrowthPlus.dataAccessLayer.Language.Translator;
import com.GrowthPlus.dataAccessLayer.Lesson.LessonSchema;
import com.GrowthPlus.dataAccessLayer.LessonContent.LessonContent;
import com.GrowthPlus.dataAccessLayer.child.ChildSchema;
import com.GrowthPlus.fragment.Counting;
import com.GrowthPlus.fragment.HorizontalEquation;
import com.GrowthPlus.fragment.WordGrid;
import com.GrowthPlus.fragment.WordImage;
import com.GrowthPlus.fragment.WordImageEquation;
import com.GrowthPlus.roadMapActivity.RoadMapOne;

import io.realm.Realm;
import io.realm.RealmList;

public class Lesson extends AppCompatActivity {
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
    private int lessonIndex;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson);
        init();

        introBackBtn.setOnClickListener(view -> {
            Intent lessonIntent = new Intent(Lesson.this, RoadMapOne.class);
            // TODO: Dynamically change return address based on child's progress
            lessonIntent.putExtra("childIdentify", childId);
            startActivity(lessonIntent);
            this.finish();
        });
        setTopBar();

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
            bundle.putInt("level", 1);

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
                    Intent flashcardIntent = new Intent(Lesson.this, Flashcard.class);
                    flashcardIntent.putExtra("dataBaseLessonId", dataBaseLessonId);
                    flashcardIntent.putExtra("childId", childId);
                    flashcardIntent.putExtra("lessonImage", image);
                    flashcardIntent.putExtra("lessonIndex", lessonIndex);
                    startActivity(flashcardIntent);
                    finish();
                }
                else{
                    String category = contents.get(counter).getCategory();

                    // These variables as needed in each switch statement
                    // Same vars as are found in the roadmap.json for lessons
                    String lessonImg, word, firstNumber, firstOperator, secondNumber,
                            secondOperator, thirdNumber, imgOne, imgTwo, imgThree,
                            imgFour, imgFive, name;

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

                        case "horizontalEquation": {
                            firstNumber = contents.get(counter).getFirstNumber();
                            firstOperator = contents.get(counter).getFirstOperator();
                            secondNumber = contents.get(counter).getSecondNumber();
                            secondOperator = contents.get(counter).getSecondOperator();
                            thirdNumber = contents.get(counter).getThirdNumber();
                            String equation = firstNumber + " " + firstOperator + " " + secondNumber + " " + secondOperator + " " + thirdNumber;

                            lessonImg = lesson.getImage();

                            int firstNumInt = Integer.valueOf(firstNumber);
                            int secondNumInt = Integer.valueOf(secondNumber);
                            int thirdNumInt = Integer.valueOf(thirdNumber);

                            if (savedInstanceState == null) {
                                Bundle bundle = new Bundle();
                                bundle.putString("text1", equation);
                                bundle.putString("lessonImg", lessonImg);
                                bundle.putInt("num1", firstNumInt);
                                bundle.putInt("num2", secondNumInt);
                                bundle.putInt("num3", thirdNumInt);

                                FragmentTransaction transaction = fragmentManager.beginTransaction();
                                transaction.setReorderingAllowed(true);
                                transaction.replace(R.id.frame_layout_lesson, HorizontalEquation.class, bundle);
                                transaction.commit();
                            }
                            break;
                        }

                        case "wordImage" : {
                            name = contents.get(counter).getWord();
                            word = contents.get(counter).getWord();
                            if(!trans.getString(word).equals("empty")){
                                word = trans.getString(word);
                            }
                            imgOne = contents.get(counter).getImgOne();
                            // TODO: look into how we're storing images for the wordImage lessons

                            if (savedInstanceState == null) {
                                Bundle bundle = new Bundle();
                                bundle.putString("name", name);
                                bundle.putString("locationIntroText", word);
                                bundle.putString("locationIntroImage", imgOne);

                                FragmentTransaction transaction = fragmentManager.beginTransaction();
                                transaction.setReorderingAllowed(true);
                                transaction.replace(R.id.frame_layout_lesson, WordImage.class, bundle);
                                transaction.commit();
                            }
                            break;
                        }

                        // This category can be found on the roadmap.json file
                        case "wordImageEquation": {
                            // Reference the fragment_word_image_equation.xml file to see which components we need

                            // Methods attached to contents.get(counter) can all be found in the DAL of the application
                            // Reference the roadmap.json to see which methods are the correct ones to call

                            // Access top text and bottom text
                            firstNumber = contents.get(counter).getFirstNumber();
                            secondNumber = contents.get(counter).getSecondNumber();
                            // Translate to respective words
                            String firstWord = "10 " + trans.getString(firstNumber);
                            String secondWord = trans.getString(secondNumber);

                            // Multiple image is many images of one tile with small numbers
                            // Single image is one image with one large number
                            imgOne = contents.get(counter).getImgOne();
                            imgTwo = contents.get(counter).getImgTwo();
                            imgThree = contents.get(counter).getImgThree();

                            // Access the operator as shown on the xml file and Lesson image
                            firstOperator = contents.get(counter).getFirstOperator();
                            lessonImg = lesson.getImage();

                            if (savedInstanceState == null) {
                                Bundle bundle = new Bundle();
                                // Add the proper components to the bundle using uniquely set IDs and the content that we accessed
                                bundle.putString("topText", firstWord);
                                bundle.putString("bottomText", secondWord);
                                bundle.putString("multipliedImage", imgOne);
                                bundle.putString("singleImage", imgTwo);
                                bundle.putString("operatorSymbol", firstOperator);
                                bundle.putString("multipleImage", imgThree);

                                // Make the fragment transaction and commit it
                                FragmentTransaction transaction = fragmentManager.beginTransaction();
                                transaction.setReorderingAllowed(true);
                                transaction.replace(R.id.frame_layout_lesson, WordImageEquation.class, bundle);
                                transaction.commit();
                            }
                            break;
                        }

                        case "wordGrid" :{
                            firstNumber = contents.get(counter).getFirstNumber();
                            firstOperator = contents.get(counter).getFirstOperator();
                            secondNumber = contents.get(counter).getSecondNumber();
                            secondOperator = contents.get(counter).getSecondOperator();
                            thirdNumber = contents.get(counter).getThirdNumber();
                            String equation = firstNumber + " " + firstOperator + " " + secondNumber + " " + secondOperator + " " + thirdNumber;
                            imgOne = contents.get(counter).getImgOne();

                            int numImg = 0;
                            if(lesson.getCategory().equals("division")){
                                numImg = Integer.valueOf(thirdNumber);
                            }
                            else if(lesson.getCategory().equals("multiplication")){
                                numImg = Integer.valueOf(secondNumber);
                            }

                            if (savedInstanceState == null) {
                                Bundle bundle = new Bundle();
                                bundle.putString("wordMD", equation);
                                bundle.putString("imageMD", imgOne);
                                bundle.putInt("numMD", numImg);
                                bundle.putInt("level", 1);

                                FragmentTransaction transaction = fragmentManager.beginTransaction();
                                transaction.setReorderingAllowed(true);
                                transaction.replace(R.id.frame_layout_lesson, WordGrid.class, bundle);
                                transaction.commit();
                            }
                        }
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
            lessonIndex = extras.getInt("lessonIndex");
        }
        realm = Realm.getDefaultInstance();
        child = realm.where(ChildSchema.class).equalTo("childId", childId).findFirst();
        lesson = realm.where(LessonSchema.class).equalTo("lessonId", dataBaseLessonId).findFirst();
        contents = lesson.getContents();
        topBar = findViewById(R.id.lessonTopBar);
        introBackBtn = topBar.findViewById(R.id.goBackBtn);
        nextContent = findViewById(R.id.next_button_lesson);
        lessonName = lesson.getLessonName();
        image = lesson.getImage();
    }

    private void setTopBar(){
        topBar.setPoints(String.valueOf(child.getScore()));
        topBar.setToTriangle();
        topBar.setShapeColor(Color.rgb(252, 209, 70));
    }
}
