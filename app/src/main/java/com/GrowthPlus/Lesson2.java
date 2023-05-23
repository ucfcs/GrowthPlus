package com.GrowthPlus;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.Button;

import com.GrowthPlus.customViews.TopBar;
import com.GrowthPlus.dataAccessLayer.Language.Translator;
import com.GrowthPlus.dataAccessLayer.Lesson.LessonSchema;
import com.GrowthPlus.dataAccessLayer.LessonContent.LessonContent;
import com.GrowthPlus.dataAccessLayer.child.ChildSchema;
import com.GrowthPlus.fragment.Conversion;
import com.GrowthPlus.fragment.ConversionTable;
import com.GrowthPlus.fragment.ConversionTableTwo;
import com.GrowthPlus.fragment.Counting;
import com.GrowthPlus.fragment.GridWord;
import com.GrowthPlus.fragment.HorizontalEquation;
import com.GrowthPlus.fragment.ImageWord;
import com.GrowthPlus.fragment.VerticalEquation;
import com.GrowthPlus.fragment.WordGrid;
import com.GrowthPlus.fragment.WordImage;
import com.GrowthPlus.fragment.WordImageEquation;
import com.GrowthPlus.roadMapActivity.RoadMapTwo;

import io.realm.Realm;
import io.realm.RealmList;

public class Lesson2 extends AppCompatActivity {
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
    private String name;
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
            Intent lessonIntent = new Intent(Lesson2.this, RoadMapTwo.class);
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
            bundle.putInt("level", 2);

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
                // Reached the end of the contents and need to start looking at flashcards or the Lesson is 10 (which is all flashcards)
                v.startAnimation(buttonClick);
                if(counter >= contentLength){
                    Intent flashcardIntent = new Intent(Lesson2.this, Flashcard2.class);
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

                        case "wordImage" : {
                            name = contents.get(counter).getWord();
                            word = contents.get(counter).getWord();
                            if(!trans.getString(word).equals("empty")){
                                word = trans.getString(word);
                            }
                            imgOne = contents.get(counter).getImgOne();

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

                        case "imageWord" : {
                            imgOne = contents.get(counter).getImgOne();
                            word = contents.get(counter).getWord();

                            String first, second;

                            if(word.contains(" ")){
                                first = word.substring(0, word.indexOf(" "));
                                second = word.substring(word.indexOf(" "));
                                if (!trans.getString(first).equals("empty")) {
                                    word = trans.getString(first) + second;
                                }
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

                        case "conversionTable" :{
                            firstNumber = contents.get(counter).getFirstNumber();
                            firstOperator = contents.get(counter).getFirstOperator();
                            secondNumber = contents.get(counter).getSecondNumber();
                            secondOperator = contents.get(counter).getSecondOperator();

                            Log.i("firstOperator", firstOperator );

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

                        case "conversionTableTwo" :{
                            firstNumber = contents.get(counter).getFirstNumber();
                            firstOperator = contents.get(counter).getFirstOperator();

                            if (savedInstanceState == null) {
                                Bundle bundle = new Bundle();
                                bundle.putString("conversionTableText1", firstNumber);
                                bundle.putString("conversionTableText2", firstOperator);

                                FragmentTransaction transaction = fragmentManager.beginTransaction();
                                transaction.setReorderingAllowed(true);
                                transaction.replace(R.id.frame_layout_lesson, ConversionTableTwo.class, bundle);
                                transaction.commit();
                            }
                            break;
                        }

                        case "gridWord" :{
                            word = contents.get(counter).getWord();
                            int firstNumberGrid = Integer.valueOf(contents.get(counter).getFirstNumber());
                            imgOne = contents.get(counter).getImgOne();

                            String first, second;

                            if(word.contains(" ")){
                                first = word.substring(0, word.indexOf(" "));
                                second = word.substring(word.indexOf(" "));
                                if (!trans.getString(first).equals("empty")) {
                                    word = trans.getString(first) + second;
                                }
                            }

                            if (savedInstanceState == null) {
                                Bundle bundle = new Bundle();
                                bundle.putString("gridWord", word);
                                bundle.putString("gridImg", imgOne);
                                bundle.putInt("gridNum", firstNumberGrid);

                                FragmentTransaction transaction = fragmentManager.beginTransaction();
                                transaction.setReorderingAllowed(true);
                                transaction.replace(R.id.frame_layout_lesson, GridWord.class, bundle);
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

                        case "wordImage" : {
                            name = contents.get(backCounter).getWord();
                            word = contents.get(backCounter).getWord();
                            if(!trans.getString(word).equals("empty")){
                                word = trans.getString(word);
                            }
                            imgOne = contents.get(backCounter).getImgOne();

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
                            firstNumber = contents.get(backCounter).getFirstNumber();
                            secondNumber = contents.get(backCounter).getSecondNumber();
                            // Translate to respective words
                            String firstWord = "10 " + trans.getString(firstNumber);
                            String secondWord = trans.getString(secondNumber);

                            // Multiple image is many images of one tile with small numbers
                            // Single image is one image with one large number
                            imgOne = contents.get(backCounter).getImgOne();
                            imgTwo = contents.get(backCounter).getImgTwo();
                            imgThree = contents.get(backCounter).getImgThree();

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

                        case "imageWord" : {
                            imgOne = contents.get(backCounter).getImgOne();
                            word = contents.get(backCounter).getWord();

                            String first, second;

                            if(word.contains(" ")){
                                first = word.substring(0, word.indexOf(" "));
                                second = word.substring(word.indexOf(" "));
                                if (!trans.getString(first).equals("empty")) {
                                    word = trans.getString(first) + second;
                                }
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
                            firstNumber = contents.get(backCounter).getFirstNumber();
                            firstOperator = contents.get(backCounter).getFirstOperator();
                            secondNumber = contents.get(backCounter).getSecondNumber();

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

                        case "conversionTable" :{
                            firstNumber = contents.get(backCounter).getFirstNumber();
                            firstOperator = contents.get(backCounter).getFirstOperator();
                            secondNumber = contents.get(backCounter).getSecondNumber();
                            secondOperator = contents.get(backCounter).getSecondOperator();

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

                        case "conversionTableTwo" :{
                            firstNumber = contents.get(backCounter).getFirstNumber();
                            firstOperator = contents.get(backCounter).getFirstOperator();

                            if (savedInstanceState == null) {
                                Bundle bundle = new Bundle();
                                bundle.putString("conversionTableText1", firstNumber);
                                bundle.putString("conversionTableText2", firstOperator);

                                FragmentTransaction transaction = fragmentManager.beginTransaction();
                                transaction.setReorderingAllowed(true);
                                transaction.replace(R.id.frame_layout_lesson, ConversionTableTwo.class, bundle);
                                transaction.commit();
                            }
                            break;
                        }

                        case "gridWord" :{
                            word = contents.get(backCounter).getWord();
                            int firstNumberGrid = Integer.valueOf(contents.get(backCounter).getFirstNumber());
                            imgOne = contents.get(backCounter).getImgOne();

                            String first, second;

                            if(word.contains(" ")){
                                first = word.substring(0, word.indexOf(" "));
                                second = word.substring(word.indexOf(" "));
                                if (!trans.getString(first).equals("empty")) {
                                    word = trans.getString(first) + second;
                                }
                            }

                            if (savedInstanceState == null) {
                                Bundle bundle = new Bundle();
                                bundle.putString("gridWord", word);
                                bundle.putString("gridImg", imgOne);
                                bundle.putInt("gridNum", firstNumberGrid);

                                FragmentTransaction transaction = fragmentManager.beginTransaction();
                                transaction.setReorderingAllowed(true);
                                transaction.replace(R.id.frame_layout_lesson, GridWord.class, bundle);
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
        nextContent = findViewById(R.id.next_button_lesson);
        lessonName = lesson.getLessonName();
        image = lesson.getImage();
        lessonBackground = findViewById(R.id.lesson2);
        topBarBackground = findViewById(R.id.topBar);
        backButton = findViewById(R.id.back_button_lesson);
    }

    private void setTopBar(){
        topBar.setPoints(String.valueOf(child.getScore()));
        topBar.setToTriangle();
        topBarBackground.setBackgroundColor(Color.rgb(252, 209, 70));
        topBar.setShapeColor(Color.rgb(96, 163, 200));
        topBar.setPointIconBackground(Color.rgb(252, 209, 70));
        topBar.setPointsTextColor(Color.rgb(96, 163, 200));
    }
    public void setLevelColor(){
        lessonBackground.setBackgroundColor(Color.rgb(232, 160, 78));
    }

    private void setButtonColor(){
        nextContent.setBackgroundTintList(ColorStateList.valueOf(Color.rgb(252, 209, 70)));
        backButton.setBackgroundTintList(ColorStateList.valueOf(Color.rgb(252, 209, 70)));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Close the Realm instance.
        realm.close();
    }
}
