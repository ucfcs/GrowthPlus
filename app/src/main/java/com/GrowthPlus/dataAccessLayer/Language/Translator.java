package com.GrowthPlus.dataAccessLayer.Language;

import io.realm.Realm;

public class Translator {
    private LanguageSchema languageSchema;
    private LanguageSchemaService languageSchemaService;
    private Realm realm;

    public Translator(String id){
        realm = Realm.getDefaultInstance();
        languageSchemaService = new LanguageSchemaService(realm, id);
        languageSchema = languageSchemaService.getLanguageSchemaById();
    }

    public String getString(String word){
        String value = "empty";
        switch (word){
            case "languageId":
                value = languageSchema.getLanguageId();
                break;
            case "languageName":
                value = languageSchema.getLanguageName();
                break;
            case "language":
                value = languageSchema.getLanguage();
                break;
            case "english":
                value = languageSchema.getEnglish();
                break;
            case "french":
                value = languageSchema.getFrench();
                break;
            case "growthPlus":
                value = languageSchema.getGrowthPlus();
                break;
            case "parent":
                value = languageSchema.getParent();
                break;
            case "child":
                value = languageSchema.getChild();
                break;
            case "points":
                value = languageSchema.getPoints();
                break;
            case "pin":
                value = languageSchema.getPin();
                break;
            case "forgotPin":
                value = languageSchema.getForgotPin();
                break;
            case "create":
                value = languageSchema.getCreate();
                break;
            case "confirm":
                value = languageSchema.getConfirm();
                break;
            case "numbers":
                value = languageSchema.getNumbers();
                break;
            case "addition":
                value = languageSchema.getAddition();
                break;
            case "subtraction":
                value = languageSchema.getSubtraction();
                break;
            case "multiplication":
                value = languageSchema.getMultiplication();
                break;
            case "division":
                value = languageSchema.getDivision();
                break;
            case "measurements":
                value = languageSchema.getMeasurements();
                break;
            case "weights":
                value = languageSchema.getWeights();
                break;
            case "money":
                value = languageSchema.getMoney();
                break;
            case "time":
                value = languageSchema.getTime();
                break;
            case "shapes":
                value = languageSchema.getShapes();
                break;
            case "angles":
                value = languageSchema.getAngles();
                break;
            case "name":
                value = languageSchema.getName();
                break;
            case "level":
                value = languageSchema.getLevel();
                break;
            case "Lesson":
                value = languageSchema.getLesson();
                break;
            case "Game":
                value = languageSchema.getGame();
                break;
            case "roadmap":
                value = languageSchema.getRoadmap();
                break;
            case "francs":
                value = languageSchema.getFrancs();
                break;
            case "cfa":
                value = languageSchema.getCfa();
                break;
            case "centimeter":
                value = languageSchema.getCentimeter();
                break;
            case "decimeter":
                value = languageSchema.getDecimeter();
                break;
            case "meter":
                value = languageSchema.getMeter();
                break;
            case "decameter":
                value = languageSchema.getDecameter();
                break;
            case "hectometer":
                value = languageSchema.getHectometer();
                break;
            case "centigram":
                value = languageSchema.getCentigram();
                break;
            case "decigram":
                value = languageSchema.getDecigram();
                break;
            case "gram":
                value = languageSchema.getGram();
                break;
            case "decagram":
                value = languageSchema.getDecagram();
                break;
            case "hectogram":
                value = languageSchema.getHectogram();
                break;
            case "centiliter":
                value = languageSchema.getCentiliter();
                break;
            case "deciliter":
                value = languageSchema.getDeciliter();
                break;
            case "liter":
                value = languageSchema.getLiter();
                break;
            case "millimeter":
                value = languageSchema.getMillimeter();
                break;
            case "kilometer":
                value = languageSchema.getKilometer();
                break;
            case "milligram":
                value = languageSchema.getMilligram();
                break;
            case "kilogram":
                value = languageSchema.getKilogram();
                break;
            case "milliliter":
                value = languageSchema.getMilliliter();
                break;
            case "kiloliter":
                value = languageSchema.getKiloliter();
                break;
            case "unit":
                value = languageSchema.getUnit();
                break;
            case "unitOfTen":
                value = languageSchema.getUnitOfTen();
                break;
            case "unitOfOneHundred":
                value = languageSchema.getUnitOfOneHundred();
                break;
            case "clock":
                value = languageSchema.getClock();
                break;
            case "hour":
                value = languageSchema.getHour();
                break;
            case "minute":
                value = languageSchema.getMinute();
                break;
            case "day":
                value = languageSchema.getDay();
                break;
            case "circle":
                value = languageSchema.getCircle();
                break;
            case "ray":
                value = languageSchema.getRay();
                break;
            case "diameter":
                value = languageSchema.getDiameter();
                break;
            case "center":
                value = languageSchema.getCenter();
                break;
            case "rectangle":
                value = languageSchema.getRectangle();
                break;
            case "width":
                value = languageSchema.getWidth();
                break;
            case "length":
                value = languageSchema.getLength();
                break;
            case "side":
                value = languageSchema.getSide();
                break;
            case "square":
                value = languageSchema.getSquare();
                break;
            case "triangle":
                value = languageSchema.getTriangle();
                break;
            case "perimeter":
                value = languageSchema.getPerimeter();
                break;
            case "parallelLines":
                value = languageSchema.getParallelLines();
                break;
            case "perpendicularLines":
                value = languageSchema.getPerpendicularLines();
                break;
            case "rightAngle":
                value = languageSchema.getRightAngle();
                break;
            case "area":
                value = languageSchema.getArea();
                break;
            case "one":
                value = languageSchema.getOne();
                break;
            case "two":
                value = languageSchema.getTwo();
                break;
            case "three":
                value = languageSchema.getThree();
                break;
            case "four":
                value = languageSchema.getFour();
                break;
            case "five":
                value = languageSchema.getFive();
                break;
            case "six":
                value = languageSchema.getSix();
                break;
            case "seven":
                value = languageSchema.getSeven();
                break;
            case "eight":
                value = languageSchema.getEight();
                break;
            case "nine":
                value = languageSchema.getNine();
                break;
            case "ten":
                value = languageSchema.getTen();
                break;
            case "eleven":
                value = languageSchema.getEleven();
                break;
            case "twelve":
                value = languageSchema.getTwelve();
                break;
            case "thirteen":
                value = languageSchema.getThirteen();
                break;
            case "fourteen":
                value = languageSchema.getFourteen();
                break;
            case "fifteen":
                value = languageSchema.getFifteen();
                break;
            case "sixteen":
                value = languageSchema.getSixteen();
                break;
            case "seventeen":
                value = languageSchema.getSeventeen();
                break;
            case "eighteen":
                value = languageSchema.getEighteen();
                break;
            case "nineteen":
                value = languageSchema.getNineteen();
                break;
            case "twenty":
                value = languageSchema.getTwenty();
                break;
            case "twentyOne":
                value = languageSchema.getTwentyOne();
                break;
            case "twentyTwo":
                value = languageSchema.getTwentyTwo();
                break;
            case "twentyThree":
                value = languageSchema.getTwentyThree();
                break;
            case "twentyFour":
                value = languageSchema.getTwentyFour();
                break;
            case "twentyFive":
                value = languageSchema.getTwentyFive();
                break;
            case "twentySix":
                value = languageSchema.getTwentySix();
                break;
            case "twentySeven":
                value = languageSchema.getTwentySeven();
                break;
            case "twentyEight":
                value = languageSchema.getTwentyEight();
                break;
            case "twentyNine":
                value = languageSchema.getTwentyNine();
                break;
            case "thirty":
                value = languageSchema.getThirty();
                break;
            case "thirtyOne":
                value = languageSchema.getThirtyOne();
                break;
            case "thirtyTwo":
                value = languageSchema.getThirtyTwo();
                break;
            case "thirtyThree":
                value = languageSchema.getThirtyThree();
                break;
            case "thirtyFour":
                value = languageSchema.getThirtyFour();
                break;
            case "thirtyFive":
                value = languageSchema.getThirtyFive();
                break;
            case "thirtySix":
                value = languageSchema.getThirtySix();
                break;
            case "thirtySeven":
                value = languageSchema.getThirtySeven();
                break;
            case "thirtyEight":
                value = languageSchema.getThirtyEight();
                break;
            case "thirtyNine":
                value = languageSchema.getThirtyNine();
                break;
            case "forty":
                value = languageSchema.getForty();
                break;
            case "fortyOne":
                value = languageSchema.getFortyOne();
                break;
            case "fortyTwo":
                value = languageSchema.getFortyTwo();
                break;
            case "fortyThree":
                value = languageSchema.getFortyThree();
                break;
            case "fortyFour":
                value = languageSchema.getFortyFour();
                break;
            case "fortyFive":
                value = languageSchema.getFortyFive();
                break;
            case "fortySix":
                value = languageSchema.getFortySix();
                break;
            case "fortySeven":
                value = languageSchema.getFortySeven();
                break;
            case "fortyEight":
                value = languageSchema.getFortyEight();
                break;
            case "fortyNine":
                value = languageSchema.getFortyNine();
                break;
            case "fifty":
                value = languageSchema.getFifty();
                break;
            case "fiftyOne":
                value = languageSchema.getFiftyOne();
                break;
            case "fiftyTwo":
                value = languageSchema.getFiftyTwo();
                break;
            case "fiftyThree":
                value = languageSchema.getFiftyThree();
                break;
            case "fiftyFour":
                value = languageSchema.getFiftyFour();
                break;
            case "fiftyFive":
                value = languageSchema.getFiftyFive();
                break;
            case "fiftySix":
                value = languageSchema.getFiftySix();
                break;
            case "fiftySeven":
                value = languageSchema.getFiftySeven();
                break;
            case "fiftyEight":
                value = languageSchema.getFiftyEight();
                break;
            case "fiftyNine":
                value = languageSchema.getFiftyNine();
                break;
            case "sixty":
                value = languageSchema.getSixty();
                break;
            case "sixtyOne":
                value = languageSchema.getSixtyOne();
                break;
            case "sixtyTwo":
                value = languageSchema.getSixtyTwo();
                break;
            case "sixtyThree":
                value = languageSchema.getSixtyThree();
                break;
            case "sixtyFour":
                value = languageSchema.getSixtyFour();
                break;
            case "sixtyFive":
                value = languageSchema.getSixtyFive();
                break;
            case "sixtySix":
                value = languageSchema.getSixtySix();
                break;
            case "sixtySeven":
                value = languageSchema.getSixtySeven();
                break;
            case "sixtyEight":
                value = languageSchema.getSixtyEight();
                break;
            case "sixtyNine":
                value = languageSchema.getSixtyNine();
                break;
            case "seventy":
                value = languageSchema.getSeventy();
                break;
            case "seventyOne":
                value = languageSchema.getSeventyOne();
                break;
            case "seventyTwo":
                value = languageSchema.getSeventyTwo();
                break;
            case "seventyThree":
                value = languageSchema.getSeventyThree();
                break;
            case "seventyFour":
                value = languageSchema.getSeventyFour();
                break;
            case "seventyFive":
                value = languageSchema.getSeventyFive();
                break;
            case "seventySix":
                value = languageSchema.getSeventySix();
                break;
            case "seventySeven":
                value = languageSchema.getSeventySeven();
                break;
            case "seventyEight":
                value = languageSchema.getSeventyEight();
                break;
            case "seventyNine":
                value = languageSchema.getSeventyNine();
                break;
            case "eighty":
                value = languageSchema.getEighty();
                break;
            case "eightyOne":
                value = languageSchema.getEightyOne();
                break;
            case "eightyTwo":
                value = languageSchema.getEightyTwo();
                break;
            case "eightyThree":
                value = languageSchema.getEightyThree();
                break;
            case "eightyFour":
                value = languageSchema.getEightyFour();
                break;
            case "eightyFive":
                value = languageSchema.getEightyFive();
                break;
            case "eightySix":
                value = languageSchema.getEightySix();
                break;
            case "eightySeven":
                value = languageSchema.getEightySeven();
                break;
            case "eightyEight":
                value = languageSchema.getEightyEight();
                break;
            case "eightyNine":
                value = languageSchema.getEightyNine();
                break;
            case "ninety":
                value = languageSchema.getNinety();
                break;
            case "ninetyOne":
                value = languageSchema.getNinetyOne();
                break;
            case "ninetyTwo":
                value = languageSchema.getNinetyTwo();
                break;
            case "ninetyThree":
                value = languageSchema.getNinetyThree();
                break;
            case "ninetyFour":
                value = languageSchema.getNinetyFour();
                break;
            case "ninetyFive":
                value = languageSchema.getNinetyFive();
                break;
            case "ninetySix":
                value = languageSchema.getNinetySix();
                break;
            case "ninetySeven":
                value = languageSchema.getNinetySeven();
                break;
            case "ninetyEight":
                value = languageSchema.getNinetyEight();
                break;
            case "ninetyNine":
                value = languageSchema.getNinetyNine();
                break;
            case "oneHundred":
                value = languageSchema.getOneHundred();
                break;
            case "twoHundred":
                value = languageSchema.getTwoHundred();
                break;
            case "threeHundred":
                value = languageSchema.getThreeHundred();
                break;
            case "fourHundred":
                value = languageSchema.getFourHundred();
                break;
            case "fiveHundred":
                value = languageSchema.getFiveHundred();
                break;
            case "sixHundred":
                value = languageSchema.getSixHundred();
                break;
            case "sevenHundred":
                value = languageSchema.getSevenHundred();
                break;
            case "eightHundred":
                value = languageSchema.getEightHundred();
                break;
            case "nineHundred":
                value = languageSchema.getNineHundred();
                break;
            case "oneThousand":
                value = languageSchema.getOneThousand();
                break;
            case "twoThousand":
                value = languageSchema.getTwoThousand();
                break;
            case "threeThousand":
                value = languageSchema.getThreeThousand();
                break;
            case "fourThousand":
                value = languageSchema.getFourThousand();
                break;
            case "fiveThousand":
                value = languageSchema.getFiveThousand();
                break;
            case "sixThousand":
                value = languageSchema.getSixThousand();
                break;
            case "sevenThousand":
                value = languageSchema.getSevenThousand();
                break;
            case "eightThousand":
                value = languageSchema.getEightThousand();
                break;
            case "nineThousand":
                value = languageSchema.getNineThousand();
                break;
            case "tenThousand":
                value = languageSchema.getTenThousand();
                break;
            default:
                value = "empty";
        }
        return value;
    }
}
