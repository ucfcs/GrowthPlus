package com.GrowthPlus.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.GrowthPlus.R;
public class Division extends Fragment {
    View view;
    TextView divisor;
    TextView dividend;
    TextView quotient;
    TextView subtractNumber;
    TextView operator;
    TextView remainder;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_division, container, false);
        divisor = view.findViewById(R.id.divisor);
        dividend = view.findViewById(R.id.dividend);
        quotient = view.findViewById(R.id.quotient);
        subtractNumber = view.findViewById(R.id.subtractNumber);
        operator = view.findViewById(R.id.operator);
        remainder = view.findViewById(R.id.remainder);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        String getDivisor = requireArguments().getString("divisor");
        String getDividend = requireArguments().getString("dividend");
        String getQuotient = requireArguments().getString("quotient");
        
        divisor.setText(getDivisor);
        dividend.setText(getDividend);
        quotient.setText(getQuotient);
        
//        int getSubtractNumber = Integer.valueOf(getDivisor)*Integer.valueOf(getQuotient);
//        subtractNumber.setText(String.valueOf(getSubtractNumber));
        
        operator.setText("-"); //operator is always gonna be -
        
//        int getRemainder = Integer.valueOf(getDividend)%Integer.valueOf(getDivisor);
//        remainder.setText(String.valueOf(getRemainder));

    }
    
    
}