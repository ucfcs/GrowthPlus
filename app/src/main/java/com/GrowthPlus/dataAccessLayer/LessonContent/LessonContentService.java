package com.GrowthPlus.dataAccessLayer.LessonContent;

import android.util.Log;

import com.GrowthPlus.dataAccessLayer.Lesson.LessonSchema;

import io.realm.Realm;
import io.realm.RealmResults;

public class LessonContentService {
    private Realm realm;
    private String lessonContentId;
    private String word;
    private String firstNumber;
    private String firstOperator;
    private String secondNumber;
    private String secondOperator;
    private String thirdNumber;
    private String imgOne;
    private String imgTwo;
    private String imgThree;


    public LessonContentService(Realm realm, String lessonContentId, String word, String firstNumber, String firstOperator, String secondNumber, String secondOperator, String thirdNumber, String imgOne, String imgTwo, String imgThree, String imgFour, String imgFive) {
        this.realm = realm;
        this.lessonContentId = lessonContentId;
        this.word = word;
        this.firstNumber = firstNumber;
        this.firstOperator = firstOperator;
        this.secondNumber = secondNumber;
        this.secondOperator = secondOperator;
        this.thirdNumber = thirdNumber;
        this.imgOne = imgOne;
        this.imgTwo = imgTwo;
        this.imgThree = imgThree;
    }

    public String getLessonContentId() {
        return lessonContentId;
    }

    public void createLessonContent (String lessonContentId){
        this.lessonContentId = lessonContentId;
        realm.executeTransactionAsync(realm ->{
            LessonContent newLessonContent = realm.createObject(LessonContent.class, String.valueOf(lessonContentId));
            newLessonContent.setWord(word);
            newLessonContent.setFirstNumber(firstNumber);
            newLessonContent.setFirstOperator(firstOperator);
            newLessonContent.setSecondNumber(secondNumber);
            newLessonContent.setSecondOperator(secondOperator);
            newLessonContent.setThirdNumber(thirdNumber);
            newLessonContent.setImgOne(imgOne);
            newLessonContent.setImgTwo(imgTwo);
            newLessonContent.setImgThree(imgThree);
        }, () ->{
            Log.i("Success", "New Lesson Content added to realm");
        }, error -> {
            Log.e("Error", "Something went wrong! " + error);
        });
    }

    public RealmResults<LessonContent> getAllLessonContent(){
        return realm.where(LessonContent.class).findAll();
    }

    public LessonContent getLessonById(String lessonContentId){
        return realm.where(LessonContent.class)
                .equalTo("lessonContentId", lessonContentId)
                .findFirst();
    }

    public void deleteRealmLessonContent(){
        realm.executeTransactionAsync(realm ->{
            LessonContent lessonContent = getLessonById(lessonContentId);
            lessonContent.deleteFromRealm();
            lessonContent = null;
        });
    }
}
