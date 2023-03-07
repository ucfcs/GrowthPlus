package com.GrowthPlus;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.GrowthPlus.dataAccessLayer.Language.LanguageSchema;
import com.GrowthPlus.dataAccessLayer.Language.LanguageSchemaService;
import com.GrowthPlus.dataAccessLayer.parent.ParentSchema;
import com.GrowthPlus.dataAccessLayer.parent.ParentSchemaService;

import org.bson.types.ObjectId;

import io.realm.Realm;

public class ParentForgotPassword extends AppCompatActivity implements View.OnClickListener{

    Realm realm;
    Resources resources;
    TextView forgotPasswordTitle, phoneNumberText, yourPinIsText, pinText;
    EditText phoneNumberInput;
    Button getPinButton, backToLoginButton;
    String phoneNumberStr, parentPhoneNumberStr;

    private ParentSchemaService parentService;
    private ParentSchema parent;
    private Integer parentPIN;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activitiy_parent_forgot_password);
        init();

        getPinButton.setOnClickListener(this);
        backToLoginButton.setOnClickListener(this);
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
        backToLoginButton = findViewById(R.id.backToLoginButton);
        backToLoginButton.setVisibility(View.INVISIBLE);
        parentService = new ParentSchemaService(realm);
        parent = parentService.getAllParentSchemas().get(0); //gets the parent,
        parentPIN = parent.getPIN(); //their PIN,
        parentPhoneNumberStr = parent.getPhoneNumber();//and their phone number
    }

//    @Override
//    protected void onResume() {
//        super.onResume();
//
//        // create instance of shared preferences
//        SharedPreferences langPrefs = getSharedPreferences("LangPreferences", MODE_PRIVATE);
//        // create language schema service and set strings
//        LanguageSchemaService langSchemaService = new LanguageSchemaService(realm, langPrefs.getString("languageId", "frenchZero"));
//        LanguageSchema lang = langSchemaService.getLanguageSchemaById();
//
//
//        forgotPasswordTitle.setText(lang.getCreate());
//        phoneNumberText.setText(lang.getConfirm());
//        yourPinIsText.setText(lang.getPhoneNumber());
//        pinText.setHint(lang.getPin());
//        phoneNumberInput.setHint(lang.getPhoneNumber());
//    }

    @Override
    public void onClick(View view) {

        int id = view.getId();

        //if they click the green "play" button
        if(id == R.id.parentGetPinBtn){

            boolean inputValid = validInput(phoneNumberInput);//checks for null and input that's too short

            if(inputValid == true) {
                phoneNumberStr = String.valueOf(phoneNumberInput.getText());

                //if the phone numbers match (and the input was valid)
                if(confirmPhoneNumberMatch(phoneNumberStr, parentPhoneNumberStr) == true){

                    //change the input box back to grey (in case it has been earlier changed to red)
                    phoneNumberInput.setBackgroundTintList(ColorStateList.valueOf(Color.rgb(204, 204, 204)));

                    //show pin
                    pinText.setText(parentPIN.toString());
                    backToLoginButton.setVisibility(View.VISIBLE);
                    getPinButton.setVisibility(View.INVISIBLE);
                }

                else{ //phone numbers don't match -> change input box to red to indicate the phone number is incorrect
                    phoneNumberInput.setBackgroundTintList(ColorStateList.valueOf(Color.rgb(221, 97, 87)));
                    phoneNumberInput.setText("");
                }
            }

            else {//input was not valid -> change input box to red to indicate the phone number is incorrect
                phoneNumberInput.setBackgroundTintList(ColorStateList.valueOf(Color.rgb(221, 97, 87)));
                phoneNumberInput.setText("");
            }

        }//end if parentGetPinButton

        if(id == R.id.backToLoginButton){
            startLoginActivity();
        }


        }//end onClick

    private boolean validInput(EditText input) {
        String inputString = String.valueOf(input.getText());

        if (!inputString.equals(null) &&
                inputString.length() >= 8) {
            return true;
        }
        else{
            return false;
        }
    }

    private boolean confirmPhoneNumberMatch(String phoneNum1, String phoneNum2){

        if(phoneNum1.equals(phoneNum2)) //they match
            return true;

        else //they don't match
            return false;
    }

    //moves to the login page
    public void startLoginActivity(){
        Intent parentLogin = new Intent(ParentForgotPassword.this, ParentLogin.class);
        startActivity(parentLogin);
        this.finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Close the Realm instance.
        realm.close();
    }
}
