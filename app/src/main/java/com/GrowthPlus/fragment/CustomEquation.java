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

public class CustomEquation extends Fragment {
    View view;
    TextView firstNumber;
    TextView secondNumber;
    TextView operator;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_custom_equation, container, false);
        firstNumber = view.findViewById(R.id.first_number);
        secondNumber = view.findViewById(R.id.second_number);
        operator = view.findViewById(R.id.operator);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        String firstNumberAr = requireArguments().getString("firstNumber");
        String operatorAr = requireArguments().getString("firstOperator");
        String secondNumberAr = requireArguments().getString("secondNumber");

        firstNumber.setText(firstNumberAr);
        operator.setText(operatorAr);
        secondNumber.setText(secondNumberAr);
    }
}