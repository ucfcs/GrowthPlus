package com.GrowthPlus.dataAccessLayer.child;

import android.util.Log;

import com.GrowthPlus.dataAccessLayer.Report.ReportSchema;
import com.GrowthPlus.dataAccessLayer.Report.ReportSchemaService;
import com.GrowthPlus.dataAccessLayer.RoadMap.RoadMapSchema;

import javax.annotation.Nullable;

import io.realm.Realm;
import io.realm.RealmResults;

public class ChildSchemaService {

    private String childId;
    private String name;
    private String avatarName;
    private String colorName;
    private ReportSchema report;
    private RoadMapSchema roadmap;
    private Realm realm;

    /*
      Constructor
    */
    public ChildSchemaService(Realm realm){

        this.realm = realm;
    }

    public ChildSchemaService(Realm realm, String name, @Nullable  ReportSchema report, @Nullable  RoadMapSchema roadmap, String avatarName, String colorName){
        this.realm = realm;
        this.name = name;
        this.report = report;
        this.roadmap = roadmap;
        this.avatarName = avatarName;
        this.colorName = colorName;
    }

    /*
    This method creates a new child schema.
     */
    public void createChildSchema(String childId){
        this.childId = childId;
        realm.executeTransactionAsync(realm -> {
            ChildSchema newChild = realm.createObject(ChildSchema.class, String.valueOf(childId));
            newChild.setName(name);
            newChild.setReport(report);
            newChild.setRoadmap(roadmap);
            newChild.setAvatarName(avatarName);
            newChild.setColorName(colorName);
        }, () -> { //Lambda expression
            /* success actions */
            Log.i("Success", "New child report object added to realm!");
            //realm.close(); // Not sure if this the correct place to close the realm instance
        }, error -> {
            /* failure actions */
            Log.e("Error", "Something went wrong! " + error);
        });
    }

    /*
    This method updates a child's roadmap.
    */
    public void updateRoadmap(RoadMapSchema roadmap){
        realm.executeTransactionAsync(realm -> {
            ChildSchema childSchema = getChildSchemaById();
            childSchema.setRoadmap(roadmap);
        });
    }

    /*
    This method updates a child's report.
    */
    public void updateReport(ReportSchema report){
        realm.executeTransactionAsync(realm -> {
            ChildSchema childSchema = getChildSchemaById();
            childSchema.setReport(report);
        });
    }

    /*
    This method returns all child schemas.
    */
    public RealmResults<ChildSchema> getAllChildSchemas(){
        return realm.where(ChildSchema.class).findAll();
    }

    /*
    This method returns a child schema by ID.
     */
    public ChildSchema getChildSchemaById(){
        return realm.where(ChildSchema.class).equalTo("childId", childId).findFirst();
    }

    /*
    This method returns a child schema by name.
     */
    public ChildSchema getChildSchemaByName(){
        return realm.where(ChildSchema.class).equalTo("name", name).findFirst();
    }

    /*
    This method deletes a child's roadmap.
     */
    public void deleteChildRoadmaps(){
        realm.executeTransactionAsync(realm -> {
            ChildSchema childSchema = getChildSchemaById();
            RoadMapSchema roadmap = childSchema.getRoadmap();
            roadmap.deleteFromRealm();
            roadmap = null;
        });
    }
    /*
    This method deletes a child's report.
     */
    public void deleteChildReport(){
        realm.executeTransactionAsync(realm -> {
            ChildSchema childSchema = getChildSchemaById();
            ReportSchema report = childSchema.getReport();
            report.deleteFromRealm();
            report = null;
        });
    }

    /*
    This method deletes a child schema.
     */
    public void deleteChildSchema(){
        realm.executeTransactionAsync(realm -> {
            ChildSchema childSchema = getChildSchemaById();
            childSchema.deleteFromRealm();
            childSchema = null;
        });
    }

    public ReportSchema getReportByChildName(String childName) {
        ReportSchemaService report = new ReportSchemaService(realm);
        report.getChildReportByName(childName);

        return report.getChildReportByName(childName);
    }
}
