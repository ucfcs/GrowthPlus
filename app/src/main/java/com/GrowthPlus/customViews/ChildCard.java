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

public class ChildCard extends ConstraintLayout {

    private ImageView childCardAvatar;
    private TextView childCardName;


    public ChildCard(@NonNull Context context) {
        super(context);
        init(null);
    }

    public ChildCard(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public ChildCard(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    public ChildCard(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(attrs);
    }

    private void init(@Nullable AttributeSet attrs){

        inflate(getContext(), R.layout.child_card, this);
        childCardAvatar = findViewById(R.id.childCardAvatar);
        childCardName = findViewById(R.id.childCarName);

        ColorStateList color = ContextCompat.getColorStateList(getContext(), R.color.red);

        setBackgroundTintList(color);
        setImageResource(R.mipmap.camel_foreground);
        setTextColor(color);
        setText("Pablo");
    }

    /*
    * Change the circle background color of the child card
    * */
    public void setBackgroundTintList (ColorStateList tint){
        childCardAvatar.setBackgroundTintList(tint);
    }

    /*
    * Change the image source of the child card
    * */
    public void setImageResource (int resId){

        childCardAvatar.setImageResource(resId);
    }

    /*
    * Change the name of the child card
    * */
    public final void setText (CharSequence newName){

        childCardName.setText(newName);
    }

    /*
    * Change the color of the text
    * */
    public void setTextColor (ColorStateList color){

        childCardName.setTextColor(color);
    }

    /**
     * Methods to set the component's height
     */


    /**
     * Methods to set the component's width
     */

}
