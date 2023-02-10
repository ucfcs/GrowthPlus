package com.GrowthPlus;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.GrowthPlus.dataAccessLayer.parent.ParentSchema;
import com.GrowthPlus.dataAccessLayer.parent.ParentSchemaService;
import com.GrowthPlus.realmImporter.JsonSampleData;

import io.realm.Realm;

public class ParentLogin extends AppCompatActivity implements View.OnClickListener{

    Realm realm;
    Resources resources;

    private Button loginButton;
    private Button loginBackButton;
    private EditText loginPinInput;

    private ParentSchemaService loginParentService;
    private ParentSchema loginParent;
    private Integer parentSignupPIN;

    Integer loginPinInputInteger;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parent_login);
        init();

        loginButton.setOnClickListener(this);
        loginBackButton.setOnClickListener(this);
    }

    private void init(){
        realm = Realm.getDefaultInstance();
        resources = getResources();
        loginButton = findViewById(R.id.parentLoginBtn);
        loginBackButton = findViewById(R.id.backParentLogin);
        loginPinInput = findViewById(R.id.loginPinInput);
        loginParentService = new ParentSchemaService(realm);
        loginParent = loginParentService.getAllParentSchemas().get(0); //gets the parent
        parentSignupPIN = loginParent.getPIN(); //and their PIN
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();

        //the user clicked the login button
        if(id == R.id.parentLoginBtn){

            boolean inputValid = validInput(loginPinInput);//checks for null and blank input

            if(inputValid == true){
                loginPinInputInteger = Integer.parseInt(loginPinInput.getText().toString());

                //if the PIN's match and the input was valid, start the parent portal activity
                if(confirmPinMatch(loginPinInputInteger, parentSignupPIN) == true){
                    startParentPortalActivity();
                }

                else{ //PIN's don't match -> display a toast
                    Context context = getApplicationContext();
                    CharSequence text = "That is not the correct PIN. Please try typing it again.";
                    int duration = Toast.LENGTH_SHORT;
                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                }
            }

            else {//input was not valid -> display a toast
                Context context = getApplicationContext();
                CharSequence text = "The PIN you entered was blank or null. Please enter a 4-digit number.";
                int duration = Toast.LENGTH_SHORT;
                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
            }
        }

        //the user clicked the back button
        if(id == R.id.backParentLogin){
            //if a parent already exists, then clicking the back arrow should send you back to the landing page
            if(loginParent != null){
                startLandingPageActivity();
            }

            //if a parent doesn't exist, then go to the signup page.....but this never is the case, right?
            else {
                startParentSignupActivity();
            }
        }
        loginPinInput.setText("");//clears the EditText
    }

    private boolean validInput(EditText input) {
        String inputString = input.getText().toString();

        if (!inputString.equals(null) && !inputString.equals("")) {
            return true;
        }
        else{
            return false;
        }
    }
    private boolean confirmPinMatch(Integer pin1, Integer pin2){

        if(pin1.equals(pin2)) //they match
            return true;

        else //they don't match
            return false;
    }

    public void startParentSignupActivity(){
        Intent parentSignup = new Intent(ParentLogin.this, ParentSignup.class);
        startActivity(parentSignup);
    }

    public void startParentPortalActivity(){
        Intent parentPortal = new Intent(ParentLogin.this, ParentPortal.class);
        startActivity(parentPortal);
    }

    //moves to the MainActivity page
    public void startLandingPageActivity(){
        Intent landingPageActivity = new Intent(ParentLogin.this, MainActivity.class);
        startActivity(landingPageActivity);

    }
}