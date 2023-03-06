package com.GrowthPlus;

import android.content.Context;
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
import android.widget.Toast;

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
    Button getPinButton;
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

            Log.i("in the if statement", String.valueOf(id));
            boolean inputValid = validInput(phoneNumberInput);//checks for null and input that's too short

            if(inputValid == true) {
                phoneNumberStr = String.valueOf(phoneNumberInput.getText());

                //if the phone numbers match and the input was valid
                if(confirmPhoneNumberMatch(phoneNumberStr, parentPhoneNumberStr) == true){
                    Log.i("phone numbers match", phoneNumberStr);
                    Log.i("parent pin", parentPIN.toString());
                    //show pin
                    pinText.setText(parentPIN.toString());
                }

                else{ //phone numbers don't match -> display a toast
                    Context context = getApplicationContext();
                    CharSequence text = "That is not the correct phone number. Please try typing it again.";
                    int duration = Toast.LENGTH_SHORT;
                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                }
            }

            else {//input was not valid -> display a toast
                Context context = getApplicationContext();
                CharSequence text = "Please enter a 10 or 11-digit number for the PIN.";
                int duration = Toast.LENGTH_SHORT;
                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
            }
        }
    }

    private boolean validInput(EditText input) {
        String inputString = String.valueOf(input.getText());

        if (!inputString.equals(null) &&
                inputString.length() >= 10) {
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

}
