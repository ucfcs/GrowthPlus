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
    TextView conversionTableText1;
    TextView conversionTableText2;
    TextView conversionTableText3;
    TextView conversionTableText4;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_conversion_table, container, false);
        conversionTableText1 = view.findViewById(R.id.conversion_table_first_text);
        conversionTableText2 = view.findViewById(R.id.conversion_table_second_text);
        conversionTableText3 = view.findViewById(R.id.conversion_table_third_text);
        conversionTableText4 = view.findViewById(R.id.conversion_table_fourth_text);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {

        String getConversionTableText1 = requireArguments().getString("conversionTableText1");
        String getConversionTableText2 = requireArguments().getString("conversionTableText2");
        String getConversionTableText3 = requireArguments().getString("conversionTableText3");
        String getConversionTableText4 = requireArguments().getString("conversionTableText4");

        conversionTableText1.setText(getConversionTableText1);
        conversionTableText2.setText(getConversionTableText2);
        conversionTableText3.setText(getConversionTableText3);
        conversionTableText4.setText(getConversionTableText4);

    }
}