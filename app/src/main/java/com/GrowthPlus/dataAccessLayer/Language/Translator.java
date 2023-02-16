package com.GrowthPlus.dataAccessLayer.Language;

import com.GrowthPlus.dataAccessLayer.Lesson.LessonSchema;
import com.GrowthPlus.dataAccessLayer.Lesson.LessonSchemaService;

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
            case "language":
                value = languageSchema.getLanguage();
                break;

            case "lesson":
                value = languageSchema.getLesson();
                break;
            case "unit":
                value = languageSchema.getUnit();
                break;
            case "unitOfTen":
                value = languageSchema.getUnitOfTen();
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

            default:
                value = "empty";
        }
        return value;

    }
}
