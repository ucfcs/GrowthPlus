package com.GrowthPlus.customViews;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;

import com.GrowthPlus.R;

import com.GrowthPlus.dataAccessLayer.child.ChildSchema;
import com.GrowthPlus.utilities.ImageSrcIdentifier;

public class RoadMapTile extends ConstraintLayout {

    // Add fields for each component
    ImageView tile;
    TextView triangle;
    ImageView circle;
    ImageView star;
    ImageView avatar;
    Boolean isSelected;
    Boolean isCompleted;
    private ImageSrcIdentifier avatarImage;

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
        avatarImage = new ImageSrcIdentifier();
        tile = findViewById(R.id.tile);
        triangle = findViewById(R.id.roadmapTriangle);
        circle = findViewById(R.id.roadmapCircle);
        star = findViewById(R.id.roadmapStar);
        avatar = findViewById(R.id.avatar);
    }

    // TODO: Need methods to change the state of each view

    // Method to change the state of the tile
    public void setTileSelectedState(){
        tile.setImageResource(R.drawable.tile_shape_selected);
    }

    public void setTriangleSelectedState(){
        triangle.setTextColor(ContextCompat.getColor(getContext(), R.color.light_green));
    }

    // The path to this resource needs to come from the database
    // Have not figured it out yet
    public void setAvatar(String name){
        avatar.setImageResource(avatarImage.getImageSrcId(name));
    }

    public void removeAvatar(){
        avatar.setImageResource(0);
    }

    public void setToTriangle(){
        triangle.setVisibility(View.VISIBLE);
        circle.setVisibility(View.INVISIBLE);
        star.setVisibility(View.INVISIBLE);
    }

    public void setToCircle(){
        triangle.setVisibility(View.INVISIBLE);
        circle.setVisibility(View.VISIBLE);
        star.setVisibility(View.INVISIBLE);
    }

    public void setToStar(){
        triangle.setVisibility(View.INVISIBLE);
        circle.setVisibility(View.INVISIBLE);
        star.setVisibility(View.VISIBLE);
    }

    public void hideShape(){
        // TODO: HIDE SHAPE BRO
    }

    public void setSelectedState(ChildSchema child){
        setTileSelectedState();
        setTriangleSelectedState();
        hideShape();
        setAvatar(child.getAvatarName());
    }

    // Completed state once the child has reached the goal of the Lesson
    public void setCompletedState(){
        setTileSelectedState();
        setTriangleSelectedState();
        removeAvatar();
    }
}