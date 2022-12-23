package com.fall2022_group20.dummyData;

import android.util.Log;

import com.fall2022_group20.dataAccessLayer.Lesson.LessonSchema;
import com.fall2022_group20.dataAccessLayer.Lesson.LessonSchemaService;

import org.bson.types.ObjectId;

import io.realm.Realm;

public class LessonDummyData {
    private String lessonId;
    private Integer points;
    private String lessonName;
    private Realm realm;

    public LessonDummyData(String lessonName, Realm realm, Integer points){
        this.points = points;
        this.lessonName = lessonName;
        this.realm = realm;

    }

    /*
    * Check if the realm object already exist
    * */
    public void createLessonDummyData(){
        LessonSchemaService lesson = new LessonSchemaService(realm, points, lessonName);
        if(lesson.getLessonByName() == null){
            this.lessonId = String.valueOf(new ObjectId());
            lesson.createLesson(lessonId);
        }
    }

    // Need to create an upsert method to prevent duplicate values
}
