package com.fall2022_group20.customViews;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import com.fall2022_group20.R;

public class Tile extends View {

    private static final int TITLE_SIZE = 300;
    Resources resources;
    private RectF rect;
    private Paint tileFillPaint;
    private Paint tileStrokePaint;

    public Tile(Context context) {
        super(context);
        resources = getResources();

        init(null);
    }

    public Tile(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public Tile(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    public Tile(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(attrs);
    }

    public void init(@Nullable AttributeSet set){

        rect = new RectF();
        tileFillPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        tileStrokePaint = new Paint();
    }

    @Override
    protected void onDraw(Canvas canvas){

        rect.left = 10;
        rect.top = 10;
        rect.right = 220;
        rect.bottom = 130;

        setTileFillPaintBlue();

        tileStrokePaint.setStyle(Paint.Style.STROKE);
        tileStrokePaint.setColor(ContextCompat.getColor(getContext(), R.color.black));
        tileStrokePaint.setStrokeWidth(8);

        int cornerRadius = 30;
        canvas.drawRoundRect(rect, cornerRadius, cornerRadius, tileFillPaint);    // fill
        canvas.drawRoundRect(rect, cornerRadius, cornerRadius, tileStrokePaint);  // stroke

    }

    public void setTileFillPaintLightGreen(){
        tileFillPaint.setStyle(Paint.Style.FILL);
        tileFillPaint.setColor(ContextCompat.getColor(getContext(), R.color.light_green));
    }

    public void setTileFillPaintBlue(){
        tileFillPaint.setStyle(Paint.Style.FILL);
        tileFillPaint.setColor(ContextCompat.getColor(getContext(), R.color.blue));
    }
}
