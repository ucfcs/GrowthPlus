package com.GrowthPlus.customViews;

import android.content.Context;
import android.content.res.ColorStateList;
import android.util.AttributeSet;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.GrowthPlus.R;

public class Flashcard extends ConstraintLayout {
    ImageView flashcard;
    EditText answer;
    FrameLayout frame_layout;

    public Flashcard(@NonNull Context context) {
        super(context);
        init(null);
    }

    public Flashcard(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public Flashcard(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    public Flashcard(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(attrs);
    }

    private void init(@Nullable AttributeSet set){
        inflate(getContext(), R.layout.custom_flashcard, this);
        flashcard = findViewById(R.id.flashcard);
        answer = findViewById(R.id.answer);
        frame_layout = findViewById(R.id.frame_layout);
    }

    // TODO: Add methods to change color, image, etc.
    public void setFlashcardColor(ColorStateList tint){
        flashcard.setBackgroundTintList(tint);
    }

    public String getAnswer(){

        return answer.getText().toString();
    }

    /*
    * The type of data being placed in a text field,
    * used to help an input method decide how to let the user enter input.
    * 1 is text input only.
    * 2 is number input only.
    * */
    public void setRawInputType(int type){

        answer.setRawInputType(type);
    }

    public void setText(CharSequence text){
        answer.setText(text);
    }

    public void setAnswerVisibility(int visibility){
        answer.setVisibility(visibility);
    }

    public void setAnswerEnabled(boolean enabled){
        answer.setEnabled(enabled);
    }

    public void setAnswerOpacity(float alpha){
        answer.setAlpha(alpha);
    }
}
