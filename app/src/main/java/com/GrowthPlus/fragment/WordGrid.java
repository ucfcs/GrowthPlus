package com.GrowthPlus.fragment;

import android.content.res.ColorStateList;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
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
    ColorStateList blue, green;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_word_grid, container, false);
        wordMD = view.findViewById(R.id.wordMD);
        gridLayoutMD = view.findViewById(R.id.gridLayoutMD);
        imageSrcIdentifier = new ImageSrcIdentifier();
        sizeInPixels  = getResources().getDimension(R.dimen.elephantLarge);
        blue = ColorStateList.valueOf(ContextCompat.getColor(getContext(), R.color.blue));
        green = ColorStateList.valueOf(ContextCompat.getColor(getContext(), R.color.dark_green));
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        String wordIn = requireArguments().getString("wordMD");
        int num = requireArguments().getInt("numMD");
        String image = requireArguments().getString("imageMD");
        int level = requireArguments().getInt("level");

        wordMD.setText(wordIn);
        if(level == 2 || level == 4){
            wordMD.setTextColor(blue);
        }
        if( level == 3){
            wordMD.setTextColor(green);
        }

        if(num <= 3){
            sizeInPixels = getResources().getDimension(R.dimen.elephantXLarge);
            gridLayoutMD.setRowCount(3);
            gridLayoutMD.setColumnCount(1);
        }
        else if(num <= 5){
            sizeInPixels = getResources().getDimension(R.dimen.elephantXLarge);
            gridLayoutMD.setRowCount(3);
            gridLayoutMD.setColumnCount(2);
        }

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