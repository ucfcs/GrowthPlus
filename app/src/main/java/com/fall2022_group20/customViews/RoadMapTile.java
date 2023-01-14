package com.fall2022_group20.customViews;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import com.fall2022_group20.R;

public class RoadMapTile extends View {

    private static final int TITLE_SIZE = 300;
    Resources resources;
    private Boolean isComplete;


    public RoadMapTile(Context context) {
        super(context);
        resources = getResources();

        init(null);
    }

    public RoadMapTile(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        init(attrs);
    }

    public RoadMapTile(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        init(attrs);
    }

    public RoadMapTile(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);

        init(attrs);
    }

    public void init(@Nullable AttributeSet set){

    }

    @Override
    protected void onDraw(Canvas canvas){

        Rect rect = new Rect();
        rect.left = TITLE_SIZE;
        rect.top = TITLE_SIZE;
        rect.right = rect.left + 100;
        rect.bottom = rect.top + 100;

        Paint paint = new Paint();
        paint.setColor(ContextCompat.getColor(getContext(), R.color.blue));

        canvas.drawRect(rect,paint);

    }
}
