package com.GrowthPlus;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.GrowthPlus.dataAccessLayer.Language.LanguageSchema;
import com.GrowthPlus.dataAccessLayer.Language.LanguageSchemaService;
import com.GrowthPlus.dataAccessLayer.parent.ParentSchema;
import com.GrowthPlus.dataAccessLayer.parent.ParentSchemaService;
import com.GrowthPlus.realmImporter.JsonSampleData;
import com.GrowthPlus.realmImporter.LanguagesRealmImporter;

import io.realm.Realm;
import org.bson.types.ObjectId;

import java.io.InputStream;

public class ParentSignup extends AppCompatActivity implements View.OnClickListener{

    Realm realm;
    Resources resources;
    private Button signupButton;
    private Button signupBackButton;

    private EditText enterPinInput;
    private EditText confirmPinInput;
    private TextView createText;
    private TextView confirmText;
    Integer enterPinInputInteger;
    Integer confirmPinInputInteger;

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
        createText = findViewById(R.id.createPinText);
        confirmText = findViewById(R.id.confirmPinText);
        parentId = new ObjectId();
        parentIdString = parentId.toString();
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

        createText.setText(lang.getCreate());
        confirmText.setText(lang.getConfirm());
        enterPinInput.setHint(lang.getPin());
        confirmPinInput.setHint(lang.getPin());
    }

    @Override
    public void onClick(View view) {

        int id = view.getId();

        //if they click the signup button
        if(id == R.id.parentSignupBtn){

            boolean inputValid = validInput(enterPinInput, confirmPinInput);//checks for null and blank input

            if(inputValid == true){
                //track what the user entered
                enterPinInputInteger = Integer.parseInt(enterPinInput.getText().toString());
                confirmPinInputInteger = Integer.parseInt(confirmPinInput.getText().toString());

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
                CharSequence text = "At least one PIN was blank or null. Please enter a 4-digit number.";
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
        confirmPinInput.setText("");//clears the EditText(s)

    }

    private boolean validInput(EditText input1, EditText input2) {
        String input1String = input1.getText().toString();
        String input2String = input2.getText().toString();

        if (!input1String.equals(null) &&
            !input1String.equals("") &&
            !input2String.equals(null) &&
            !input2String.equals("")){
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
    }

    //since the signup page involves about creating an account, we need a method to create a parent
    private void createParent(){
        signupParentService = new ParentSchemaService(realm, parentIdString, confirmPinInputInteger, null);
        signupParentService.createParentSchema();
    }
}