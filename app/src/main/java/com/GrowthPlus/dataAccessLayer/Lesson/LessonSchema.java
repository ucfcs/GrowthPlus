package com.GrowthPlus.dataAccessLayer.Lesson;

import com.GrowthPlus.dataAccessLayer.Flashcard.FlashcardSchema;

import com.GrowthPlus.dataAccessLayer.LessonContent.LessonContent;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class LessonSchema extends RealmObject{
    @PrimaryKey
    private String lessonId;
    private Integer maxPoints;
    private Integer minPoints;
    private String lessonName;
    private String category;
    private String image;
    private RealmList<LessonContent> contents;
    private RealmList<FlashcardSchema> flashcards;

    public String getLessonId() {
        return lessonId;
    }

    public void setLessonId(String lessonId) {
        this.lessonId = lessonId;
    }

    public String getLessonName() {
        return lessonName;
    }

    public void setLessonName(String lessonName) {
        this.lessonName = lessonName;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Integer getMaxPoints() {
        return maxPoints;
    }

    public void setMaxPoints(Integer maxPoints) {
        this.maxPoints = maxPoints;
    }

    public Integer getMinPoints() {
        return minPoints;
    }

    public void setMinPoints(Integer minPoints) {
        this.minPoints = minPoints;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public RealmList<LessonContent> getContents() {
        return contents;
    }

    public void setContents(RealmList<LessonContent> contents){
        this.contents = contents;
    }

    public RealmList<FlashcardSchema> getFlashcards() {
        return flashcards;
    }

    public void setFlashcards(RealmList<FlashcardSchema> flashcards) {
        this.flashcards = flashcards;
    }
}
