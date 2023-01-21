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
        roadMapTile1 = findViewById(R.id.tile1);
        roadMapTile1.findViewById(R.id.avatar).setBackground(null);

        roadMapTile2 = findViewById(R.id.tile2);
        roadMapTile2.findViewById(R.id.avatar).setBackground(null);

        roadMapTile3 = findViewById(R.id.tile3);
        roadMapTile3.findViewById(R.id.avatar).setBackground(null);

        roadMapTile4 = findViewById(R.id.tile4);
        roadMapTile4.findViewById(R.id.avatar).setBackground(null);

        roadMapTile5 = findViewById(R.id.tile5);
        roadMapTile5.findViewById(R.id.avatar).setBackground(null);

        roadMapTile6 = findViewById(R.id.tile6);
        roadMapTile6.findViewById(R.id.avatar).setBackground(null);

        roadMapTile7 = findViewById(R.id.tile7);
        roadMapTile7.findViewById(R.id.avatar).setBackground(null);

        roadMapTile8 = findViewById(R.id.tile8);
        roadMapTile8.findViewById(R.id.avatar).setBackground(null);

        roadMapTile9 = findViewById(R.id.tile9);
        roadMapTile9.findViewById(R.id.avatar).setBackground(null);

        roadMapTile10 = findViewById(R.id.tile10);
        roadMapTile10.findViewById(R.id.avatar).setBackground(null);

        roadMapTile11 = findViewById(R.id.tile11);
        roadMapTile11.findViewById(R.id.avatar).setBackground(null);

        roadMapTile12 = findViewById(R.id.tile12);
        roadMapTile12.findViewById(R.id.avatar).setBackground(null);

        roadMapTile13 = findViewById(R.id.tile13);
        roadMapTile13.findViewById(R.id.avatar).setBackground(null);

    }
}
