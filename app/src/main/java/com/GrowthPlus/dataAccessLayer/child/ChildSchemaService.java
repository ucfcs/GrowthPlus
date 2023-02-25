package com.GrowthPlus.dataAccessLayer.child;

import android.util.Log;

import com.GrowthPlus.dataAccessLayer.ChildRoadMap.ChildRoadMap;
import com.GrowthPlus.dataAccessLayer.parent.ParentSchema;

import io.realm.Realm;
import io.realm.RealmResults;

public class ChildSchemaService {

    private String childId;
    private String name;
    private String avatarName;
    private String colorName;
    private Integer score;
    private Realm realm;
    ChildRoadMap childRoadMapOne;
    ChildRoadMap childRoadMapTwo;
    ChildRoadMap childRoadMapThree;
    ChildRoadMap childRoadMapFour;

    /*
      Constructor
    */
    public ChildSchemaService(Realm realm){

        this.realm = realm;
    }

    public ChildSchemaService(Realm realm,
                              String name,
                              String avatarName,
                              String colorName,
                              Integer score,
                              ChildRoadMap childRoadMapOne,
                              ChildRoadMap childRoadMapTwo,
                              ChildRoadMap childRoadMapThree,
                              ChildRoadMap childRoadMapFour){
        this.realm = realm;
        this.name = name;
        this.avatarName = avatarName;
        this.colorName = colorName;
        this.score = score;
        this.childRoadMapOne = childRoadMapOne;
        this.childRoadMapTwo = childRoadMapTwo;
        this.childRoadMapThree = childRoadMapThree;
        this.childRoadMapFour = childRoadMapFour;
    }

    /*
    This method creates a new child schema.
     */
    public void createChildSchema(String childId){
        this.childId = childId;
        realm.executeTransactionAsync(realm -> {
            ChildSchema newChild = realm.createObject(ChildSchema.class, String.valueOf(childId));
            newChild.setName(name);
            newChild.setAvatarName(avatarName);
            newChild.setColorName(colorName);
            newChild.setScore(score);
            newChild.setRoadMapOne(childRoadMapOne);
            newChild.setRoadMapTwo(childRoadMapTwo);
            newChild.setRoadMapThree(childRoadMapThree);
            newChild.setRoadMapFour(childRoadMapFour);
            newChild.setCatCountNumbers(0);
            newChild.setCatCountUnits(0);
            newChild.setCatCountAddition(0);
            newChild.setCatCountSubtraction(0);
            newChild.setCatCountMultiplication(0);
            newChild.setCatCountDivision(0);
            newChild.setCatCountLength(0);
            newChild.setCatCountWeightVolume(0);
            newChild.setCatCountMoney(0);
            newChild.setCatCountTime(0);
            newChild.setCatCountShapes(0);
            newChild.setCatCountAngles(0);
        }, () -> { //Lambda expression
            /* success actions */
            Log.i("Success", "New child added to realm!");

        }, error -> {
            /* failure actions */
            Log.e("Error", "Something went wrong, could not add child to realm " + error);
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
        return realm.where(ChildSchema.class).equalTo("childId", childId).findFirst();
    }

    /*
   This method returns a child schema by ID.
    */
    public ChildSchema getChildSchemaById(String childId){
        return realm.where(ChildSchema.class).equalTo("childId", childId).findFirst();
    }

    /*
    This method returns a child schema by name.
     */
    public ChildSchema getChildSchemaByName(){
        return realm.where(ChildSchema.class).equalTo("name", name).findFirst();
    }


    /*
    This method deletes a child schema.
     */
    public void deleteChildSchemaById(String childId){
        realm.executeTransactionAsync(realm -> {
            ChildSchema childSchema = getChildSchemaById(childId);
            childSchema.deleteFromRealm();
            childSchema = null;
        });
    }

    public void deleteChildFromRealm(ChildSchema child){
        realm.executeTransactionAsync(realm -> {
            child.deleteFromRealm();
        });
    }

}
