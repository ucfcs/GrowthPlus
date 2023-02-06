package com.GrowthPlus;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.GrowthPlus.dataAccessLayer.child.ChildSchema;
import com.GrowthPlus.dataAccessLayer.child.ChildSchemaService;
import com.GrowthPlus.realmImporter.LanguagesRealmImporter;
import com.GrowthPlus.roadMapActivity.RoadMapOne;
import com.GrowthPlus.utilities.ImageSrcIdentifier;

import java.io.InputStream;

import io.realm.Realm;

public class LanguageSettingActivity extends AppCompatActivity implements View.OnClickListener {

    private Button backSet;
    private RelativeLayout english;
    private RelativeLayout french;

    Realm realm;
    Resources resources;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_language_setting);
        init();

        backSet.setOnClickListener(this);
        english.setOnClickListener(this);
        french.setOnClickListener(this);
    }

    public void init(){
        realm = Realm.getDefaultInstance();
        resources = getResources();
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

           //  import english language json file
            InputStream englishInputStream = resources.openRawResource(R.raw.english);
            LanguagesRealmImporter englishRealmImporter = new LanguagesRealmImporter(realm, resources, englishInputStream);
            englishRealmImporter.importLanguagesFromJson();

        }
        if(langView == R.id.frenchBtn){
            freCheck.setVisibility(View.VISIBLE);
            engCheck.setVisibility(View.INVISIBLE);

            // import french language json file
            InputStream frenchInputStream = this.resources.openRawResource(R.raw.french);
            LanguagesRealmImporter frenchRealmImporter = new LanguagesRealmImporter(realm, resources, frenchInputStream);
            frenchRealmImporter.importLanguagesFromJson();
        }
    }
}