package com.GrowthPlus.customViews;

import android.content.Context;
import android.content.res.ColorStateList;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.GrowthPlus.R;

public class QuizCircle extends ConstraintLayout {
    ImageView circle;
    TextView number;

    public QuizCircle(@NonNull Context context) {
        super(context);
        init(null);
    }

    public QuizCircle(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public QuizCircle(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    public QuizCircle(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(attrs);
    }

    private void init(@Nullable AttributeSet set){
        inflate(getContext(), R.layout.custom_quiz_circle, this);
        circle = findViewById(R.id.circle);
        number = findViewById(R.id.number);
    }

    // TODO: Add methods to change color and number
    //These are methods I (Mack) have used in my own custom components, they could help
    public void setBackgroundTintList (ColorStateList tint){
        circle.setBackgroundTintList(tint);
    }

    public void setQuizCircleNumber (CharSequence text){
        number.setText(text);
    }
}
