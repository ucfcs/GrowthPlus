package com.GrowthPlus.fragment;

import android.graphics.Typeface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.core.content.res.ResourcesCompat;
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

public class Counting extends Fragment {
    View view;
    TextView word;
    TextView number;
    GridLayout gridLayoutCounting;
    ImageSrcIdentifier imageSrcIdentifier;
    Float sizeInPixels;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_counting, container, false);
        word = view.findViewById(R.id.wordCounting);
        number = view.findViewById(R.id.numberCounting);
        gridLayoutCounting = view.findViewById(R.id.gridLayoutCounting);
        imageSrcIdentifier = new ImageSrcIdentifier();
        sizeInPixels  = getResources().getDimension(R.dimen.elephantSize);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        String getWord = requireArguments().getString("lessonWord");
        String getNumber = requireArguments().getString("lessonNumber");
        String getImg = requireArguments().getString("lessonImage");
        String lang = requireArguments().getString("langId");
        int imgNum = Integer.parseInt(getNumber);

        if(imgNum >= 1000){
            imgNum /= 1000;
        }
        else if(imgNum >= 100){
            imgNum /= 100;
        }
        else if(imgNum >= 20 && !getImg.equals("elephant")){
            imgNum /= 10;
        }

        if(imgNum <= 4){
            sizeInPixels  = getResources().getDimension(R.dimen.elephantMLarge);
            gridLayoutCounting.setRowCount(2);
            gridLayoutCounting.setColumnCount(2);
        }
        else if(imgNum <= 6){
            sizeInPixels  = getResources().getDimension(R.dimen.elephantMLarge);
            gridLayoutCounting.setRowCount(2);
            gridLayoutCounting.setColumnCount(3);
        }
        else if(imgNum <= 9){
            sizeInPixels  = getResources().getDimension(R.dimen.elephantSize);
            gridLayoutCounting.setRowCount(3);
            gridLayoutCounting.setColumnCount(3);
        }
        else if(imgNum == 10){
            sizeInPixels  = getResources().getDimension(R.dimen.elephantSize);
            gridLayoutCounting.setRowCount(2);
            gridLayoutCounting.setColumnCount(5);
        }
        else {
            sizeInPixels  = getResources().getDimension(R.dimen.elephantSize);
            gridLayoutCounting.setRowCount(5);
            gridLayoutCounting.setColumnCount(5);
        }

        word.setText(getWord);
        // Changes font to default Roboto if our Fredoka font does not include these characters
        if(lang.equals("lagwanZero") || lang.equals("mousgoumZero")){
            word.setTypeface(Typeface.create("sans-serif", Typeface.NORMAL));
        }

        number.setText(getNumber);

        int resId = imageSrcIdentifier.getImageSrcId(getImg);
        for(int i=0; i<imgNum; i++){
            // width and height are pixels not dp, so need to convert from dp to pixels
            ImageView imageTemp = setImageView(resId, sizeInPixels.intValue(), sizeInPixels.intValue());
            gridLayoutCounting.addView(imageTemp, i);
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