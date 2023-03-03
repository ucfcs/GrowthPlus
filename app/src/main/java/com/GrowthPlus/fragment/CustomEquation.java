package com.GrowthPlus.fragment;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.GrowthPlus.R;

public class CustomEquation extends Fragment {
    View view;
    TextView firstNumber;
    TextView secondNumber;
    TextView operator;
    EditText carryInput;
    ImageView bar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_custom_equation, container, false);
        firstNumber = view.findViewById(R.id.first_number);
        secondNumber = view.findViewById(R.id.second_number);
        operator = view.findViewById(R.id.operator);
        carryInput = view.findViewById(R.id.carryInputHolder);
        bar = view.findViewById(R.id.bar);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        String firstNumberAr = requireArguments().getString("firstNumber");
        String operatorAr = requireArguments().getString("firstOperator");
        String secondNumberAr = requireArguments().getString("secondNumber");
        String carryString = requireArguments().getString("carryInput");
        int textColor = requireArguments().getInt("textColor");

        firstNumber.setText(firstNumberAr);
        operator.setText(operatorAr);
        secondNumber.setText(secondNumberAr);
        if(carryString.equals("carry")){
            carryInput.setVisibility(View.VISIBLE);
        }
        firstNumber.setTextColor(textColor);
        operator.setTextColor(textColor);
        secondNumber.setTextColor(textColor);
        carryInput.setTextColor(textColor);
        bar.setBackgroundTintList(ColorStateList.valueOf(textColor));
    }
}