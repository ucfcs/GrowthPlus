package com.GrowthPlus.customViews;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.GrowthPlus.R;

public class CustomImageOperator extends ConstraintLayout {
    ImageView first_image, second_image;
    TextView operator;

    public CustomImageOperator(@NonNull Context context) {
        super(context);
        init(null);
    }

    public CustomImageOperator(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public CustomImageOperator(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    public CustomImageOperator(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(attrs);
    }

    private void init(@Nullable AttributeSet set){
        inflate(getContext(), R.layout.custom_image_operator, this);
        first_image = findViewById(R.id.first_image);
        second_image = findViewById(R.id.second_image);
        operator = findViewById(R.id.operator);
    }

    //TODO: Add functions to change text and images
}
