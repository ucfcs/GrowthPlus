package com.GrowthPlus.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.GrowthPlus.R;

public class ConversionTable extends Fragment {
    View view;
    TextView conversionText1;
    TextView conversionText2;
    TextView conversionText3;
    TextView conversionText4;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_conversion_table, container, false);
        conversionText1 = view.findViewById(R.id.conversion_first_text);
        conversionText2 = view.findViewById(R.id.conversion_second_text);
        conversionText3 = view.findViewById(R.id.conversion_table_third_text);
        conversionText4 = view.findViewById(R.id.conversion_table_fourth_text);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {

        String getConversionText1 = requireArguments().getString("conversionText1");
        String getConversionText2 = requireArguments().getString("conversionText2");
        String getConversionText3 = requireArguments().getString("conversionText3");
        String getConversionText4 = requireArguments().getString("conversionText4");

        conversionText1.setText(getConversionText1);
        conversionText2.setText(getConversionText2);
        conversionText3.setText(getConversionText3);
        conversionText4.setText(getConversionText4);

    }
}