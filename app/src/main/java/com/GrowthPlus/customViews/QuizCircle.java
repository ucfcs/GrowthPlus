package com.GrowthPlus.customViews;

import android.content.Context;
import android.content.res.ColorStateList;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;

import com.GrowthPlus.R;
import com.GrowthPlus.utilities.ColorIdentifier;

public class QuizCircle extends ConstraintLayout {
    ImageView circle;
    TextView number;
    ColorStateList green, red, blue, darkGreen;
    ColorIdentifier colorIdentifier;

    public QuizCircle(@NonNull Context context) {
        super(context);
        init(null);
        setColors();
    }

    public QuizCircle(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
        setColors();
    }

    public QuizCircle(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
        setColors();
    }

    public QuizCircle(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(attrs);
        setColors();
    }

    private void init(@Nullable AttributeSet set){
        inflate(getContext(), R.layout.custom_quiz_circle, this);
        circle = findViewById(R.id.circle);
        number = findViewById(R.id.number);
    }

    private void setColors(){
        green = ColorStateList.valueOf(ContextCompat.getColor(getContext(), R.color.light_green));
        red = ColorStateList.valueOf(ContextCompat.getColor(getContext(), R.color.red));
        blue = ColorStateList.valueOf(ContextCompat.getColor(getContext(), R.color.blue));
        darkGreen = ColorStateList.valueOf(ContextCompat.getColor(getContext(), R.color.dark_green));
    }

    public void setAnswer(String answer){
        number.setText(answer);
    }

    public String getAnswer(){
        return (String) number.getText();
    }

    public void correct(){
        circle.setBackgroundTintList(green);
    }

    public void correctQuiz3(){circle.setBackgroundTintList(darkGreen);}

    public void incorrect(){
        circle.setBackgroundTintList(red);
    }

    public void setBack(){
        circle.setBackgroundTintList(blue);
    }
}
