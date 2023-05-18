package com.GrowthPlus;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.GrowthPlus.customViews.TopBar;
import com.GrowthPlus.dataAccessLayer.Language.Translator;
import com.GrowthPlus.dataAccessLayer.Lesson.LessonSchema;
import com.GrowthPlus.dataAccessLayer.LessonContent.LessonContent;
import com.GrowthPlus.dataAccessLayer.child.ChildSchema;
import com.GrowthPlus.fragment.Conversion;
import com.GrowthPlus.fragment.ConversionTable;
import com.GrowthPlus.fragment.ConversionTableTwo;
import com.GrowthPlus.fragment.Counting;
import com.GrowthPlus.fragment.Division;
import com.GrowthPlus.fragment.Family;
import com.GrowthPlus.fragment.ImageWord;
import com.GrowthPlus.fragment.LinesAngles;
import com.GrowthPlus.fragment.PerimeterArea;
import com.GrowthPlus.fragment.Shape;
import com.GrowthPlus.fragment.ShapesAngles;
import com.GrowthPlus.fragment.VerticalEquation;
import com.GrowthPlus.fragment.WordGrid;
import com.GrowthPlus.roadMapActivity.RoadMapFour;
import io.realm.Realm;
import io.realm.RealmList;

public class Lesson4 extends AppCompatActivity {

    private String dataBaseLessonId;
    private String childId;
    private ChildSchema child;
    private LessonSchema lesson;
    private Realm realm;
    private RealmList<LessonContent> contents;
    private TopBar topBar;
    private Button introBackBtn;
    private Button backButton;
    private int contentLength;
    private Button nextContent;
    private int counter;
    private int backCounter;
    private String lessonName;
    private String image;
    private int lessonIndex;
    ConstraintLayout lessonBackground;
    ConstraintLayout topBarBackground;
    public AlphaAnimation buttonClick = new AlphaAnimation(1F, 0.8F);

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson);
        init();

        introBackBtn.setOnClickListener(view -> {
            view.startAnimation(buttonClick);
            Intent lessonIntent = new Intent(Lesson4.this, RoadMapFour.class);
            lessonIntent.putExtra("childIdentify", childId);
            startActivity(lessonIntent);
            this.finish();
        });
        setTopBar();
        setLevelColor();
        setButtonColor();

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
            bundle.putInt("level", 4);

            FragmentTransaction transaction = fragmentManager.beginTransaction();
            transaction.setReorderingAllowed(true);
            transaction.replace(R.id.frame_layout_lesson, WordGrid.class, bundle);
            transaction.commit();
        }

        contentLength = contents.size();
        // Use the counter to access the contents of the appropriate Lesson
        counter = 0;
        backCounter = counter - 2;
        if(backCounter < 0){
            backButton.getBackground().setAlpha(64);
        }

        nextContent.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                v.startAnimation(buttonClick);
                // Reached the end of the contents and need to start looking at flashcards or the Lesson is 10 (which is all flashcards)
                if(counter >= contentLength){
                    Intent flashcardIntent = new Intent(Lesson4.this, Flashcard4.class);
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
                            secondOperator, thirdNumber, imgOne, imgTwo, imgThree;

                    switch (category){
                        case "counting": {
                            word = contents.get(counter).getWord();
                            firstNumber = contents.get(counter).getFirstNumber();
                            imgOne = contents.get(counter).getImgOne();
                            if(!trans.getString(word).equals("empty")){
                                word = trans.getString(word);
                            }

                            if (savedInstanceState == null) {
                                Bundle bundle = new Bundle();
                                bundle.putString("lessonWord", word);
                                bundle.putString("lessonNumber", firstNumber);
                                bundle.putString("lessonImage", imgOne);
                                bundle.putString("langId", langId);

                                FragmentTransaction transaction = fragmentManager.beginTransaction();
                                transaction.setReorderingAllowed(true);
                                transaction.replace(R.id.frame_layout_lesson, Counting.class, bundle);
                                transaction.commit();
                            }
                            break;
                        }

                        case "division":{
                            word = contents.get(counter).getWord();
                            firstNumber = contents.get(counter).getFirstNumber();
                            firstOperator = contents.get(counter).getFirstOperator();
                            secondNumber = contents.get(counter).getSecondNumber();
                            secondOperator = contents.get(counter).getSecondOperator();
                            thirdNumber = contents.get(counter).getThirdNumber();

                            if (savedInstanceState == null) {
                                Bundle bundle = new Bundle();
                                bundle.putString("divisor", secondNumber);
                                bundle.putString("dividend", firstNumber);
                                bundle.putString("quotient", thirdNumber);
                                bundle.putString("subtractedNum", secondOperator);
                                bundle.putString("subtractedAns", firstOperator);
                                bundle.putString("type", word);

                                FragmentTransaction transaction = fragmentManager.beginTransaction();
                                transaction.setReorderingAllowed(true);
                                transaction.replace(R.id.frame_layout_lesson, Division.class, bundle);
                                transaction.commit();
                            }

                            break;
                        }

                        case "shape":{
                            word = contents.get(counter).getWord();
                            imgOne = contents.get(counter).getImgOne();
                            imgTwo = contents.get(counter).getImgTwo();
                            imgThree = contents.get(counter).getImgThree();

                            if (!trans.getString(word).equals("empty")) {
                                word = trans.getString(word);
                            }

                            if (savedInstanceState == null) {
                                Bundle bundle = new Bundle();
                                bundle.putString("shapeText", word);
                                bundle.putString("shapeImage1", imgOne);
                                bundle.putString("shapeImage2", imgTwo);
                                bundle.putString("shapeImage3", imgThree);

                                FragmentTransaction transaction = fragmentManager.beginTransaction();
                                transaction.setReorderingAllowed(true);
                                transaction.replace(R.id.frame_layout_lesson, Shape.class, bundle);
                                transaction.commit();
                            }
                            break;
                        }

                        case "linesAngles":{
                            word = contents.get(counter).getWord();
                            imgOne = contents.get(counter).getImgOne();
                            imgTwo = contents.get(counter).getImgTwo();

                            if (!trans.getString(word).equals("empty")) {
                                word = trans.getString(word);
                            }

                            if (savedInstanceState == null) {
                                Bundle bundle = new Bundle();
                                bundle.putString("LAText", word);
                                bundle.putString("LAImage1", imgOne);
                                bundle.putString("LAImage2", imgTwo);

                                FragmentTransaction transaction = fragmentManager.beginTransaction();
                                transaction.setReorderingAllowed(true);
                                transaction.replace(R.id.frame_layout_lesson, LinesAngles.class, bundle);
                                transaction.commit();
                            }
                            break;
                        }

                      case "perimeterArea": {
                          String identify = contents.get(counter).getWord();
                          word = contents.get(counter).getWord();
                          firstNumber = contents.get(counter).getFirstNumber();
                          firstOperator = contents.get(counter).getFirstOperator();
                          secondNumber = contents.get(counter).getSecondNumber();
                          secondOperator = contents.get(counter).getSecondOperator();
                          thirdNumber = contents.get(counter).getThirdNumber();
                          imgOne = contents.get(counter).getImgOne();

                          if (!trans.getString(word).equals("empty")) {
                              word = trans.getString(word);
                          }
                          if (!trans.getString(secondNumber).equals("empty")) {
                              secondNumber = trans.getString(secondNumber);
                          }
                          if (!trans.getString(thirdNumber).equals("empty")) {
                              thirdNumber = trans.getString(thirdNumber);
                          }

                          if (savedInstanceState == null) {
                              Bundle bundle = new Bundle();
                              bundle.putString("PAIdentify", identify);
                              bundle.putString("PAWord", word);
                              bundle.putString("PAFirstNumber", firstNumber);
                              bundle.putString("PAFirstOperator", firstOperator);
                              bundle.putString("PASecondNumber", secondNumber);
                              bundle.putString("PASecondOperator", secondOperator);
                              bundle.putString("PAThirdNumber", thirdNumber);
                              bundle.putString("PAImage", imgOne);

                              FragmentTransaction transaction = fragmentManager.beginTransaction();
                              transaction.setReorderingAllowed(true);
                              transaction.replace(R.id.frame_layout_lesson, PerimeterArea.class, bundle);
                              transaction.commit();
                          }
                          break;
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

                        case "shapesAngles":{
                            imgOne = contents.get(counter).getImgOne();
                            imgTwo = contents.get(counter).getImgTwo();
                            word = contents.get(counter).getWord();
                            firstNumber = contents.get(counter).getFirstNumber();

                            if (!trans.getString(word).equals("empty")) {
                                word = trans.getString(word);
                            }

                            if (savedInstanceState == null) {
                                Bundle bundle = new Bundle();
                                bundle.putString("firstImage", imgOne);
                                bundle.putString("secondImage", imgTwo);
                                bundle.putString("word", word);
                                bundle.putString("degree", firstNumber);

                                FragmentTransaction transaction = fragmentManager.beginTransaction();
                                transaction.setReorderingAllowed(true);
                                transaction.replace(R.id.frame_layout_lesson, ShapesAngles.class, bundle);
                                transaction.commit();
                            }

                            break;
                        }

                        case "family": {
                            word = contents.get(counter).getWord();
                            firstNumber = contents.get(counter).getFirstNumber();
                            firstOperator = contents.get(counter).getFirstOperator();
                            secondNumber = contents.get(counter).getSecondNumber();

                            if (!trans.getString(word).equals("empty")) {
                                word = trans.getString(word);
                            }

                            if (savedInstanceState == null) {
                                Bundle bundle = new Bundle();
                                bundle.putString("familyWord", word);
                                bundle.putString("familyFirstNumber", firstNumber);
                                bundle.putString("familyFirstOperator", firstOperator);
                                bundle.putString("familySecondNumber", secondNumber);

                                FragmentTransaction transaction = fragmentManager.beginTransaction();
                                transaction.setReorderingAllowed(true);
                                transaction.replace(R.id.frame_layout_lesson, Family.class, bundle);
                                transaction.commit();
                            }
                            break;
                        }

                        case "verticalEquation":{
                            word = contents.get(counter).getWord();
                            firstNumber = contents.get(counter).getFirstNumber();
                            firstOperator = contents.get(counter).getFirstOperator();
                            secondNumber = contents.get(counter).getSecondNumber();
                            secondOperator = contents.get(counter).getSecondOperator();
                            thirdNumber = contents.get(counter).getThirdNumber();

                            if (savedInstanceState == null) {
                                Bundle bundle = new Bundle();
                                bundle.putString("wordEqu", word);
                                bundle.putString("firstNum", firstNumber);
                                bundle.putString("secondNum", secondNumber);
                                bundle.putString("carry", secondOperator);
                                bundle.putString("answer", thirdNumber);
                                bundle.putString("opt", firstOperator);

                                FragmentTransaction transaction = fragmentManager.beginTransaction();
                                transaction.setReorderingAllowed(true);
                                transaction.replace(R.id.frame_layout_lesson, VerticalEquation.class, bundle);
                                transaction.commit();
                            }
                            break;
                        }

                        default:
                    }
                    counter++;
                    backCounter = counter -2;
                    if(backCounter < 0){
                        backButton.getBackground().setAlpha(64);
                    }
                    else{
                        backButton.getBackground().setAlpha(255);
                    }
                }
            }
        });

        // Back button decrements the counter variable and display previous lesson content
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(backCounter >= 0){
                    backButton.getBackground().setAlpha(255);
                    view.startAnimation(buttonClick);
                    String category = contents.get(backCounter).getCategory();

                    // These variables as needed in each switch statement
                    // Same vars as are found in the roadmap.json for lessons
                    String lessonImg, word, firstNumber, firstOperator, secondNumber,
                            secondOperator, thirdNumber, imgOne, imgTwo, imgThree, name;

                    switch (category){
                        case "counting": {
                            word = contents.get(backCounter).getWord();
                            firstNumber = contents.get(backCounter).getFirstNumber();
                            imgOne = contents.get(backCounter).getImgOne();
                            if(!trans.getString(word).equals("empty")){
                                word = trans.getString(word);
                            }

                            if (savedInstanceState == null) {
                                Bundle bundle = new Bundle();
                                bundle.putString("lessonWord", word);
                                bundle.putString("lessonNumber", firstNumber);
                                bundle.putString("lessonImage", imgOne);
                                bundle.putString("langId", langId);

                                FragmentTransaction transaction = fragmentManager.beginTransaction();
                                transaction.setReorderingAllowed(true);
                                transaction.replace(R.id.frame_layout_lesson, Counting.class, bundle);
                                transaction.commit();
                            }
                            break;
                        }

                        case "division":{
                            word = contents.get(backCounter).getWord();
                            firstNumber = contents.get(backCounter).getFirstNumber();
                            firstOperator = contents.get(backCounter).getFirstOperator();
                            secondNumber = contents.get(backCounter).getSecondNumber();
                            secondOperator = contents.get(backCounter).getSecondOperator();
                            thirdNumber = contents.get(backCounter).getThirdNumber();

                            if (savedInstanceState == null) {
                                Bundle bundle = new Bundle();
                                bundle.putString("divisor", secondNumber);
                                bundle.putString("dividend", firstNumber);
                                bundle.putString("quotient", thirdNumber);
                                bundle.putString("subtractedNum", secondOperator);
                                bundle.putString("subtractedAns", firstOperator);
                                bundle.putString("type", word);

                                FragmentTransaction transaction = fragmentManager.beginTransaction();
                                transaction.setReorderingAllowed(true);
                                transaction.replace(R.id.frame_layout_lesson, Division.class, bundle);
                                transaction.commit();
                            }

                            break;
                        }

                        case "shape":{
                            word = contents.get(backCounter).getWord();
                            imgOne = contents.get(backCounter).getImgOne();
                            imgTwo = contents.get(backCounter).getImgTwo();
                            imgThree = contents.get(backCounter).getImgThree();

                            if (!trans.getString(word).equals("empty")) {
                                word = trans.getString(word);
                            }

                            if (savedInstanceState == null) {
                                Bundle bundle = new Bundle();
                                bundle.putString("shapeText", word);
                                bundle.putString("shapeImage1", imgOne);
                                bundle.putString("shapeImage2", imgTwo);
                                bundle.putString("shapeImage3", imgThree);

                                FragmentTransaction transaction = fragmentManager.beginTransaction();
                                transaction.setReorderingAllowed(true);
                                transaction.replace(R.id.frame_layout_lesson, Shape.class, bundle);
                                transaction.commit();
                            }
                            break;
                        }

                        case "linesAngles":{
                            word = contents.get(backCounter).getWord();
                            imgOne = contents.get(backCounter).getImgOne();
                            imgTwo = contents.get(backCounter).getImgTwo();

                            if (!trans.getString(word).equals("empty")) {
                                word = trans.getString(word);
                            }

                            if (savedInstanceState == null) {
                                Bundle bundle = new Bundle();
                                bundle.putString("LAText", word);
                                bundle.putString("LAImage1", imgOne);
                                bundle.putString("LAImage2", imgTwo);

                                FragmentTransaction transaction = fragmentManager.beginTransaction();
                                transaction.setReorderingAllowed(true);
                                transaction.replace(R.id.frame_layout_lesson, LinesAngles.class, bundle);
                                transaction.commit();
                            }
                            break;
                        }

                        case "perimeterArea": {
                            String identify = contents.get(backCounter).getWord();
                            word = contents.get(backCounter).getWord();
                            firstNumber = contents.get(backCounter).getFirstNumber();
                            firstOperator = contents.get(backCounter).getFirstOperator();
                            secondNumber = contents.get(backCounter).getSecondNumber();
                            secondOperator = contents.get(backCounter).getSecondOperator();
                            thirdNumber = contents.get(backCounter).getThirdNumber();
                            imgOne = contents.get(backCounter).getImgOne();

                            if (!trans.getString(word).equals("empty")) {
                                word = trans.getString(word);
                            }
                            if (!trans.getString(secondNumber).equals("empty")) {
                                secondNumber = trans.getString(secondNumber);
                            }
                            if (!trans.getString(thirdNumber).equals("empty")) {
                                thirdNumber = trans.getString(thirdNumber);
                            }

                            if (savedInstanceState == null) {
                                Bundle bundle = new Bundle();
                                bundle.putString("PAIdentify", identify);
                                bundle.putString("PAWord", word);
                                bundle.putString("PAFirstNumber", firstNumber);
                                bundle.putString("PAFirstOperator", firstOperator);
                                bundle.putString("PASecondNumber", secondNumber);
                                bundle.putString("PASecondOperator", secondOperator);
                                bundle.putString("PAThirdNumber", thirdNumber);
                                bundle.putString("PAImage", imgOne);

                                FragmentTransaction transaction = fragmentManager.beginTransaction();
                                transaction.setReorderingAllowed(true);
                                transaction.replace(R.id.frame_layout_lesson, PerimeterArea.class, bundle);
                                transaction.commit();
                            }
                            break;
                        }

                        case "imageWord" : {
                            imgOne = contents.get(backCounter).getImgOne();
                            word = contents.get(backCounter).getWord();
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

                        case "shapesAngles":{
                            imgOne = contents.get(backCounter).getImgOne();
                            imgTwo = contents.get(backCounter).getImgTwo();
                            word = contents.get(backCounter).getWord();
                            firstNumber = contents.get(backCounter).getFirstNumber();

                            if (!trans.getString(word).equals("empty")) {
                                word = trans.getString(word);
                            }

                            if (savedInstanceState == null) {
                                Bundle bundle = new Bundle();
                                bundle.putString("firstImage", imgOne);
                                bundle.putString("secondImage", imgTwo);
                                bundle.putString("word", word);
                                bundle.putString("degree", firstNumber);

                                FragmentTransaction transaction = fragmentManager.beginTransaction();
                                transaction.setReorderingAllowed(true);
                                transaction.replace(R.id.frame_layout_lesson, ShapesAngles.class, bundle);
                                transaction.commit();
                            }

                            break;
                        }

                        case "family": {
                            word = contents.get(backCounter).getWord();
                            firstNumber = contents.get(backCounter).getFirstNumber();
                            firstOperator = contents.get(backCounter).getFirstOperator();
                            secondNumber = contents.get(backCounter).getSecondNumber();

                            if (!trans.getString(word).equals("empty")) {
                                word = trans.getString(word);
                            }

                            if (savedInstanceState == null) {
                                Bundle bundle = new Bundle();
                                bundle.putString("familyWord", word);
                                bundle.putString("familyFirstNumber", firstNumber);
                                bundle.putString("familyFirstOperator", firstOperator);
                                bundle.putString("familySecondNumber", secondNumber);

                                FragmentTransaction transaction = fragmentManager.beginTransaction();
                                transaction.setReorderingAllowed(true);
                                transaction.replace(R.id.frame_layout_lesson, Family.class, bundle);
                                transaction.commit();
                            }
                            break;
                        }

                        case "verticalEquation":{
                            word = contents.get(backCounter).getWord();
                            firstNumber = contents.get(backCounter).getFirstNumber();
                            firstOperator = contents.get(backCounter).getFirstOperator();
                            secondNumber = contents.get(backCounter).getSecondNumber();
                            secondOperator = contents.get(backCounter).getSecondOperator();
                            thirdNumber = contents.get(backCounter).getThirdNumber();

                            if (savedInstanceState == null) {
                                Bundle bundle = new Bundle();
                                bundle.putString("wordEqu", word);
                                bundle.putString("firstNum", firstNumber);
                                bundle.putString("secondNum", secondNumber);
                                bundle.putString("carry", secondOperator);
                                bundle.putString("answer", thirdNumber);
                                bundle.putString("opt", firstOperator);

                                FragmentTransaction transaction = fragmentManager.beginTransaction();
                                transaction.setReorderingAllowed(true);
                                transaction.replace(R.id.frame_layout_lesson, VerticalEquation.class, bundle);
                                transaction.commit();
                            }
                            break;
                        }

                        default:
                    }
                    backCounter--;
                    counter = backCounter + 2;
                    if(backCounter < 0){
                        backButton.getBackground().setAlpha(64);
                    }
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
        backButton = findViewById(R.id.back_button_lesson);
        nextContent = findViewById(R.id.next_button_lesson);
        lessonName = lesson.getLessonName();
        image = lesson.getImage();
        lessonBackground = findViewById(R.id.lesson2);
        topBarBackground = findViewById(R.id.topBar);
    }

    private void setTopBar(){
        topBar.setPoints(String.valueOf(child.getScore()));
        topBar.setToTriangle();
        topBarBackground.setBackgroundColor(Color.rgb(232, 160, 78));
        topBar.setShapeColor(Color.rgb(96, 163, 200));
        topBar.setPointIconBackground(Color.rgb( 232, 160, 78));
        topBar.setPointsTextColor(Color.rgb(96, 163, 200));
    }

    public void setLevelColor(){
        lessonBackground.setBackgroundColor(Color.rgb(252, 209, 70));
    }

    private void setButtonColor(){
        nextContent.setBackgroundTintList(ColorStateList.valueOf(Color.rgb(232, 160, 78)));
        backButton.setBackgroundTintList(ColorStateList.valueOf(Color.rgb(232, 160, 78)));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Close the Realm instance.
        realm.close();
    }
}
