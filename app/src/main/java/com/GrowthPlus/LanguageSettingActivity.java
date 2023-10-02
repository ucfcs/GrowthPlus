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
import com.GrowthPlus.dataAccessLayer.child.ChildSchema;
import com.GrowthPlus.dataAccessLayer.parent.ParentSchema;
import com.GrowthPlus.realmImporter.LanguagesRealmImporter;

import java.io.InputStream;

import io.realm.Realm;
import io.realm.RealmList;

import android.os.Handler;

public class LanguageSettingActivity extends AppCompatActivity implements View.OnClickListener {
    private Button backSet, firstTime;
    private RelativeLayout english, french, chad, lagwan, mousgoum, massana, musey;
    private ImageView engCheck, freCheck, chadCheck, lagwanCheck, mousgoumCheck, massanaCheck, museyCheck;
    private final AlphaAnimation buttonClick = new AlphaAnimation(1F, 0.8F);
    private TextView name, englishText, frenchText, chadText, lagwanText, mousgoumText, massanaText, museyText;
    Realm realm;
    Resources resources;
    SharedPreferences settings;
    Handler handler;

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
        mousgoum.setOnClickListener(this);
        massana.setOnClickListener(this);
        musey.setOnClickListener(this);
    }

    public void init(){
        realm = Realm.getDefaultInstance();
        resources = getResources();
        handler = new Handler();
        backSet = findViewById(R.id.backLang);
        englishText = findViewById(R.id.englishText);
        frenchText = findViewById(R.id.frenchText);
        chadText = findViewById(R.id.chadText);
        lagwanText = findViewById(R.id.lagwanText);
        mousgoumText = findViewById(R.id.mousgoumText);
        massanaText = findViewById(R.id.massanaText);
        museyText = findViewById(R.id.museyText);
        engCheck = findViewById(R.id.englishCheck);
        freCheck = findViewById(R.id.frenchCheck);
        chadCheck = findViewById(R.id.chadCheck);
        lagwanCheck = findViewById(R.id.lagwanCheck);
        mousgoumCheck = findViewById(R.id.mousgoumCheck);
        massanaCheck = findViewById(R.id.massanaCheck);
        museyCheck = findViewById(R.id.museyCheck);
        english = findViewById(R.id.englishBtn);
        french = findViewById(R.id.frenchBtn);
        chad = findViewById(R.id.chadBtn);
        lagwan = findViewById(R.id.lagwanBtn);
        mousgoum = findViewById(R.id.mousgoumBtn);
        massana = findViewById(R.id.massanaBtn);
        musey = findViewById(R.id.museyBtn);
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
            handler.removeCallbacksAndMessages(null);
        }
        else if(langView == R.id.englishBtn){
            engCheck.setVisibility(View.VISIBLE);
            freCheck.setVisibility(View.INVISIBLE);
            chadCheck.setVisibility(View.INVISIBLE);
            lagwanCheck.setVisibility(View.INVISIBLE);
            mousgoumCheck.setVisibility(View.INVISIBLE);
            massanaCheck.setVisibility(View.INVISIBLE);
            museyCheck.setVisibility(View.INVISIBLE);
            setEnglishText();
        }
        else if(langView == R.id.frenchBtn){
            freCheck.setVisibility(View.VISIBLE);
            engCheck.setVisibility(View.INVISIBLE);
            chadCheck.setVisibility(View.INVISIBLE);
            lagwanCheck.setVisibility(View.INVISIBLE);
            mousgoumCheck.setVisibility(View.INVISIBLE);
            massanaCheck.setVisibility(View.INVISIBLE);
            museyCheck.setVisibility(View.INVISIBLE);
            setFrenchText();
        }
        else if(langView == R.id.chadBtn){
            chadCheck.setVisibility(View.VISIBLE);
            freCheck.setVisibility(View.INVISIBLE);
            engCheck.setVisibility(View.INVISIBLE);
            lagwanCheck.setVisibility(View.INVISIBLE);
            mousgoumCheck.setVisibility(View.INVISIBLE);
            massanaCheck.setVisibility(View.INVISIBLE);
            museyCheck.setVisibility(View.INVISIBLE);
            setChadText();
        }
        else if(langView == R.id.lagwanBtn){
            lagwanCheck.setVisibility(View.VISIBLE);
            chadCheck.setVisibility(View.INVISIBLE);
            freCheck.setVisibility(View.INVISIBLE);
            engCheck.setVisibility(View.INVISIBLE);
            mousgoumCheck.setVisibility(View.INVISIBLE);
            massanaCheck.setVisibility(View.INVISIBLE);
            museyCheck.setVisibility(View.INVISIBLE);
            setLagwanText();
        }
        else if(langView == R.id.mousgoumBtn){
            mousgoumCheck.setVisibility(View.VISIBLE);
            chadCheck.setVisibility(View.INVISIBLE);
            freCheck.setVisibility(View.INVISIBLE);
            engCheck.setVisibility(View.INVISIBLE);
            lagwanCheck.setVisibility(View.INVISIBLE);
            massanaCheck.setVisibility(View.INVISIBLE);
            museyCheck.setVisibility(View.INVISIBLE);
            setMousgoumText();
        }
        else if(langView == R.id.massanaBtn){
            massanaCheck.setVisibility(View.VISIBLE);
            chadCheck.setVisibility(View.INVISIBLE);
            freCheck.setVisibility(View.INVISIBLE);
            engCheck.setVisibility(View.INVISIBLE);
            lagwanCheck.setVisibility(View.INVISIBLE);
            mousgoumCheck.setVisibility(View.INVISIBLE);
            museyCheck.setVisibility(View.INVISIBLE);
            setMassanaText();
        }
        else if(langView == R.id.museyBtn){
            museyCheck.setVisibility(View.VISIBLE);
            chadCheck.setVisibility(View.INVISIBLE);
            freCheck.setVisibility(View.INVISIBLE);
            engCheck.setVisibility(View.INVISIBLE);
            lagwanCheck.setVisibility(View.INVISIBLE);
            massanaCheck.setVisibility(View.INVISIBLE);
            mousgoumCheck.setVisibility(View.INVISIBLE);
            setMuseyText();
        }
    }

    public void setEnglishText(){
        // Create shared preferences class to save default language, french
        SharedPreferences mPrefs = getSharedPreferences("LangPreferences", MODE_PRIVATE);
        // Add english as default lang
        SharedPreferences.Editor prefsEditor = mPrefs.edit();
        prefsEditor.putString("languageId", "englishZero");
        prefsEditor.apply();
        updateLanguageJsonInRealm("englishZero");
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
        updateLanguageJsonInRealm("frenchZero");
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
        updateLanguageJsonInRealm("chadianArabicZero");
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
        updateLanguageJsonInRealm("lagwanZero");
        // refresh UI
        onResume();
    }

    public void setMousgoumText(){
        // Create shared preferences class to save default language, french
        SharedPreferences mPrefs = getSharedPreferences("LangPreferences", MODE_PRIVATE);
        // Add english as default lang
        SharedPreferences.Editor prefsEditor = mPrefs.edit();
        prefsEditor.putString("languageId", "mousgoumZero");
        prefsEditor.apply();
        updateLanguageJsonInRealm("mousgoumZero");
        // refresh UI
        onResume();
    }

    public void setMassanaText(){
        // Create shared preferences class to save default language, french
        SharedPreferences mPrefs = getSharedPreferences("LangPreferences", MODE_PRIVATE);
        // Add english as default lang
        SharedPreferences.Editor prefsEditor = mPrefs.edit();
        prefsEditor.putString("languageId", "massanaZero");
        prefsEditor.apply();
        updateLanguageJsonInRealm("massanaZero");
        // refresh UI
        onResume();
    }

    public void setMuseyText(){
        // Create shared preferences class to save default language, french
        SharedPreferences mPrefs = getSharedPreferences("LangPreferences", MODE_PRIVATE);
        // Add english as default lang
        SharedPreferences.Editor prefsEditor = mPrefs.edit();
        prefsEditor.putString("languageId", "museyZero");
        prefsEditor.apply();
        updateLanguageJsonInRealm("museyZero");
        // refresh UI
        onResume();
    }

    @Override
    protected void onResume() {
        super.onResume();

        // create instance of shared preferences
        SharedPreferences langPrefs = getSharedPreferences("LangPreferences", MODE_PRIVATE);
        String langId = langPrefs.getString("languageId", "frenchZero");
        updateLanguageJsonInRealm(langId);
        // create language schema service and set strings
        LanguageSchemaService langSchemaService = new LanguageSchemaService(realm, langPrefs.getString("languageId", langId));
        LanguageSchema lang = langSchemaService.getLanguageSchemaById();

        name.setText(lang.getLanguage());
        englishText.setText(lang.getEnglish());
        frenchText.setText(lang.getFrench());
        chadText.setText(lang.getChadianArabic());
        lagwanText.setText(lang.getLagwan());
        mousgoumText.setText(lang.getMousgoum());
        massanaText.setText(lang.getMassana());
        museyText.setText(lang.getMusey());
        switch(langId){
            case "englishZero":
                engCheck.setVisibility(View.VISIBLE);
                freCheck.setVisibility(View.INVISIBLE);
                chadCheck.setVisibility(View.INVISIBLE);
                lagwanCheck.setVisibility(View.INVISIBLE);
                mousgoumCheck.setVisibility(View.INVISIBLE);
                massanaCheck.setVisibility(View.INVISIBLE);
                museyCheck.setVisibility(View.INVISIBLE);
                break;
            case "frenchZero":
                freCheck.setVisibility(View.VISIBLE);
                engCheck.setVisibility(View.INVISIBLE);
                chadCheck.setVisibility(View.INVISIBLE);
                lagwanCheck.setVisibility(View.INVISIBLE);
                mousgoumCheck.setVisibility(View.INVISIBLE);
                massanaCheck.setVisibility(View.INVISIBLE);
                museyCheck.setVisibility(View.INVISIBLE);
                break;
            case "chadianArabicZero":
                chadCheck.setVisibility(View.VISIBLE);
                engCheck.setVisibility(View.INVISIBLE);
                freCheck.setVisibility(View.INVISIBLE);
                lagwanCheck.setVisibility(View.INVISIBLE);
                mousgoumCheck.setVisibility(View.INVISIBLE);
                massanaCheck.setVisibility(View.INVISIBLE);
                museyCheck.setVisibility(View.INVISIBLE);
                break;
            case "lagwanZero":
                lagwanCheck.setVisibility(View.VISIBLE);
                chadCheck.setVisibility(View.INVISIBLE);
                engCheck.setVisibility(View.INVISIBLE);
                freCheck.setVisibility(View.INVISIBLE);
                mousgoumCheck.setVisibility(View.INVISIBLE);
                massanaCheck.setVisibility(View.INVISIBLE);
                museyCheck.setVisibility(View.INVISIBLE);
                break;
            case "mousgoumZero":
                mousgoumCheck.setVisibility(View.VISIBLE);
                chadCheck.setVisibility(View.INVISIBLE);
                engCheck.setVisibility(View.INVISIBLE);
                freCheck.setVisibility(View.INVISIBLE);
                lagwanCheck.setVisibility(View.INVISIBLE);
                massanaCheck.setVisibility(View.INVISIBLE);
                museyCheck.setVisibility(View.INVISIBLE);
                break;
            case "massanaZero":
                massanaCheck.setVisibility(View.VISIBLE);
                chadCheck.setVisibility(View.INVISIBLE);
                engCheck.setVisibility(View.INVISIBLE);
                freCheck.setVisibility(View.INVISIBLE);
                lagwanCheck.setVisibility(View.INVISIBLE);
                mousgoumCheck.setVisibility(View.INVISIBLE);
                museyCheck.setVisibility(View.INVISIBLE);
                break;
            case "museyZero":
                museyCheck.setVisibility(View.VISIBLE);
                chadCheck.setVisibility(View.INVISIBLE);
                engCheck.setVisibility(View.INVISIBLE);
                freCheck.setVisibility(View.INVISIBLE);
                lagwanCheck.setVisibility(View.INVISIBLE);
                massanaCheck.setVisibility(View.INVISIBLE);
                mousgoumCheck.setVisibility(View.INVISIBLE);
                break;
        }
    }

    private void updateLanguageJsonInRealm(String lang){


        if(lang.equals("frenchZero")){
            realm.executeTransactionAsync(realm1 -> {
                LanguageSchema langRealm = realm1.where(LanguageSchema.class).findFirst();
                assert langRealm != null;
                langRealm.deleteFromRealm();
            });
            InputStream langInputStream = resources.openRawResource(R.raw.french);
            LanguagesRealmImporter langRealmImporter = new LanguagesRealmImporter(realm, resources, langInputStream);
            langRealmImporter.importLanguagesFromJson();
        }
        else{
            realm.executeTransactionAsync(realm1 -> {
                LanguageSchema langRealm = realm1.where(LanguageSchema.class).findFirst();
                assert langRealm != null;
                langRealm.deleteFromRealm();
            });
            InputStream langInputStream = resources.openRawResource(R.raw.languages);
            LanguagesRealmImporter langRealmImporter = new LanguagesRealmImporter(realm, resources, langInputStream);
            langRealmImporter.importLanguagesFromJson();
        }



        // import language json file
//        InputStream langInputStream = resources.openRawResource(R.raw.french);
//        LanguagesRealmImporter langRealmImporter = new LanguagesRealmImporter(realm, resources, langInputStream);
//        langRealmImporter.importLanguagesFromJson();

//        realm.executeTransactionAsync(realm -> {
//                    ParentSchema parentDel = realm.where(ParentSchema.class).equalTo("parentId", parentId).findFirst();
//                    assert parentDel != null;
//                    RealmList<ChildSchema> children = parentDel.getChildren();
//                    if(children.size()>0){
//                        children.deleteAllFromRealm();
//                    }
//                    parentDel.deleteFromRealm();
//                }


//            ChildSchema child = realm1.where(ChildSchema.class).equalTo("childId", childId).findFirst();
//            assert child != null;
//            child.setScore(childScore);
//            child.getRoadMapOne().getScenarioGame().setCurrentPoints(gameScore);

    }

    @Override
    protected void onDestroy() {
        if (!realm.isClosed()) realm.close();
        handler.removeCallbacksAndMessages(null);
        super.onDestroy();
    }
}