package com.GrowthPlus;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.gridlayout.widget.GridLayout;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.GrowthPlus.customViews.LandingPageAddChild;
import com.GrowthPlus.customViews.LandingPageChildCard;
import com.GrowthPlus.dataAccessLayer.Language.LanguageSchema;
import com.GrowthPlus.dataAccessLayer.Language.LanguageSchemaService;
import com.GrowthPlus.dataAccessLayer.child.ChildSchema;
import com.GrowthPlus.dataAccessLayer.child.ChildSchemaService;

import com.GrowthPlus.dataAccessLayer.parent.ParentSchema;
import com.GrowthPlus.dataAccessLayer.parent.ParentSchemaService;
import com.GrowthPlus.realmImporter.JsonSampleData;
import com.GrowthPlus.utilities.ColorIdentifier;
import com.GrowthPlus.utilities.ImageSrcIdentifier;

import java.util.HashMap;
import java.util.Random;

import io.realm.Realm;
import io.realm.RealmResults;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private ColorIdentifier colorIdentifier;
    private ColorStateList red, darkGreen, blue, yellow, lightGreen;
    private Realm realm;
    private Resources resources;
    private ImageButton parentPortal;
    private ImageButton language;
    private TextView parentText;
    private GridLayout landingPageGridLayout;
    private ChildSchemaService landingPageChildren;
    private HashMap<Integer, Integer> landingPageChildCardIds;
    private HashMap<Integer, String> landingPageChildId;
    public ImageSrcIdentifier imageSrcIdentifier;
    public final int MAX_CHILDREN = 6;
    public AlphaAnimation buttonClick = new AlphaAnimation(1F, 0.8F);
    private JsonSampleData jsonSampleData;
    private RealmResults<ChildSchema> children;

    private ParentSchemaService landingPageParentService;
    private RealmResults<ParentSchema> parent;
    int parentSize;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        importRoadMapData();
        //importChildData(); UNCOMMENT FOR TESTING
        setAllLandingPageCards(children);

        parentPortal.setOnClickListener(this);
        language.setOnClickListener(this);
    }

    private void init(){
        realm = Realm.getDefaultInstance();
        resources = getResources();
        jsonSampleData = new JsonSampleData(realm, resources);
        parentPortal = findViewById(R.id.idParent);
        language = findViewById(R.id.langBtn);
        landingPageGridLayout = findViewById(R.id.landingPageChildGrid);
        parentText = findViewById(R.id.parentText);
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
        children = landingPageChildren.getAllChildSchemas();

        landingPageParentService = new ParentSchemaService(realm);
        parent = landingPageParentService.getAllParentSchemas();//this gets the parent
        parentSize = parent.size();

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            parentText.setText(extras.getString("setParent"));
        }
        else{
            Log.i("extras null", "the extras were null");
        }

    }

    @Override
    protected void onResume() {
        super.onResume();

        // create instance of shared preferences
        SharedPreferences langPrefs = getSharedPreferences("LangPreferences", MODE_PRIVATE);
        // create language schema service and set strings
        LanguageSchemaService langSchemaService = new LanguageSchemaService(realm, langPrefs.getString("languageId", "frenchZero"));
        LanguageSchema lang = langSchemaService.getLanguageSchemaById();

        parentText.setText(lang.getParent());
    }

    private void importSampleData(){
        jsonSampleData.importDataFromJson();
    }

    public void importRoadMapData(){
        jsonSampleData.importRoadMapData();
    }

    public void importChildData(){
        jsonSampleData.importChildData();
    }

    @Override
    protected void onDestroy() {
        if (!realm.isClosed()) realm.close();
        super.onDestroy();
    }

    @Override
    public void onClick(View view) {

        view.startAnimation(buttonClick);

        int id = view.getId();

        if(id == R.id.idParent){//the user clicked the parent button

            //If a parent exists, we can go to the login page
            if(parentSize > 0) {
                startParentLoginActivity();
            }

            else{ //otherwise, go to the signup page
                startParentSignupActivity();
            }
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

            //If a parent exists, we can go to the add child screen
            if(parentSize > 0) {
                startAddChildActivity();
            }

            else{ //otherwise, display a toast encouraging them to signup for a parent account
                Context context = getApplicationContext();
                CharSequence text = "You cannot add a child account yet. Please create a parent account first.";
                int duration = Toast.LENGTH_SHORT;

                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
            }
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
        selectChildAvatar.putExtra("comingFrom", "LandingPage");
        startActivity(selectChildAvatar);
    }

    private void setAllLandingPageCards(RealmResults<ChildSchema> children){
        LandingPageChildCard childCardTemp;
        ChildSchema childTemp;

        int childrenNum = children.size();
        int random;
        String childIdTemp;
        String childNameTemp;
        String avatarNameTemp;
        String colorNameTemp;

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

    }
    public void startParentSignupActivity(){
        Intent parentSignup = new Intent(MainActivity.this, ParentSignup.class);
        startActivity(parentSignup);
        this.finish();
    }

    public void startParentLoginActivity(){
        Intent parentLogin = new Intent(MainActivity.this, ParentLogin.class);
        startActivity(parentLogin);
        this.finish();
    }
}