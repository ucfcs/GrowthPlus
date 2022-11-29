package com.fall2022_group20.dataAccessLayer.Lesson;

import android.util.Log;

import io.realm.Realm;
import io.realm.RealmResults;

public class LessonSchemaService {
    private Realm realm;
    private Integer lessonId;
    private Integer points;
    private String lessonName;

    public LessonSchemaService(Realm realm, Integer lessonId, Integer points, String lessonName){
        this.realm = realm;
        this.lessonId = lessonId;
        this.lessonName = lessonName;
        this.points = points;
    }

    public void createLesson (){
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

    public RealmResults<LessonSchema> getAllRoadMaps(){
        return realm.where(LessonSchema.class).findAll();
    }

    public LessonSchema getLessonSchema(){
        return realm.where(LessonSchema.class)
                .equalTo("lessonName", lessonName)
                .findFirst();
    }

    public void deleteRealmLesson(){
        realm.executeTransactionAsync(realm ->{
            LessonSchema lesson = getLessonSchema();
            lesson.deleteFromRealm();
            lesson = null;
        });
    }
}

