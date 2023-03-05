package com.GrowthPlus.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.gridlayout.widget.GridLayout;

import com.GrowthPlus.R;
import com.GrowthPlus.utilities.ImageSrcIdentifier;

public class CustomImageWord extends Fragment {
    View view;
    GridLayout topGrid;
    TextView operator;
    ImageView img;
    ImageSrcIdentifier imageSrcIdentifier;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_custom_image_word, container, false);
        topGrid = view.findViewById(R.id.topGrid);
        operator = view.findViewById(R.id.customImageOp);
        img = view.findViewById(R.id.customImageWord);
        imageSrcIdentifier = new ImageSrcIdentifier();
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        String image = requireArguments().getString("image");
        String firstNumber = requireArguments().getString("firstNumber");
        String firstOperator = requireArguments().getString("firstOperator");
        String secondNumber = requireArguments().getString("secondNumber");
        String secondOperator = requireArguments().getString("secondOperator");
        int textColor = requireArguments().getInt("textColor");
        int resId = imageSrcIdentifier.getImageSrcId(image);

        img.setImageResource(resId);
        operator.setText(firstNumber + firstOperator +"\n"+ secondNumber + secondOperator);
        operator.setTextColor(textColor);
    }


}