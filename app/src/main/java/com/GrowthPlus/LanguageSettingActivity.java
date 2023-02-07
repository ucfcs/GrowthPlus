package com.GrowthPlus;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.GrowthPlus.dataAccessLayer.Language.LanguageSchema;
import com.GrowthPlus.dataAccessLayer.Language.LanguageSchemaService;
import com.GrowthPlus.realmImporter.LanguagesRealmImporter;


import java.io.InputStream;

import io.realm.Realm;

public class LanguageSettingActivity extends AppCompatActivity implements View.OnClickListener {

    private Button backSet;
    private RelativeLayout english;
    private RelativeLayout french;
    private final AlphaAnimation buttonClick = new AlphaAnimation(1F, 0.8F);


    private TextView name;

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
        name = findViewById(R.id.languageText);
    }

    @Override
    public void onClick(View view) {

        view.startAnimation(buttonClick);

        int langView = view.getId();
        ImageView engCheck = findViewById(R.id.englishCheck);
        ImageView freCheck = findViewById(R.id.frenchCheck);

        if(langView == R.id.backLang){
            this.finish();
        }
        if(langView == R.id.englishBtn){

            // set check mark to english button
            engCheck.setVisibility(View.VISIBLE);
            freCheck.setVisibility(View.INVISIBLE);

            setEnglishText();
        }
        if(langView == R.id.frenchBtn){

            // set check mark to french button
            freCheck.setVisibility(View.VISIBLE);
            engCheck.setVisibility(View.INVISIBLE);

            setFrenchText();
        }
    }

    public void setEnglishText(){

        // import english language json file
        InputStream englishInputStream = resources.openRawResource(R.raw.english);
        LanguagesRealmImporter englishRealmImporter = new LanguagesRealmImporter(realm, resources, englishInputStream);
        englishRealmImporter.importLanguagesFromJson();

        // create language schema service and set strings
        LanguageSchemaService englangSchemaService = new LanguageSchemaService(realm, "englishZero");
        LanguageSchema eng = englangSchemaService.getLanguageSchemaById();
        name.setText(eng.getLanguage());
        Log.i("LanguageSettings", eng.getLanguage());

        // Main activity words
        Intent mainIntent = new Intent(LanguageSettingActivity.this, MainActivity.class);
        mainIntent.putExtra("setParent", eng.getParent());
        startActivity(mainIntent);

    }

    public void setFrenchText(){

        // import french language json file
        InputStream frenchInputStream = this.resources.openRawResource(R.raw.french);
        LanguagesRealmImporter frenchRealmImporter = new LanguagesRealmImporter(realm, resources, frenchInputStream);
        frenchRealmImporter.importLanguagesFromJson();

        // create language schema service and set strings
        LanguageSchemaService frelangSchemaService = new LanguageSchemaService(realm, "frenchZero");
        LanguageSchema fre = frelangSchemaService.getLanguageSchemaById();
        name.setText(fre.getLanguage());

        // Main activity words
        Intent mainIntent = new Intent(LanguageSettingActivity.this, MainActivity.class);
        mainIntent.putExtra("setParent", fre.getParent());
        startActivity(mainIntent);
    }


}