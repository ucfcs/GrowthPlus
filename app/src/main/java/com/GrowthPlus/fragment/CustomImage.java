package com.GrowthPlus.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.gridlayout.widget.GridLayout;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.GrowthPlus.R;
import com.GrowthPlus.utilities.ImageSrcIdentifier;

public class CustomImage extends Fragment {
    View view;
    GridLayout customImageGrid;
    ImageSrcIdentifier imageSrcIdentifier;
    Float sizeInPixels;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_custom_image, container, false);
        customImageGrid = view.findViewById(R.id.customImageGrid);
        imageSrcIdentifier = new ImageSrcIdentifier();
        sizeInPixels  = getResources().getDimension(R.dimen.elephantSize);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        String image = requireArguments().getString("image");
        String firstNumber = requireArguments().getString("firstNumber");
        String answer = requireArguments().getString("answer");
        Boolean isAnimationDone = requireArguments().getBoolean("isAnimationDone");
        int imgNum = Integer.parseInt(firstNumber);
        int resId = imageSrcIdentifier.getImageSrcId(image);

        if(isAnimationDone){
            // Display answer only
            TextView answerView = setAnswerView(sizeInPixels.intValue(), sizeInPixels.intValue(), answer);
            customImageGrid.removeAllViews();
            customImageGrid.addView(answerView);

        }else {
            // Check num of image and map to the size of images
            for(int i=0; i<imgNum; i++){
                // width and height are pixels not dp, so need to convert from dp to pixels
                ImageView imageTemp = setImageView(resId, sizeInPixels.intValue(), sizeInPixels.intValue());
                customImageGrid.addView(imageTemp, i);
            }
        }
    }

    public ImageView setImageView(int resId, int width, int height){
        ImageView imageTemp = new ImageView(getActivity());
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(width, height);
        imageTemp.setLayoutParams(layoutParams);
        imageTemp.setImageResource(resId);

        return imageTemp;
    }

    public TextView setAnswerView(int width, int height, CharSequence charSequence){
        TextView text = new TextView(getActivity());
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(width, height);
        text.setLayoutParams(layoutParams);
        text.setTextColor(getResources().getColor(R.color.blue));
        text.setText(charSequence);

        return text;
    }
}