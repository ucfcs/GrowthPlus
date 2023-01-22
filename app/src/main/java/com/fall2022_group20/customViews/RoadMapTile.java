package com.fall2022_group20.customViews;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.fall2022_group20.R;

public class RoadMapTile extends ConstraintLayout {

    // Add fields for each component
    ImageView tile;
    TextView triangle;
    ImageView avatar;


    public RoadMapTile(@NonNull Context context) {
        super(context);
        init(null);
    }

    public RoadMapTile(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public RoadMapTile(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    public RoadMapTile(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(attrs);
    }

    private void init(@Nullable AttributeSet set) {
        inflate(getContext(), R.layout.roadmap_tile, this);
        tile = findViewById(R.id.tile);
        triangle = findViewById(R.id.triangle);
        avatar = findViewById(R.id.avatar);
    }
}
