package com.GrowthPlus.customViews;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.GrowthPlus.R;
import com.GrowthPlus.dataAccessLayer.child.ChildSchema;
import com.GrowthPlus.dataAccessLayer.child.ChildSchemaService;

import io.realm.Realm;

public class TopBar extends ConstraintLayout {
    Button goBackButton;
    ImageView pointsIcon;
    TextView points;
    ChildSchemaService childSchemaService;
    Realm realm;

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
        inflate(getContext(), R.layout.activity_top_bar, this);
        realm = Realm.getDefaultInstance();
        goBackButton = findViewById(R.id.goBackBtn);
        pointsIcon = findViewById(R.id.pointsIcon);
        points = findViewById(R.id.points);
        childSchemaService = new ChildSchemaService(realm);
    }

    public void setPoints(String number){
        points.setText(number);
    }
}