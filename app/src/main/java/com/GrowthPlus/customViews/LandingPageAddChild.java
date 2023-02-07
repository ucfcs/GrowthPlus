package com.GrowthPlus.customViews;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.util.AttributeSet;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;

import com.GrowthPlus.R;

public class LandingPageAddChild extends ConstraintLayout {
    private ImageView landingPageAddChild;
    private ImageView landingPageAddChildCircle;

    public LandingPageAddChild(@NonNull Context context) {
        super(context);
        init(null);
    }

    public LandingPageAddChild(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public LandingPageAddChild(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    public LandingPageAddChild(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(attrs);
    }

    private void init(@Nullable AttributeSet set ){
        inflate(getContext(), R.layout.landing_page_add_child, this);
        landingPageAddChild = findViewById(R.id.landingPageAddChild);
        landingPageAddChildCircle = findViewById(R.id.landingPageAddChildCircle);

    }

    // Set color of circle background
    public void setCircleColor(ColorStateList color){
        landingPageAddChildCircle.setBackgroundTintList(color);
    }

    // Set color of add icon
    public void setAddIconColor(ColorStateList color){
        landingPageAddChild.setBackgroundTintList(color);
    }
}
