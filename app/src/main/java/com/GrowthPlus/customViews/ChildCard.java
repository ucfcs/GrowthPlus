package com.GrowthPlus.customViews;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.GrowthPlus.R;

public class ChildCard extends ConstraintLayout {

    private ImageView childCardAvatar;
    private TextView childCardName;
    private ImageView childCardAdd;


    public ChildCard(@NonNull Context context) {
        super(context);
        init(null);
    }

    public ChildCard(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public ChildCard(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    public ChildCard(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(attrs);
    }

    private void init(@Nullable AttributeSet attrs){

        inflate(getContext(), R.layout.child_card, this);
        childCardAvatar = findViewById(R.id.childCardAvatar);
        childCardName = findViewById(R.id.childCarName);
        childCardAdd = findViewById(R.id.childCardAdd);
    }
}
