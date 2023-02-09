package com.GrowthPlus.dataAccessLayer.Flashcard;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class FlashcardSchema extends RealmObject {
    @PrimaryKey
    private String flashCardId;
    private String flashCardName;
    private String question;
    private String answer;
    private Integer lessonNumber;

    public String getFlashCardId() {
        return flashCardId;
    }

    public void setFlashCardId(String flashCardId) {
        this.flashCardId = flashCardId;
    }

    public String getFlashCardName() {
        return flashCardName;
    }

    public void setFlashCardName(String flashCardName) {
        this.flashCardName = flashCardName;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public Integer getLessonNumber() {
        return lessonNumber;
    }

    public void setLessonNumber(Integer lessonNumber) {
        this.lessonNumber = lessonNumber;
    }
}
