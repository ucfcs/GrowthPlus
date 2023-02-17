package com.GrowthPlus.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
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

public class WordGrid extends Fragment {
    View view;
    TextView wordMD;
    GridLayout gridLayoutMD;
    ImageSrcIdentifier imageSrcIdentifier;
    Float sizeInPixels;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_word_grid, container, false);
        wordMD = view.findViewById(R.id.wordMD);
        gridLayoutMD = view.findViewById(R.id.gridLayoutMD);
        imageSrcIdentifier = new ImageSrcIdentifier();
        sizeInPixels  = getResources().getDimension(R.dimen.elephantSize);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        String wordIn = requireArguments().getString("wordMD");
        int num = requireArguments().getInt("numMD");
        String image = requireArguments().getString("imageMD");

        wordMD.setText(wordIn);

        int resId = imageSrcIdentifier.getImageSrcId(image);
        for(int i=0; i<num; i++){
            // width and height are pixels not dp, so need to convert from dp to pixels
            ImageView imageTemp = setImageView(resId, sizeInPixels.intValue(), sizeInPixels.intValue());
            gridLayoutMD.addView(imageTemp, i);
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