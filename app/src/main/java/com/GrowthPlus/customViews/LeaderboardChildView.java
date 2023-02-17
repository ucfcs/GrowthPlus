package com.GrowthPlus.customViews;

import android.content.Context;
import android.content.res.ColorStateList;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;

import com.GrowthPlus.R;

public class LeaderboardChildView extends ConstraintLayout{
    private ImageView childAvatar;
    private TextView childName;
    private TextView childScore;

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
    }
}