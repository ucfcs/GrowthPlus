package com.GrowthPlus;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.gridlayout.widget.GridLayout;

import com.GrowthPlus.dataAccessLayer.child.ChildSchema;
import com.GrowthPlus.dataAccessLayer.child.ChildSchemaService;
import com.GrowthPlus.realmImporter.JsonSampleData;

import java.util.HashMap;

import io.realm.RealmResults;
import io.realm.gradle.Realm;

public class Leaderboard extends AppCompatActivity implements View.OnClickListener {

    Realm realm;
    Resources resources;

    private Button backChildPortalBtn;
    private GridLayout leaderBoardGridLayout;

    private ChildSchemaService childrenLeaderBoard;

    private HashMap<Integer, Integer> leaderBoardChildCardIds;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leaderboard);

        init();
        importSampleData();

        RealmResults<ChildSchema> children = childrenLeaderBoard.getAllChildSchemas();

        backChildPortalBtn.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                startActivity(new Intent(Leaderboard.this, MainActivity.class));
            }
        });

    }

    private void init(){
        realm = Realm.getDefaultInstance();

        backChildPortalBtn = findViewById(R.id.backChildPortal);
        leaderBoardGridLayout = findViewById(R.id.leaderBoardGridLayout);
        childrenLeaderBoard = new ChildSchemaService(realm);
        leaderBoardChildCardIds = new HashMap<>();

    }

    private void importSampleData(){
        JsonSampleData jsonSampleData = new JsonSampleData(realm, resources);
        jsonSampleData.importDataFromJson();
    }

    @Override
    public void onClick(View v) {

    }

    private void setChildCardIds(){
        leaderBoardChildCardIds.put(0, R.id.leaderBoardChildCard0);
        leaderBoardChildCardIds.put(1, R.id.leaderBoardChildCard1);
        leaderBoardChildCardIds.put(2, R.id.leaderBoardChildCard2);
        leaderBoardChildCardIds.put(3, R.id.leaderBoardChildCard3);
        leaderBoardChildCardIds.put(4, R.id.leaderBoardChildCard4);
        leaderBoardChildCardIds.put(5, R.id.leaderBoardChildCard5);
    }
}