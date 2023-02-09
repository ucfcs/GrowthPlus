package com.GrowthPlus.dataAccessLayer.LessonContent;

import android.util.Log;

import com.GrowthPlus.dataAccessLayer.Lesson.LessonSchema;

import io.realm.Realm;
import io.realm.RealmResults;

public class LessonContentService {
    private Realm realm;
    private String lessonContentId;
    private String word;
    private String number;
    private String imgNum;


    public LessonContentService(Realm realm, String lessonContentId, String word, String number, String imgNum) {
        this.realm = realm;
        this.lessonContentId = lessonContentId;
        this.word = word;
        this.number = number;
        this.imgNum = imgNum;
    }

    public String getLessonContentId() {
        return lessonContentId;
    }

    public void createLessonContent (String lessonContentId){
        this.lessonContentId = lessonContentId;
        realm.executeTransactionAsync(realm ->{
            LessonContent newLessonContent = realm.createObject(LessonContent.class, String.valueOf(lessonContentId));
            newLessonContent.setWord(word);
            newLessonContent.setNumber(number);
            newLessonContent.setImgNum(imgNum);
        }, () ->{
            Log.i("Success", "New Lesson Content added to realm");
        }, error -> {
            Log.e("Error", "Something went wrong! " + error);
        });
    }

    public RealmResults<LessonContent> getAllLessonContent(){
        return realm.where(LessonContent.class).findAll();
    }

    public LessonContent getLessonByName(){
        return realm.where(LessonContent.class)
                .equalTo("word", word)
                .findFirst();
    }

    public LessonContent getLessonById( String lessonId){
        return realm.where(LessonContent.class)
                .equalTo("lessonContentId", lessonId)
                .findFirst();
    }

    public void deleteRealmLessonContent(){
        realm.executeTransactionAsync(realm ->{
            LessonContent lessonContent = getLessonByName();
            lessonContent.deleteFromRealm();
            lessonContent = null;
        });
    }
}
