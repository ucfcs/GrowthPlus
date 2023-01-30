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

public class HorizontalProgressBarOne extends ConstraintLayout {

    ProgressBar horizontalProgressBarOne;
    TextView progressBarLevelNumberOne;

    public HorizontalProgressBarOne(@NonNull Context context) {
        super(context);
        init(null);
    }

    public HorizontalProgressBarOne(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public HorizontalProgressBarOne(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    public HorizontalProgressBarOne(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(attrs);
    }

    private void init(@Nullable AttributeSet attrs){
        inflate(getContext(), R.layout.horizontal_progress_bar_one, this);
        horizontalProgressBarOne = findViewById(R.id.horizontalProgressBarOne);
        progressBarLevelNumberOne = findViewById(R.id.progressBarLevelNumberOne);
    }

}
