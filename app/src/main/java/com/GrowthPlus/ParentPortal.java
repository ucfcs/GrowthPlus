package com.GrowthPlus;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.gridlayout.widget.GridLayout;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.GrowthPlus.customViews.ChildCard;
import com.GrowthPlus.customViews.ChildCardAdd;
import com.GrowthPlus.customViews.VerticalProgressBar;
import com.GrowthPlus.dataAccessLayer.Language.Translator;
import com.GrowthPlus.dataAccessLayer.child.ChildSchema;
import com.GrowthPlus.dataAccessLayer.child.ChildSchemaService;
import com.GrowthPlus.dataAccessLayer.parent.ParentSchema;
import com.GrowthPlus.dataAccessLayer.parent.ParentSchemaService;
import com.GrowthPlus.utilities.ColorIdentifier;
import com.GrowthPlus.utilities.ImageSrcIdentifier;
import java.util.HashMap;

import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmResults;


/**
 * This class implements a View.OnClickListener
 * It will provide onClick method that we can override and implement custom logic
 */
public class ParentPortal extends AppCompatActivity implements View.OnClickListener {
    private Button buttonBackChild;
    private Button deleteParent;
    private GridLayout parentPortalGridLayout;
    private LinearLayout parentPortalLinearLayout;
    private ChildSchemaService childSchemaService;
    private HashMap<Integer, Integer> childCardId; // ChildCard component id == android:id="@+id/"
    private HashMap<Integer, String> childId; // Store the children ids childName : id
    private HashMap<Integer, Integer> progressBarIds;
    public ColorIdentifier colorIdentifier;
    public ImageSrcIdentifier imageSrcIdentifier;
    private String parentId;
    private Realm realm;
    private AlertDialog dialogue;

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
        Integer totalLessonsCompleted;
        Integer lessonProgress;

        // Looping through the number of children from the realm result
        // Dynamically add the child card components with their corresponding data to the grid layout
        for(int i = 0; i < childrenRealmResultSize; i++){
            childRealmObjectTemp = children.get(i);
            assert childRealmObjectTemp != null;
            childIdTemp = childRealmObjectTemp.getChildId();
            childNameTemp = childRealmObjectTemp.getName();
            avatarNameTemp = childRealmObjectTemp.getAvatarName();
            colorNameTemp = childRealmObjectTemp.getColorName();
            totalLessonsCompleted = childRealmObjectTemp.getTotalLessonsCompleted();

            lessonProgress = calculateLessonProgress(totalLessonsCompleted);

            childCardTemp = setChildCard(childCardId.get(i), childNameTemp, avatarNameTemp, colorNameTemp);
            parentPortalGridLayout.addView(childCardTemp, i);

            verticalProgressBarTemp = setVerticalProgressBar(progressBarIds.get(i), avatarNameTemp, colorNameTemp, lessonProgress);
            parentPortalLinearLayout.addView(verticalProgressBarTemp, i);

            // Map the child card component ids to their corresponding child schema ids from realm
            childId.put(childCardId.get(i), childIdTemp);
        }

        // Make sure children do not surpass 6
        int MAX_CHILDREN = 6;
        if(childrenRealmResultSize < MAX_CHILDREN){
            ChildCardAdd addChildCard1 = new ChildCardAdd(this);
            addChildCard1.setId(R.id.parentPortalChildCardAdd);
            addChildCard1.setOnClickListener(this);
            parentPortalGridLayout.addView(addChildCard1);
        }

        buttonBackChild.setOnClickListener(this);

        deleteParent.setOnClickListener(view -> createDeleteParentDialogue());
    }

    /**
     * Init an instance of realm and for every field
     */
    private void init(){
        realm = Realm.getDefaultInstance();
        buttonBackChild = findViewById(R.id.backChild);
        deleteParent = findViewById(R.id.deleteParentButton);
        parentPortalGridLayout = findViewById(R.id.parentPortalGrid);
        parentPortalLinearLayout = findViewById(R.id.parentPortalPB);
        childSchemaService = new ChildSchemaService(realm);
        colorIdentifier = new ColorIdentifier();
        imageSrcIdentifier = new ImageSrcIdentifier();
        ParentSchemaService parentService = new ParentSchemaService(realm);
        ParentSchema parent = parentService.getAllParentSchemas().get(0);
        assert parent != null;
        childCardId = new HashMap<>();
        childId = new HashMap<>();
        progressBarIds = new HashMap<>();
        parentId = parent.getParentId();

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
    public VerticalProgressBar setVerticalProgressBar(Integer id, String avatarName, String colorName, Integer lessonProgress){
        ColorStateList color = ContextCompat.getColorStateList(this, colorIdentifier.getColorIdentifier(colorName));
        VerticalProgressBar temp = new VerticalProgressBar(this);

        temp.setId(id);
        temp.setImageResource(imageSrcIdentifier.getImageSrcId(avatarName));
        temp.setBackgroundTintList(color);
        temp.setProgress(lessonProgress);

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
        Intent childScreen = new Intent(ParentPortal.this, ChildScreen.class);
        childScreen.putExtra("childIdParentPortal",childId);
        startActivity(childScreen);
        this.finish();
    }

    public void startSelectChildAvatarActivity(){
        Intent selectChildAvatar = new Intent(ParentPortal.this, SelectChildAvatar.class);
        selectChildAvatar.putExtra("comingFrom", "parentPortal");
        startActivity(selectChildAvatar);
        this.finish();
    }

    public void startLandingPageActivity(){
        Intent landingPage = new Intent(ParentPortal.this, MainActivity.class);
        startActivity(landingPage);
        this.finish();
    }

    //this creates the delete child popup
    public void createDeleteParentDialogue(){
        //create the dialogue builder using this context
        //this is for the delete child confirmation popup screen
        AlertDialog.Builder dialogueBuilder = new AlertDialog.Builder(this);

        //to create the view we have an inflator that calls our custom xml file
        View deleteChildPopupView = getLayoutInflater().
                inflate(R.layout.delete_child_confirmation_popup,
                        null);

        //here we want to grab the confirm and cancel buttons from the view
        //this is important so that we can set up the proper logic for the
        //onClickListeners
        Button confirmParentDelete = deleteChildPopupView.findViewById(R.id.confirmBtn);
        Button cancelParentDelete = deleteChildPopupView.findViewById(R.id.cancelBtn);

        //here we grab the fields in the custom PopUp xml file that we want to change
        //based on the child that is being deleted
        TextView parentNameDel = deleteChildPopupView.findViewById(R.id.childName);
        ImageView parentAvatarDel = deleteChildPopupView.findViewById(R.id.childAvatar);
        TextView deleteText = deleteChildPopupView.findViewById(R.id.delete);


        //here we set the child name and avatar to the popUp so that the parent can
        //know which child they are or are not deleting

        parentAvatarDel.setImageResource(imageSrcIdentifier.getImageSrcId("parent"));

        // Grab the current language selection and update the delete text with that language
        // Create instance of shared preferences and save current language id
        SharedPreferences langPrefs = getSharedPreferences("LangPreferences", MODE_PRIVATE);
        String langId = langPrefs.getString("languageId", "frenchZero");
        // Create language translator and set up the Lesson string
        Translator trans = new Translator(langId);
        String text = trans.getString("delete")+"?";
        deleteText.setText(text);
        parentNameDel.setText(trans.getString("parent"));

        //in the dialogue builder we have to set this view
        dialogueBuilder.setView(deleteChildPopupView);

        //now we create and build this view
        dialogue = dialogueBuilder.create();
        dialogue.show();

        //this is the logic if the parent confirms that they want to delete the child
        //we grab the child using their unique ID, delete the child, and verify that the
        //deletion was sucessful and eventually dismiss the popUp
        confirmParentDelete.setOnClickListener(view -> {
            realm.executeTransactionAsync(realm -> {
                ParentSchema parentDel = realm.where(ParentSchema.class).equalTo("parentId", parentId).findFirst();
                assert parentDel != null;
                RealmList<ChildSchema> children = parentDel.getChildren();
                if(children.size()>0){
                    children.deleteAllFromRealm();
                }
                parentDel.deleteFromRealm();
            },()->{
                Intent intent = new Intent(ParentPortal.this, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                overridePendingTransition(0, 0);
                startActivity(new Intent(ParentPortal.this, MainActivity.class));
                overridePendingTransition(0, 0);
                this.finish();
            }, error -> Log.i("Error", "Could not delete parent from realm " + error));
            dialogue.dismiss();
        });

        //here the parent does not wish to delete the child so we simply dismiss our popUp
        cancelParentDelete.setOnClickListener(v -> dialogue.dismiss());
    }

    private Integer calculateLessonProgress(Integer lessonsCompleted){
        int TOTAL_LESSONS = 40;
        double percentage = (lessonsCompleted.doubleValue() / (double) TOTAL_LESSONS);
        double progress = percentage * 100;

        return (int) progress;
    }
}