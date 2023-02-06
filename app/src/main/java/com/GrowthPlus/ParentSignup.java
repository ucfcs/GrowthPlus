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

    ParentSchema parentSchema;
    private ParentSchemaService signupParentService;
    private Button signupButton;
    private Button signupBackButton;

    private EditText enterPinInput;
    private EditText confirmPinInput;
    Integer enterPinInputInteger;
    Integer confirmPinInputInteger;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parent_signup);
        init();
        importSampleData();

        //new way
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
    }

    private void importSampleData(){
        JsonSampleData jsonSampleData = new JsonSampleData(realm, resources);
        jsonSampleData.importDataFromJson();
    }

    @Override
    public void onClick(View view) {

        int id = view.getId();

        if(id == R.id.parentSignupBtn){

            //track what the user entered
            enterPinInputInteger = Integer.parseInt(enterPinInput.getText().toString());
            confirmPinInputInteger = Integer.parseInt(confirmPinInput.getText().toString());
            Log.i("enterPinInputInteger ==", enterPinInputInteger.toString());
            Log.i("confirmPinInputIntgr ==", confirmPinInputInteger.toString());

            if(confirmPinMatch(enterPinInputInteger, confirmPinInputInteger) == true){
                //create a parent with the pin
                ObjectId parentId = new ObjectId();
                String parentIdString = parentId.toString();
                signupParentService = new ParentSchemaService(realm, parentIdString, confirmPinInputInteger, null);
                signupParentService.createParentSchema();

                Log.i("parent signup parent id", parentId.toString());
                //Log.i("parent signup prnt srvc", signupParentService.getParentSchema().toString());

                //move on to the launch screen
                Intent parentLogin = new Intent(ParentSignup.this, ParentLogin.class);
                parentLogin.putExtra("confirmedPin", confirmPinInputInteger.toString());
                startActivity(parentLogin);
            }

            else{
                //while loop?
            }
        }

        if(id == R.id.backParentSU){
            Intent mainActivity = new Intent(ParentSignup.this, MainActivity.class);
            startActivity(mainActivity);
        }
    }

    private boolean confirmPinMatch(Integer pin1, Integer pin2){

        if(pin1.equals(pin2)){
            //they match
            return true;
        }

        else{
            //they don't match
            Context context = getApplicationContext();
            CharSequence text = "PINs do not match! Please try typing them again.";
            int duration = Toast.LENGTH_LONG;

            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
            return false;
        }
    }
}