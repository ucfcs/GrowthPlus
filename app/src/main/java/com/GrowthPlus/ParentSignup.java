package com.GrowthPlus;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.GrowthPlus.dataAccessLayer.parent.ParentSchema;

public class ParentSignup extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parent_signup);

        Button signupButton = (Button) findViewById(R.id.parentLoginBtn);
        signupButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                startActivity(new Intent(ParentSignup.this, ParentLogin.class));
            }
        });

        Button parentSUBackButton = (Button) findViewById(R.id.backParentSU);
        parentSUBackButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                startActivity(new Intent(ParentSignup.this, MainActivity.class));
            }
        });
    }
}