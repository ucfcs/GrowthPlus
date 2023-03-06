package com.GrowthPlus;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.GrowthPlus.dataAccessLayer.child.ChildSchema;
import com.GrowthPlus.dataAccessLayer.child.ChildSchemaService;
import com.GrowthPlus.roadMapActivity.RoadMapFour;
import com.GrowthPlus.roadMapActivity.RoadMapOne;
import com.GrowthPlus.roadMapActivity.RoadMapThree;
import com.GrowthPlus.roadMapActivity.RoadMapTwo;
import com.GrowthPlus.utilities.ImageSrcIdentifier;
import io.realm.Realm;

public class ChildPortal extends AppCompatActivity implements View.OnClickListener{
    private ImageView learnButton;
    private ImageView leaderBoardBtn;
    private Button backBtn;
    private TextView childPortalName;
    private TextView pointsNumber;
    private String childId;
    private ChildSchemaService childSchemaService;
    private ImageSrcIdentifier imageSrcIdentifier;
    private final AlphaAnimation buttonClick = new AlphaAnimation(1F, 0.8F);
    private ChildSchema child;
    private Realm realm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_child_portal);
        init();

        child = childSchemaService.getChildSchemaById(childId);

        pointsNumber.setText(String.valueOf(child.getScore())); // Update score of specific child
        childPortalName.setText(child.getName());
        learnButton.setImageResource(imageSrcIdentifier.getImageSrcId(child.getAvatarName()));
        leaderBoardBtn.setImageResource(imageSrcIdentifier.getImageSrcId(child.getAvatarName()));

        backBtn.setOnClickListener(this);
        leaderBoardBtn.setOnClickListener(this);
        learnButton.setOnClickListener(this);
    }

    public void init(){
        realm = Realm.getDefaultInstance();
        learnButton = findViewById(R.id.learnBtn);
        leaderBoardBtn = findViewById(R.id.leaderboardBtn);
        backBtn = findViewById(R.id.backChild);
        childPortalName = findViewById(R.id.childPortalName);
        pointsNumber = findViewById(R.id.pointsValue);
        childSchemaService = new ChildSchemaService(realm);
        imageSrcIdentifier = new ImageSrcIdentifier();

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            childId = extras.getString("childIdLandingPage");
        }
    }

    @Override
    public void onClick(View view) {
        view.startAnimation(buttonClick);

        int viewId = view.getId();

        if(viewId == R.id.learnBtn){

            Intent intent;

            String currentRoadMapId;
            if(child.getRoadMapOne().getCurrent()){
                currentRoadMapId = "RoadMapOne";
                intent = new Intent(ChildPortal.this, RoadMapOne.class);
            }else if(child.getRoadMapTwo().getCurrent()){
                currentRoadMapId = "RoadMapTwo";
                intent = new Intent(ChildPortal.this, RoadMapTwo.class);
            }else if(child.getRoadMapThree().getCurrent()){
                currentRoadMapId = "RoadMapThree";
                intent = new Intent(ChildPortal.this, RoadMapThree.class);
            }else if(child.getRoadMapFour().getCurrent()){
                currentRoadMapId = "RoadMapFour";
                intent = new Intent(ChildPortal.this, RoadMapFour.class);
            }else{
                currentRoadMapId = "RoadMapOne";
                intent = new Intent(ChildPortal.this, RoadMapOne.class);
            }

            intent.putExtra("childIdentify", childId); // Pass childID to Roadmap page
            intent.putExtra("roadMapId", currentRoadMapId);
            startActivity(intent);
            this.finish();
        }
        if(viewId == R.id.leaderboardBtn){
            startActivity(new Intent(ChildPortal.this, Leaderboard.class));
        }
        if(viewId == R.id.backChild){
            startActivity(new Intent(ChildPortal.this, MainActivity.class));
            this.finish();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Close the Realm instance.
        realm.close();
    }
}