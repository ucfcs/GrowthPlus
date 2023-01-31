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
        //as of right now I don't know if this even works
        //horizontalBarLevelTwo = findViewById(R.id.horizontalProgressBarLevelTwo);
        //horizontalBarLevelTwo.setBarLevelText("2");
    }
}