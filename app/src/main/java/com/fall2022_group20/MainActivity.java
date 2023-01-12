package com.fall2022_group20;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import io.realm.Realm;

public class MainActivity extends AppCompatActivity {
    private Realm realm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_roadmap_one);
        realm = Realm.getDefaultInstance();
    }
}