package com.GrowthPlus;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
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
import com.GrowthPlus.realmImporter.LanguagesRealmImporter;
import com.GrowthPlus.utilities.ColorIdentifier;
import com.GrowthPlus.utilities.ImageSrcIdentifier;

import org.bson.types.ObjectId;

import java.io.InputStream;

import io.realm.Realm;
import io.realm.RealmList;

public class CreateAccount extends AppCompatActivity {
    Button backButton, loginButton;
    EditText nameInput;
    ColorIdentifier colorIdentifier;
    ImageSrcIdentifier imageSrcIdentifier;
    String colorName, animalName;
    ChildAvatarComponent childAvatar;
    ColorStateList color;
    ChildSchemaService newChild;
    ParentSchemaService parentService;
    Realm realm;
    Resources resources;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);
        init();

        RoadMapLesson roadMapLesson = new RoadMapLesson(
                "Roadmap One Lesson 1",
                "elephant",
                "numbers",
                true,
                false,
                7,
                10,
                "RmOneLessonOne",
                "RmOneLessonOneContentOne",
                "RmOneLessonOneFlashOne",
                true,
                false,
                0);
        RealmList<RoadMapLesson> roadMapLessons = new RealmList<>();
        roadMapLessons.add(roadMapLesson);

        RoadMapQuiz roadMapQuiz = new RoadMapQuiz(
                "RoadMap One Quiz One",
                "elephant",
                10,
                7,
                true,
                false,
                "RmOneQuizOne",
                0
        );
        RealmList<RoadMapQuiz> roadMapQuizzes = new RealmList<>();
        roadMapQuizzes.add(roadMapQuiz);

        RoadMapScenarioGame roadMapOneScenarioGame = new RoadMapScenarioGame(
                "Fishing",
                "fish",
                20,
                17,
                false,
                "RmOneScenarioGame",
                0
        );


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
                true,
                null,
                null,
                null,
                "RoadMapTwo");

        ChildRoadMap childRoadMapThree = new ChildRoadMap(
                "roadMapThree",
                0,
                true,
                false,
                true,
                null,
                null,
                null,
                "RoadMapThree");

        ChildRoadMap childRoadMapFour = new ChildRoadMap(
                "roadMapFour",
                0,
                false,
                false,
                true,
                null,
                null,
                null,
                "RoadMapFour");

        // Go to main page with update new child
        View.OnClickListener goNext = v -> {
            if (!nameInput.getText().toString().equals(null) && !nameInput.getText().toString().equals("")){

                newChild = new ChildSchemaService(realm,
                        nameInput.getText().toString(),
                        animalName,
                        colorName, 0,
                        childRoadMapOne,
                        childRoadMapTwo,
                        childRoadMapThree,
                        childRoadMapFour);

                ObjectId childId = new ObjectId();
                newChild.createChildSchema(String.valueOf(childId));

                // Adding newly created child to parent's children
                realm.executeTransactionAsync(realm -> {
                    ChildSchema child = realm.where(ChildSchema.class).equalTo("childId", childId.toString()).findFirst();
                    ParentSchema parent = realm.where(ParentSchema.class).findFirst();
                    parent.getChildren().add(child);

                }, ()->{
                        Intent intent = new Intent(CreateAccount.this, MainActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        overridePendingTransition(0, 0);
                        startActivity(intent);
                        overridePendingTransition(0, 0);
                        finish();
                }, error -> {
                        Log.i("Error", "Could not add child to parent " + error);
                });

            }
        };
        loginButton.setOnClickListener(goNext);

        // Go back to select child avatar
        View.OnClickListener goBack = v -> {
            finish();
        };
        backButton.setOnClickListener(goBack);
    }

    public void init(){
        realm = Realm.getDefaultInstance();
        resources = getResources();
        backButton = findViewById(R.id.backCreateAccount);
        loginButton = findViewById(R.id.loginBtn);
        nameInput = findViewById(R.id.nameInput);
        colorIdentifier = new ColorIdentifier();
        imageSrcIdentifier = new ImageSrcIdentifier();
        childAvatar = findViewById(R.id.childAvatar);
        parentService = new ParentSchemaService(realm);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            colorName = extras.getString("selectColor"); // Get color
            animalName = extras.getString("selectAnimal"); // Get animal
        }
        color = ContextCompat.getColorStateList(this, colorIdentifier.getColorIdentifier(colorName));

        backButton.setBackgroundTintList(color); // Set color
        loginButton.setBackgroundTintList(color);

        setChildAvatar();
    }

    @Override
    protected void onResume() {
        super.onResume();

        // create instance of shared preferences
        SharedPreferences langPrefs = getSharedPreferences("LangPreferences", MODE_PRIVATE);
        // import language json file
        InputStream langInputStream = resources.openRawResource(R.raw.languages);
        LanguagesRealmImporter langRealmImporter = new LanguagesRealmImporter(realm, resources, langInputStream);
        langRealmImporter.importLanguagesFromJson();
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
}