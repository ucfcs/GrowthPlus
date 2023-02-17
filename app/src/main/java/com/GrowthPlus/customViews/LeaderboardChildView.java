package com.GrowthPlus.customViews;

import android.content.Context;
import android.content.res.ColorStateList;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.GrowthPlus.R;

public class LeaderboardChildView extends ConstraintLayout{
    private ImageView childAvatar;
    private TextView childName;
    private TextView childScore;
    private View nameBlock;

    public LeaderboardChildView(@NonNull Context context) {
        super(context);
        init(null);
    }

    public LeaderboardChildView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public LeaderboardChildView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    public LeaderboardChildView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(attrs);
    }

    private void init(@Nullable AttributeSet set ){
        inflate(getContext(), R.layout.leaderboard_child_view, this);
        childAvatar = findViewById(R.id.leaderboardAvatarImg);
        childName = findViewById(R.id.leaderboardName);
        childScore = findViewById(R.id.leaderboardPoints);
        nameBlock = findViewById(R.id.nameBlock);
    }

    //childAvatar is ImgView with the avatar img to be changed
    public void setImageResource (int resId){
        childAvatar.setImageResource(resId);
    }
    //childName is the TextView with the name that needs to be changed
    public void setName (CharSequence newName){
        childName.setText(newName);
    }
    //nameBlock is a View contains the way to change the entire background of the card
    public void setBackgroundTintList (ColorStateList tint){
        nameBlock.setBackgroundTintList(tint);
    }
    //childScore is a TextView that contains text that needs to be changed
    public void setPoints (CharSequence points){
        childScore.setText(points);
    }
}