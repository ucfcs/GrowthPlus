package com.fall2022_group20.dataAccessLayer.Report;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class ReportSchema extends RealmObject {

    @PrimaryKey
    private String id;
    private String childName;
    private Integer childScore;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getChildName() {
        return childName;
    }

    public void setChildName(String childName) {
        this.childName = childName;
    }

    public Integer getChildScore() {
        return childScore;
    }

    public void setChildScore(Integer childScore) {
        this.childScore = childScore;
    }
}
