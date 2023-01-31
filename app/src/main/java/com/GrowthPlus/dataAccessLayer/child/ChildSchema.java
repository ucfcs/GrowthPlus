package com.GrowthPlus.dataAccessLayer.child;

//import android.media.Image;
import android.media.Image;

import com.GrowthPlus.dataAccessLayer.Report.ReportSchema;
import com.GrowthPlus.dataAccessLayer.RoadMap.RoadMapSchema;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class ChildSchema extends RealmObject {
    @PrimaryKey
    private String childId;
    private String name;
    private Integer avatarSrc;
    private ReportSchema report;
    private RoadMapSchema roadmap;
    private Integer colorId;

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

    public ReportSchema getReport() {
        return report;
    }

    public void setReport(ReportSchema report) {
        this.report = report;
    }

    public RoadMapSchema getRoadmap() {
        return roadmap;
    }

    public void setRoadmap(RoadMapSchema roadmap) {
        this.roadmap = roadmap;
    }

    public Integer getAvatarSrc() {
        return avatarSrc;
    }

    public void setAvatarSrc(Integer avatarSrc) {
        this.avatarSrc = avatarSrc;
    }

    public Integer getColorId() {
        return colorId;
    }

    public void setColorId(Integer colorId) {
        this.colorId = colorId;
    }
}
