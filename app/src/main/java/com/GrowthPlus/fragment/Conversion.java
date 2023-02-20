package com.GrowthPlus.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.GrowthPlus.R;
public class Conversion extends Fragment {
    View view;
    TextView text1, operator, text2;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_conversion, container, false);
        text1 = view.findViewById(R.id.first_text);
        operator = view.findViewById(R.id.operator);
        text2 = view.findViewById(R.id.second_text);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        String t1 = requireArguments().getString("text1");
        String t2 = requireArguments().getString("text2");

        text1.setText(t1);
        text2.setText(t2);
    }
}