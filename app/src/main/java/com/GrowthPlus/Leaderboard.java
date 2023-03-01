package com.GrowthPlus;

import android.content.res.ColorStateList;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.gridlayout.widget.GridLayout;

import com.GrowthPlus.customViews.LeaderboardChildView;
import com.GrowthPlus.dataAccessLayer.child.ChildSchema;
import com.GrowthPlus.dataAccessLayer.child.ChildSchemaService;
import com.GrowthPlus.utilities.ColorIdentifier;
import com.GrowthPlus.utilities.ImageSrcIdentifier;

import io.realm.Realm;
import io.realm.RealmResults;

public class Leaderboard extends AppCompatActivity {

    Realm realm;
    private Button backChildPortalBtn;
    private GridLayout leaderBoardGridLayout;
    public ColorIdentifier colorIdentifier;
    public ImageSrcIdentifier imageSrcIdentifier;

    private ChildSchemaService childrenLeaderBoard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leaderboard);
        init();
        RealmResults<ChildSchema> children = childrenLeaderBoard.getAllChildSchemas();

        children = children.sort("score");
        //loop through the children and add them to the view
        for(int i = children.size() - 1; i>=0; i--){
            ChildSchema curr = children.get(i);
            LeaderboardChildView C = setLeaderboardChildView(curr.getName(), String.valueOf(curr.getScore()),
                    curr.getAvatarName(), curr.getColorName());

            leaderBoardGridLayout.addView(C);
        }

        backChildPortalBtn.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
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
    }

}