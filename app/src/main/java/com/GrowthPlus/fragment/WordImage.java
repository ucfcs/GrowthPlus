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

public class WordImage extends Fragment {
    View view;
    TextView text;
    ImageView image;
    ImageSrcIdentifier imageSrcIdentifier;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_word_image, container, false);
        text = view.findViewById(R.id.wordFragment);
        image = view.findViewById(R.id.imageFragment);
        imageSrcIdentifier = new ImageSrcIdentifier();

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        String getText = requireArguments().getString("locationIntroText");
        String getImage = requireArguments().getString("locationIntroImage");

        text.setText(getText);
        image.setImageResource(imageSrcIdentifier.getImageSrcId(getImage));
    }
}