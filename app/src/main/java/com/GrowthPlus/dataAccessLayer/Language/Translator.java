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
        String value = "";
        switch (word){
            case "language":
                value = languageSchema.getLanguage();
                break;

            case "lesson":
                value = languageSchema.getLesson();
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
            default:
                value = "empty";
        }
        return value;

    }
}
