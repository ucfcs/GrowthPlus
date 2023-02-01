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
import com.GrowthPlus.utilities.ColorIdentifier;
import com.GrowthPlus.utilities.ImageSrcIdentifier;

import org.bson.types.ObjectId;

import java.util.HashMap;

import io.realm.Realm;
import io.realm.RealmResults;

public class ParentPortal extends AppCompatActivity {

    private Button buttonBackChild;
    private ImageButton languageButton;
    private GridLayout parentPortalGridLayout;
    private LinearLayout parentPortalPb;
    private ChildSchemaService childSchemaService;
    public ColorIdentifier colorIdentifier;
    public ImageSrcIdentifier imageSrcIdentifier;

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

        String childName = child.getName();
        Log.i("Child name", childName);

        String avatarName = child.getAvatarName();
        Log.i("Child avatar name", avatarName);

        String colorName = child.getColorName();
        Log.i("Child color ", colorName);

        ColorStateList color = ContextCompat.getColorStateList(this, colorIdentifier.getColorIdentifier(colorName));

        ChildCard childCardTemp = new ChildCard(this);
        childCardTemp.setImageResource(imageSrcIdentifier.getImageSrcId(avatarName));
        childCardTemp.setBackgroundTintList(color);
        childCardTemp.setText(childName);
        childCardTemp.setTextColor(color);
        childCardTemp.setId(R.id.parentPortalChildCard1);

        parentPortalGridLayout.addView(childCardTemp);

        ChildCardAdd addChildCard1 = new ChildCardAdd(this);

        parentPortalGridLayout.addView(addChildCard1);

        /*
        * Adding a child object to realm
        * */
        Realm realm = Realm.getDefaultInstance();
        ChildSchemaService newChild = new ChildSchemaService(realm, "SomeChild", null, null, "camel", "red");
        ObjectId id = new ObjectId();
        newChild.createChildSchema(String.valueOf(id));

    }

    private void init(){
        buttonBackChild = findViewById(R.id.backChild);
        languageButton = findViewById(R.id.langBtn);
        parentPortalGridLayout = findViewById(R.id.parentPortalGridLayout);
        parentPortalPb = findViewById(R.id.parentPortalPb);
        childSchemaService = new ChildSchemaService();
        colorIdentifier = new ColorIdentifier();
        imageSrcIdentifier = new ImageSrcIdentifier();
    }

    public void setChildCard( ChildCard childCard, String childName, String avatarName, String colorName){
        ColorStateList color = ContextCompat.getColorStateList(this, colorIdentifier.getColorIdentifier(colorName));
    }

}