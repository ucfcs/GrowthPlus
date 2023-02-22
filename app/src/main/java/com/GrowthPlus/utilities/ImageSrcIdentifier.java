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
        imageSrcId.put("DUimg", R.mipmap.du_foreground);
        imageSrcId.put("CDUimg", R.mipmap.cdu_foreground);
        imageSrcId.put("unit", R.mipmap.unit_foreground);
        imageSrcId.put("unitOfTen", R.mipmap.unit_of_ten_foreground);
        imageSrcId.put("unitOfOneHundred", R.mipmap.unit_of_one_hundred_foreground);
        imageSrcId.put("unitOfOneThousand", R.mipmap.unit_of_one_thousand_foreground);
        imageSrcId.put("twoElephants", R.mipmap.two_elephants_foreground);
        imageSrcId.put("fiveElephants", R.mipmap.five_elephants_foreground);
        imageSrcId.put("fiveFrancs", R.mipmap.five_fcfa_foreground);
        imageSrcId.put("tenFrancs", R.mipmap.ten_fcfa_foreground);
        imageSrcId.put("twentyFiveFrancs", R.mipmap.twenty_five_fcfa_foreground);
        imageSrcId.put("fiftyFrancs", R.mipmap.fifty_fcfa_foreground);
        imageSrcId.put("oneHundredFrancs", R.mipmap.one_hundred_fcfa_foreground);
        imageSrcId.put("fiveHundredCoin", R.mipmap.five_hundred_coin_fcfa_foreground);
        imageSrcId.put("centimeter", R.mipmap.centimeter_foreground);
        imageSrcId.put("decimeter", R.mipmap.decimeter_foreground);
        imageSrcId.put("meter", R.mipmap.meter_foreground);
        imageSrcId.put("hectometer", R.mipmap.hectometer_foreground);
        imageSrcId.put("millimeter", R.mipmap.millimeter_foreground);
        imageSrcId.put("kilometer", R.mipmap.kilometer_foreground);
        imageSrcId.put("centigram", R.mipmap.centigram_foreground);
        imageSrcId.put("decigram", R.mipmap.decigram_foreground);
        imageSrcId.put("gram", R.mipmap.gram_foreground);
        imageSrcId.put("decagram", R.mipmap.decagram_foreground);
        imageSrcId.put("hectogram", R.mipmap.hectogram_foreground);
        imageSrcId.put("milligram", R.mipmap.milligram_foreground);
        imageSrcId.put("kilogram", R.mipmap.kilogram_foreground);
        imageSrcId.put("centiliter", R.mipmap.centiliter_foreground);
        imageSrcId.put("deciliter", R.mipmap.deciliter_foreground);
        imageSrcId.put("liter", R.mipmap.liter_foreground);
        imageSrcId.put("milliliter", R.mipmap.milliter_foreground);
        imageSrcId.put("kiloliter", R.mipmap.kiloliter_foreground);
        imageSrcId.put("fiveHundredFrancs", R.mipmap.five_hundred_fcfa_foreground);
        imageSrcId.put("oneThousandFrancs", R.mipmap.one_thousand_fcfa_foreground);
        imageSrcId.put("clock", R.mipmap.clock_foreground);
        imageSrcId.put("circle", R.mipmap.circle_foreground);
        imageSrcId.put("rectangle", R.mipmap.rectangle_foreground);
        imageSrcId.put("triangleYellow", R.mipmap.triangle_foreground);
        imageSrcId.put("triangleGreen", R.mipmap.triangle_green_foreground);

    }

    public Integer getImageSrcId(String imageName){
        return imageSrcId.get(imageName);
    }
}
