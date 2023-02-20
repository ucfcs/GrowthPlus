package com.GrowthPlus.customViews;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.GrowthPlus.R;

public class CustomTimerComponent extends ConstraintLayout {
    ImageView timerCircle;
    TextView timerText;

    public CustomTimerComponent(@NonNull Context context) {
        super(context);
        init(null);
    }

    public CustomTimerComponent(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public CustomTimerComponent(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    public CustomTimerComponent(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(attrs);
    }

    private void init(@Nullable AttributeSet set){
        inflate(getContext(), R.layout.custom_timer_component, this);
        timerCircle = findViewById(R.id.timerCircle);
        timerText = findViewById(R.id.timerText);
    }

    public void setTimerText (CharSequence text){
        timerText.setText(text);
    }

}
