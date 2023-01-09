package com.fall2022_group20.dummyData;

import com.fall2022_group20.dataAccessLayer.Report.ReportSchema;
import com.fall2022_group20.dataAccessLayer.RoadMap.RoadMapSchema;
import com.fall2022_group20.dataAccessLayer.child.ChildSchema;
import com.fall2022_group20.dataAccessLayer.child.ChildSchemaService;

import org.bson.types.ObjectId;

import io.realm.Realm;

public class ChildDummyData {

    private String childId;
    private String name;
    private String avatar;
    private ReportSchema report;
    private RoadMapSchema roadmap;
    private Realm realm;

    public ChildDummyData(String name, String avatar, ReportSchema report, RoadMapSchema roadmap, Realm realm){
        this.name = name;
        this.avatar = avatar;
        this.report = report;
        this.roadmap = roadmap;
        this.realm = realm;
    }

    public void generateChildDummyData(){
        ChildSchemaService newChild = new ChildSchemaService(realm, name, report, roadmap, avatar);
        if(newChild.getChildSchemaByName()== null){
            this.childId = String.valueOf(new ObjectId());
            newChild.createChildSchema(childId);
        }
    }
}


