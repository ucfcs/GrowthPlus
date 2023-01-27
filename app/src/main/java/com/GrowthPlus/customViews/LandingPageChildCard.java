package com.GrowthPlus.customViews;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.GrowthPlus.R;

public class LandingPageChildCard extends ConstraintLayout {

    private ChildAvatarComponent landingPageChildAvatar;
    private TextView landingPageChildName;

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
}
