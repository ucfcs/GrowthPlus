package com.fall2022_group20.dataAccessLayer.child;

//import android.media.Image;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class ChildSchema extends RealmObject {
    @PrimaryKey
    private String ID;
    private String name;
    //private Image avatar;

    /* Can't create the below two fields without the schemas
    private ProgressReportSchema report;
    private RoadMapSchema roadmap;
    */

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

    /*Once the corresponding fields are uncommented, these
      getters and setters can be uncommented too.

    public ProgressReport getReport() {
        return report;
    }

    public void setReport(ProgressReport report) {
        this.report = report;
    }

    public RoadMap getRoadmap() {
        return roadmap;
    }

    public void setRoadmap(RoadMap roadmap) {
        this.roadmap = roadmap;
    }
    */
}
