package com.fall2022_group20.customViews;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import com.fall2022_group20.R;

public class RoadMapTile extends View {

    private static final int TITLE_SIZE = 300;
    Resources resources;
    private Path roadMapTitle;
    private RectF rect;
    private Paint tileFillPaint;
    private Paint tileStrokePaint;

    private Paint trianglePaint;
    private Path trianglePath;


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

        roadMapTitle = new Path();
        rect = new RectF();
        tileFillPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        tileStrokePaint = new Paint();

        trianglePaint = new Paint();
        trianglePath = new Path();

    }

    @Override
    protected void onDraw(Canvas canvas){

        rect.left = 10;
        rect.top = 10;
        rect.right = 220;
        rect.bottom = 130;

        tileFillPaint.setStyle(Paint.Style.FILL);
        tileFillPaint.setColor(ContextCompat.getColor(getContext(), R.color.blue));

        tileStrokePaint.setStyle(Paint.Style.STROKE);
        tileStrokePaint.setColor(ContextCompat.getColor(getContext(), R.color.black));
        tileStrokePaint.setStrokeWidth(8);

        int cornerRadius = 30;
        canvas.drawRoundRect(rect, cornerRadius, cornerRadius, tileFillPaint);    // fill
        canvas.drawRoundRect(rect, cornerRadius, cornerRadius, tileStrokePaint);  // stroke

        drawTriangle(canvas, trianglePaint, 100,60, 60);

    }

    public void drawTriangle(Canvas canvas, Paint paint, int x, int y, int width) {
        int halfWidth = width / 2;

        paint.setStyle(Paint.Style.FILL);
        paint.setColor(ContextCompat.getColor(getContext(), R.color.yellow));

        Path path = new Path();
        path.moveTo(x, y - halfWidth); // Top
        path.lineTo(x - halfWidth, y + halfWidth); // Bottom left
        path.lineTo(x + halfWidth, y + halfWidth); // Bottom right
        path.lineTo(x, y - halfWidth); // Back to Top
        path.close();

        canvas.drawPath(path, paint);
    }

}
