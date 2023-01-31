package com.GrowthPlus;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.gridlayout.widget.GridLayout;

import android.content.res.ColorStateList;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import com.GrowthPlus.customViews.ChildCard;
import com.GrowthPlus.customViews.ChildCardAdd;
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

        Log.i("Children size", String.valueOf(children.size()));

        ChildSchema child = children.get(0);
        Log.i("Child" , String.valueOf(child));

        Log.i("Child name", child.getName());

        Log.i("Child avatar src", String.valueOf(child.getAvatarSrc()));

        Log.i("Child color id", String.valueOf(child.getColorId()));

        /*
        * Let's figure out how to add a child to the layout
        * Might be useful:
        *
        *   onViewAdded(View child)
            Called when a new child is added to this ViewGroup
        *
        *
        * */

        ColorStateList color = ContextCompat.getColorStateList(this, R.color.red);


        ChildCard childCardTemp = new ChildCard(this);
        childCardTemp.setImageResource(R.mipmap.camel_foreground);
        childCardTemp.setBackgroundTintList(color);
        childCardTemp.setText(child.getName());
        childCardTemp.setTextColor(color);
        childCardTemp.setId(0);

        parentPortalGridLayout.addView(childCardTemp);

        ChildCardAdd addChildCard1 = new ChildCardAdd(this);

        parentPortalGridLayout.addView(addChildCard1);

    }

    private void init(){
        buttonBackChild = findViewById(R.id.backChild);
        languageButton = findViewById(R.id.langBtn);
        parentPortalGridLayout = findViewById(R.id.parentPortalGridLayout);
        parentPortalPb = findViewById(R.id.parentPortalPb);
        childSchemaService = new ChildSchemaService();
    }





}