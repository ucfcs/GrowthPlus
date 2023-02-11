package com.GrowthPlus.customViews;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.GrowthPlus.R;
import com.GrowthPlus.utilities.ImageSrcIdentifier;

public class CustomEquation extends ConstraintLayout {
    ImageView bar;
    TextView num1, num2, operator;

    public CustomEquation(@NonNull Context context) {
        super(context);
        init(null);
    }

    public CustomEquation(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public CustomEquation(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    public CustomEquation(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(attrs);
    }

    private void init(@Nullable AttributeSet set){
        inflate(getContext(), R.layout.custom_equation, this);
        bar = findViewById(R.id.bar);
        num1 = findViewById(R.id.first_number);
        num2 = findViewById(R.id.second_number);
        operator = findViewById(R.id.operator);
    }

    // TODO: Add method to change text
}
