package com.GrowthPlus.customViews;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.GrowthPlus.R;

public class ChildNameScoreComponent extends ConstraintLayout {

    TextView childNameTextView;
    TextView childScoreTextView;

    public ChildNameScoreComponent(@NonNull Context context) {
        super(context);
        init(null);
    }

    public ChildNameScoreComponent(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public ChildNameScoreComponent(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    public ChildNameScoreComponent(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(attrs);
    }

    private void init(@Nullable AttributeSet set) {
        inflate(getContext(), R.layout.child_name_score_component, this);
        childNameTextView = findViewById(R.id.childName);
        childScoreTextView = findViewById(R.id.childScore);
    }

    //There are multiple ".setText" methods, I'm not sure which is the best fit for us
    public void setChildNameText (CharSequence text){
        childNameTextView.setText(text);
    }

    public void setChildScoreText (CharSequence text){
        childScoreTextView.setText(text);
    }

}