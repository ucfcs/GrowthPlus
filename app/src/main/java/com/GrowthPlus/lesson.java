package com.GrowthPlus;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.GrowthPlus.customViews.TopBar;
import com.GrowthPlus.dataAccessLayer.Language.LanguageSchema;
import com.GrowthPlus.dataAccessLayer.Language.LanguageSchemaService;
import com.GrowthPlus.dataAccessLayer.Language.Translator;
import com.GrowthPlus.dataAccessLayer.Lesson.LessonSchema;
import com.GrowthPlus.dataAccessLayer.LessonContent.LessonContent;
import com.GrowthPlus.dataAccessLayer.child.ChildSchema;
import com.GrowthPlus.fragment.Counting;
import com.GrowthPlus.fragment.HorizontalEquation;
import com.GrowthPlus.fragment.WordImage;
import com.GrowthPlus.fragment.WordImageEquation;
import com.GrowthPlus.roadMapActivity.RoadMapOne;

import io.realm.Realm;
import io.realm.RealmList;

public class lesson extends AppCompatActivity {

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
    int counter;

    private String lessonName;
    private String image;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson);
        init();

        introBackBtn.setOnClickListener(view -> {
            Intent lessonIntent = new Intent(lesson.this, RoadMapOne.class);
            // TODO: Dynamically change return address based on child's progress
            lessonIntent.putExtra("childIdentify", childId);
            startActivity(lessonIntent);
        });
        setTopBar();

        // Create one fragment that we will dynamically change
        FragmentManager fragmentManager = getSupportFragmentManager();

        // Create instance of shared preferences and save current language id
        SharedPreferences langPrefs = getSharedPreferences("LangPreferences", MODE_PRIVATE);
        String langId = langPrefs.getString("languageId", "frenchZero");
        // Create language translator and set up the lesson string
        Translator trans = new Translator(langId);
        String lessonTranslated = trans.getString("lesson") + " "+ lessonName;

        // Fragment for lesson intro
        if (savedInstanceState == null) {
            Bundle bundle = new Bundle();
            bundle.putString("locationIntroText", lessonTranslated);
            bundle.putString("locationIntroImage", image);
            bundle.putInt("locationIntroNum", Integer.valueOf(lessonName));

            FragmentTransaction transaction = fragmentManager.beginTransaction();
            transaction.setReorderingAllowed(true);
            transaction.replace(R.id.frame_layout_lesson, WordImage.class, bundle);
            transaction.commit();
        }

        contentLength = contents.size();
        // Use the counter to access the contents of the appropriate lesson
        counter = 0;
        nextContent.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Reached the end of the contents and need to start looking at flashcards or the lesson is 10 (which is all flashcards)
                if(counter >= contentLength || counter == 8){
                    Intent lessonIntent = new Intent(lesson.this, flashcard.class);
                    startActivity(lessonIntent);
                }
                else{
                    String category = contents.get(counter).getCategory();
                    Log.i("category", category);

                    //use these variables as needed in each switch statement
                    //these are the same vars as are found in the roadmap.json for lessons
                    String lessonImg, word, firstNumber, firstOperator, secondNumber,
                            secondOperator, thirdNumber, imgOne, imgTwo, imgThree,
                            imgFour, imgFive;

                    switch (category){
                        case "counting": {
                            word = contents.get(counter).getWord();
                            firstNumber = contents.get(counter).getFirstNumber();
                            lessonImg = lesson.getImage();

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
                        // This category can be found on the roadmap.json file
                        case "wordImageEquation": {
                            //it's good to reference the fragment_word_image_equation.xml file to see which components we need
                            //to grab

                            //the methods attached to contents.get(counter) can all be found in the DAL of the application
                            //reference the roadmap.json to see which methods are the correct ones to call

                            //here we grab the top text and bottom text
                            firstNumber = contents.get(counter).getFirstNumber();
                            secondNumber = contents.get(counter).getSecondNumber();

                            //multiple image is many images of one tile with small numbers
                            //single image is one image with one large number
                            imgOne = contents.get(counter).getImgOne();
                            imgTwo = contents.get(counter).getImgTwo();


                            //here we are grabbing the operator as shown on the xml file
                            firstOperator = contents.get(counter).getFirstOperator();
                            lessonImg = lesson.getImage();

                            if (savedInstanceState == null) {
                                Bundle bundle = new Bundle();
                                //here we're adding the proper components to the bundle using
                                //their uniquely set IDs and the content that we grabbed above
                                bundle.putString("topText", firstNumber);
                                bundle.putString("bottomText", secondNumber);
                                bundle.putString("multipliedImage", imgOne);
                                bundle.putString("singleImage", imgTwo);
                                bundle.putString("operatorSymbol", firstOperator);
                                bundle.putString("lessonImg", lessonImg);

                                //make the fragment transaction and commit it
                                FragmentTransaction transaction = fragmentManager.beginTransaction();
                                transaction.setReorderingAllowed(true);
                                transaction.replace(R.id.frame_layout_lesson, WordImageEquation.class, bundle);
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
                            word = contents.get(counter).getWord();
                            //imgOne = contents.get(counter).getImgOne();
                            lessonImg = lesson.getImage();
                            //TODO: look into how we're storing images for the wordImage lessons

                            if (savedInstanceState == null) {
                                Bundle bundle = new Bundle();
                                bundle.putString("locationIntroText", word);
                                bundle.putString("locationIntroImage", lessonImg);

                                FragmentTransaction transaction = fragmentManager.beginTransaction();
                                transaction.setReorderingAllowed(true);
                                transaction.replace(R.id.frame_layout_lesson, WordImage.class, bundle);
                                transaction.commit();
                            }
                            break;
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
    }


    private void setTopBar(){
        topBar.setPoints(String.valueOf(child.getScore()));
    }

}