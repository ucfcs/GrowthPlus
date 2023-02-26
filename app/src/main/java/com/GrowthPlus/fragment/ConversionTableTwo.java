package com.GrowthPlus.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.GrowthPlus.R;

public class ConversionTableTwo extends Fragment {
    View view;
    TextView conversionTableText1;
    TextView conversionTableText2;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_conversion_table_two, container, false);
        conversionTableText1 = view.findViewById(R.id.conversion_table_first_text);
        conversionTableText2 = view.findViewById(R.id.conversion_table_second_text);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {

        String getConversionTableText1 = requireArguments().getString("conversionTableText1");
        String getConversionTableText2 = requireArguments().getString("conversionTableText2");

        conversionTableText1.setText(getConversionTableText1);
        conversionTableText2.setText(getConversionTableText2);

    }
}