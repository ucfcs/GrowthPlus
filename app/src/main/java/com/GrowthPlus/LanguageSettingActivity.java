package com.GrowthPlus;

import androidx.appcompat.app.AppCompatActivity;

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

    private Button backSet;
    private RelativeLayout english;
    private RelativeLayout french;
    private RelativeLayout chad;
    private ImageView engCheck;
    private ImageView freCheck;
    private ImageView chadCheck;
    private final AlphaAnimation buttonClick = new AlphaAnimation(1F, 0.8F);

    private TextView name;
    private TextView englishText;
    private TextView frenchText;
    private TextView chadText;

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
        chad.setOnClickListener(this);
    }

    public void init(){
        realm = Realm.getDefaultInstance();
        resources = getResources();
        backSet = findViewById(R.id.backLang);
        englishText = findViewById(R.id.englishText);
        frenchText = findViewById(R.id.frenchText);
        chadText = findViewById(R.id.chadText);
        engCheck = findViewById(R.id.englishCheck);
        freCheck = findViewById(R.id.frenchCheck);
        chadCheck = findViewById(R.id.chadCheck);
        english = findViewById(R.id.englishBtn);
        french = findViewById(R.id.frenchBtn);
        chad = findViewById(R.id.chadBtn);
        name = findViewById(R.id.languageText);
    }

    @Override
    public void onClick(View view) {

        view.startAnimation(buttonClick);

        int langView = view.getId();

        if(langView == R.id.backLang){
            this.finish();
        }
        if(langView == R.id.englishBtn){
            engCheck.setVisibility(View.VISIBLE);
            freCheck.setVisibility(View.INVISIBLE);
            chadCheck.setVisibility(View.INVISIBLE);
            setEnglishText();
        }
        if(langView == R.id.frenchBtn){
            freCheck.setVisibility(View.VISIBLE);
            engCheck.setVisibility(View.INVISIBLE);
            chadCheck.setVisibility(View.INVISIBLE);
            setFrenchText();
        }
        if(langView == R.id.chadBtn){
            chadCheck.setVisibility(View.VISIBLE);
            freCheck.setVisibility(View.INVISIBLE);
            engCheck.setVisibility(View.INVISIBLE);
            setChadText();
        }
    }

    public void setEnglishText(){

        // Create shared preferences class to save default language, french
        SharedPreferences mPrefs = getSharedPreferences("LangPreferences", MODE_PRIVATE);

        // Add english as default lang
        SharedPreferences.Editor prefsEditor = mPrefs.edit();
        prefsEditor.putString("languageId", "englishZero");
        prefsEditor.commit();
        // refresh UI
        onResume();

    }

    public void setFrenchText(){

        // Create shared preferences class to save default language, french
        SharedPreferences mPrefs = getSharedPreferences("LangPreferences", MODE_PRIVATE);

        // Add english as default lang
        SharedPreferences.Editor prefsEditor = mPrefs.edit();
        prefsEditor.putString("languageId", "frenchZero");
        prefsEditor.commit();
        // refresh UI
        onResume();

    }

    public void setChadText(){

        // Create shared preferences class to save default language, french
        SharedPreferences mPrefs = getSharedPreferences("LangPreferences", MODE_PRIVATE);

        // Add english as default lang
        SharedPreferences.Editor prefsEditor = mPrefs.edit();
        prefsEditor.putString("languageId", "chadianArabicZero");
        prefsEditor.commit();
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
        if(langId.equals("englishZero")){
            engCheck.setVisibility(View.VISIBLE);
            freCheck.setVisibility(View.INVISIBLE);
            chadCheck.setVisibility(View.INVISIBLE);
        }
        else if(langId.equals("frenchZero")){
            freCheck.setVisibility(View.VISIBLE);
            engCheck.setVisibility(View.INVISIBLE);
            chadCheck.setVisibility(View.INVISIBLE);
        }
        else if(langId.equals("chadianArabicZero")){
            chadCheck.setVisibility(View.VISIBLE);
            engCheck.setVisibility(View.INVISIBLE);
            freCheck.setVisibility(View.INVISIBLE);
        }
    }


}