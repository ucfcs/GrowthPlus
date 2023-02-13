package com.GrowthPlus.customViews;

import android.content.Context;
import android.content.res.ColorStateList;
import android.util.AttributeSet;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.GrowthPlus.R;
import com.GrowthPlus.utilities.ColorIdentifier;
import com.GrowthPlus.utilities.ImageSrcIdentifier;

public class ChildAvatarComponent extends ConstraintLayout {

    ImageView childAvatarImg;
    ImageView childAvatarColor;


    public ChildAvatarComponent(@NonNull Context context) {
        super(context);
    }

    public ChildAvatarComponent(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(null);
    }

    public ChildAvatarComponent(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    public ChildAvatarComponent(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(attrs);
    }

    private void init(@Nullable AttributeSet set) {
        inflate(getContext(), R.layout.child_avatar_component, this);
        childAvatarImg = findViewById(R.id.childAvatarImg);
        childAvatarColor = findViewById(R.id.childAvatarColor);
    }

    public void setImageResource(int resId){
        childAvatarImg.setImageResource(resId);
    }

    public void setBackgroundResource(int resId){
        childAvatarImg.setBackgroundResource(resId);
    }

    public void setBackgroundTintList (ColorStateList tint){
        childAvatarColor.setBackgroundTintList(tint);
    }
}
