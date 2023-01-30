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

public class HorizontalProgressBarThree extends ConstraintLayout {

    ProgressBar horizontalProgressBarThree;
    TextView progressBarLevelNumberThree;

    public HorizontalProgressBarThree(@NonNull Context context) {
        super(context);
        init(null);
    }

    public HorizontalProgressBarThree(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public HorizontalProgressBarThree(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    public HorizontalProgressBarThree(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(attrs);
    }

    private void init(@Nullable AttributeSet attrs){
        inflate(getContext(), R.layout.horizontal_progress_bar_three, this);
        horizontalProgressBarThree = findViewById(R.id.horizontalProgressBarThree);
        progressBarLevelNumberThree = findViewById(R.id.progressBarLevelNumberThree);
    }

}
