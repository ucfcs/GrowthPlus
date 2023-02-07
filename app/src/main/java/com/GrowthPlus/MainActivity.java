package com.GrowthPlus;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.gridlayout.widget.GridLayout;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.os.Bundle;

import android.view.View;
import android.widget.ImageButton;

import com.GrowthPlus.customViews.LandingPageAddChild;
import com.GrowthPlus.customViews.LandingPageChildCard;
import com.GrowthPlus.dataAccessLayer.child.ChildSchema;
import com.GrowthPlus.dataAccessLayer.child.ChildSchemaService;

import com.GrowthPlus.realmImporter.JsonSampleData;
import com.GrowthPlus.utilities.ColorIdentifier;
import com.GrowthPlus.utilities.ImageSrcIdentifier;

import java.util.HashMap;
import java.util.Random;

import io.realm.Realm;
import io.realm.RealmResults;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    ColorIdentifier colorIdentifier;
    ColorStateList red, darkGreen, blue, yellow, lightGreen;
    Realm realm;
    Resources resources;
    private ImageButton childPortal;
    private ImageButton language;
    private GridLayout landingPageGridLayout;
    private ChildSchemaService landingPageChildren;
    private HashMap<Integer, Integer> landingPageChildCardIds;
    private HashMap<Integer, String> landingPageChildId;
    public ImageSrcIdentifier imageSrcIdentifier;
    public final int MAX_CHILDREN = 6;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        importSampleData();

        RealmResults<ChildSchema> children = landingPageChildren.getAllChildSchemas();
        LandingPageChildCard childCardTemp;
        ChildSchema childTemp;

        int childrenNum = children.size(), random;
        String childIdTemp;
        String childNameTemp;
        String avatarNameTemp;
        String colorNameTemp;


        // Up here
        for(int index = 0; index < childrenNum; index ++){
            childTemp = children.get(index);

            childIdTemp = childTemp.getChildId();
            childNameTemp = childTemp.getName();
            avatarNameTemp = childTemp.getAvatarName();
            colorNameTemp = childTemp.getColorName();

            childCardTemp = setLandingPageChildCard(landingPageChildCardIds.get(index), childNameTemp, avatarNameTemp, colorNameTemp);
            landingPageGridLayout.addView(childCardTemp, index);

            landingPageChildId.put(landingPageChildCardIds.get(index), childIdTemp);

            childCardTemp = null;
            childTemp = null;
        }

        if(childrenNum < MAX_CHILDREN){
            LandingPageAddChild landingPageAddChild = new LandingPageAddChild(this);
            landingPageAddChild.setId(R.id.landingPageChildCardAdd);
            landingPageAddChild.setOnClickListener(this);

            // Produces a 'random' color for the add student button
            Random rand = new Random();
            random = rand.nextInt(5);
            if(random == 0){
                landingPageAddChild.setCircleColor(red);
                landingPageAddChild.setAddIconColor(blue);
            }
            else if(random == 1){
                landingPageAddChild.setCircleColor(darkGreen);
                landingPageAddChild.setAddIconColor(yellow);
            }
            else if(random == 2){
                landingPageAddChild.setCircleColor(darkGreen);
                landingPageAddChild.setAddIconColor(blue);
            }
            else if(random == 3){
                landingPageAddChild.setCircleColor(lightGreen);
                landingPageAddChild.setAddIconColor(darkGreen);
            }
            else{
                landingPageAddChild.setCircleColor(yellow);
                landingPageAddChild.setAddIconColor(red);
            }

            landingPageGridLayout.addView(landingPageAddChild);
        }

        // TODO: this button is currently navigating to child portal, change to parent portal
        childPortal.setOnClickListener(this);
        language.setOnClickListener(this);
    }

    private void init(){
        realm = Realm.getDefaultInstance();
        resources = getResources();
        childPortal = findViewById(R.id.idParent);
        language = findViewById(R.id.langBtn);
        landingPageGridLayout = findViewById(R.id.landingPageChildGrid);
        landingPageChildren = new ChildSchemaService(realm);
        colorIdentifier = new ColorIdentifier();
        imageSrcIdentifier = new ImageSrcIdentifier();
        landingPageChildId = new HashMap<>();

        red = ContextCompat.getColorStateList(this, colorIdentifier.getColorIdentifier("red"));
        darkGreen = ContextCompat.getColorStateList(this, colorIdentifier.getColorIdentifier("dark_green"));
        blue = ContextCompat.getColorStateList(this, colorIdentifier.getColorIdentifier("blue"));
        yellow = ContextCompat.getColorStateList(this, colorIdentifier.getColorIdentifier("yellow"));
        lightGreen = ContextCompat.getColorStateList(this, colorIdentifier.getColorIdentifier("light_green"));

        landingPageChildCardIds = new HashMap<>();
        setLandingPageChildCardIds();
    }

    private void importSampleData(){
        JsonSampleData jsonSampleData = new JsonSampleData(realm, resources);
        jsonSampleData.importDataFromJson();
    }

    @Override
    protected void onDestroy() {
        if (!realm.isClosed()) realm.close();
        super.onDestroy();
    }

    @Override
    public void onClick(View view){
        int id = view.getId();

        if(id == R.id.idParent){
            startActivity(new Intent(MainActivity.this, ParentPortal.class));
        }

        if(id == R.id.langBtn){
            startActivity(new Intent(MainActivity.this, LanguageSettingActivity.class));
        }

        if(id == R.id.landingPageChildCard0){
            String childId = landingPageChildId.get(R.id.landingPageChildCard0);
            startChildPortalActivity(childId);
        }

        if(id == R.id.landingPageChildCard1){
            String childId = landingPageChildId.get(R.id.landingPageChildCard1);
            startChildPortalActivity(childId);
        }

        if(id == R.id.landingPageChildCard2){
            String childId = landingPageChildId.get(R.id.landingPageChildCard2);
            startChildPortalActivity(childId);
        }

        if(id == R.id.landingPageChildCard3){
            String childId = landingPageChildId.get(R.id.landingPageChildCard3);
            startChildPortalActivity(childId);
        }

        if(id == R.id.landingPageChildCard4){
            String childId = landingPageChildId.get(R.id.landingPageChildCard4);
            startChildPortalActivity(childId);
        }

        if(id == R.id.landingPageChildCard5){
            String childId = landingPageChildId.get(R.id.landingPageChildCard5);
            startChildPortalActivity(childId);
        }

        if( id == R.id.landingPageChildCardAdd){
            startAddChildActivity();
        }
    }

    private void setLandingPageChildCardIds(){
        landingPageChildCardIds.put(0, R.id.landingPageChildCard0);
        landingPageChildCardIds.put(1, R.id.landingPageChildCard1);
        landingPageChildCardIds.put(2, R.id.landingPageChildCard2);
        landingPageChildCardIds.put(3, R.id.landingPageChildCard3);
        landingPageChildCardIds.put(4, R.id.landingPageChildCard4);
        landingPageChildCardIds.put(5, R.id.landingPageChildCard5);
    }

    private LandingPageChildCard setLandingPageChildCard(Integer id, String childName, String avatarName, String colorName){
        ColorStateList color = ContextCompat.getColorStateList(this, colorIdentifier.getColorIdentifier(colorName));
        LandingPageChildCard temp = new LandingPageChildCard(this);

        // Using landing page child card custom methods
        temp.setId(id);
        temp.setChildCardName(childName);
        temp.setNameBackground(color);
        temp.setAvatarBackgroundColor(color);
        temp.setAvatarSrc(imageSrcIdentifier.getImageSrcId(avatarName));
        temp.setOnClickListener(this);

        return temp;
    }

    public void startChildPortalActivity(String childId){
        Intent childPortal = new Intent(MainActivity.this, ChildPortal.class);
        childPortal.putExtra("childIdLandingPage",childId);
        startActivity(childPortal);
    }

    public void startAddChildActivity(){
        Intent selectChildAvatar = new Intent(MainActivity.this, SelectChildAvatar.class);
        startActivity(selectChildAvatar);
    }
}