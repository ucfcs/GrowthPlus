package com.fall2022_group20.dataAccessLayer.child;

import android.util.Log;

import com.fall2022_group20.dataAccessLayer.Report.ReportSchema;
import com.fall2022_group20.dataAccessLayer.RoadMap.RoadMapSchema;

import io.realm.Realm;
import io.realm.RealmResults;

public class ChildSchemaService {


    private String ID;
    private String name;
    //need to add a variable for avatar
    private ReportSchema report;
    private RoadMapSchema roadmap;
    private Realm realm;

    /*
    Constructor
     */
    public ChildSchemaService(Realm realm, String ID, String name, ReportSchema report, RoadMapSchema roadmap){
        this.realm = realm;
        this.ID = ID;
        this.name = name;
        this.report = report;
        this.roadmap = roadmap;
    }

    /*
    This method creates a new child schema.
     */
    public void createChildSchema(){
        realm.executeTransactionAsync(realm -> {
            ChildSchema newChild = realm.createObject(ChildSchema.class, String.valueOf(ID));
            newChild.setID(Long.parseLong(ID));
            newChild.setName(name);
            newChild.setReport(report);
            newChild.setRoadmap(roadmap);
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
            ChildSchema childSchema = getChildSchema();
            childSchema.setRoadmap(roadmap);
        });
    }

    /*
    This method updates a child's report.
    */
    public void updateReport(ReportSchema report){
        realm.executeTransactionAsync(realm -> {
            ChildSchema childSchema = getChildSchema();
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
    public ChildSchema getChildSchema(){
        return realm.where(ChildSchema.class).equalTo("ID", ID).findFirst();
    }

    /*
    This method deletes a child's roadmap.
     */
    public void deleteChildRoadmaps(){
        realm.executeTransactionAsync(realm -> {
            ChildSchema childSchema = getChildSchema();
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
            ChildSchema childSchema = getChildSchema();
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
            ChildSchema childSchema = getChildSchema();
            childSchema.deleteFromRealm();
            childSchema = null;
        });
    }
}
