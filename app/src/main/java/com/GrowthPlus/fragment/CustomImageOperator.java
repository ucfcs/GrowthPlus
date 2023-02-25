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
import android.widget.TextView;

import com.GrowthPlus.R;
import com.GrowthPlus.utilities.ImageSrcIdentifier;

public class CustomImageOperator extends Fragment {
    View view;
    GridLayout topGrid;
    TextView operator;
    GridLayout bottomGrid;
    Float sizeInPixels;
    ImageSrcIdentifier imageSrcIdentifier;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_custom_image_operator, container, false);
        topGrid = view.findViewById(R.id.topGrid);
        operator = view.findViewById(R.id.customImageOp);
        bottomGrid = view.findViewById(R.id.bottomGrid);
        sizeInPixels  = getResources().getDimension(R.dimen.elephantSize);
        imageSrcIdentifier = new ImageSrcIdentifier();
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        String image = requireArguments().getString("image");
        int firstNumber = Integer.parseInt(requireArguments().getString("firstNumber"));
        String firstOperator = requireArguments().getString("firstOperator");
        int secondNumber = Integer.parseInt(requireArguments().getString("secondNumber"));
        int resId = imageSrcIdentifier.getImageSrcId(image);

        // Adjust grid rows and columns based on num of images
        if(firstNumber <= 4){
            sizeInPixels  = getResources().getDimension(R.dimen.elephantMLarge);
            topGrid.setRowCount(2);
            topGrid.setColumnCount(2);
        }
        else if(firstNumber <= 6){
            sizeInPixels  = getResources().getDimension(R.dimen.elephantMLarge);
            topGrid.setRowCount(2);
            topGrid.setColumnCount(3);
        }
        else if(firstNumber <= 9){
            sizeInPixels  = getResources().getDimension(R.dimen.elephantLarge);
            topGrid.setRowCount(3);
            topGrid.setColumnCount(3);
        }
        else if(firstNumber == 10){
            sizeInPixels  = getResources().getDimension(R.dimen.elephantSize);
            topGrid.setRowCount(2);
            topGrid.setColumnCount(5);
        }
        else if (firstNumber <= 20){
            sizeInPixels  = getResources().getDimension(R.dimen.elephantSize);
            topGrid.setRowCount(5);
            topGrid.setColumnCount(4);
        }

        // Setting top grid
        for(int i=0; i<firstNumber; i++){
            ImageView imageTemp = setImageView(resId, sizeInPixels.intValue(), sizeInPixels.intValue());
            topGrid.addView(imageTemp, i);
        }

        // Setting operator
        operator.setText(firstOperator);

        // Adjust grid rows and columns based on num of images
        if(secondNumber <= 4){
            sizeInPixels  = getResources().getDimension(R.dimen.elephantMLarge);
            bottomGrid.setRowCount(2);
            bottomGrid.setColumnCount(2);
        }
        else if(secondNumber <= 6){
            sizeInPixels  = getResources().getDimension(R.dimen.elephantMLarge);
            bottomGrid.setRowCount(2);
            bottomGrid.setColumnCount(3);
        }
        else if(secondNumber <= 9){
            sizeInPixels  = getResources().getDimension(R.dimen.elephantLarge);
            bottomGrid.setRowCount(3);
            bottomGrid.setColumnCount(3);
        }
        else if(secondNumber == 10){
            sizeInPixels  = getResources().getDimension(R.dimen.elephantSize);
            bottomGrid.setRowCount(2);
            bottomGrid.setColumnCount(5);
        }
        else if (secondNumber <= 20){
            sizeInPixels  = getResources().getDimension(R.dimen.elephantSize);
            bottomGrid.setRowCount(5);
            bottomGrid.setColumnCount(4);
        }

        // Setting bottom grid
        for(int i=0; i<secondNumber; i++){
            ImageView imageTemp = setImageView(resId, sizeInPixels.intValue(), sizeInPixels.intValue());
            bottomGrid.addView(imageTemp, i);
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