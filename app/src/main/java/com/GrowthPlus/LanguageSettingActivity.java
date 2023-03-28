package com.GrowthPlus;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.GrowthPlus.dataAccessLayer.Language.LanguageSchema;
import com.GrowthPlus.dataAccessLayer.Language.LanguageSchemaService;

import io.realm.Realm;

public class LanguageSettingActivity extends AppCompatActivity implements View.OnClickListener {
    private Button backSet, firstTime;
    private RelativeLayout english, french, chad, lagwan;
    private ImageView engCheck, freCheck, chadCheck, lagwanCheck;
    private final AlphaAnimation buttonClick = new AlphaAnimation(1F, 0.8F);
    private TextView name, englishText, frenchText, chadText, lagwanText;
    Realm realm;
    Resources resources;
    SharedPreferences settings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_language_setting);
        init();

        if(settings.getBoolean("firstTime", true)){
            settings.edit().putBoolean("firstTime", false).apply();
            backSet.setVisibility(View.INVISIBLE);

            firstTime.setOnClickListener(v -> {
                Intent view = new Intent(LanguageSettingActivity.this, MainActivity.class);
                startActivity(view);
                finish();
            });
        }
        else{
            firstTime.setVisibility(View.INVISIBLE);
        }

        backSet.setOnClickListener(this);
        english.setOnClickListener(this);
        french.setOnClickListener(this);
        chad.setOnClickListener(this);
        lagwan.setOnClickListener(this);
    }

    public void init(){
        realm = Realm.getDefaultInstance();
        resources = getResources();
        backSet = findViewById(R.id.backLang);
        englishText = findViewById(R.id.englishText);
        frenchText = findViewById(R.id.frenchText);
        chadText = findViewById(R.id.chadText);
        lagwanText = findViewById(R.id.lagwanText);
        engCheck = findViewById(R.id.englishCheck);
        freCheck = findViewById(R.id.frenchCheck);
        chadCheck = findViewById(R.id.chadCheck);
        lagwanCheck = findViewById(R.id.lagwanCheck);
        english = findViewById(R.id.englishBtn);
        french = findViewById(R.id.frenchBtn);
        chad = findViewById(R.id.chadBtn);
        lagwan = findViewById(R.id.lagwanBtn);
        name = findViewById(R.id.languageText);
        firstTime = findViewById(R.id.firstTime);
        settings = getSharedPreferences("MyPrefsFile", 0);
    }

    @Override
    public void onClick(View view) {
        view.startAnimation(buttonClick);
        int langView = view.getId();

        if(langView == R.id.backLang){
            this.finish();
        }
        else if(langView == R.id.englishBtn){
            engCheck.setVisibility(View.VISIBLE);
            freCheck.setVisibility(View.INVISIBLE);
            chadCheck.setVisibility(View.INVISIBLE);
            lagwanCheck.setVisibility(View.INVISIBLE);
            setEnglishText();
        }
        else if(langView == R.id.frenchBtn){
            freCheck.setVisibility(View.VISIBLE);
            engCheck.setVisibility(View.INVISIBLE);
            chadCheck.setVisibility(View.INVISIBLE);
            lagwanCheck.setVisibility(View.INVISIBLE);
            setFrenchText();
        }
        else if(langView == R.id.chadBtn){
            chadCheck.setVisibility(View.VISIBLE);
            freCheck.setVisibility(View.INVISIBLE);
            engCheck.setVisibility(View.INVISIBLE);
            lagwanCheck.setVisibility(View.INVISIBLE);
            setChadText();
        }
        else if(langView == R.id.lagwanBtn){
            lagwanCheck.setVisibility(View.VISIBLE);
            chadCheck.setVisibility(View.INVISIBLE);
            freCheck.setVisibility(View.INVISIBLE);
            engCheck.setVisibility(View.INVISIBLE);
            setLagwanText();
        }
    }

    public void setEnglishText(){
        // Create shared preferences class to save default language, french
        SharedPreferences mPrefs = getSharedPreferences("LangPreferences", MODE_PRIVATE);
        // Add english as default lang
        SharedPreferences.Editor prefsEditor = mPrefs.edit();
        prefsEditor.putString("languageId", "englishZero");
        prefsEditor.apply();
        // refresh UI
        onResume();
    }

    public void setFrenchText(){
        // Create shared preferences class to save default language, french
        SharedPreferences mPrefs = getSharedPreferences("LangPreferences", MODE_PRIVATE);
        // Add english as default lang
        SharedPreferences.Editor prefsEditor = mPrefs.edit();
        prefsEditor.putString("languageId", "frenchZero");
        prefsEditor.apply();
        // refresh UI
        onResume();
    }

    public void setChadText(){
        // Create shared preferences class to save default language, french
        SharedPreferences mPrefs = getSharedPreferences("LangPreferences", MODE_PRIVATE);
        // Add english as default lang
        SharedPreferences.Editor prefsEditor = mPrefs.edit();
        prefsEditor.putString("languageId", "chadianArabicZero");
        prefsEditor.apply();
        // refresh UI
        onResume();
    }

    public void setLagwanText(){
        // Create shared preferences class to save default language, french
        SharedPreferences mPrefs = getSharedPreferences("LangPreferences", MODE_PRIVATE);
        // Add english as default lang
        SharedPreferences.Editor prefsEditor = mPrefs.edit();
        prefsEditor.putString("languageId", "lagwanZero");
        prefsEditor.apply();
        // refresh UI
        onResume();
    }

    @Override
    protected void onResume() {
        super.onResume();

        // create instance of shared preferences
        SharedPreferences langPrefs = getSharedPreferences("LangPreferences", MODE_PRIVATE);
        String langId = langPrefs.getString("languageId", "frenchZero");
        // create language schema service and set strings
        LanguageSchemaService langSchemaService = new LanguageSchemaService(realm, langPrefs.getString("languageId", langId));
        LanguageSchema lang = langSchemaService.getLanguageSchemaById();

        name.setText(lang.getLanguage());
        englishText.setText(lang.getEnglish());
        frenchText.setText(lang.getFrench());
        chadText.setText(lang.getChadianArabic());
        lagwanText.setText(lang.getLagwan());
        switch(langId){
            case "englishZero":
                engCheck.setVisibility(View.VISIBLE);
                freCheck.setVisibility(View.INVISIBLE);
                chadCheck.setVisibility(View.INVISIBLE);
                lagwanCheck.setVisibility(View.INVISIBLE);
                break;
            case "frenchZero":
                freCheck.setVisibility(View.VISIBLE);
                engCheck.setVisibility(View.INVISIBLE);
                chadCheck.setVisibility(View.INVISIBLE);
                lagwanCheck.setVisibility(View.INVISIBLE);
                break;
            case "chadianArabicZero":
                chadCheck.setVisibility(View.VISIBLE);
                engCheck.setVisibility(View.INVISIBLE);
                freCheck.setVisibility(View.INVISIBLE);
                lagwanCheck.setVisibility(View.INVISIBLE);
                break;
            case "lagwanZero":
                lagwanCheck.setVisibility(View.VISIBLE);
                chadCheck.setVisibility(View.INVISIBLE);
                engCheck.setVisibility(View.INVISIBLE);
                freCheck.setVisibility(View.INVISIBLE);
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        realm.close();
    }
}