package com.GrowthPlus.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.gridlayout.widget.GridLayout;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.GrowthPlus.R;

public class CustomImageOperator extends Fragment {
    View view;
    GridLayout customImageFirst;
    TextView customImageOp;
    GridLayout customImageSecond;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_custom_image_operator, container, false);
        customImageFirst = view.findViewById(R.id.customImageFirstGrid);
        customImageOp = view.findViewById(R.id.customImageOp);
        customImageSecond = view.findViewById(R.id.customImageSecondGrid);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        String image = requireArguments().getString("image");
        String firstNumber = requireArguments().getString("firstNumber");
        String firstOperator = requireArguments().getString("firstOperator");
        String secondNumber = requireArguments().getString("secondNumber");
        String answer = requireArguments().getString("answer");

    }
}