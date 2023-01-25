package com.GrowthPlus;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class LanguageSettingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_language_setting);

        Button backSet = findViewById(R.id.backLang);

        backSet.setOnClickListener(v -> startActivity(new Intent(LanguageSettingActivity.this, MainActivity.class)));
    }
}