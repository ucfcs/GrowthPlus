package com.GrowthPlus.dataAccessLayer.RoadMapLesson;

import io.realm.RealmObject;
import io.realm.annotations.RealmClass;

@RealmClass(embedded = true)
public class RoadMapLesson extends RealmObject {
    String lessonName;
    String image;
    String category;
    Boolean isCurrent;
    Boolean isCompleted;
    Integer minScore;
    Integer maxScore;

    public RoadMapLesson(){}

    public RoadMapLesson(String lessonName, String image, String category, Boolean isCurrent, Boolean isCompleted, Integer minScore, Integer maxScore) {
        this.lessonName = lessonName;
        this.image = image;
        this.category = category;
        this.isCurrent = isCurrent;
        this.isCompleted = isCompleted;
        this.minScore = minScore;
        this.maxScore = maxScore;
    }

    public String getLessonName() {
        return lessonName;
    }

    public void setLessonName(String lessonName) {
        this.lessonName = lessonName;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Boolean getCurrent() {
        return isCurrent;
    }

    public void setCurrent(Boolean current) {
        isCurrent = current;
    }

    public Boolean getCompleted() {
        return isCompleted;
    }

    public void setCompleted(Boolean completed) {
        isCompleted = completed;
    }

    public Integer getMinScore() {
        return minScore;
    }

    public void setMinScore(Integer minScore) {
        this.minScore = minScore;
    }

    public Integer getMaxScore() {
        return maxScore;
    }

    public void setMaxScore(Integer maxScore) {
        this.maxScore = maxScore;
    }
}
