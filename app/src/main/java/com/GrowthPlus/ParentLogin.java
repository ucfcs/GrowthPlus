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

    private String parentIdString;
    private ParentSchemaService loginParentService;
    private ParentSchema loginParent;
    private Integer parentSignupPIN;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parent_login);
        init();
        importSampleData();

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

        //get the parentId passed in from ParentSignup
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            parentIdString = extras.getString("parentIdString");
        }

        //get the parentschema without the parentId
        loginParent = loginParentService.getParentSchema();

        //using the parentId, get the parentSchema
        loginParent = loginParentService.getParentSchemaById(parentIdString);

        //Log.i("parentIdString PL", String.valueOf(parentIdString));
        //Log.i("parent PL", String.valueOf(loginParent));

        parentSignupPIN = loginParent.getPIN();
        //Log.i("parent signup pin PL", String.valueOf(parentSignupPIN));
    }

    private void importSampleData(){
        JsonSampleData jsonSampleData = new JsonSampleData(realm, resources);
        jsonSampleData.importDataFromJson();
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();

        //if they click the login button,
        if(id == R.id.parentLoginBtn){

            Integer loginPinInputInteger = Integer.parseInt(loginPinInput.getText().toString());

            //check that the PIN matches the PIN and then start the parent portal activity
            if(confirmPinMatch(loginPinInputInteger, parentSignupPIN) == true){
                loginPinInput.setText("");//clears the EditText
                startParentPortalActivity();
            }

            //if the PIN doesn't match, display a toast
            else{
                //ruth said something about a while loop?
                //for now just display a toast
                Context context = getApplicationContext();
                CharSequence text = "That is not the correct PIN. Please try typing it again.";
                int duration = Toast.LENGTH_LONG;

                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
            }

        }

        if(id == R.id.backParentLogin){
            //go back to the signup page if a parent doesn't exist.....but it always does, right?
            if(loginParent == null) {
                Log.i("parent login", "loginParent IS null");
                startParentSignupActivity();
            }

            //if a parent already exists, then clicking the back arrow should send you back to the landing page
            else{
                Log.i("parent login", "loginParent is NOT null");
                startLandingPageActivity();
            }
        }
    }

    private boolean confirmPinMatch(Integer pin1, Integer pin2){

        if(pin1.equals(pin2)){
            //they match
            return true;
        }

        else{
            //they don't match
            return false;
        }
    }

    public void startParentSignupActivity(){
        Intent parentSignup = new Intent(ParentLogin.this, ParentSignup.class);
        startActivity(parentSignup);
    }

    public void startParentPortalActivity(){
        Intent parentPortal = new Intent(ParentLogin.this, ParentPortal.class);
        parentPortal.putExtra("parentIdString", parentIdString);
        startActivity(parentPortal);
    }

    //moves to the MainActivity page
    public void startLandingPageActivity(){
        Intent landingPageActivity = new Intent(ParentLogin.this, MainActivity.class);
        landingPageActivity.putExtra("parentIdString", parentIdString);
        startActivity(landingPageActivity);
    }
}