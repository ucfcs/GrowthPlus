package com.fall2022_group20.dataAccessLayer.Lesson;

import android.util.Log;

import io.realm.Realm;
import io.realm.RealmResults;

public class LessonSchemaService {
    private Realm realm;
    private String lessonId;
    private Integer points;
    private String lessonName;

    public LessonSchemaService(Realm realm, Integer points, String lessonName){
        this.realm = realm;
        this.lessonName = lessonName;
        this.points = points;
    }

    public String getLessonId() {
        return lessonId;
    }

    public void createLesson (String lessonId){
            this.lessonId = lessonId;
            realm.executeTransactionAsync(realm ->{
                LessonSchema newLessonSchema = realm.createObject(LessonSchema.class, String.valueOf(lessonId));
                newLessonSchema.setLessonName(lessonName);
                newLessonSchema.setPoints(points);
            }, () ->{
                Log.i("Success", "New Lesson added to realm");
            }, error -> {
                Log.e("Error", "Something went wrong! " + error);
            });
    }

    public RealmResults<LessonSchema> getAllLessons(){
        return realm.where(LessonSchema.class).findAll();
    }

    public LessonSchema getLessonByName(){
        return realm.where(LessonSchema.class)
                .equalTo("lessonName", lessonName)
                .findFirst();
    }

    public LessonSchema getLessonById( String lessonId){
        return realm.where(LessonSchema.class)
                .equalTo("lessonId", lessonId)
                .findFirst();
    }

    public void deleteRealmLesson(){
        realm.executeTransactionAsync(realm ->{
            LessonSchema lesson = getLessonByName();
            lesson.deleteFromRealm();
            lesson = null;
        });
    }
}

