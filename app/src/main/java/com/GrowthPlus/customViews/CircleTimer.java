package com.GrowthPlus.customViews;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import androidx.core.content.ContextCompat;

import com.GrowthPlus.R;
import com.GrowthPlus.utilities.ColorIdentifier;

public class CircleTimer extends View{
    private Paint fillPaint;
    private Paint borderPaint;
    private float sweepAngle;

    public CircleTimer(Context context, AttributeSet attrs){
        super(context, attrs);
        init();
    }

    private void init(){
        String red = "#DD6157";
        String blue = "#60A3C8";
        int redInt = Color.parseColor(red);
        int blueInt = Color.parseColor(blue);

        fillPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        fillPaint.setColor(blueInt);
        fillPaint.setStyle(Paint.Style.FILL);

        borderPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        borderPaint.setColor(redInt);
        borderPaint.setStyle(Paint.Style.STROKE);
        borderPaint.setStrokeWidth(11f);
    }

    public void setProgress(float progress){
        sweepAngle = 360f * progress;
        invalidate();  // redraw circle
    }

    @Override
    protected void onDraw(Canvas canvas){
        super.onDraw(canvas);
        int width = getWidth() - getPaddingLeft() - getPaddingRight();
        int height = getHeight() - getPaddingTop() - getPaddingBottom();
        int size = Math.min(width, height);
        float centerX = getWidth() / 2f;
        float centerY = getHeight() / 2f;

        // circle border
        canvas.drawCircle(centerX, centerY, size / 2.3f, borderPaint);

        // fill circle
        float radius = (size - 2 * borderPaint.getStrokeWidth()) / 2.3f; // subtract border width
        canvas.drawArc(
                centerX - radius, centerY - radius,
                centerX + radius, centerY + radius,
                -90, -sweepAngle, true, fillPaint);
    }
}