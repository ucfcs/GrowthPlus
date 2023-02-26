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
        String answer = requireArguments().getString("answer");
        int imgNum = Integer.parseInt(answer);
        int resId = imageSrcIdentifier.getImageSrcId(image);

        if(image.equals("unitOfTen")){
            imgNum = imgNum / 10;
        }
        else if(image.equals("unitOfOneHundred")){
            imgNum = imgNum / 100;
        }
        else if(image.equals("unitOfOneThousand")){
            imgNum = imgNum / 1000;
        }
        else if(image.equals("unitOfTenThousand")){
            imgNum = imgNum / 10000;
        }
        else if(image.equals("fiveFrancs") || image.equals("tenFrancs") || image.equals("fiftyFrancs") || image.equals("oneHundredFrancs") || image.equals("fiveThousandFrancs") || image.equals("tenThousandFrancs")
        || image.equals("rectangleAngles") || image.equals("equTriangleAngles") || image.equals("squareAngles") || image.equals("rightTriangleAngles")){
            imgNum = 1;
            sizeInPixels  = getResources().getDimension(R.dimen.elephantXLarge);
        }

        // Adjust grid rows and columns based on num of images
        // TODO: Make bigger sizes for this fragment
        if(imgNum <= 4){
            sizeInPixels  = getResources().getDimension(R.dimen.elephantXLarge);
            customImageGrid.setRowCount(2);
            customImageGrid.setColumnCount(2);
        }
        else if(imgNum <= 6){
            sizeInPixels  = getResources().getDimension(R.dimen.elephantMLarge);
            customImageGrid.setRowCount(2);
            customImageGrid.setColumnCount(3);
        }
        else if(imgNum <= 9){
            sizeInPixels  = getResources().getDimension(R.dimen.elephantLarge);
            customImageGrid.setRowCount(3);
            customImageGrid.setColumnCount(3);
        }
        else if(imgNum == 10){
            sizeInPixels  = getResources().getDimension(R.dimen.elephantSize);
            customImageGrid.setRowCount(2);
            customImageGrid.setColumnCount(5);
        }
        else if (imgNum <= 20){
            sizeInPixels  = getResources().getDimension(R.dimen.elephantSize);
            customImageGrid.setRowCount(5);
            customImageGrid.setColumnCount(4);
        }

        // Check num of image and map to the size of images
        for(int i=0; i<imgNum; i++){
            // width and height are pixels not dp, so need to convert from dp to pixels
            ImageView imageTemp = setImageView(resId, sizeInPixels.intValue(), sizeInPixels.intValue());
            customImageGrid.addView(imageTemp, i);
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