package com.fall2022_group20.dataAccessLayer.child;

//import android.media.Image;
import com.fall2022_group20.dataAccessLayer.Report.ReportSchema;
import com.fall2022_group20.dataAccessLayer.RoadMap.RoadMapSchema;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class ChildSchema extends RealmObject {
    @PrimaryKey
    private String ID;
    private String name;
    //need to add a variable for avatar
    private ReportSchema report;
    private RoadMapSchema roadmap;

    public String getID() {
        return ID;
    }

    public void setID(long ID) {
        this.ID = String.valueOf(ID);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /*public Image getAvatar() {
        return avatar;
    }

    public void setAvatar(Image avatar) {
        this.avatar = avatar;
    }*/

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

}
