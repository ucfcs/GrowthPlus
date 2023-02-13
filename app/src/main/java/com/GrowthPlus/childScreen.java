package com.GrowthPlus;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.util.Log;
import android.view.animation.AlphaAnimation;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.GrowthPlus.customViews.ChildAvatarComponent;
import com.GrowthPlus.customViews.ChildNameScoreComponent;
import com.GrowthPlus.customViews.HorizontalProgressBar;
import com.GrowthPlus.customViews.SubjectCompletionComponent;
import com.GrowthPlus.dataAccessLayer.child.ChildSchema;
import com.GrowthPlus.dataAccessLayer.child.ChildSchemaService;
import com.GrowthPlus.utilities.ColorIdentifier;
import com.GrowthPlus.utilities.ImageSrcIdentifier;

import io.realm.Realm;

public class childScreen extends AppCompatActivity {

    private Button backParentPortal;
    private Button deleteChildButton;
    private ChildAvatarComponent childAvatar;
    private ChildNameScoreComponent childNameAndScore;

    private HorizontalProgressBar horizontalProgressBarOne;
    private HorizontalProgressBar horizontalProgressBarTwo;
    private HorizontalProgressBar horizontalProgressBarThree;
    private HorizontalProgressBar horizontalProgressBarFour;

    private SubjectCompletionComponent numbers;
    private SubjectCompletionComponent length;
    private SubjectCompletionComponent unit;
    private SubjectCompletionComponent weight;
    private SubjectCompletionComponent addition;
    private SubjectCompletionComponent money;
    private SubjectCompletionComponent subtraction;
    private SubjectCompletionComponent time;
    private SubjectCompletionComponent multiplication;
    private SubjectCompletionComponent shapes;
    private SubjectCompletionComponent division;
    private SubjectCompletionComponent angles;
    private ColorIdentifier colorIdentifier;
    private ImageSrcIdentifier imageSrcIdentifier;

    private String childId;
    private ColorStateList progressBarOneColor;
    private ColorStateList progressBarTwoColor;
    private ColorStateList progressBarThreeColor;
    private ColorStateList progressBarFourColor;

    private Realm realm;
    private ChildSchemaService childSchemaService;
    private AlphaAnimation buttonClick = new AlphaAnimation(1F, 0.8F);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_child_screen);
        init();

        ChildSchema child = childSchemaService.getChildSchemaById(childId);
        setChildMetaData(child);

        // TODO: Progress is hardcoded for now, need to figure out the correct point system
        setProgressBar(horizontalProgressBarOne, "1", progressBarOneColor, 50);
        setProgressBar(horizontalProgressBarTwo, "2", progressBarTwoColor, 50);
        setProgressBar(horizontalProgressBarThree, "3", progressBarThreeColor, 50);
        setProgressBar(horizontalProgressBarFour, "4", progressBarFourColor, 50);

        backParentPortal.setOnClickListener(view -> {
            view.startAnimation(buttonClick);
            startActivity(new Intent(childScreen.this, ParentPortal.class));
            this.finish();
        });

        deleteChildButton.setOnClickListener(view -> {
            realm.executeTransactionAsync(realm -> {
                ChildSchema deleteChild = realm.where(ChildSchema.class).equalTo("childId", childId).findFirst();
                deleteChild.deleteFromRealm();

            },()->{
                Intent intent = new Intent(childScreen.this, ParentPortal.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                overridePendingTransition(0, 0);
                startActivity(new Intent(childScreen.this, ParentPortal.class));
                overridePendingTransition(0, 0);
                this.finish();
            }, error -> {
                Log.i("Error", "Could not delete child from realm " + error);
            });
        });

    }

    private void init(){
        realm = Realm.getDefaultInstance();
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String id = extras.getString("childIdParentPortal");
            childId = id;
        }

        childSchemaService = new ChildSchemaService(realm);

        backParentPortal = findViewById(R.id.backParentPortal);
        deleteChildButton = findViewById(R.id.deleteChildButton);
        childAvatar = findViewById(R.id.childAvatar);
        childNameAndScore = findViewById(R.id.childNameAndScore);

        horizontalProgressBarOne = findViewById(R.id.horizontalProgressBarLevelOne);
        horizontalProgressBarTwo = findViewById(R.id.horizontalProgressBarLevelTwo);
        horizontalProgressBarThree = findViewById(R.id.horizontalProgressBarLevelThree);
        horizontalProgressBarFour = findViewById(R.id.horizontalProgressBarLevelFour);

        progressBarOneColor = ContextCompat.getColorStateList(this, R.color.light_green);
        progressBarTwoColor = ContextCompat.getColorStateList(this, R.color.orange);
        progressBarThreeColor = ContextCompat.getColorStateList(this, R.color.blue);
        progressBarFourColor = ContextCompat.getColorStateList(this, R.color.yellow);

        numbers = findViewById(R.id.numbersCompletion);
        length = findViewById(R.id.lengthCompletion);
        unit = findViewById(R.id.unitCompletion);
        weight = findViewById(R.id.weightCompletion);
        addition = findViewById(R.id.additionCompletion);
        money = findViewById(R.id.moneyCompletion);
        subtraction = findViewById(R.id.subtractionCompletion);
        time = findViewById(R.id.timeCompletion);
        multiplication = findViewById(R.id.multiplicationCompletion);
        shapes = findViewById(R.id.shapesCompletion);
        division = findViewById(R.id.divisionCompletion);
        angles = findViewById(R.id.anglesCompletion);

        colorIdentifier = new ColorIdentifier();
        imageSrcIdentifier = new ImageSrcIdentifier();
    }

    @Override
    protected void onDestroy() {
        if (!realm.isClosed()) realm.close();
        super.onDestroy();
    }

    private void setProgressBar(HorizontalProgressBar temp,  CharSequence text, ColorStateList tint, Integer progress){
        temp.setBarLevelText(text);
        temp.setBarLevelColor(tint);
        temp.setHorizontalBarColor(tint);
        temp.setHorizontalBarProgress(progress);
    }

    private void setChildAvatar(String childAvatarName, ColorStateList color){
        childAvatar.setBackgroundTintList(color);
        childAvatar.setImageResource(imageSrcIdentifier.getImageSrcId(childAvatarName));
    }

    private void setChildNameAndScore(String childName, Integer score){
        childNameAndScore.setChildNameText(childName);
        childNameAndScore.setChildScoreText(String.valueOf(score));
    }


    private void setChildMetaData(ChildSchema child){
        ColorStateList color = ContextCompat.getColorStateList(this, colorIdentifier.getColorIdentifier(child.getColorName()));
        String name = child.getName();
        String avatarName = child.getAvatarName();

        //TODO: CHANGE CHILD SCHEMA
        Integer score = 100;
        setChildAvatar(avatarName, color);
        setChildNameAndScore(name, score);
    }
}