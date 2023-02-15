package com.GrowthPlus.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.gridlayout.widget.GridLayout;

import com.GrowthPlus.R;
import com.GrowthPlus.utilities.ImageSrcIdentifier;

public class WordImageEquation extends Fragment {
    View view;
    ImageSrcIdentifier imageSrcIdentifier;
    TextView text1, operator, text2;
    ImageView image;
    GridLayout layout;
    Float sizeInPixels;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_word_image, container, false);
        text1 = view.findViewById(R.id.first_word);
        operator = view.findViewById(R.id.operator);
        text2 = view.findViewById(R.id.second_word);
        image = view.findViewById(R.id.second_image);
        imageSrcIdentifier = new ImageSrcIdentifier();
        layout = view.findViewById(R.id.gridLayoutWordImageEquation);
        imageSrcIdentifier = new ImageSrcIdentifier();
        sizeInPixels  = getResources().getDimension(R.dimen.elephantSize);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        String t1 = requireArguments().getString("topText");
        String t2 = requireArguments().getString("bottomText");
        String firstImg = requireArguments().getString("multipliedImage"); // Image to be multiplied, goes in the grid
        String secondImg = requireArguments().getString("singleImage"); // Single image
        String operator = requireArguments().getString("operatorSymbol");
        int imgNum = requireArguments().getInt("number"); // How many copies of the image in grid layout
        int resId = imageSrcIdentifier.getImageSrcId(firstImg);

        text1.setText(t1);
        text2.setText(t2);
        image.setImageResource(imageSrcIdentifier.getImageSrcId(secondImg));

        for(int i=0; i<imgNum; i++){
            // width and height are pixels not dp, so need to convert from dp to pixels
            ImageView imageTemp = setImageView(resId, sizeInPixels.intValue(), sizeInPixels.intValue());
            layout.addView(imageTemp, i);
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