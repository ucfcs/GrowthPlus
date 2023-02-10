package com.GrowthPlus.dataAccessLayer.Lesson;

import android.util.Log;

import com.GrowthPlus.dataAccessLayer.LessonContent.LessonContent;

import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmResults;

public class LessonSchemaService {
    private Realm realm;
    private String lessonId;
    private Integer maxPoints;
    private Integer minPoints;
    private String lessonName;
    private String image;
    private RealmList<LessonContent> contents;

    public LessonSchemaService(Realm realm, String lessonId, Integer maxPoints, Integer minPoints, String lessonName, String image, RealmList<LessonContent> contents) {
        this.realm = realm;
        this.lessonId = lessonId;
        this.maxPoints = maxPoints;
        this.minPoints = minPoints;
        this.lessonName = lessonName;
        this.image = image;
        this.contents = contents;
    }

    public String getLessonId() {
        return lessonId;
    }

    public void createLesson (String lessonId){
            this.lessonId = lessonId;
            realm.executeTransactionAsync(realm ->{
                LessonSchema newLessonSchema = realm.createObject(LessonSchema.class, String.valueOf(lessonId));
                newLessonSchema.setLessonName(lessonName);
                newLessonSchema.setMinPoints(minPoints);
                newLessonSchema.setMaxPoints(maxPoints);
                newLessonSchema.setImage(image);
                newLessonSchema.setContents(contents);
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

