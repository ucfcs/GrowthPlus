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
        String getPAWord = requireArguments().getString("PAWord");
        String getPAFirstNumber = requireArguments().getString("PAFirstNumber");
        String getPAFirstOperator = requireArguments().getString("PAFirstOperator");
        String getPASecondNumber = requireArguments().getString("PASecondNumber");
        String getPASecondOperator = requireArguments().getString("PASecondOperator");
        String getPAThirdNumber = requireArguments().getString("PAThirdNumber");
        String getPAImage = requireArguments().getString("perimeterAreaImage");

        perimeterAreaText1.setText("");
        perimeterAreaText2.setText("");
        perimeterAreaImage.setImageResource(imageSrcIdentifier.getImageSrcId(getPAImage));

        if(getPAWord.equals("perimeter")){

            switch(getPAImage){

                case "square":{
                    break;
                }

                case "rectangle":{
                    break;
                }

                case "triangle":{
                   break;
                }

                default:

            }
        }

        else if(getPAWord.equals("area")){

            switch(getPAImage){

                case "square":{
                    break;
                }

                case "rectangle":{
                    break;
                }

                case "triangle":{
                    //isn't needed, but maybe it will be? idk
                    break;
                }

                default:

            }
        }

        else{
            //we shouldn't get here: word must be perimeter or area
        }

    }
}