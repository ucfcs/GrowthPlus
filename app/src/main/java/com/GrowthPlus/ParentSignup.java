package com.GrowthPlus;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.GrowthPlus.dataAccessLayer.child.ChildSchema;
import com.GrowthPlus.dataAccessLayer.Language.LanguageSchema;
import com.GrowthPlus.dataAccessLayer.Language.LanguageSchemaService;
import com.GrowthPlus.dataAccessLayer.parent.ParentSchemaService;

import io.realm.Realm;
import io.realm.RealmList;

import org.bson.types.ObjectId;

public class ParentSignup extends AppCompatActivity implements View.OnClickListener{

    Realm realm;
    Resources resources;
    private Button signupButton;
    private Button signupBackButton;

    private EditText enterPinInput;
    private EditText confirmPinInput;
    private EditText phoneNumberInput;
    private TextView createText;
    private TextView confirmText;
    private TextView phoneNumberText;
    Integer enterPinInputInteger;
    Integer confirmPinInputInteger;
    String phoneNumString;


    private ObjectId parentId;
    private String parentIdString;

    private ParentSchemaService signupParentService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parent_signup);
        init();

        signupButton.setOnClickListener(this);
        signupBackButton.setOnClickListener(this);

    }

    private void init(){
        realm = Realm.getDefaultInstance();
        resources = getResources();
        signupButton = findViewById(R.id.parentSignupBtn);
        signupBackButton = findViewById(R.id.backParentSU);
        enterPinInput = findViewById(R.id.enterPin);
        confirmPinInput = findViewById(R.id.confirmPinInput);
        phoneNumberInput = findViewById(R.id.phoneNumberInput);
        createText = findViewById(R.id.createPinText);
        confirmText = findViewById(R.id.confirmPinText);
        phoneNumberText = findViewById(R.id.phoneNumberText);
        parentId = new ObjectId();
        parentIdString = parentId.toString();
    }

    @Override
    protected void onResume() {
        super.onResume();

        // create instance of shared preferences
        SharedPreferences langPrefs = getSharedPreferences("LangPreferences", MODE_PRIVATE);
        // create language schema service and set strings
        LanguageSchemaService langSchemaService = new LanguageSchemaService(realm, langPrefs.getString("languageId", "frenchZero"));
        LanguageSchema lang = langSchemaService.getLanguageSchemaById();

        createText.setText(lang.getCreate());
        confirmText.setText(lang.getConfirm());
        phoneNumberText.setText(lang.getPhoneNumber());
        enterPinInput.setHint(lang.getPin());
        confirmPinInput.setHint(lang.getPin());
        phoneNumberInput.setHint(lang.getPhoneNumber());
    }

    @Override
    public void onClick(View view) {

        int id = view.getId();

        //if they click the signup button
        if(id == R.id.parentSignupBtn){

            //check for null input or input that is too short
            boolean inputValid = validInput(enterPinInput, confirmPinInput, phoneNumberInput);

            if(inputValid == true){
                //track what the user entered
                enterPinInputInteger = Integer.parseInt(enterPinInput.getText().toString());
                confirmPinInputInteger = Integer.parseInt(confirmPinInput.getText().toString());
                phoneNumString = phoneNumberInput.getText().toString();

                //if the PIN's match, start the parent portal activity
                if(confirmPinMatch(enterPinInputInteger, confirmPinInputInteger) == true){
                    //create a parent with the pin
                    createParent();

                    //move on to the login screen
                    startLoginActivity();
                }

                else{//PIN's don't match -> display a toast
                    Context context = getApplicationContext();
                    CharSequence text = "PINs do not match! Please try typing them again.";
                    int duration = Toast.LENGTH_SHORT;
                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                }
            }

            else { //input was not valid -> display a toast
                Context context = getApplicationContext();
                CharSequence text = "Please enter a 4-digit number for both PIN's, and a 10-digit " +
                        "number for the phone number.";
                int duration = Toast.LENGTH_SHORT;
                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
            }
        }

        //if they click the back button, go to the main landing page
        if(id == R.id.backParentSU){
            startMainActivity();
        }

        enterPinInput.setText("");
        confirmPinInput.setText("");
        phoneNumberInput.setText("");//clears the EditText(s)

    }

    private boolean validInput(EditText pin1, EditText pin2, EditText phoneNum) {
        String pin1String = pin1.getText().toString();
        String pin2String = pin2.getText().toString();
        String phoneNumStr = phoneNum.getText().toString();

        if(!pin1String.equals(null) &&
           !pin2String.equals(null) &&
           !phoneNumStr.equals(null) &&
           pin1String.length() == 4 &&
           pin2String.length() == 4 &&
           phoneNumStr.length() >= 10)
        {
            return true;
        }
        else{
            return false;
        }
    }

    //simple method to see if two pins match
    private boolean confirmPinMatch(Integer pin1, Integer pin2){
        if(pin1.equals(pin2))
            return true;
        else
            return false;
    }

    //moves to the MainActivity page
    public void startMainActivity(){
        Intent mainActivity = new Intent(ParentSignup.this, MainActivity.class);
        startActivity(mainActivity);
        this.finish();
    }

    //moves to the login page, passing over the parentId
    public void startLoginActivity(){
        Intent parentLogin = new Intent(ParentSignup.this, ParentLogin.class);
        startActivity(parentLogin);
        this.finish();
    }

    //since the signup page involves about creating an account, we need a method to create a parent
    private void createParent(){
        RealmList <ChildSchema> children = new RealmList<>();
        signupParentService = new ParentSchemaService(realm, parentIdString, confirmPinInputInteger, phoneNumString, children);
        signupParentService.createParentSchema();
    }
}