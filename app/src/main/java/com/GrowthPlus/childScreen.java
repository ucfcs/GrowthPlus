package com.GrowthPlus;

import android.content.res.ColorStateList;
import android.os.Bundle;
import android.util.AttributeSet;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.GrowthPlus.customViews.HorizontalProgressBar;

public class childScreen extends AppCompatActivity {

    private HorizontalProgressBar horizontalBarLevelOne;
    private HorizontalProgressBar horizontalBarLevelTwo;
    private HorizontalProgressBar horizontalBarLevelThree;
    private HorizontalProgressBar horizontalBarLevelFour;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_child_screen);
    }

    private void init(@Nullable AttributeSet set) {
        horizontalBarLevelTwo = findViewById(R.id.horizontalBarLevelTwo);
        horizontalBarLevelTwo.setBarLevelText("2");
    }
}