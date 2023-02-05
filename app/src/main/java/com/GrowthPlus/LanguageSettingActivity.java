package com.GrowthPlus;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.GrowthPlus.dataAccessLayer.child.ChildSchema;
import com.GrowthPlus.dataAccessLayer.child.ChildSchemaService;
import com.GrowthPlus.roadMapActivity.RoadMapOne;
import com.GrowthPlus.utilities.ImageSrcIdentifier;

import io.realm.Realm;

public class LanguageSettingActivity extends AppCompatActivity implements View.OnClickListener {

    private Button backSet;
    private RelativeLayout english;
    private RelativeLayout french;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_language_setting);
        init();

        backSet.setOnClickListener(this);
        english.setOnClickListener(this);
        french.setOnClickListener(this);
    }

//    private ImageView learnButton;
//    private ImageView leaderBoardBtn;
//    private Button backBtn;
//    private TextView childPortalName;
//    private TextView pointsNumber;
//    private String childId;
//    private ChildSchemaService childSchemaService;
//    private Realm realm;
//    private ImageSrcIdentifier imageSrcIdentifier;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_child_portal);
//        init();
//
//        ChildSchema child = childSchemaService.getChildSchemaById(childId);
//
//        childPortalName.setText(child.getName());
//        learnButton.setImageResource(imageSrcIdentifier.getImageSrcId(child.getAvatarName()));
//        leaderBoardBtn.setImageResource(imageSrcIdentifier.getImageSrcId(child.getAvatarName()));
//
//        backBtn.setOnClickListener(this);
//        leaderBoardBtn.setOnClickListener(this);
//        learnButton.setOnClickListener(this);
//    }
//
    public void init(){

        backSet = findViewById(R.id.backLang);
        english = findViewById(R.id.englishBtn);
        french = findViewById(R.id.frenchBtn);
    }


    @Override
    public void onClick(View view) {

        int langView = view.getId();
        ImageView engCheck = (ImageView) findViewById(R.id.englishCheck);
        ImageView freCheck = (ImageView) findViewById(R.id.frenchCheck);

        if(langView == R.id.backLang){
            this.finish();
        }
        if(langView == R.id.englishBtn){
            engCheck.setVisibility(View.VISIBLE);
            freCheck.setVisibility(View.INVISIBLE);

        }
        if(langView == R.id.frenchBtn){
            freCheck.setVisibility(View.VISIBLE);
            engCheck.setVisibility(View.INVISIBLE);
        }
    }
}