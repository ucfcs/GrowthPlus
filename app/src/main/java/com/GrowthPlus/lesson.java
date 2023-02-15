package com.GrowthPlus;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.GrowthPlus.customViews.TopBar;
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

        // Fragment for lesson intro
        if (savedInstanceState == null) {
            Bundle bundle = new Bundle();
            bundle.putString("locationIntroText", lessonName);
            bundle.putString("locationIntroImage", image);

            FragmentTransaction transaction = fragmentManager.beginTransaction();
            transaction.setReorderingAllowed(true);
            transaction.replace(R.id.frame_layout_lesson, WordImage.class, bundle);
            transaction.commit();
        }

        contentLength = contents.size();
        //use the counter to access the contents of the appropriate lesson
        counter = 0;
        nextContent.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //here we've reached the end of the line and need to start looking at flashcards
                if(counter >= contentLength){
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
                        case "counting":
                            word = contents.get(counter).getWord();
                            firstNumber = contents.get(counter).getFirstNumber();
                            lessonImg = lesson.getImage();

                            if(savedInstanceState == null) {
                                Bundle bundle = new Bundle();
                                bundle.putString("lessonWord", word);
                                bundle.putString("lessonNumber", firstNumber);
                                bundle.putString("lessonImage", lessonImg);

                                FragmentTransaction transaction = fragmentManager.beginTransaction();
                                transaction.setReorderingAllowed(true);
                                transaction.replace(R.id.frame_layout_lesson, Counting.class, bundle);
                                transaction.commit();
                            }
                        //this category can be found on the roadmap.json file
                        case "wordImageEquation":
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

                            if(savedInstanceState == null){
                                Bundle bundle = new Bundle();
                                //here we're adding the proper components to the bundle using
                                //their uniquely set IDs and the content that we grabbed above
                                bundle.putString("topText", firstNumber);
                                bundle.putString("bottomText", secondNumber);
                                bundle.putString("multipliedImage", imgOne);
                                bundle.putString("singleImage", imgTwo);
                                bundle.putString("operatorSymbol", firstOperator);

                                //make the fragment transaction and commit it
                                FragmentTransaction transaction = fragmentManager.beginTransaction();
                                transaction.setReorderingAllowed(true);
                                transaction.replace(R.id.frame_layout_lesson, WordImageEquation.class, bundle);
                                transaction.commit();
                            }

                        case "horizontalEquation":
                            String firstNumStr = contents.get(counter).getFirstNumber();
                            String firstOp = contents.get(counter).getFirstOperator();
                            String secondNumStr = contents.get(counter).getSecondNumber();
                            String secondOp = contents.get(counter).getSecondOperator();
                            String thirdNumStr = contents.get(counter).getThirdNumber();
                            String equation = firstNumStr+" "+firstOp+" "+secondNumStr+" "+secondOp+" "+thirdNumStr;

                            lessonImg = lesson.getImage();

                            int firstNumInt = Integer.valueOf(firstNumStr);
                            int secondNumInt = Integer.valueOf(firstNumStr);
                            int thirdNumInt = Integer.valueOf(firstNumStr);

                            if(savedInstanceState == null) {
                                Bundle bundle = new Bundle();
                                bundle.putString("text", equation);
                                bundle.putString("lessonImg", lessonImg);
                                bundle.putInt("num1", firstNumInt);
                                bundle.putInt("num2", secondNumInt);
                                bundle.putInt("num3", thirdNumInt);

                                FragmentTransaction transaction = fragmentManager.beginTransaction();
                                transaction.setReorderingAllowed(true);
                                transaction.replace(R.id.frame_layout_lesson, HorizontalEquation.class, bundle);
                                transaction.commit();
                            }

                        case "wordImage" :
                            word = contents.get(counter).getWord();
                            imgOne = contents.get(counter).getImgOne();

                            if(savedInstanceState == null) {
                                Bundle bundle = new Bundle();
                                bundle.putString("locationIntroText", word);
                                bundle.putString("locationIntroImage", imgOne);

                                FragmentTransaction transaction = fragmentManager.beginTransaction();
                                transaction.setReorderingAllowed(true);
                                transaction.replace(R.id.frame_layout_lesson, WordImage.class, bundle);
                                transaction.commit();
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