package com.GrowthPlus.customViews;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.GrowthPlus.R;

public class FishMirror extends ConstraintLayout {
    ImageView fish;
    TextView number;

    public FishMirror(@NonNull Context context) {
        super(context);
        init(null);
    }

    public FishMirror(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public FishMirror(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    public FishMirror(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(attrs);
    }

    private void init(@Nullable AttributeSet set){
        inflate(getContext(), R.layout.custom_fish_mirror, this);
        fish = findViewById(R.id.fish);
        number = findViewById(R.id.number);
    }

    public void setNumber(String question){
        number.setText(question);
    }

    public String getNumber(){
        return String.valueOf(number.getText());
    }
}
