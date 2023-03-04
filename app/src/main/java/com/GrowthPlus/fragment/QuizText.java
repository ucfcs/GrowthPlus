package com.GrowthPlus.fragment;

import android.graphics.Color;
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
        text.setText(getWord);
        setTextColor(textColor);
    }

    public void setTextColor(int color){
        text.setTextColor(color);
    }

}