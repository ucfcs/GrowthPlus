package com.GrowthPlus.customViews;

import android.content.Context;
import android.content.res.ColorStateList;
import android.util.AttributeSet;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.GrowthPlus.R;

public class HorizontalProgressBarFour extends ConstraintLayout {

    ProgressBar horizontalProgressBarFour;
    TextView progressBarLevelNumberFour;

    public HorizontalProgressBarFour(@NonNull Context context) {
        super(context);
        init(null);
    }

    public HorizontalProgressBarFour(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public HorizontalProgressBarFour(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    public HorizontalProgressBarFour(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(attrs);
    }

    private void init(@Nullable AttributeSet attrs){
        inflate(getContext(), R.layout.horizontal_progress_bar_four, this);
        horizontalProgressBarFour = findViewById(R.id.horizontalProgressBarFour);
        progressBarLevelNumberFour = findViewById(R.id.progressBarLevelNumberFour);
    }


    public void setBarLevelText (CharSequence text){
        progressBarLevelNumberFour.setText(text);
    }

    //sets the color of the progress bar level number textview (levels 1, 2, 3, and 4 have different colors)
    public void setBarLevelColor (ColorStateList tint){
        progressBarLevelNumberFour.setBackgroundTintList(tint);
    }

    public void setHorizontalBarColor(ColorStateList list){
        horizontalProgressBarFour.setProgressTintList(list);
    }


    /*
    public void setBarLevelTextColor (int color){
        progressBarLevelNumber.setTextColor(color);
    }
    Note: theres another types of setTextColor method asking for a ColorStateList rather than an int
    Update: don't need this method anyway because text is blue for all 4 horizontal bars
    */
}
