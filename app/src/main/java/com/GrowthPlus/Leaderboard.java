package com.GrowthPlus;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.gridlayout.widget.GridLayout;

import com.GrowthPlus.customViews.LeaderboardChildView;
import com.GrowthPlus.dataAccessLayer.child.ChildSchema;
import com.GrowthPlus.dataAccessLayer.child.ChildSchemaService;
import com.GrowthPlus.realmImporter.JsonSampleData;
import com.GrowthPlus.utilities.ColorIdentifier;
import com.GrowthPlus.utilities.ImageSrcIdentifier;

import io.realm.Realm;
import io.realm.RealmResults;

public class Leaderboard extends AppCompatActivity {

    Realm realm;
    Resources resources;

    private Button backChildPortalBtn;
    private GridLayout leaderBoardGridLayout;
    public ColorIdentifier colorIdentifier;
    public ImageSrcIdentifier imageSrcIdentifier;

    private ChildSchemaService childrenLeaderBoard;

    //private HashMap<Integer, Integer> leaderBoardChildCardIds;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leaderboard);

        init();
        //importSampleData();

        RealmResults<ChildSchema> children = childrenLeaderBoard.getAllChildSchemas();
        //setChildCardIds();

        //loop through the children and add them to the view
        for(int i = 0; i<children.size(); i++){
            ChildSchema curr = children.get(i);
            LeaderboardChildView C = setLeaderboardChildView(curr.getName(), String.valueOf(curr.getScore()),
                    curr.getAvatarName(), curr.getColorName());

            leaderBoardGridLayout.addView(C);
        }

        backChildPortalBtn.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                //startActivity(new Intent(Leaderboard.this, ChildPortal.class));
                finish();
            }
        });

    }

    private LeaderboardChildView setLeaderboardChildView(String childName, String points, String avatarName, String colorName){
        ColorStateList color = ContextCompat.getColorStateList(this, colorIdentifier.getColorIdentifier(colorName));
        LeaderboardChildView temp = new LeaderboardChildView(this);

        temp.setName(childName);
        temp.setPoints(points);
        temp.setImageResource(imageSrcIdentifier.getImageSrcId(avatarName));
        temp.setBackgroundTintList(color);

        return temp;
    }

    private void init(){
        realm = Realm.getDefaultInstance();

        backChildPortalBtn = findViewById(R.id.backChildPortal);
        leaderBoardGridLayout = findViewById(R.id.leaderBoardGridLayout);
        childrenLeaderBoard = new ChildSchemaService(realm);
        imageSrcIdentifier = new ImageSrcIdentifier();
        colorIdentifier = new ColorIdentifier();
        //leaderBoardChildCardIds = new HashMap<>();
    }

    private void importSampleData(){
        JsonSampleData jsonSampleData = new JsonSampleData(realm, resources);
        jsonSampleData.importDataFromJson();
    }


   /* private void setChildCardIds(){
        leaderBoardChildCardIds.put(0, R.id.leaderBoardChildCard0);
        leaderBoardChildCardIds.put(1, R.id.leaderBoardChildCard1);
        leaderBoardChildCardIds.put(2, R.id.leaderBoardChildCard2);
        leaderBoardChildCardIds.put(3, R.id.leaderBoardChildCard3);
        leaderBoardChildCardIds.put(4, R.id.leaderBoardChildCard4);
        leaderBoardChildCardIds.put(5, R.id.leaderBoardChildCard5);
    }*/
}