package com.GrowthPlus;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

import com.GrowthPlus.customViews.ChildAvatarComponent;
import com.GrowthPlus.dataAccessLayer.ChildRoadMap.ChildRoadMap;
import com.GrowthPlus.dataAccessLayer.Language.LanguageSchema;
import com.GrowthPlus.dataAccessLayer.Language.LanguageSchemaService;
import com.GrowthPlus.dataAccessLayer.RoadMapLesson.RoadMapLesson;
import com.GrowthPlus.dataAccessLayer.RoadMapQuiz.RoadMapQuiz;
import com.GrowthPlus.dataAccessLayer.RoadMapScenarioGame.RoadMapScenarioGame;
import com.GrowthPlus.dataAccessLayer.child.ChildSchema;
import com.GrowthPlus.dataAccessLayer.child.ChildSchemaService;
import com.GrowthPlus.dataAccessLayer.parent.ParentSchema;
import com.GrowthPlus.dataAccessLayer.parent.ParentSchemaService;
import com.GrowthPlus.utilities.ColorIdentifier;
import com.GrowthPlus.utilities.ImageSrcIdentifier;

import org.bson.types.ObjectId;

import java.util.Objects;

import io.realm.Realm;
import io.realm.RealmChangeListener;
import io.realm.RealmList;
import io.realm.RealmResults;

public class CreateAccount extends AppCompatActivity {
    Button backButton, loginButton;
    EditText nameInput;
    ColorIdentifier colorIdentifier;
    ImageSrcIdentifier imageSrcIdentifier;
    String colorName, animalName;
    ChildAvatarComponent childAvatar;
    ColorStateList color;
    ParentSchemaService parentService;

    ChildSchemaService childSchemaService;
    Realm realm;
    Resources resources;
    private RealmChangeListener<ParentSchema> realmListener;
    private String goBackTo;
    private final AlphaAnimation buttonClick = new AlphaAnimation(1F, 0.8F);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);
        init();

        //search through the children in realm

        // ROADMAP 1 : lessons, quizzes, and game
        RoadMapLesson roadMapLesson1 = new RoadMapLesson(
                "Roadmap One Lesson 1",
                "elephant",
                "numbers",
                false,
                true,
                7,
                10,
                "RmOneLessonOne",
                "RmOneLessonOneContentOne",
                "RmOneLessonOneFlashOne",
                true,
                false,
                0);
        RoadMapLesson roadMapLesson2 = new RoadMapLesson(
                "Roadmap One Lesson 2",
                "elephant",
                "addition",
                false,
                true,
                7,
                10,
                "RmOneLessonTwo",
                "RmOneLessonTwoContentOne",
                "RmOneLessonTwoFlashOne",
                true,
                false,
                0);
        RoadMapLesson roadMapLesson3 = new RoadMapLesson(
                "Roadmap One Lesson 3",
                "unit",
                "units",
                false,
                true,
                7,
                10,
                "RmOneLessonThree",
                "RmOneLessonThreeContentOne",
                "RmOneLessonThreeFlashOne",
                true,
                false,
                0);
        RoadMapLesson roadMapLesson4 = new RoadMapLesson(
                "Roadmap One Lesson 4",
                "elephant",
                "numbers",
                false,
                true,
                7,
                10,
                "RmOneLessonFour",
                "RmOneLessonFourContentOne",
                "RmOneLessonFourFlashOne",
                true,
                false,
                0);
        RoadMapLesson roadMapLesson5 = new RoadMapLesson(
                "Roadmap One Lesson 5",
                "elephant",
                "subtraction",
                false,
                true,
                7,
                10,
                "RmOneLessonFive",
                "RmOneLessonFiveContentOne",
                "RmOneLessonFiveFlashFive",
                true,
                false,
                0);
        RoadMapLesson roadMapLesson6 = new RoadMapLesson(
                "Roadmap One Lesson 6",
                "elephant",
                "multiplication",
                false,
                true,
                7,
                10,
                "RmOneLessonSix",
                "RmOneLessonSixContentOne",
                "RmOneLessonSixFlashOne",
                true,
                false,
                0);
        RoadMapLesson roadMapLesson7 = new RoadMapLesson(
                "Roadmap One Lesson 7",
                "elephant",
                "division",
                false,
                true,
                7,
                10,
                "RmOneLessonSeven",
                "RmOneLessonSevenContentOne",
                "RmOneLessonSevenFlashOne",
                true,
                false,
                0);
        RoadMapLesson roadMapLesson8 = new RoadMapLesson(
                "Roadmap One Lesson 8",
                "elephant",
                "multiplication",
                false,
                true,
                7,
                10,
                "RmOneLessonEight",
                "RmOneLessonEightContentOne",
                "RmOneLessonEightFlashOne",
                true,
                false,
                0);
        RoadMapLesson roadMapLesson9 = new RoadMapLesson(
                "Roadmap One Lesson 9",
                "elephant",
                "division",
                false,
                true,
                7,
                10,
                "RmOneLessonNine",
                "RmOneLessonNineContentOne",
                "RmOneLessonNineFlashOne",
                true,
                false,
                0);
        RoadMapLesson roadMapLesson10 = new RoadMapLesson(
                "Roadmap One Lesson 10",
                "elephant",
                null,
                false,
                true,
                7,
                10,
                "RmOneLessonTen",
                null,
                "RmOneLessonOneFlashOne",
                true,
                false,
                0);

        RealmList<RoadMapLesson> roadMapLessons = new RealmList<>();
        roadMapLessons.add(roadMapLesson1);
        roadMapLessons.add(roadMapLesson2);
        roadMapLessons.add(roadMapLesson3);
        roadMapLessons.add(roadMapLesson4);
        roadMapLessons.add(roadMapLesson5);
        roadMapLessons.add(roadMapLesson6);
        roadMapLessons.add(roadMapLesson7);
        roadMapLessons.add(roadMapLesson8);
        roadMapLessons.add(roadMapLesson9);
        roadMapLessons.add(roadMapLesson10);

        RoadMapQuiz roadMapQuiz1 = new RoadMapQuiz(
                "RoadMap One Quiz One",
                "elephant",
                10,
                7,
                false,
                true,
                "RmOneQuizOne",
                0
        );
        RoadMapQuiz roadMapQuiz2 = new RoadMapQuiz(
                "RoadMap One Quiz Two",
                "elephant",
                10,
                7,
                false,
                true,
                "RmOneQuizTwo",
                0
        );
        RealmList<RoadMapQuiz> roadMapQuizzes = new RealmList<>();
        roadMapQuizzes.add(roadMapQuiz1);
        roadMapQuizzes.add(roadMapQuiz2);

        RoadMapScenarioGame roadMapOneScenarioGame = new RoadMapScenarioGame(
                "Game 1",
                "elephant",
                20,
                17,
                true,
                false,
                "RmOneScenarioGame",
                0
        );

        // ROADMAP 2 : lessons, quizzes, and game

        RoadMapLesson roadMapLesson11 = new RoadMapLesson(
                "Roadmap Two Lesson 1",
                "camel",
                "numbers",
                false,
                true,
                7,
                10,
                "RmTwoLessonOne",
                "RmTwoLessonOneContentOne",
                "RmTwoLessonOneFlashOne",
                true,
                false,
                0);
        RoadMapLesson roadMapLesson12 = new RoadMapLesson(
                "Roadmap Two Lesson 2",
                "camel",
                "addition",
                false,
                true,
                7,
                10,
                "RmTwoLessonTwo",
                "RmTwoLessonTwoContentOne",
                "RmTwoLessonTwoFlashOne",
                true,
                false,
                0);
        RoadMapLesson roadMapLesson13 = new RoadMapLesson(
                "Roadmap Two Lesson 3",
                "camel",
                "units",
                false,
                true,
                7,
                10,
                "RmTwoLessonThree",
                "RmTwoLessonThreeContentOne",
                "RmTwoLessonThreeFlashOne",
                true,
                false,
                0);
        RoadMapLesson roadMapLesson14 = new RoadMapLesson(
                "Roadmap Two Lesson 4",
                "camel",
                "numbers",
                false,
                true,
                7,
                10,
                "RmTwoLessonFour",
                "RmTwoLessonFourContentOne",
                "RmTwoLessonFourFlashOne",
                true,
                false,
                0);
        RoadMapLesson roadMapLesson15 = new RoadMapLesson(
                "Roadmap Two Lesson 5",
                "camel",
                "subtraction",
                false,
                true,
                7,
                10,
                "RmTwoLessonFive",
                "RmTwoLessonFiveContentOne",
                "RmTwoLessonFiveFlashOne",
                true,
                false,
                0);
        RoadMapLesson roadMapLesson16 = new RoadMapLesson(
                "Roadmap Two Lesson 6",
                "camel",
                "money",
                false,
                true,
                7,
                10,
                "RmTwoLessonSix",
                "RmTwoLessonSixContentOne",
                "RmTwoLessonSixFlashOne",
                true,
                false,
                0);
        RoadMapLesson roadMapLesson17 = new RoadMapLesson(
                "Roadmap Two Lesson 7",
                "camel",
                "length",
                false,
                true,
                7,
                10,
                "RmTwoLessonSeven",
                "RmTwoLessonSevenContentOne",
                "RmTwoLessonSevenFlashOne",
                true,
                false,
                0);
        RoadMapLesson roadMapLesson18 = new RoadMapLesson(
                "Roadmap Two Lesson 8",
                "camel",
                "weight",
                false,
                true,
                7,
                10,
                "RmTwoLessonEight",
                "RmTwoLessonEightContentOne",
                "RmTwoLessonEightFlashOne",
                true,
                false,
                0);
        RoadMapLesson roadMapLesson19 = new RoadMapLesson(
                "Roadmap Two Lesson 9",
                "camel",
                "weight",
                false,
                true,
                7,
                10,
                "RmTwoLessonNine",
                "RmTwoLessonNineContentOne",
                "RmTwoLessonNineFlashOne",
                true,
                false,
                0);
        RoadMapLesson roadMapLesson20 = new RoadMapLesson(
                "Roadmap Two Lesson 10",
                "camel",
                null,
                false,
                true,
                7,
                10,
                "RmTwoLessonTen",
                null,
                "RmTwoLessonTenFlashOne",
                true,
                false,
                0);
        RealmList<RoadMapLesson> roadMapLessons2 = new RealmList<>();
        roadMapLessons2.add(roadMapLesson11);
        roadMapLessons2.add(roadMapLesson12);
        roadMapLessons2.add(roadMapLesson13);
        roadMapLessons2.add(roadMapLesson14);
        roadMapLessons2.add(roadMapLesson15);
        roadMapLessons2.add(roadMapLesson16);
        roadMapLessons2.add(roadMapLesson17);
        roadMapLessons2.add(roadMapLesson18);
        roadMapLessons2.add(roadMapLesson19);
        roadMapLessons2.add(roadMapLesson20);

        RoadMapQuiz roadMapQuiz3 = new RoadMapQuiz(
                "RoadMap Two Quiz One",
                "camel",
                10,
                7,
                false,
                true,
                "RmTwoQuizOne",
                0
        );
        RoadMapQuiz roadMapQuiz4 = new RoadMapQuiz(
                "RoadMap Two Quiz Two",
                "camel",
                10,
                7,
                false,
                true,
                "RmTwoQuizTwo",
                0
        );
        RealmList<RoadMapQuiz> roadMapQuizzes2 = new RealmList<>();
        roadMapQuizzes2.add(roadMapQuiz3);
        roadMapQuizzes2.add(roadMapQuiz4);

        RoadMapScenarioGame roadMapTwoScenarioGame = new RoadMapScenarioGame(
                "Game 1",
                "camel",
                20,
                17,
                true,
                false,
                "RmTwoScenarioGame",
                0
        );

        // ROADMAP 3 : lessons, quizzes, and game

        RoadMapLesson roadMapLesson21 = new RoadMapLesson(
                "Roadmap Three Lesson 1",
                "squirrel",
                "numbers",
                false,
                true,
                7,
                10,
                "RmThreeLessonOne",
                "RmThreeLessonOneContentOne",
                "RmThreeLessonOneFlashOne",
                true,
                false,
                0);
        RoadMapLesson roadMapLesson22 = new RoadMapLesson(
                "Roadmap Three Lesson 2",
                "squirrel",
                "multiplication",
                false,
                true,
                7,
                10,
                "RmThreeLessonTwo",
                "RmThreeLessonTwoContentOne",
                "RmThreeLessonTwoFlashOne",
                true,
                false,
                0);
        RoadMapLesson roadMapLesson23 = new RoadMapLesson(
                "Roadmap Two Lesson 3",
                "squirrel",
                "division",
                false,
                true,
                7,
                10,
                "RmThreeLessonThree",
                "RmThreeLessonThreeContentOne",
                "RmThreeLessonThreeFlashOne",
                true,
                false,
                0);
        RoadMapLesson roadMapLesson24 = new RoadMapLesson(
                "Roadmap Three Lesson 4",
                "squirrel",
                "money",
                false,
                true,
                7,
                10,
                "RmThreeLessonFour",
                "RmThreeLessonFourContentOne",
                "RmThreeLessonFourFlashOne",
                true,
                false,
                0);
        RoadMapLesson roadMapLesson25 = new RoadMapLesson(
                "Roadmap Three Lesson 5",
                "squirrel",
                "length",
                false,
                true,
                7,
                10,
                "RmThreeLessonFive",
                "RmThreeLessonFiveContentOne",
                "RmThreeLessonFiveFlashOne",
                true,
                false,
                0);
        RoadMapLesson roadMapLesson26 = new RoadMapLesson(
                "Roadmap Three Lesson 6",
                "squirrel",
                "weight",
                false,
                true,
                7,
                10,
                "RmThreeLessonSix",
                "RmThreeLessonSixContentOne",
                "RmThreeLessonSixFlashOne",
                true,
                false,
                0);
        RoadMapLesson roadMapLesson27 = new RoadMapLesson(
                "Roadmap Three Lesson 7",
                "squirrel",
                "time",
                false,
                true,
                7,
                10,
                "RmThreeLessonSeven",
                "RmThreeLessonSevenContentOne",
                "RmThreeLessonSevenFlashOne",
                true,
                false,
                0);
        RoadMapLesson roadMapLesson28 = new RoadMapLesson(
                "Roadmap Three Lesson 8",
                "squirrel",
                "shapes",
                false,
                true,
                7,
                10,
                "RmThreeLessonEight",
                "RmThreeLessonEightContentOne",
                "RmThreeLessonEightFlashOne",
                true,
                false,
                0);
        RoadMapLesson roadMapLesson29 = new RoadMapLesson(
                "Roadmap Three Lesson 9",
                "squirrel",
                "shapes",
                false,
                true,
                7,
                10,
                "RmThreeLessonNine",
                "RmThreeLessonNineContentOne",
                "RmThreeLessonNineFlashOne",
                true,
                false,
                0);
        RoadMapLesson roadMapLesson30 = new RoadMapLesson(
                "Roadmap Three Lesson 10",
                "squirrel",
                null,
                false,
                true,
                7,
                10,
                "RmThreeLessonTen",
                null,
                "RmThreeLessonTenFlashOne",
                true,
                false,
                0);
        RealmList<RoadMapLesson> roadMapLessons3 = new RealmList<>();
        roadMapLessons3.add(roadMapLesson21);
        roadMapLessons3.add(roadMapLesson22);
        roadMapLessons3.add(roadMapLesson23);
        roadMapLessons3.add(roadMapLesson24);
        roadMapLessons3.add(roadMapLesson25);
        roadMapLessons3.add(roadMapLesson26);
        roadMapLessons3.add(roadMapLesson27);
        roadMapLessons3.add(roadMapLesson28);
        roadMapLessons3.add(roadMapLesson29);
        roadMapLessons3.add(roadMapLesson30);

        RoadMapQuiz roadMapQuiz6 = new RoadMapQuiz(
                "RoadMap Three Quiz One",
                "squirrel",
                10,
                7,
                false,
                true,
                "RmThreeQuizOne",
                0
        );
        RoadMapQuiz roadMapQuiz7 = new RoadMapQuiz(
                "RoadMap Three Quiz Two",
                "squirrel",
                10,
                7,
                false,
                true,
                "RmThreeQuizTwo",
                0
        );
        RealmList<RoadMapQuiz> roadMapQuizzes3 = new RealmList<>();
        roadMapQuizzes3.add(roadMapQuiz6);
        roadMapQuizzes3.add(roadMapQuiz7);

        RoadMapScenarioGame roadMapThreeScenarioGame = new RoadMapScenarioGame(
                "Game 1",
                "squirrel",
                20,
                17,
                true,
                false,
                "RmThreeScenarioGame",
                0
        );

        // ROADMAP 3 : lessons, quizzes, and game

        RoadMapLesson roadMapLesson31 = new RoadMapLesson(
                "Roadmap Four Lesson 1",
                "giraffe",
                "numbers",
                false,
                true,
                7,
                10,
                "RmFourLessonOne",
                "RmFourLessonOneContentOne",
                "RmFourLessonOneFlashOne",
                true,
                false,
                0);
        RoadMapLesson roadMapLesson32 = new RoadMapLesson(
                "Roadmap Four Lesson 2",
                "giraffe",
                "multiplication",
                false,
                true,
                7,
                10,
                "RmFourLessonTwo",
                "RmFourLessonTwoContentOne",
                "RmFourLessonTwoFlashOne",
                true,
                false,
                0);
        RoadMapLesson roadMapLesson33 = new RoadMapLesson(
                "Roadmap Four Lesson 3",
                "giraffe",
                "division",
                false,
                true,
                7,
                10,
                "RmFourLessonThree",
                "RmFourLessonThreeContentOne",
                "RmFourLessonThreeFlashOne",
                true,
                false,
                0);
        RoadMapLesson roadMapLesson34 = new RoadMapLesson(
                "Roadmap Four Lesson 4",
                "giraffe",
                "angles",
                false,
                true,
                7,
                10,
                "RmFourLessonFour",
                "RmFourLessonFourContentOne",
                "RmFourLessonFourFlashOne",
                true,
                false,
                0);
        RoadMapLesson roadMapLesson35 = new RoadMapLesson(
                "Roadmap Four Lesson 5",
                "giraffe",
                "shapes",
                false,
                true,
                7,
                10,
                "RmFourLessonFive",
                "RmFourLessonFiveContentOne",
                "RmFourLessonFiveFlashOne",
                true,
                false,
                0);
        RoadMapLesson roadMapLesson36 = new RoadMapLesson(
                "Roadmap Four Lesson 6",
                "giraffe",
                "money",
                false,
                true,
                7,
                10,
                "RmFourLessonSix",
                "RmFourLessonSixContentOne",
                "RmFourLessonSixFlashOne",
                true,
                false,
                0);
        RoadMapLesson roadMapLesson37 = new RoadMapLesson(
                "Roadmap Four Lesson 7",
                "giraffe",
                "angles",
                false,
                true,
                7,
                10,
                "RmFourLessonSeven",
                "RmFourLessonSevenContentOne",
                "RmFourLessonSevenFlashOne",
                true,
                false,
                0);
        RoadMapLesson roadMapLesson38 = new RoadMapLesson(
                "Roadmap Four Lesson 8",
                "giraffe",
                "multiplication",
                false,
                true,
                7,
                10,
                "RmFourLessonEight",
                "RmFourLessonEightContentOne",
                "RmFourLessonEightFlashOne",
                true,
                false,
                0);
        RoadMapLesson roadMapLesson39 = new RoadMapLesson(
                "Roadmap Four Lesson 9",
                "giraffe",
                "division",
                false,
                true,
                7,
                10,
                "RmFourLessonNine",
                "RmFourLessonNineContentOne",
                "RmFourLessonNineFlashOne",
                true,
                false,
                0);
        RoadMapLesson roadMapLesson40 = new RoadMapLesson(
                "Roadmap Four Lesson 10",
                "giraffe",
                null,
                false,
                true,
                7,
                10,
                "RmFourLessonTen",
                null,
                "RmFourLessonTenFlashOne",
                true,
                false,
                0);
        RealmList<RoadMapLesson> roadMapLessons4 = new RealmList<>();
        roadMapLessons4.add(roadMapLesson31);
        roadMapLessons4.add(roadMapLesson32);
        roadMapLessons4.add(roadMapLesson33);
        roadMapLessons4.add(roadMapLesson34);
        roadMapLessons4.add(roadMapLesson35);
        roadMapLessons4.add(roadMapLesson36);
        roadMapLessons4.add(roadMapLesson37);
        roadMapLessons4.add(roadMapLesson38);
        roadMapLessons4.add(roadMapLesson39);
        roadMapLessons4.add(roadMapLesson40);

        RoadMapQuiz roadMapQuiz8 = new RoadMapQuiz(
                "RoadMap Four Quiz One",
                "giraffe",
                10,
                7,
                false,
                true,
                "RmFourQuizOne",
                0
        );
        RoadMapQuiz roadMapQuiz9 = new RoadMapQuiz(
                "RoadMap Four Quiz Two",
                "giraffe",
                10,
                7,
                false,
                true,
                "RmFourQuizTwo",
                0
        );
        RealmList<RoadMapQuiz> roadMapQuizzes4 = new RealmList<>();
        roadMapQuizzes4.add(roadMapQuiz8);
        roadMapQuizzes4.add(roadMapQuiz9);

        RoadMapScenarioGame roadMapFourScenarioGame = new RoadMapScenarioGame(
                "Game 1",
                "giraffe",
                20,
                17,
                false,
                true,
                "RmFourScenarioGame",
                0
        );

        // EMBEDDED CHILD ROADMAPS

        ChildRoadMap childRoadMapOne = new ChildRoadMap(
                "roadMapOne",
                0,
                false,
                true,
                false,
                roadMapLessons,
                roadMapQuizzes,
                roadMapOneScenarioGame,
                "RoadMapOne");

        ChildRoadMap childRoadMapTwo = new ChildRoadMap(
                "roadMapTwo",
                0,
                false,
                true,
                false,
                roadMapLessons2,
                roadMapQuizzes2,
                roadMapTwoScenarioGame,
                "RoadMapTwo");

        ChildRoadMap childRoadMapThree = new ChildRoadMap(
                "roadMapThree",
                0,
                false,
                true,
                false,
                roadMapLessons3,
                roadMapQuizzes3,
                roadMapThreeScenarioGame,
                "RoadMapThree");

        ChildRoadMap childRoadMapFour = new ChildRoadMap(
                "roadMapFour",
                0,
                true,
                false,
                false,
                roadMapLessons4,
                roadMapQuizzes4,
                roadMapFourScenarioGame,
                "RoadMapFour");

        // Go to main page with update new child
        View.OnClickListener goNext = v -> {
            v.startAnimation(buttonClick);
            Objects.requireNonNull(realm.where(ParentSchema.class).findFirst()).addChangeListener(realmListener);
            if(!hasNoDupes() || nameInput.getText().toString().equals("")){
                nameInput.setBackgroundTintList(ColorStateList.valueOf(Color.rgb(221, 97, 87)));
                nameInput.setText("");
            }
            else if (!nameInput.getText().toString().equals("") && hasNoDupes()){
                nameInput.setBackgroundTintList(ColorStateList.valueOf(Color.rgb(204, 204, 204)));
                loginButton.setOnClickListener(null);
                backButton.setOnClickListener(null);
                realm.executeTransactionAsync(realm -> {
                    ParentSchema parent = realm.where(ParentSchema.class).findFirst();
                    assert parent != null;

                    ObjectId childId = new ObjectId();
                    ChildSchema newChild = realm.createObject(ChildSchema.class, String.valueOf(childId));
                    newChild.setName(nameInput.getText().toString());
                    newChild.setAvatarName(animalName);
                    newChild.setColorName(colorName);
                    newChild.setScore(0);
                    newChild.setRoadMapOne(childRoadMapOne);
                    newChild.setRoadMapTwo(childRoadMapTwo);
                    newChild.setRoadMapThree(childRoadMapThree);
                    newChild.setRoadMapFour(childRoadMapFour);
                    newChild.setCatCountNumbers(0);
                    newChild.setCatCountUnits(0);
                    newChild.setCatCountAddition(0);
                    newChild.setCatCountSubtraction(0);
                    newChild.setCatCountMultiplication(0);
                    newChild.setCatCountDivision(0);
                    newChild.setCatCountLength(0);
                    newChild.setCatCountWeightVolume(0);
                    newChild.setCatCountMoney(0);
                    newChild.setCatCountTime(0);
                    newChild.setCatCountShapes(0);
                    newChild.setCatCountAngles(0);
                    newChild.setCatCountReview(0);
                    newChild.setTotalLessonsCompleted(0);

                    parent.getChildren().add(newChild);
                });
            }//end of if statement
        };
        loginButton.setOnClickListener(goNext);

        // Go back to select child avatar
        View.OnClickListener goBack = v -> {
            v.startAnimation(buttonClick);
            backToSelectAvatar();
        };
        backButton.setOnClickListener(goBack);
    }

    public boolean hasNoDupes(){
        RealmResults<ChildSchema> children = childSchemaService.getAllChildSchemas();
        int childrenRealmResultSize = children.size();

        for(int i = 0; i<childrenRealmResultSize; i++){
            ChildSchema childRealmObjectTemp = children.get(i);
            if(childRealmObjectTemp.getName().equalsIgnoreCase(nameInput.getText().toString())){
                return false;
            }
        }
        return true;
    }
    public void init(){
        realm = Realm.getDefaultInstance();
        resources = getResources();
        backButton = findViewById(R.id.backCreateAccount);
        loginButton = findViewById(R.id.loginBtn);

        nameInput = findViewById(R.id.nameInput);
        nameInput.setOnFocusChangeListener(this::hideKeyboard);

        colorIdentifier = new ColorIdentifier();
        imageSrcIdentifier = new ImageSrcIdentifier();
        childAvatar = findViewById(R.id.childAvatar);
        parentService = new ParentSchemaService(realm);
        childSchemaService = new ChildSchemaService(realm);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            colorName = extras.getString("selectColor"); // Get color
            animalName = extras.getString("selectAnimal"); // Get animal
            goBackTo = extras.getString("comingFrom"); // Send back to select avatar
        }
        color = ContextCompat.getColorStateList(this, colorIdentifier.getColorIdentifier(colorName));
        backButton.setBackgroundTintList(color); // Set color
        loginButton.setBackgroundTintList(color);
        realmListener = realm -> backToLandingPage();
        setChildAvatar();
    }

    @Override
    protected void onResume() {
        super.onResume();

        // create instance of shared preferences
        SharedPreferences langPrefs = getSharedPreferences("LangPreferences", MODE_PRIVATE);
        // create language schema service and set strings
        LanguageSchemaService langSchemaService = new LanguageSchemaService(realm, langPrefs.getString("languageId", "frenchZero"));
        LanguageSchema lang = langSchemaService.getLanguageSchemaById();

        nameInput.setHint(lang.getName());

    }

    // Change custom view animal and color
    public void setChildAvatar(){
        childAvatar.setImageResource(imageSrcIdentifier.getImageSrcId(animalName));
        childAvatar.setBackgroundTintList(color);
    }

    private void backToLandingPage(){
        Intent intent = new Intent(CreateAccount.this, MainActivity.class);
        startActivity(intent);
        this.finish();
    }

    private void backToSelectAvatar(){
        Intent intent = new Intent(CreateAccount.this, SelectChildAvatar.class);
        intent.putExtra("comingFrom", goBackTo);
        startActivity(intent);
        this.finish();
    }

    private void hideKeyboard(View view, boolean hasFocus) {
        if (!hasFocus) {
            InputMethodManager inputMethodManager =(InputMethodManager)getSystemService(INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Remove the listener.
        Objects.requireNonNull(realm.where(ParentSchema.class).findFirst()).removeChangeListener(realmListener);
        realm.removeAllChangeListeners();
        // Close the Realm instance.
        realm.close();
    }
}