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

    Realm realm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leaderboard);
        realm = Realm.getDefaultInstance();
        Button backBtn = (Button) findViewById(R.id.backChildPortal);

        backBtn.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                startActivity(new Intent(Leaderboard.this, ChildPortal.class));
            }
        });

        RecyclerView recyclerView = findViewById(R.id.recyclerview);
//        RealmResults<ChildSchema> children= realm.where(ChildSchema.class).findAll();
//        ChildSchemaService service = new ChildSchemaService(realm,)

//
//        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//        recyclerView.setAdapter(new LeaderboardAdapter(getApplicationContext(), children));
    }
}