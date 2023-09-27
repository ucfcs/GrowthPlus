package com.GrowthPlus;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.WindowManager;
import android.widget.TextView;

import com.GrowthPlus.dataAccessLayer.Language.LanguageSchema;
import com.GrowthPlus.dataAccessLayer.Language.LanguageSchemaService;
import com.GrowthPlus.realmImporter.LanguagesRealmImporter;

import java.io.InputStream;

import io.realm.Realm;

public class SplashScreen extends AppCompatActivity {
    private Realm realm;
    private Resources resources;
    private TextView appName;
    SharedPreferences settings;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        init();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        if(settings.getBoolean("firstTime", true)){
            Log.d("Comments", "This is first time");

            new Handler().postDelayed(() -> {
                Intent view = new Intent(SplashScreen.this, LanguageSettingActivity.class);
                startActivity(view);
                finish();
            }, 4000);
        }
        else{
            new Handler().postDelayed(() -> {
                Intent view = new Intent(SplashScreen.this, MainActivity.class);
                startActivity(view);
                finish();
            }, 4000);
        }
    }

    public void init(){
        realm = Realm.getDefaultInstance();
        resources = getResources();
        appName = findViewById(R.id.appName);
        settings = getSharedPreferences("MyPrefsFile", 0);
    }

    @Override
    protected void onResume(){
        super.onResume();

        // create instance of shared preferences
        SharedPreferences langPrefs = getSharedPreferences("LangPreferences", MODE_PRIVATE);

        // import language json file
        InputStream langInputStream = resources.openRawResource(R.raw.french);
        LanguagesRealmImporter langRealmImporter = new LanguagesRealmImporter(realm, resources, langInputStream);
        langRealmImporter.importLanguagesFromJson();
        // create language schema service and set strings
        LanguageSchemaService langSchemaService = new LanguageSchemaService(realm, langPrefs.getString("languageId", "frenchZero"));
        LanguageSchema lang = langSchemaService.getLanguageSchemaById();
        if(lang != null){
            appName.setText(lang.getGrowthPlus());
        }
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        realm.close();
    }
}