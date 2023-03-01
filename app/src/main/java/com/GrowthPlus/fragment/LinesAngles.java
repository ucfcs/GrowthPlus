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

public class LinesAngles extends Fragment {
    View view;
    TextView LAText;
    ImageView LAImage1;
    ImageView LAImage2;
    ImageSrcIdentifier imageSrcIdentifier;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_lines_angles, container, false);
        LAText = view.findViewById(R.id.text);
        LAImage1 = view.findViewById(R.id.first_image);
        LAImage2 = view.findViewById(R.id.second_image);
        imageSrcIdentifier = new ImageSrcIdentifier();
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        String getLAText = requireArguments().getString("LAText");
        String getLAImage1 = requireArguments().getString("LAImage1");
        String getLAImage2 = requireArguments().getString("LAImage2");

        LAText.setText(getLAText);
        LAImage1.setImageResource(imageSrcIdentifier.getImageSrcId(getLAImage1));
        LAImage2.setImageResource(imageSrcIdentifier.getImageSrcId(getLAImage2));
    }
}