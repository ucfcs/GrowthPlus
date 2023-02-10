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
import android.widget.ImageButton;
import android.widget.Toast;

import com.GrowthPlus.dataAccessLayer.parent.ParentSchema;
import com.GrowthPlus.dataAccessLayer.parent.ParentSchemaService;
import com.GrowthPlus.realmImporter.JsonSampleData;

import io.realm.Realm;
import org.bson.types.ObjectId;

public class ParentSignup extends AppCompatActivity implements View.OnClickListener{

    Realm realm;
    Resources resources;
    private Button signupButton;
    private Button signupBackButton;

    private EditText enterPinInput;
    private EditText confirmPinInput;
    Integer enterPinInputInteger;
    Integer confirmPinInputInteger;

    private ObjectId parentId;
    private String parentIdString;

    private ParentSchemaService signupParentService;
    private ParentSchema signupParent;

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
        parentId = new ObjectId();
        parentIdString = parentId.toString();
    }

    @Override
    public void onClick(View view) {

        int id = view.getId();

        //if they click the signup button
        if(id == R.id.parentSignupBtn){
            //track what the user entered
            enterPinInputInteger = Integer.parseInt(enterPinInput.getText().toString());
            confirmPinInputInteger = Integer.parseInt(confirmPinInput.getText().toString());

            if(confirmPinMatch(enterPinInputInteger, confirmPinInputInteger) == true){
                //create a parent with the pin
                createParent();

                //move on to the login screen
                startLoginActivity();
            }

            else{
                //display a toast
                Context context = getApplicationContext();
                CharSequence text = "PINs do not match! Please try typing them again.";
                int duration = Toast.LENGTH_LONG;

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