package com.GrowthPlus.fragment;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.GrowthPlus.R;
public class QuizText extends Fragment {
    View view;
    TextView text;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_quiz_text, container, false);
        text = view.findViewById(R.id.text);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        String getWord = requireArguments().getString("text");
        int textColor = requireArguments().getInt("textColor");
        String langId = requireArguments().getString("langId");

        text.setText(getWord);
        // Changes font to default Roboto if our Fredoka font does not include these characters
        if((langId.equals("lagwanZero") ||  langId.equals("mousgoumZero")) && !Character.isDigit(getWord.charAt(0))){
            text.setTypeface(Typeface.create("sans-serif", Typeface.NORMAL));
        }
        setTextColor(textColor);
    }

    public void setTextColor(int color){
        text.setTextColor(color);
    }

}