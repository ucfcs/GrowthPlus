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

public class Counting extends Fragment {
    View view;
    TextView word;
    TextView number;
    ImageView img;
    ImageSrcIdentifier imageSrcIdentifier;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_counting, container, false);
        word = view.findViewById(R.id.wordCounting);
        number = view.findViewById(R.id.numberCounting);
        img = view.findViewById(R.id.imageCounting);
        imageSrcIdentifier = new ImageSrcIdentifier();
        return view;
    }

    public Counting(){
        super(R.layout.fragment_counting);
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        String getWord = requireArguments().getString("lessonWord");
        String getNumber = requireArguments().getString("lessonNumber");
        String getImg = requireArguments().getString("lessonImage");

        word.setText(getWord);
        number.setText(getNumber);
        img.setImageResource(imageSrcIdentifier.getImageSrcId(getImg));
    }
}