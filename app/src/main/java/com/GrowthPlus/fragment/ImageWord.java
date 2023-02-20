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

public class ImageWord extends Fragment {
    View view;
    ImageView image;
    TextView text;
    ImageSrcIdentifier imageSrcIdentifier;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_image_word, container, false);
        image = view.findViewById(R.id.imageWordImage);
        text = view.findViewById(R.id.imageWordWord);
        imageSrcIdentifier = new ImageSrcIdentifier();

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        String getText = requireArguments().getString("imageWordText");
        String getImage = requireArguments().getString("imageWordImage");

        image.setImageResource(imageSrcIdentifier.getImageSrcId(getImage));
        text.setText(getText);
    }
}