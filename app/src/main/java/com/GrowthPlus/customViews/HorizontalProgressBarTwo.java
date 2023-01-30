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

public class HorizontalProgressBarTwo extends ConstraintLayout {

    ProgressBar horizontalProgressBarTwo;
    TextView progressBarLevelNumberTwo;

    public HorizontalProgressBarTwo(@NonNull Context context) {
        super(context);
        init(null);
    }

    public HorizontalProgressBarTwo(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public HorizontalProgressBarTwo(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    public HorizontalProgressBarTwo(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(attrs);
    }

    private void init(@Nullable AttributeSet attrs){
        inflate(getContext(), R.layout.horizontal_progress_bar_two, this);
        horizontalProgressBarTwo = findViewById(R.id.horizontalProgressBarTwo);
        progressBarLevelNumberTwo = findViewById(R.id.progressBarLevelNumberTwo);
    }


}
