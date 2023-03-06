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

public class SubjectCompletionComponent extends ConstraintLayout {

    TextView subjectName;
    ImageView checkMark;

    public SubjectCompletionComponent(@NonNull Context context) {
        super(context);
        init(null);
    }

    public SubjectCompletionComponent(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public SubjectCompletionComponent(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    public SubjectCompletionComponent(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(attrs);
    }

    private void init(@Nullable AttributeSet set) {
        inflate(getContext(), R.layout.subject_completion_component, this);
        subjectName = findViewById(R.id.subjectName);
        checkMark = findViewById(R.id.checkMark);
    }

    public final void setText (CharSequence text){
        subjectName.setText(text);
    }

    public void setBackgroundTintList (ColorStateList tint){
        checkMark.setBackgroundTintList(tint);
    }

    public void setImageResource (int resId){
        checkMark.setImageResource(resId);
    }

    public void setSubjectCompletion(CharSequence text, Integer totalNum ,Integer numCompleted){
        setText(text);
        double percentage = ((double) numCompleted / (double) totalNum);
        double progress = percentage * 100;
        boolean completed = progress >= 75;

        if(completed){
            setImageResource(R.drawable.ic_check_mark);
            setBackgroundTintList(ContextCompat.getColorStateList(getContext(), R.color.light_green));
        }else {
            setImageResource(R.drawable.ic_baseline_close);
            setBackgroundTintList(ContextCompat.getColorStateList(getContext(), R.color.red));
        }
    }

}
