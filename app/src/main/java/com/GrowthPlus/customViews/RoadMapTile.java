package com.GrowthPlus.customViews;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;

import com.GrowthPlus.R;

public class RoadMapTile extends ConstraintLayout {

    // Add fields for each component
    ImageView tile;
    TextView triangle;
    ImageView avatar;
    Boolean isSelected;
    Boolean isCompleted;

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
        inflate(getContext(), R.layout.activity_road_map_tile, this);
        tile = findViewById(R.id.tile);
        triangle = findViewById(R.id.triangle);
        avatar = findViewById(R.id.avatar);
    }

    // TODO: Need methods to change the state of each view

    // Method to change the state of the tile
    private void setTileSelectedState(){
        tile.setImageResource(R.drawable.tile_shape_selected);
    }

    private void setTriangleSelectedState(){
        triangle.setTextColor(ContextCompat.getColor(getContext(), R.color.light_green));
    }

    // The path to this resource needs to come from the database
    // Have not figured it out yet
    private void setAvatar(){
        avatar.setImageResource(R.mipmap.bunny_foreground);
    }

    private void removeAvatar(){
        avatar.setImageResource(0);
    }

    public void setSelectedState(){
        setTileSelectedState();
        setTriangleSelectedState();
        setAvatar();
    }

    // Completed state once the child has reached the goal of the lesson
    public void setCompletedState(){
        setTileSelectedState();
        setTriangleSelectedState();
        removeAvatar();
    }

}