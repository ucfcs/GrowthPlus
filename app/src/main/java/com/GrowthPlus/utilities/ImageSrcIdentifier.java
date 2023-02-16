package com.GrowthPlus.utilities;

import com.GrowthPlus.R;

import java.util.HashMap;

public class ImageSrcIdentifier {

    private HashMap<String, Integer> imageSrcId;

    public ImageSrcIdentifier(){

        imageSrcId = new HashMap<String, Integer>();
        imageSrcId.put("bunny", R.mipmap.bunny_foreground);
        imageSrcId.put("camel", R.mipmap.camel_foreground);
        imageSrcId.put("elephant", R.mipmap.elephant_foreground);
        imageSrcId.put("giraffe", R.mipmap.giraffe_foreground);
        imageSrcId.put("guinea_fowl", R.mipmap.guinea_fowl_foreground);
        imageSrcId.put("squirrel", R.mipmap.squirrel_foreground);
        imageSrcId.put("unit", R.layout.custom_unit);
        imageSrcId.put("DUimg", R.mipmap.du_foreground);
        imageSrcId.put("CDUimg", R.mipmap.cdu_foreground);
        imageSrcId.put("unit", R.mipmap.unit_foreground);
        imageSrcId.put("unitOfTen", R.mipmap.unit_of_ten_foreground);
        imageSrcId.put("unitOfOneHundred", R.mipmap.unit_of_one_hundred_foreground);
        imageSrcId.put("unitOfOneThousand", R.mipmap.unit_of_one_thousand_foreground);
        imageSrcId.put("twoElephants", R.mipmap.two_elephants_foreground);
        imageSrcId.put("fiveElephants", R.mipmap.five_elephants_foreground);
    }

    public Integer getImageSrcId(String imageName){
        return imageSrcId.get(imageName);
    }
}
