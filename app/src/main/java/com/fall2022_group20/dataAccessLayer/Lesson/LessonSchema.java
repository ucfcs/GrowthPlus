package com.fall2022_group20.dataAccessLayer.Lesson;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class LessonSchema extends RealmObject{
    @PrimaryKey
    private Integer id;
    private Integer points;
    private String lessonName;

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPoints() {return points;}
    public void setPoints(Integer points) {
        this.points = points;
    }

    public String getLessonName() {return lessonName;}
    public void setLessonName(String lessonName) {
        this.lessonName = lessonName;
    }
}
