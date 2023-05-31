package com.GrowthPlus.customViews;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.GrowthPlus.R;

public class LanguageButtons extends FrameLayout {
    RelativeLayout english;
    RelativeLayout french;
    RelativeLayout chadianArabic;
    RelativeLayout lagwan;
    RelativeLayout mousgoum;
    RelativeLayout massana;
    RelativeLayout musey;

    public LanguageButtons(@NonNull Context context) {
        super(context);
        init(null);
    }

    public LanguageButtons(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public LanguageButtons(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    public LanguageButtons(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(attrs);
    }

    private void init(@Nullable AttributeSet set){
        inflate(getContext(), R.layout.custom_language_buttons, this);
        english = findViewById(R.id.englishBtn);
        french = findViewById(R.id.frenchBtn);
        chadianArabic = findViewById(R.id.chadBtn);
        lagwan = findViewById(R.id.lagwanBtn);
        mousgoum = findViewById(R.id.mousgoumBtn);
        massana = findViewById(R.id.massanaBtn);
        musey = findViewById(R.id.museyBtn);
    }

}
