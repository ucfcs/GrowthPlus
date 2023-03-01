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

public class ShapesAngles extends Fragment {
    View view;
    ImageView firstImage;
    ImageView secondImage;
    TextView word;
    TextView degree;
    ImageSrcIdentifier imageSrcIdentifier;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_shapes_angles, container, false);
        firstImage = view.findViewById(R.id.first_image);
        secondImage = view.findViewById(R.id.second_image);
        word = view.findViewById(R.id.word);
        degree = view.findViewById(R.id.degree);
        imageSrcIdentifier = new ImageSrcIdentifier();

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        String getFirstImage = requireArguments().getString("firstImage");
        String getSecondImage = requireArguments().getString("secondImage");
        String getWord = requireArguments().getString("word");
        String getDegree = requireArguments().getString("degree");

        firstImage.setImageResource(imageSrcIdentifier.getImageSrcId(getFirstImage));
        secondImage.setImageResource(imageSrcIdentifier.getImageSrcId(getSecondImage));
        word.setText(getWord);
        degree.setText(getDegree);
    }
}