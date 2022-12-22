package com.fall2022_group20;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.fall2022_group20.dummyData.ReportDummyData;

import io.realm.Realm;

public class MainActivity extends AppCompatActivity {
    private Realm realm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //realm = Realm.getDefaultInstance();

        ReportDummyData reportTestData = new ReportDummyData(String.valueOf(0));
        reportTestData.createChildReportDummyData();
        reportTestData.closeRealm();
    }

}