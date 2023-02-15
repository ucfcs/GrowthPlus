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

    }

    public Integer getImageSrcId(String imageName){
        return imageSrcId.get(imageName);
    }
}
