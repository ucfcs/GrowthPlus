package com.GrowthPlus.customViews;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.os.Build;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.GrowthPlus.R;

import java.lang.ref.Reference;

public class LandingPageChildCard extends ConstraintLayout {

    private ImageView landingPageChildAvatar;
    private TextView landingPageChildName;
    private String childName;
    private ColorStateList nameBackground;
    private ColorStateList avatarBackgroundColor;
    private int avatarSrc;


    public LandingPageChildCard(@NonNull Context context) {
        super(context);
        init(null);
    }

    public LandingPageChildCard(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public LandingPageChildCard(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    public LandingPageChildCard(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(attrs);
    }

    private void init(@Nullable AttributeSet set){
        inflate(getContext(), R.layout.landing_page_child_card, this);
        landingPageChildAvatar = findViewById(R.id.landingPageChildAvatar);
        landingPageChildName = findViewById(R.id.landingPageChildName);
    }


    public void setChildCardName(String name){

        landingPageChildName.setText(name);
    }

    public void setNameBackground (ColorStateList color){
        landingPageChildName.setBackgroundTintList(color);
    }

    public void setAvatarBackgroundColor(ColorStateList color){
        landingPageChildAvatar.setBackgroundTintList(color);
    }

    public void setAvatarSrc(int imageResource){
        landingPageChildAvatar.setImageResource(imageResource);
    }

}
