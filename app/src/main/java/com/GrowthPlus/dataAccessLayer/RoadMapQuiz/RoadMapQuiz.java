package com.GrowthPlus.dataAccessLayer.RoadMapQuiz;

import io.realm.RealmObject;
import io.realm.annotations.RealmClass;

@RealmClass(embedded = true)
public class RoadMapQuiz extends RealmObject {
    String quizName;
    String image;
    Integer maxPoints;
    Integer minPoints;
    Boolean isCurrent;
    Boolean isCompleted;
    String question;
    String answer;

    public RoadMapQuiz() {
    }

    public RoadMapQuiz(
            String quizName,
            String image,
            Integer maxPoints,
            Integer minPoints,
            Boolean isCurrent,
            Boolean isCompleted,
            String question,
            String answer) {
        this.quizName = quizName;
        this.image = image;
        this.maxPoints = maxPoints;
        this.minPoints = minPoints;
        this.isCurrent = isCurrent;
        this.isCompleted = isCompleted;
        this.question = question;
        this.answer = answer;
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
}
