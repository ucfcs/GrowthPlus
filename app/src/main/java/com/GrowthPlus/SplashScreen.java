package com.GrowthPlus;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Handler;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        init();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent view = new Intent(SplashScreen.this, MainActivity.class);
                startActivity(view);
                finish();
            }
        }, 3000);
    }

    public void init(){
        realm = Realm.getDefaultInstance();
        resources = getResources();
        appName = findViewById(R.id.appName);
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
        if(lang != null)
        {
            appName.setText(lang.getGrowthPlus());
        }

    }

}