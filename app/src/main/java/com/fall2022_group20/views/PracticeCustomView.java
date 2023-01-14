package com.fall2022_group20.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

public class PracticeCustomView extends View {
    public PracticeCustomView(Context context) {
        super(context);

        init(null);
    }

    public PracticeCustomView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        init(attrs);
    }

    public PracticeCustomView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        init(attrs);
    }

    public PracticeCustomView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);

        init(attrs);
    }

    public void init(@Nullable AttributeSet set){

    }

    @Override
    protected void onDraw(Canvas canvas){

        Rect rect = new Rect();
        rect.left = 10;
        rect.top = 10;
        rect.right = rect.left + 10;
        rect.bottom = rect.top + 10;

        Paint paint = new Paint();
    }
}
