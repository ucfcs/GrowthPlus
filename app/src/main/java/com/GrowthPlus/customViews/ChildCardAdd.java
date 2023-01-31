package com.GrowthPlus.customViews;

import android.content.Context;
import android.content.res.ColorStateList;
import android.util.AttributeSet;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.GrowthPlus.R;

public class ChildCardAdd extends ConstraintLayout {

    private ImageView addChildCard;

    public ChildCardAdd(@NonNull Context context) {
        super(context);
        init(null);
    }

    public ChildCardAdd(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public ChildCardAdd(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    public ChildCardAdd(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(attrs);
    }

    private void init(@Nullable AttributeSet attrs){
        inflate(getContext(), R.layout.child_card_add, this);
        addChildCard = findViewById(R.id.addChildCard);
    }

    public void setBackgroundTintList (ColorStateList tint){
        addChildCard.setBackgroundTintList(tint);
    }
}
