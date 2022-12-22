package com.fall2022_group20.dummyData;

import android.util.Log;

import com.fall2022_group20.dataAccessLayer.Report.ReportSchemaService;

import org.bson.types.ObjectId;

import io.realm.Realm;

public class ReportDummyData {

    private String reportId;
    private String childId;
    private String childName;
    private Integer totalScore;
    private Realm realm;

    // Need to provide the childId
    public ReportDummyData(String childId){
        this.reportId = String.valueOf(new ObjectId());
        this.childId = childId;
        this.childName = "Pablo Martinez";
        this.totalScore = 85;
        this.realm = Realm.getDefaultInstance();
    }

    public void createChildReportDummyData(){
        ReportSchemaService childReportZero = new ReportSchemaService(realm, childId, childName, totalScore, reportId);
        childReportZero.createChildRealmReport();
    }

    public void closeRealm(){
        realm.close();
    }

}
