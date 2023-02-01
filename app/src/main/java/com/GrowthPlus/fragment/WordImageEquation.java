package com.GrowthPlus.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.GrowthPlus.R;
public class WordImageEquation extends Fragment {
    View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_word_image_equation, container, false);
        return view;
    }

    public WordImageEquation(){
        super(R.layout.fragment_word_image_equation);
    }
}