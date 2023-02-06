package com.GrowthPlus;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.GrowthPlus.realmImporter.JsonSampleData;

import io.realm.Realm;

public class ParentLogin extends AppCompatActivity implements View.OnClickListener{

    Realm realm;
    Resources resources;

    private Button loginButton;

    private Button loginBackButton;

    private EditText loginPinInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parent_login);
        init();
        importSampleData();

        //new way
        loginButton.setOnClickListener(this);
        loginBackButton.setOnClickListener(this);


    }

    private void init(){
        realm = Realm.getDefaultInstance();
        resources = getResources();
        loginButton = findViewById(R.id.parentLoginBtn);
        loginBackButton = findViewById(R.id.backParentLogin);
    }

    private void importSampleData(){
        JsonSampleData jsonSampleData = new JsonSampleData(realm, resources);
        jsonSampleData.importDataFromJson();
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();

        if(id == R.id.parentLoginBtn){
            startActivity(new Intent(ParentLogin.this, ParentPortal.class));

            loginPinInput = findViewById(R.id.loginPinInput);

            Integer loginPinInputInteger = Integer.parseInt(loginPinInput.getText().toString());
            Log.i("loginPinInputInteger ==", loginPinInputInteger.toString());

        }

        if(id == R.id.backParentLogin){

            startActivity(new Intent(ParentLogin.this, ParentSignup.class));
        }
    }
}