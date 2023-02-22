package com.GrowthPlus.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.GrowthPlus.R;
public class Family extends Fragment {
    View view;
    TextView familyNumber;
    TextView familyText1;
    TextView familyText2;
    TextView familyText3;
    TextView familyText4;
    TextView familyText5;
    TextView familyText6;
    TextView familyText7;
    TextView familyText8;
    TextView familyText9;
    TextView familyText10;
    TextView familyText11;
    TextView familyText12;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_family, container, false);
        familyNumber = view.findViewById(R.id.number);
        familyText1 = view.findViewById(R.id.text1);
        familyText2 = view.findViewById(R.id.text2);
        familyText3 = view.findViewById(R.id.text3);
        familyText4 = view.findViewById(R.id.text4);
        familyText5 = view.findViewById(R.id.text5);
        familyText6 = view.findViewById(R.id.text6);
        familyText7 = view.findViewById(R.id.text7);
        familyText8 = view.findViewById(R.id.text8);
        familyText9 = view.findViewById(R.id.text9);
        familyText10 = view.findViewById(R.id.text10);
        familyText11 = view.findViewById(R.id.text11);
        familyText12 = view.findViewById(R.id.text12);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        String getConversionTableText1 = requireArguments().getString("conversionTableText1");

        String getFamilyWord = requireArguments().getString("familyWord");
        String getFamilyFirstNumber = requireArguments().getString("familyFirstNumber");
        String getFamilyFirstOperator = requireArguments().getString("familyFirstOperator");
        String getFamilySecondNumber = requireArguments().getString("familySecondNumber");

        familyNumber.setText(getFamilyWord);

        if(getFamilyFirstOperator.equals("x")){
            familyText1.setText("1 x " + getFamilySecondNumber + " = " + 1*Integer.valueOf(getFamilySecondNumber));
            familyText2.setText("2 x " + getFamilySecondNumber + " = " + 2*Integer.valueOf(getFamilySecondNumber));
            familyText3.setText("3 x " + getFamilySecondNumber + " = " + 3*Integer.valueOf(getFamilySecondNumber));
            familyText4.setText("4 x " + getFamilySecondNumber + " = " + 4*Integer.valueOf(getFamilySecondNumber));
            familyText5.setText("5 x " + getFamilySecondNumber + " = " + 5*Integer.valueOf(getFamilySecondNumber));
            familyText6.setText("6 x " + getFamilySecondNumber + " = " + 6*Integer.valueOf(getFamilySecondNumber));
            familyText7.setText("7 x " + getFamilySecondNumber + " = " + 7*Integer.valueOf(getFamilySecondNumber));
            familyText8.setText("8 x " + getFamilySecondNumber + " = " + 8*Integer.valueOf(getFamilySecondNumber));
            familyText9.setText("9 x " + getFamilySecondNumber + " = " + 9*Integer.valueOf(getFamilySecondNumber));
            familyText10.setText("10 x " + getFamilySecondNumber + " = " + 10*Integer.valueOf(getFamilySecondNumber));
            familyText11.setText("11 x " + getFamilySecondNumber + " = " + 11*Integer.valueOf(getFamilySecondNumber));
            familyText12.setText("12 x " + getFamilySecondNumber + " = " + 12*Integer.valueOf(getFamilySecondNumber));

        }

        else if(getFamilyFirstOperator.equals(":")){
            familyText1.setText(1*Integer.valueOf(getFamilySecondNumber) + " : " + getFamilySecondNumber + " = 1");
            familyText2.setText(2*Integer.valueOf(getFamilySecondNumber) + " : " + getFamilySecondNumber + " = 2");
            familyText3.setText(3*Integer.valueOf(getFamilySecondNumber) + " : " + getFamilySecondNumber + " = 3");
            familyText4.setText(4*Integer.valueOf(getFamilySecondNumber) + " : " + getFamilySecondNumber + " = 4");
            familyText5.setText(5*Integer.valueOf(getFamilySecondNumber) + " : " + getFamilySecondNumber + " = 5");
            familyText6.setText(6*Integer.valueOf(getFamilySecondNumber) + " : " + getFamilySecondNumber + " = 6");
            familyText7.setText(7*Integer.valueOf(getFamilySecondNumber) + " : " + getFamilySecondNumber + " = 7");
            familyText8.setText(8*Integer.valueOf(getFamilySecondNumber) + " : " + getFamilySecondNumber + " = 8");
            familyText9.setText(9*Integer.valueOf(getFamilySecondNumber) + " : " + getFamilySecondNumber + " = 9");
            familyText10.setText(10*Integer.valueOf(getFamilySecondNumber) + " : " + getFamilySecondNumber + " = 10");
            familyText11.setText(11*Integer.valueOf(getFamilySecondNumber) + " : " + getFamilySecondNumber + " = 11");
            familyText12.setText(12*Integer.valueOf(getFamilySecondNumber) + " : " + getFamilySecondNumber + " = 12");
        }

        else{
            //we shouldn't get here!
        }



    }

}