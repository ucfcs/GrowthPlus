package com.GrowthPlus;

import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.GrowthPlus.dataAccessLayer.Language.LanguageSchema;
import com.GrowthPlus.dataAccessLayer.Language.LanguageSchemaService;

import org.bson.types.ObjectId;

import io.realm.Realm;

public class ParentForgotPassword extends AppCompatActivity implements View.OnClickListener{

    Realm realm;
    Resources resources;
    TextView forgotPasswordTitle, phoneNumberText, yourPinIsText, pinText;
    EditText phoneNumberInput;
    Button getPinButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activitiy_parent_forgot_password);
        init();
    }

    private void init(){
        realm = Realm.getDefaultInstance();
        resources = getResources();
        forgotPasswordTitle = findViewById(R.id.forgotPasswordTitle);
        phoneNumberText = findViewById(R.id.phoneNumberText);
        yourPinIsText = findViewById(R.id.yourPinIsText);
        pinText = findViewById(R.id.pinText);
        phoneNumberInput = findViewById(R.id.phoneNumberInput);
        getPinButton = findViewById(R.id.parentGetPinBtn);
    }

    @Override
    protected void onResume() {
        super.onResume();

        // create instance of shared preferences
        SharedPreferences langPrefs = getSharedPreferences("LangPreferences", MODE_PRIVATE);
        // create language schema service and set strings
        LanguageSchemaService langSchemaService = new LanguageSchemaService(realm, langPrefs.getString("languageId", "frenchZero"));
        LanguageSchema lang = langSchemaService.getLanguageSchemaById();


        forgotPasswordTitle.setText(lang.getCreate());
        phoneNumberText.setText(lang.getConfirm());
        yourPinIsText.setText(lang.getPhoneNumber());
        pinText.setHint(lang.getPin());
        phoneNumberInput.setHint(lang.getPhoneNumber());
    }

    @Override
    public void onClick(View view) {

        int id = view.getId();

        //if they click the green "play" button
        if(id == R.id.parentGetPinBtn){

        }
    }

}
