package com.GrowthPlus.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.GrowthPlus.R;
import com.GrowthPlus.utilities.ImageSrcIdentifier;

public class Shape extends Fragment {
    View view;
    TextView shapeText;
    ImageView shapeImage1;
    ImageView shapeImage2;
    ImageView shapeImage3;
    ImageSrcIdentifier imageSrcIdentifier;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_shape, container, false);
        shapeText = view.findViewById(R.id.text);
        shapeImage1 = view.findViewById(R.id.first_image);
        shapeImage2 = view.findViewById(R.id.second_image);
        shapeImage3 = view.findViewById(R.id.third_image);
        imageSrcIdentifier = new ImageSrcIdentifier();

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        String getShapeText = requireArguments().getString("shapeText");
        String getShapeImage1 = requireArguments().getString("shapeImage1");
        String getShapeImage2 = requireArguments().getString("shapeImage2");
        String getShapeImage3 = requireArguments().getString("shapeImage3");

        shapeText.setText(getShapeText);
        shapeImage1.setImageResource(imageSrcIdentifier.getImageSrcId(getShapeImage1));
        shapeImage2.setImageResource(imageSrcIdentifier.getImageSrcId(getShapeImage2));
        shapeImage3.setImageResource(imageSrcIdentifier.getImageSrcId(getShapeImage3));

    }
}
