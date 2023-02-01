package com.GrowthPlus.utilities;

import com.GrowthPlus.R;

import java.util.HashMap;

public class ColorIdentifier {

    private HashMap<String, Integer> colorId;

    public ColorIdentifier(){

        colorId = new HashMap<String, Integer>();
        colorId.put("red", R.color.red);
        colorId.put("dark_green", R.color.dark_green);
        colorId.put("light_green", R.color.light_green);
        colorId.put("orange", R.color.orange);
        colorId.put("yellow", R.color.yellow);
        colorId.put("white", R.color.white);
        colorId.put("black", R.color.black);
        colorId.put("gray", R.color.grey);
        colorId.put("blue", R.color.blue);
    }

    public Integer getColorIdentifier(String colorName){
        return colorId.get(colorName);
    }
}
