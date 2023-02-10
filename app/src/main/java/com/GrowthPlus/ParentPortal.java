package com.GrowthPlus;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.gridlayout.widget.GridLayout;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.GrowthPlus.customViews.ChildCard;
import com.GrowthPlus.customViews.ChildCardAdd;
import com.GrowthPlus.customViews.VerticalProgressBar;
import com.GrowthPlus.dataAccessLayer.child.ChildSchema;
import com.GrowthPlus.dataAccessLayer.child.ChildSchemaService;
import com.GrowthPlus.utilities.ColorIdentifier;
import com.GrowthPlus.utilities.ImageSrcIdentifier;
import java.util.HashMap;

import io.realm.ObjectChangeSet;
import io.realm.Realm;
import io.realm.RealmChangeListener;
import io.realm.RealmObjectChangeListener;
import io.realm.RealmResults;


/**
 * This class implements a View.OnClickListener
 * It will provide onClick method that we can override and implement custom logic
 */
public class ParentPortal extends AppCompatActivity implements View.OnClickListener {
    private final int MAX_CHILDREN = 6;
    private Button buttonBackChild;
    private GridLayout parentPortalGridLayout;
    private LinearLayout parentPortalLinearLayout;
    private ChildSchemaService childSchemaService;
    private HashMap<Integer, Integer> childCardId; // ChildCard component id == android:id="@+id/"
    private HashMap<Integer, String> childId; // Store the children ids childName : id
    private HashMap<Integer, Integer> progressBarIds;
    public ColorIdentifier colorIdentifier;
    public ImageSrcIdentifier imageSrcIdentifier;
    private Realm realm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parent_portal);
        init();

        /*
          Temp variables to hold relative data about the child object
          Temp ChildCard component to add to the grid layout
         */
        RealmResults<ChildSchema> children = childSchemaService.getAllChildSchemas();

        int childrenRealmResultSize = children.size();
        ChildSchema childRealmObjectTemp;
        ChildCard childCardTemp;
        VerticalProgressBar verticalProgressBarTemp;
        String childIdTemp;
        String childNameTemp;
        String avatarNameTemp;
        String colorNameTemp;

        // Looping through the number of children from the realm result
        // Dynamically add the child cards components with their corresponding data to the grid layout
        for(int i = 0; i < childrenRealmResultSize; i++){
            childRealmObjectTemp = children.get(i);
            Log.i("Child ", String.valueOf(childRealmObjectTemp));
            assert childRealmObjectTemp != null;
            childIdTemp = childRealmObjectTemp.getChildId();
            childNameTemp = childRealmObjectTemp.getName();
            avatarNameTemp = childRealmObjectTemp.getAvatarName();
            colorNameTemp = childRealmObjectTemp.getColorName();

            childCardTemp = setChildCard(childCardId.get(i), childNameTemp, avatarNameTemp, colorNameTemp);
            parentPortalGridLayout.addView(childCardTemp, i);

            verticalProgressBarTemp = setVerticalProgressBar(progressBarIds.get(i), avatarNameTemp, colorNameTemp);
            parentPortalLinearLayout.addView(verticalProgressBarTemp, i);

            // Map the child card component ids to their corresponding child schema ids from realm
            childId.put(childCardId.get(i), childIdTemp);

            // Reset temp values
            childRealmObjectTemp = null;
            childCardTemp = null;
            verticalProgressBarTemp = null;
        }

        // Make sure children do not surpass 6
        if(childrenRealmResultSize < MAX_CHILDREN){
            ChildCardAdd addChildCard1 = new ChildCardAdd(this);
            addChildCard1.setId(R.id.parentPortalChildCardAdd);
            addChildCard1.setOnClickListener(this);
            parentPortalGridLayout.addView(addChildCard1);
        }

        buttonBackChild.setOnClickListener(this);
    }

    /**
     * Init an instance of realm and for every field
     */
    private void init(){
        realm = Realm.getDefaultInstance();
        buttonBackChild = findViewById(R.id.backChild);
        parentPortalGridLayout = findViewById(R.id.parentPortalGrid);
        parentPortalLinearLayout = findViewById(R.id.parentPortalPB);
        childSchemaService = new ChildSchemaService(realm);
        colorIdentifier = new ColorIdentifier();
        imageSrcIdentifier = new ImageSrcIdentifier();
        childCardId = new HashMap<>();
        childId = new HashMap<>();
        progressBarIds = new HashMap<>();

        setChildCardIds();
        setProgressBarIds();
    }

    @Nullable
    public ChildCard setChildCard(Integer id, String childName, String avatarName, String colorName){
        ColorStateList color = ContextCompat.getColorStateList(this, colorIdentifier.getColorIdentifier(colorName));
        ChildCard childCardTemp = new ChildCard(this);

        childCardTemp.setId(id);
        childCardTemp.setImageResource(imageSrcIdentifier.getImageSrcId(avatarName));
        childCardTemp.setBackgroundTintList(color);
        childCardTemp.setText(childName);
        childCardTemp.setTextColor(color);

        childCardTemp.setOnClickListener(this);

        return childCardTemp;
    }

    @Nullable
    public VerticalProgressBar setVerticalProgressBar(Integer id, String avatarName, String colorName){
        ColorStateList color = ContextCompat.getColorStateList(this, colorIdentifier.getColorIdentifier(colorName));
        VerticalProgressBar temp = new VerticalProgressBar(this);

        temp.setId(id);
        temp.setImageResource(imageSrcIdentifier.getImageSrcId(avatarName));
        temp.setBackgroundTintList(color);

        return temp;
    }

    /**
     * Setting pre-defined view ids for each child card.
     * This is necessary for parsing within the for loop.
     * References the ids.xml file within res/values.
     */
    private void setChildCardIds(){
       childCardId.put(0, R.id.parentPortalChildCard0);
       childCardId.put(1, R.id.parentPortalChildCard1);
       childCardId.put(2, R.id.parentPortalChildCard2);
       childCardId.put(3, R.id.parentPortalChildCard3);
       childCardId.put(4, R.id.parentPortalChildCard4);
       childCardId.put(5, R.id.parentPortalChildCard5);
    }

    private void setProgressBarIds(){
        progressBarIds.put(0, R.id.parentPortalChildCardPb0);
        progressBarIds.put(1, R.id.parentPortalChildCardPb1);
        progressBarIds.put(2, R.id.parentPortalChildCardPb2);
        progressBarIds.put(3, R.id.parentPortalChildCardPb3);
        progressBarIds.put(4, R.id.parentPortalChildCardPb4);
        progressBarIds.put(5, R.id.parentPortalChildCardPb5);
    }

    @Override
    public void onClick(View view) {

        if((view.getId()) == R.id.parentPortalChildCard0) {
            String selectedChildId = childId.get(R.id.parentPortalChildCard0);
            startChildScreenActivity(selectedChildId);
        }

        if((view.getId()) == R.id.parentPortalChildCard1) {
            String selectedChildId = childId.get(R.id.parentPortalChildCard1);
            startChildScreenActivity(selectedChildId);
        }

        if((view.getId()) == R.id.parentPortalChildCard2) {
            String selectedChildId = childId.get(R.id.parentPortalChildCard2);
            startChildScreenActivity(selectedChildId);
        }

        if((view.getId()) == R.id.parentPortalChildCard3) {
            String selectedChildId = childId.get(R.id.parentPortalChildCard3);
            startChildScreenActivity(selectedChildId);
        }

        if((view.getId()) == R.id.parentPortalChildCard4) {
            String selectedChildId = childId.get(R.id.parentPortalChildCard4);
            startChildScreenActivity(selectedChildId);
        }

        if((view.getId()) == R.id.parentPortalChildCard5) {
            String selectedChildId = childId.get(R.id.parentPortalChildCard5);
            startChildScreenActivity(selectedChildId);
        }

        if((view.getId()) == R.id.parentPortalChildCardAdd) {
            startSelectChildAvatarActivity();

        }

        if((view.getId()) == R.id.backChild) {
            startLandingPageActivity();
        }

    }

    public void startChildScreenActivity(String childId){
        Intent childScreen = new Intent(ParentPortal.this, childScreen.class);
        childScreen.putExtra("childIdParentPortal",childId);
        startActivity(childScreen);
        this.finish();
    }

    public void startSelectChildAvatarActivity(){
        Intent selectChildAvatar = new Intent(ParentPortal.this, SelectChildAvatar.class);
        selectChildAvatar.putExtra("comingFrom", "ParentPortal");
        startActivity(selectChildAvatar);
    }

    public void startLandingPageActivity(){
        Intent landingPage = new Intent(ParentPortal.this, MainActivity.class);
        startActivity(landingPage);
        this.finish();
    }
}