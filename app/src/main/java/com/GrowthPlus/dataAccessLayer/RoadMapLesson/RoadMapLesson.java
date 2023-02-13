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
    String databaseLessonId;
    String currentLessonContentId;
    String currentFlashcardId;
    Boolean inLesson;
    Boolean inFlashcard;
    Integer currentScore;

    public RoadMapLesson(){}

    public RoadMapLesson(
            String lessonName,
            String image,
            String category,
            Boolean isCurrent,
            Boolean isCompleted,
            Integer minScore,
            Integer maxScore,
            String databaseLessonId,
            String currentLessonContentId,
            String currentFlashcardId,
            Boolean inLesson,
            Boolean inFlashcard,
            Integer currentScore
    ) {
        this.lessonName = lessonName;
        this.image = image;
        this.category = category;
        this.isCurrent = isCurrent;
        this.isCompleted = isCompleted;
        this.minScore = minScore;
        this.maxScore = maxScore;
        this.databaseLessonId = databaseLessonId;
        this.currentLessonContentId = currentLessonContentId;
        this.currentFlashcardId = currentFlashcardId;
        this.inLesson = inLesson;
        this.inFlashcard = inFlashcard;
        this.currentScore = currentScore;
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

    public String getDatabaseLessonId() {
        return databaseLessonId;
    }

    public void setDatabaseLessonId(String databaseLessonId) {
        this.databaseLessonId = databaseLessonId;
    }

    public String getCurrentLessonContentId() {
        return currentLessonContentId;
    }

    public void setCurrentLessonContentId(String currentLessonContentId) {
        this.currentLessonContentId = currentLessonContentId;
    }

    public String getCurrentFlashcardId() {
        return currentFlashcardId;
    }

    public void setCurrentFlashcardId(String currentFlashcardId) {
        this.currentFlashcardId = currentFlashcardId;
    }

    public Boolean getInLesson() {
        return inLesson;
    }

    public void setInLesson(Boolean inLesson) {
        this.inLesson = inLesson;
    }

    public Boolean getInFlashcard() {
        return inFlashcard;
    }

    public void setInFlashcard(Boolean inFlashcard) {
        this.inFlashcard = inFlashcard;
    }

    public Integer getCurrentScore() {
        return currentScore;
    }

    public void setCurrentScore(Integer currentScore) {
        this.currentScore = currentScore;
    }
}
