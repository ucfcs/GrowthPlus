package com.GrowthPlus.dataAccessLayer.Quiz;

import com.GrowthPlus.dataAccessLayer.QuizContent.QuizContent;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class QuizSchema extends RealmObject {
    @PrimaryKey
    private String quizId;
    private String quizName;
    private String image;
    private Integer maxPoints;
    private Integer minPoints;
    private RealmList<QuizContent> quizContents;

    public String getQuizId() {
        return quizId;
    }

    public void setQuizId(String quizId) {
        this.quizId = quizId;
    }

    public String getQuizName() {
        return quizName;
    }

    public void setQuizName(String quizName) {
        this.quizName = quizName;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
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

    public RealmList<QuizContent> getQuizContentsContents() { return quizContents; }

    public void setQuizContents(RealmList<QuizContent> quizContents) { this.quizContents = quizContents; }

}
