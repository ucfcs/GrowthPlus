package com.fall2022_group20.dummyData;

import com.fall2022_group20.dataAccessLayer.Report.ReportSchemaService;

import org.bson.types.ObjectId;

import io.realm.Realm;

public class ReportDummyData {

    private String reportId;
    private String childId;
    private String childName;
    private Integer totalScore;
    private Realm realm;

    // Need to provide the childId and realm instance
    public ReportDummyData(String childId, Realm realm, String childName, Integer totalScore){
        this.childId = childId;
        this.childName = childName;
        this.totalScore = totalScore;
        this.realm = realm;
    }

    // Create brand new report
    // Check if a report for the same child already exist
    public void createChildReportDummyData(){
        ReportSchemaService childReport = new ReportSchemaService(realm, childId, childName, totalScore);
        if(childReport.getChildReportByName()== null){
            this.reportId = String.valueOf(new ObjectId());
            childReport.createChildRealmReport(reportId);
        }
    }

}
