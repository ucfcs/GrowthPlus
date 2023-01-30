package com.GrowthPlus.customViews;

import android.content.Context;
import android.content.res.ColorStateList;
import android.util.AttributeSet;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.GrowthPlus.R;

public class ChildAvatarComponent extends ConstraintLayout {

    ImageView childAvatarComponent;


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
        childAvatarComponent = findViewById(R.id.childAvatarComponent);
    }

    public void setImageResource(int resId){
        childAvatarComponent.setImageResource(resId);
    }

    public void setBackgroundResource(int resId){
        childAvatarComponent.setBackgroundResource(resId);
    }

    public void setBackgroundTintList (ColorStateList tint){
        childAvatarComponent.setBackgroundTintList(tint);
    }
}
