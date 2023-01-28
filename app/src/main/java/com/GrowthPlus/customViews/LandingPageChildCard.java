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
        getCustomAttributes(context, null, 0);
    }

    public LandingPageChildCard(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
        getCustomAttributes(context, attrs, 0);
    }

    public LandingPageChildCard(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
        getCustomAttributes(context, attrs, defStyleAttr);
    }

    public LandingPageChildCard(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(attrs);
        getCustomAttributes(context, attrs, defStyleAttr);
    }

    private void init(@Nullable AttributeSet set){
        inflate(getContext(), R.layout.landing_page_child_card, this);
        landingPageChildAvatar = findViewById(R.id.landingPageChildAvatar);
        landingPageChildName = findViewById(R.id.landingPageChildName);
    }

    /*
    * Gets custom attributes unique to Landing page child card"
    * */

    private void getCustomAttributes(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {

        TypedArray typedArray = null;
        if (attrs != null) {

            typedArray = context.getTheme().obtainStyledAttributes(attrs, R.styleable.LandingPageChildCard, defStyleAttr, 0);
            childName = typedArray.getString(R.styleable.LandingPageChildCard_childName);
            nameBackground = typedArray.getColorStateList(R.styleable.LandingPageChildCard_nameBackgroundColor);
            avatarBackgroundColor = typedArray.getColorStateList(R.styleable.LandingPageChildCard_avatarBackgroundColor);
            avatarSrc = typedArray.getResourceId(R.styleable.LandingPageChildCard_avatarSrc, R.mipmap.bunny_foreground);
        }

        setChildCardName(childName);
        setNameBackground(nameBackground);
        setAvatarBackgroundColor(avatarBackgroundColor);
        setAvatarSrc(avatarSrc);

      typedArray.recycle();
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
