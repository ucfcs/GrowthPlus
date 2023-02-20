package com.GrowthPlus.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.gridlayout.widget.GridLayout;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.GrowthPlus.R;
import com.GrowthPlus.utilities.ImageSrcIdentifier;

public class QuizImage extends Fragment {
    View view;
    GridLayout imageGrid;
    ImageSrcIdentifier imageSrcIdentifier;
    Float sizeInPixels;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_quiz_image, container, false);
        imageGrid = view.findViewById(R.id.quizImageGrid);
        imageSrcIdentifier = new ImageSrcIdentifier();
        sizeInPixels  = getResources().getDimension(R.dimen.elephantLarge);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        String getImg = requireArguments().getString("image");
        int num = requireArguments().getInt("imgNum");

        // Adjust grid rows and columns based on num of images
        if(num <= 4){
            sizeInPixels  = getResources().getDimension(R.dimen.elephantXLarge);
            imageGrid.setRowCount(2);
            imageGrid.setColumnCount(2);
        }
        else if(num <= 6){
            sizeInPixels  = getResources().getDimension(R.dimen.elephantXLarge);
            imageGrid.setRowCount(2);
            imageGrid.setColumnCount(3);
        }
        else if(num == 9){
            sizeInPixels  = getResources().getDimension(R.dimen.elephantLarge);
            imageGrid.setRowCount(3);
            imageGrid.setColumnCount(3);
        }
        else if(num == 10){
            sizeInPixels  = getResources().getDimension(R.dimen.elephantSize);
            imageGrid.setRowCount(2);
            imageGrid.setColumnCount(5);
        }

        int resId = imageSrcIdentifier.getImageSrcId(getImg);
        for(int i=0; i<num; i++){
            // width and height are pixels not dp, so need to convert from dp to pixels
            ImageView imageTemp = setImageView(resId, sizeInPixels.intValue(), sizeInPixels.intValue());
            imageGrid.addView(imageTemp, i);
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