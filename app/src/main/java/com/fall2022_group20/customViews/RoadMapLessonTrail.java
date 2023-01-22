package com.fall2022_group20.customViews;

import android.content.Context;
import android.util.AttributeSet;

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

    }
}
