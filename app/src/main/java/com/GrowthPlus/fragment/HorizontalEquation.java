package com.GrowthPlus.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.gridlayout.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.GrowthPlus.R;
import com.GrowthPlus.utilities.ImageSrcIdentifier;

public class HorizontalEquation extends Fragment {
    View view;
    TextView text1;
    ImageSrcIdentifier imageSrcIdentifier;
    Float sizeInPixels;
    GridLayout layout1, layout2, layout3;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_horizontal_equation, container, false);
        text1 = view.findViewById(R.id.equation);
        layout1 = view.findViewById(R.id.gridLayoutHorizontalEquationOne);
        layout2 = view.findViewById(R.id.gridLayoutHorizontalEquationTwo);
        layout3 = view.findViewById(R.id.gridLayoutHorizontalEquationThree);
        imageSrcIdentifier = new ImageSrcIdentifier();
        sizeInPixels = getResources().getDimension(R.dimen.elephantSmall);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        String t1 = requireArguments().getString("text1");
        String getImg = requireArguments().getString("lessonImg");
        int getNumber1 = requireArguments().getInt("num1"); // How many of first image
        int getNumber2 = requireArguments().getInt("num2"); // How many of second image
        int getNumber3 = requireArguments().getInt("num3"); // How many of third image
        int resId = imageSrcIdentifier.getImageSrcId(getImg);

        text1.setText(t1);
        for(int i=0; i<getNumber1; i++){
            // width and height are pixels not dp, so need to convert from dp to pixels
            ImageView imageTemp = setImageView(resId, sizeInPixels.intValue(), sizeInPixels.intValue());
            layout1.addView(imageTemp, i);
        }
        for(int i=0; i<getNumber2; i++){
            // width and height are pixels not dp, so need to convert from dp to pixels
            ImageView imageTemp = setImageView(resId, sizeInPixels.intValue(), sizeInPixels.intValue());
            layout2.addView(imageTemp, i);
        }
        for(int i=0; i<getNumber3; i++){
            // width and height are pixels not dp, so need to convert from dp to pixels
            ImageView imageTemp = setImageView(resId, sizeInPixels.intValue(), sizeInPixels.intValue());
            layout3.addView(imageTemp, i);
        }
    }

    public ImageView setImageView(int resId, int width, int height){
        ImageView imageTemp = new ImageView(getActivity());
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(width, height);
        imageTemp.setLayoutParams(layoutParams);
        imageTemp.setImageResource(resId);

        return imageTemp;
    }
}