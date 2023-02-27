package com.GrowthPlus.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.GrowthPlus.R;
import com.GrowthPlus.utilities.ImageSrcIdentifier;

public class PerimeterArea extends Fragment {
    View view;
    TextView perimeterAreaText1;
    ImageView perimeterAreaImage;
    TextView perimeterAreaText2;
    ImageSrcIdentifier imageSrcIdentifier;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_perimeter_area, container, false);
        perimeterAreaText1 = view.findViewById(R.id.text1);
        perimeterAreaImage = view.findViewById(R.id.image);
        perimeterAreaText2 = view.findViewById(R.id.text2);
        imageSrcIdentifier = new ImageSrcIdentifier();

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        String identify = requireArguments().getString("PAIdentify");
        String getPAWord = requireArguments().getString("PAWord");
        String getPAFirstNumber = requireArguments().getString("PAFirstNumber");
        String getPAFirstOperator = requireArguments().getString("PAFirstOperator");
        String getPASecondNumber = requireArguments().getString("PASecondNumber");
        String getPASecondOperator = requireArguments().getString("PASecondOperator");
        String getPAThirdNumber = requireArguments().getString("PAThirdNumber");
        String getPAImage = requireArguments().getString("PAImage");

        perimeterAreaText1.setText(getPAWord);
        perimeterAreaImage.setImageResource(imageSrcIdentifier.getImageSrcId(getPAImage));
        String getPerimeterAreaText2 = "";

        //TODO: handle the cases and generate the proper text for perimeterAreaText2
        if(identify.equals("perimeter")){

            switch(getPAImage){

                case "square":{
                    getPerimeterAreaText2 += "" + getPAFirstNumber + " " + getPAFirstOperator + " " + getPASecondNumber + " " + getPASecondOperator + " " + getPAThirdNumber +
                    " " + getPASecondOperator + " " + getPASecondNumber + " " + getPASecondOperator + " " + getPAThirdNumber;
                    break;
                }

                case "rectangle":{
                    getPerimeterAreaText2 += "" + getPAFirstNumber + " " + getPAFirstOperator + " " + getPASecondNumber + " " + getPASecondOperator + " " + getPAThirdNumber +
                    " " + getPASecondOperator + " " + getPASecondNumber + " " + getPASecondOperator + " " + getPAThirdNumber;
                    break;
                }

                case "triangleYellow":{
                    getPerimeterAreaText2 += "" + getPAFirstNumber + " " + getPAFirstOperator + " " + getPASecondNumber + " " + getPASecondOperator + " " + getPAThirdNumber +
                    " " + getPASecondOperator + " " + getPASecondNumber;
                   break;
                }

                default:

            }
        }

        else if(identify.equals("area")){

            switch(getPAImage){

                case "square":{
                    getPerimeterAreaText2 += "" + getPAFirstNumber + " " + getPAFirstOperator + " " + getPASecondNumber + " "
                    + getPASecondOperator + " " + getPAThirdNumber;
                    break;
                }

                case "rectangle":{
                    getPerimeterAreaText2 += "" + getPAFirstNumber + " " + getPAFirstOperator + " " + getPASecondNumber + " "
                    + getPASecondOperator + " " + getPAThirdNumber;
                    break;
                }

                case "triangleGreen":{
                    getPerimeterAreaText2 += "" + getPAFirstNumber + " " + getPAFirstOperator + " " + getPASecondNumber
                    + getPASecondOperator + " " + getPAThirdNumber;
                    break;
                }

                default:

            }
        }

        else if(identify.equals("square")){
            getPerimeterAreaText2 += "" + getPAFirstNumber;
        }

        else if(identify.equals("rectangle")){
            getPerimeterAreaText2 += "" + getPAFirstNumber;
        }

        perimeterAreaText2.setText(getPerimeterAreaText2);

    }
}