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
        imageSrcId.put("unitOfTenThousand", R.mipmap.unit_of_ten_thousand_foreground);
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
        imageSrcId.put("twoThousandFrancs", R.mipmap.two_thousand_fcfa_foreground);
        imageSrcId.put("fiveThousandFrancs", R.mipmap.five_thousand_fcfa_foreground);
        imageSrcId.put("tenThousandFrancs", R.mipmap.ten_thousand_fcfa_foreground);
        imageSrcId.put("clock", R.mipmap.clock_foreground);
        imageSrcId.put("clockLargeHand", R.mipmap.clock_large_hand_foreground);
        imageSrcId.put("clockSmallHand", R.mipmap.clock_small_hand_foreground);
        imageSrcId.put("circle", R.mipmap.circle_foreground);
        imageSrcId.put("rectangle", R.mipmap.rectangle_foreground);
        imageSrcId.put("square", R.mipmap.square_foreground);
        imageSrcId.put("triangleYellow", R.mipmap.triangle_foreground);
        imageSrcId.put("triangleGreen", R.mipmap.triangle_green_foreground);
        imageSrcId.put("rectangleAngles", R.mipmap.rectangle_angles_foreground);
        imageSrcId.put("squareAngles", R.mipmap.square_angles_foreground);
        imageSrcId.put("rightTriangleAngles", R.mipmap.right_triangle_angles_foreground);
        imageSrcId.put("equTriangleAngles", R.mipmap.equ_triangle_angles_foreground);
        imageSrcId.put("perLineOne", R.mipmap.per_line_one_foreground);
        imageSrcId.put("perLineTwo", R.mipmap.per_line_two_foreground);
        imageSrcId.put("PLOne", R.mipmap.per_line_one_foreground);
        imageSrcId.put("PLTwo", R.mipmap.pl_two_foreground);
        imageSrcId.put("PLThree", R.mipmap.pl_three_foreground);
        imageSrcId.put("rightAngle", R.mipmap.right_angle_foreground);





    }

    public Integer getImageSrcId(String imageName){
        return imageSrcId.get(imageName);
    }
}
