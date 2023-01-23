package com.fall2022_group20.customViews;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.fall2022_group20.R;

public class RoadMapLessonTrail extends ConstraintLayout {

    private RoadMapTile roadMapTile1;
    private RoadMapTile roadMapTile2;
    private RoadMapTile roadMapTile3;
    private RoadMapTile roadMapTile4;
    private RoadMapTile roadMapTile5;
    private RoadMapTile roadMapTile6;
    private RoadMapTile roadMapTile7;
    private RoadMapTile roadMapTile8;
    private RoadMapTile roadMapTile9;
    private RoadMapTile roadMapTile10;
    private RoadMapTile roadMapTile11;
    private RoadMapTile roadMapTile12;
    private RoadMapTile roadMapTile13;
    private Boolean isLocked;
    private ImageView lock;

    public Boolean getLocked() {
        return isLocked;
    }

    public void setLocked(Boolean locked) {
        isLocked = locked;
    }

    public RoadMapLessonTrail(@NonNull Context context) {
        super(context);
        init(null);
    }

    public RoadMapLessonTrail(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public RoadMapLessonTrail(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    public RoadMapLessonTrail(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(attrs);
    }

    public RoadMapTile getRoadMapTile1() {
        return roadMapTile1;
    }

    public RoadMapTile getRoadMapTile2() {
        return roadMapTile2;
    }

    public RoadMapTile getRoadMapTile3() {
        return roadMapTile3;
    }

    public RoadMapTile getRoadMapTile4() {
        return roadMapTile4;
    }

    public RoadMapTile getRoadMapTile5() {
        return roadMapTile5;
    }

    public RoadMapTile getRoadMapTile6() {
        return roadMapTile6;
    }

    public RoadMapTile getRoadMapTile7() {
        return roadMapTile7;
    }

    public RoadMapTile getRoadMapTile8() {
        return roadMapTile8;
    }

    public RoadMapTile getRoadMapTile9() {
        return roadMapTile9;
    }

    public RoadMapTile getRoadMapTile10() {
        return roadMapTile10;
    }

    public RoadMapTile getRoadMapTile11() {
        return roadMapTile11;
    }

    public RoadMapTile getRoadMapTile12() {
        return roadMapTile12;
    }

    public RoadMapTile getRoadMapTile13() {
        return roadMapTile13;
    }

    private void init(@Nullable AttributeSet set) {

        inflate(getContext(), R.layout.roadmap_lesson_trail, this);
        roadMapTile1 = findViewById(R.id.roadMapTileOne);

        roadMapTile2 = findViewById(R.id.roadMapTileTwo);

        roadMapTile3 = findViewById(R.id.roadMapTileThree);

        roadMapTile4 = findViewById(R.id.roadMapTileFour);

        roadMapTile5 = findViewById(R.id.roadMapTileFive);

        roadMapTile6 = findViewById(R.id.roadMapTileSix);

        roadMapTile7 = findViewById(R.id.roadMapTileSeven);

        roadMapTile8 = findViewById(R.id.roadMapTileEight);

        roadMapTile9 = findViewById(R.id.roadMapTileNine);

        roadMapTile10 = findViewById(R.id.roadMapTileTen);

        roadMapTile11 = findViewById(R.id.roadMapTileEleven);

        roadMapTile12 = findViewById(R.id.roadMapTileTwelve);

        roadMapTile13 = findViewById(R.id.roadMapTileThirteen);

        lock = findViewById(R.id.lock);

    }

    public void roadMapInitState(){
        setLocked(true);
    }

    public void setSelectedState(RoadMapTile roadMapTile){
        roadMapTile.setSelectedState();
    }

    public void unLockRoadMap(){
        setLocked(false);
        lock.setImageResource(0);
    }
}
