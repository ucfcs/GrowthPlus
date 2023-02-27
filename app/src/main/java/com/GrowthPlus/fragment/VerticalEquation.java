package com.GrowthPlus.fragment;

import android.content.res.ColorStateList;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.GrowthPlus.R;
public class VerticalEquation extends Fragment {
    View view;
    TextView firstNumber;
    TextView secondNumber;
    TextView ans;
    TextView carryNum;
    TextView operator;
    ImageView barOne;
    ImageView barTwo;
    ImageView boxOne;
    ImageView boxTwo;
    ImageView carry;
    ColorStateList blue, green;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_vertical_equation, container, false);
        firstNumber = view.findViewById(R.id.first_number);
        secondNumber = view.findViewById(R.id.second_number);
        operator = view.findViewById(R.id.operator);
        ans = view.findViewById(R.id.answer);
        carryNum = view.findViewById(R.id.carryInputNum);
        barOne = view.findViewById(R.id.barOne);
        barTwo = view.findViewById(R.id.barTwo);
        boxOne = view.findViewById(R.id.boxOne);
        boxTwo = view.findViewById(R.id.boxTwo);
        carry = view.findViewById(R.id.carryInput);
        blue = ColorStateList.valueOf(ContextCompat.getColor(getContext(), R.color.blue));
        green = ColorStateList.valueOf(ContextCompat.getColor(getContext(), R.color.light_green));
        return view;
    }
    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        String num = requireArguments().getString("wordEqu");
        String fstNum = requireArguments().getString("firstNum");
        String secNum = requireArguments().getString("secondNum");
        String carryNumber = requireArguments().getString("carry");
        String answer = requireArguments().getString("answer");
        String opt = requireArguments().getString("opt");

        firstNumber.setText(fstNum);
        secondNumber.setText(secNum);
        ans.setText(answer);
        operator.setText(opt);

        if(carryNumber.equals("1") || carryNumber.equals("2") || carryNumber.equals("+1") || carryNumber.equals("+2") ){
            carryNum.setText(carryNumber);
            carryNum.setVisibility(View.VISIBLE);
            carry.setVisibility(View.VISIBLE);
        }
        else if(carryNumber.equals("-1")){
            carryNum.setText(carryNumber);
            carryNum.setVisibility(View.VISIBLE);
            carry.setVisibility(View.VISIBLE);
            carry.setBackgroundTintList(green);
        }
        if(num.equals("carried")){
            barOne.setVisibility(View.VISIBLE);
        }
        else if(num.equals("one")){
            barOne.setVisibility(View.VISIBLE);
        }
        else if(num.equals("two")){
            barTwo.setVisibility(View.VISIBLE);
            carry.setBackgroundTintList(blue);
        }
        else if(num.equals("diagonal")){
            boxOne.setVisibility(View.VISIBLE);
            boxTwo.setVisibility(View.VISIBLE);
            carry.setBackgroundTintList(blue);
        }
    }
}