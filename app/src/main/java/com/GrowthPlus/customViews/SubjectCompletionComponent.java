package com.GrowthPlus.customViews;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.GrowthPlus.R;

public class SubjectCompletionComponent extends ConstraintLayout {

    TextView subjectName;
    ImageView checkGreenCircle;
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
        checkGreenCircle = findViewById(R.id.checkGreenCircle);
        checkMark = findViewById(R.id.checkMark);
    }

}
