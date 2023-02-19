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

        // Setting top grid
        for(int i=0; i<firstNumber; i++){
            ImageView imageTemp = setImageView(resId, sizeInPixels.intValue(), sizeInPixels.intValue());
            topGrid.addView(imageTemp, i);
        }

        // Setting operator
        operator.setText(firstOperator);

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