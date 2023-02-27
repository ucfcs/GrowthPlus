package com.GrowthPlus.customViews;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.GrowthPlus.R;
import com.GrowthPlus.dataAccessLayer.child.ChildSchemaService;

import io.realm.Realm;

public class TopBar extends ConstraintLayout {
    Button goBackButton;
    ImageView pointsIcon;
    TextView points;
    TextView triangle;
    ImageView circle;
    ImageView star;

    public TopBar(@NonNull Context context) {
        super(context);
        init(null);
    }

    public TopBar(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public TopBar(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    public TopBar(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(attrs);
    }

    private void init(@Nullable AttributeSet attrs) {
        inflate(getContext(), R.layout.custom_top_bar, this);
        goBackButton = findViewById(R.id.goBackBtn);
        pointsIcon = findViewById(R.id.pointsIcon);
        points = findViewById(R.id.points);
        triangle = findViewById(R.id.topBarTriangle);
        circle = findViewById(R.id.topBarCircle);
        star = findViewById(R.id.topBarStar);
    }

    public void setPoints(String number){
        points.setText(number);
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

    public void setShapeColor(int color){
        triangle.setTextColor(color);
        circle.setBackgroundTintList(ColorStateList.valueOf(color));
        star.setBackgroundTintList(ColorStateList.valueOf(color));
    }

    public void setPointIconBackground(int color){
        pointsIcon.setBackgroundTintList(ColorStateList.valueOf(color));
    }

    public void setPointsTextColor(int color){
        points.setTextColor(color);
    }
}