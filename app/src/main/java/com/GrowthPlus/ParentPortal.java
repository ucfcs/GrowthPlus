package com.GrowthPlus;

import androidx.appcompat.app.AppCompatActivity;
import androidx.gridlayout.widget.GridLayout;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import io.realm.Realm;

public class ParentPortal extends AppCompatActivity {

    private Button buttonBackChild;
    private ImageButton languageButton;
    private GridLayout parentPortalGridLayout;
    private LinearLayout parentPortalPb;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parent_portal);
        init();

    }

    private void init(){
        buttonBackChild = findViewById(R.id.backChild);
        languageButton = findViewById(R.id.langBtn);
        parentPortalGridLayout = findViewById(R.id.parentPortalGridLayout);
        parentPortalPb = findViewById(R.id.parentPortalPb);
    }

}