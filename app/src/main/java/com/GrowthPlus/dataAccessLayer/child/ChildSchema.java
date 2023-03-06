package com.GrowthPlus.dataAccessLayer.child;

import com.GrowthPlus.dataAccessLayer.ChildRoadMap.ChildRoadMap;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class ChildSchema extends RealmObject {
    @PrimaryKey
    private String childId;
    private String name;
    private String avatarName;
    private String colorName;
    private Integer score;
    private Integer totalLessonsCompleted;
    ChildRoadMap roadMapOne;
    ChildRoadMap roadMapTwo;
    ChildRoadMap roadMapThree;
    ChildRoadMap roadMapFour;
    private Integer catCountNumbers;
    private Integer catCountUnits;
    private Integer catCountAddition;
    private Integer catCountSubtraction;
    private Integer catCountMultiplication;
    private Integer catCountDivision;
    private Integer catCountLength;
    private Integer catCountWeightVolume;
    private Integer catCountMoney;
    private Integer catCountTime;
    private Integer catCountShapes;
    private Integer catCountAngles;
    private Integer catCountReview;

    public String getChildId() {
        return childId;
    }

    public void setChildId(String childId) {
        this.childId = childId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAvatarName() {
        return avatarName;
    }

    public void setAvatarName(String avatarName) {
        this.avatarName = avatarName;
    }

    public String getColorName() {
        return colorName;
    }

    public void setColorName(String colorName) {
        this.colorName = colorName;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public ChildRoadMap getRoadMapOne() {
        return roadMapOne;
    }

    public void setRoadMapOne(ChildRoadMap roadMapOne) {
        this.roadMapOne = roadMapOne;
    }

    public ChildRoadMap getRoadMapTwo() {
        return roadMapTwo;
    }

    public void setRoadMapTwo(ChildRoadMap roadMapTwo) {
        this.roadMapTwo = roadMapTwo;
    }

    public ChildRoadMap getRoadMapThree() {
        return roadMapThree;
    }

    public void setRoadMapThree(ChildRoadMap roadMapThree) {
        this.roadMapThree = roadMapThree;
    }

    public ChildRoadMap getRoadMapFour() {
        return roadMapFour;
    }

    public void setRoadMapFour(ChildRoadMap roadMapFour) {
        this.roadMapFour = roadMapFour;
    }

    public Integer getCatCountNumbers() {
        return catCountNumbers;
    }

    public void setCatCountNumbers(Integer catCountNumbers) {
        this.catCountNumbers = catCountNumbers;
    }

    public Integer getCatCountUnits() {
        return catCountUnits;
    }

    public void setCatCountUnits(Integer catCountUnits) {
        this.catCountUnits = catCountUnits;
    }

    public Integer getCatCountAddition() {
        return catCountAddition;
    }

    public void setCatCountAddition(Integer catCountAddition) {
        this.catCountAddition = catCountAddition;
    }

    public Integer getCatCountSubtraction() {
        return catCountSubtraction;
    }

    public void setCatCountSubtraction(Integer catCountSubtraction) {
        this.catCountSubtraction = catCountSubtraction;
    }

    public Integer getCatCountMultiplication() {
        return catCountMultiplication;
    }

    public void setCatCountMultiplication(Integer catCountMultiplication) {
        this.catCountMultiplication = catCountMultiplication;
    }

    public Integer getCatCountDivision() {
        return catCountDivision;
    }

    public void setCatCountDivision(Integer catCountDivision) {
        this.catCountDivision = catCountDivision;
    }

    public Integer getCatCountLength() {
        return catCountLength;
    }

    public void setCatCountLength(Integer catCountLength) {
        this.catCountLength = catCountLength;
    }

    public Integer getCatCountWeightVolume() {
        return catCountWeightVolume;
    }

    public void setCatCountWeightVolume(Integer catCountWeightVolume) {
        this.catCountWeightVolume = catCountWeightVolume;
    }

    public Integer getCatCountMoney() {
        return catCountMoney;
    }

    public void setCatCountMoney(Integer catCountMoney) {
        this.catCountMoney = catCountMoney;
    }

    public Integer getCatCountTime() {
        return catCountTime;
    }

    public void setCatCountTime(Integer catCountTime) {
        this.catCountTime = catCountTime;
    }

    public Integer getCatCountShapes() {
        return catCountShapes;
    }

    public void setCatCountShapes(Integer catCountShapes) {
        this.catCountShapes = catCountShapes;
    }

    public Integer getCatCountAngles() {
        return catCountAngles;
    }

    public void setCatCountAngles(Integer catCountAngles) {
        this.catCountAngles = catCountAngles;
    }

    public Integer getCatCountReview() {
        return catCountReview;
    }

    public void setCatCountReview(Integer catCountReview) {
        this.catCountReview = catCountReview;
    }

    public Integer getTotalLessonsCompleted() {
        return totalLessonsCompleted;
    }

    public void setTotalLessonsCompleted(Integer totalLessonsCompleted) {
        this.totalLessonsCompleted = totalLessonsCompleted;
    }
}