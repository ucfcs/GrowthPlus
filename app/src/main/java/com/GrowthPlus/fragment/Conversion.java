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
    TextView conversionText1, operator, conversionText2;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_conversion, container, false);
        conversionText1 = view.findViewById(R.id.conversion_first_text);
        operator = view.findViewById(R.id.operator);
        conversionText2 = view.findViewById(R.id.conversion_second_text);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        String getConversionText1 = requireArguments().getString("conversionText1");
        String getOperator = requireArguments().getString("operator");
        String getConversionText2 = requireArguments().getString("conversionText2");

        conversionText1.setText(getConversionText1);
        operator.setText(getOperator);
        conversionText2.setText(getConversionText2);
    }
}