package com.GrowthPlus;

import androidx.appcompat.app.AppCompatActivity;
import androidx.gridlayout.widget.GridLayout;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import com.GrowthPlus.dataAccessLayer.child.ChildSchema;
import com.GrowthPlus.dataAccessLayer.child.ChildSchemaService;

import io.realm.Realm;
import io.realm.RealmResults;

public class ParentPortal extends AppCompatActivity {

    private Button buttonBackChild;
    private ImageButton languageButton;
    private GridLayout parentPortalGridLayout;
    private LinearLayout parentPortalPb;
    private ChildSchemaService childSchemaService;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parent_portal);
        init();

        RealmResults<ChildSchema> children = childSchemaService.getAllChildSchemas();
        Log.i("Children", String.valueOf(children));

        ChildSchema childTemp = children.get(0);
        Log.i("Child position 0", String.valueOf(childTemp));

        Log.i("Child name", childTemp.getName());
        Log.i("Child avatar", String.valueOf(childTemp.getAvatar()));

        // Need a field for child color 

    }

    private void init(){
        buttonBackChild = findViewById(R.id.backChild);
        languageButton = findViewById(R.id.langBtn);
        parentPortalGridLayout = findViewById(R.id.parentPortalGridLayout);
        parentPortalPb = findViewById(R.id.parentPortalPb);
        childSchemaService = new ChildSchemaService();
    }





}