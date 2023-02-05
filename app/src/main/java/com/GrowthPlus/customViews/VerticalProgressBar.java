package com.GrowthPlus.customViews;

import android.content.Context;
import android.content.res.ColorStateList;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.GrowthPlus.R;

public class VerticalProgressBar extends ConstraintLayout {

    ProgressBar verticalProgressBar;
    ImageView progressBarAvatar;

    public VerticalProgressBar(@NonNull Context context) {
        super(context);
        init(null);
    }

    public VerticalProgressBar(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public VerticalProgressBar(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    public VerticalProgressBar(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(attrs);
    }

    private void init(@Nullable AttributeSet attrs){

        inflate(getContext(), R.layout.vertical_progress_bar, this);
        verticalProgressBar = findViewById(R.id.verticalProgressBar);
        progressBarAvatar = findViewById(R.id.progressBarAvatar);
    }

    /*
     * set background tint of the avatar
     * */
    public void setBackgroundTintList (ColorStateList tint){
        progressBarAvatar.setBackgroundTintList(tint);
    }

    /*
     * set image source of the avatar
     * */
    public void setImageResource (int resId){
        progressBarAvatar.setImageResource(resId);
    }

    /*
    * Set new bar progress
    * */
    public void setProgress (int progress){
        verticalProgressBar.setProgress(progress);
    }

    /*
    * Set progress background tint, might be useful
    * */
    public void setProgressBackgroundTintList (ColorStateList tint){
        verticalProgressBar.setBackgroundTintList(tint);
    }
}
