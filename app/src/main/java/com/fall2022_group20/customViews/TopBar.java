package com.fall2022_group20.customViews;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.fall2022_group20.R;

public class TopBar extends ConstraintLayout {

    ImageButton goBackButton;
    ImageView pointsIcon;
    TextView points;

    public TopBar(@NonNull Context context) {
        super(context);
        init(null);
    }

    public TopBar(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public TopBar(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    public TopBar(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(attrs);
    }

    private void init(@Nullable AttributeSet attrs) {

        inflate(getContext(), R.layout.top_bar, this);
        goBackButton = findViewById(R.id.goBackBtn);
        pointsIcon = findViewById(R.id.pointsIcon);
        points = findViewById(R.id.points);
    }


}
