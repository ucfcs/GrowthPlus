package com.GrowthPlus;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.GrowthPlus.dataAccessLayer.child.ChildSchema;
import com.GrowthPlus.dataAccessLayer.child.ChildSchemaService;

import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmResults;

public class Leaderboard extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leaderboard);

        Button backSet = (Button) findViewById(R.id.backChildPortal);

        backSet.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                startActivity(new Intent(Leaderboard.this, MainActivity.class));
            }
        });

    }
}