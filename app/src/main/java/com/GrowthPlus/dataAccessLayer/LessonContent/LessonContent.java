package com.GrowthPlus.dataAccessLayer.LessonContent;

import java.util.ArrayList;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class LessonContent extends RealmObject {
    @PrimaryKey
    private String lessonContentId;
    private String word;
    private String number;
    private String imgNum;

    public String getLessonContentId() { return lessonContentId; }

    public void setLessonContentId(String lessonContentId) { this.lessonContentId = lessonContentId; }

    public String getWord() { return word; }

    public void setWord(String word) { this.word = word; }

    public String getNumber() { return number; }

    public void setNumber(String numbers) { this.number = number; }

    public String getImgNum() { return imgNum; }

    public void setImgNum(String imgNum) { this.imgNum = imgNum; }
}
