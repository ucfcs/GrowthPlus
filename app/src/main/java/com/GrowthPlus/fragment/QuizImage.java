package com.GrowthPlus.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.GrowthPlus.R;
import com.GrowthPlus.utilities.ImageSrcIdentifier;

public class QuizImage extends Fragment {
    View view;
    ImageView image;
    ImageSrcIdentifier imageSrcIdentifier;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_quiz_image, container, false);
        image = view.findViewById(R.id.image);
        imageSrcIdentifier = new ImageSrcIdentifier();

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        String getImg = requireArguments().getString("image");
        image.setImageResource(imageSrcIdentifier.getImageSrcId(getImg));
    }
}