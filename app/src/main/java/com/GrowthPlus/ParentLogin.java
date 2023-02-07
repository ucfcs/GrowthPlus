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

    private String parentId;

    private ParentSchemaService loginParentService;

    ParentSchema parent;

    Integer parentSignupPIN;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parent_login);
        init();
        importSampleData();

        loginButton.setOnClickListener(this);
        loginBackButton.setOnClickListener(this);

        //using the parentId, get the parentSchema
        parent = loginParentService.getParentSchemaById(parentId);

       Log.i("parentId", parentId);
       Log.i("parent", String.valueOf(parent));

       parentSignupPIN = parent.getPIN();
       Log.i("parent signup pin", String.valueOf(parentSignupPIN));

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
            parentId = extras.getString("parentId");
            //Log.i("parentId == ", parentId);
        }

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
            //Log.i("loginPinInputInteger ==", loginPinInputInteger.toString());

            //check that the PIN matches the PIN and then start the parent portal activity
            if(confirmPinMatch(loginPinInputInteger, parentSignupPIN) == true){
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
            //go back to the signup page....is this a good thing to actually let happen though??
            startParentSignupActivity();
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
        startActivity(parentPortal);
    }
}