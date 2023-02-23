package com.GrowthPlus.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.GrowthPlus.R;
import com.GrowthPlus.utilities.ImageSrcIdentifier;

public class WordImage extends Fragment {
    View view;
    TextView text;
    ImageView image;
    ImageSrcIdentifier imageSrcIdentifier;

    //these ImageViews are the alter the backgrounds of quizzes, miniGames, and Level Games
    ImageView gameBackground;
    ImageView quizAndMiniGameBackground;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_word_image, container, false);
        text = view.findViewById(R.id.wordFragment);
        image = view.findViewById(R.id.imageFragment);
        gameBackground = view.findViewById(R.id.gameBackground);
        quizAndMiniGameBackground = view.findViewById(R.id.quizBackground);
        imageSrcIdentifier = new ImageSrcIdentifier();

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        String getText = requireArguments().getString("locationIntroText");
        String getImage = requireArguments().getString("locationIntroImage");

        //we want to determine which, if any, background we would like to make visible on screen
        if(getText.contains("Game")){
            //here we're at the last day of the level so we want to star to be visible
            gameBackground.setVisibility(View.VISIBLE);
            quizAndMiniGameBackground.setVisibility(View.INVISIBLE);
        }
        else if(getText.contains("Quiz")){
            //here we're at a quiz so we want to set the circle background to visible
            gameBackground.setVisibility(View.INVISIBLE);
            quizAndMiniGameBackground.setVisibility(View.VISIBLE);
        }
        else{
            //otherwise we are in a lesson and want no visible backgrounds
            gameBackground.setVisibility(View.INVISIBLE);
            quizAndMiniGameBackground.setVisibility(View.INVISIBLE);
        }
        text.setText(getText);
        image.setImageResource(imageSrcIdentifier.getImageSrcId(getImage));
    }
}