package com.fall2022_group20.dataAccessLayer.Report;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class ReportSchema extends RealmObject {

    @PrimaryKey
    private String reportId;
    private String childName;
    private Integer childScore;
    private String childId;

    public String getReportId() {
        return reportId;
    }

    public void setReportId(String reportId) {
        this.reportId = reportId;
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

    public String getChildId() {
        return childId;
    }

    public void setChildId(String childId) {
        this.childId = childId;
    }
}
