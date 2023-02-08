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
    ChildRoadMap roadMapOne;
    ChildRoadMap roadMapTwo;
    ChildRoadMap roadMapThree;
    ChildRoadMap roadMapFour;

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
}
