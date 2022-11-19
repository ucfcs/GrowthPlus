package com.fall2022_group20.dataAccessLayer.parent;

import android.util.Log;

import com.fall2022_group20.dataAccessLayer.child.ChildSchema;

import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmResults;

public class ParentSchemaService {

    private String ID;
    private Integer PIN;
    private RealmList<ChildSchema> children;
    private Realm realm;

    /*
    Constructor
     */
    public ParentSchemaService(Realm realm, String ID, Integer PIN, RealmList<ChildSchema> children){
        this.realm = realm;
        this.PIN = PIN;
        this.children = children;
    }

    /*
    This method creates a new parent schema.
     */
    public void createParentSchema(){
        realm.executeTransactionAsync(realm1 -> {
            ParentSchema newParent = realm.createObject(ParentSchema.class, ID);
            newParent.setID(Long.parseLong(ID));
            newParent.setPIN(PIN);
            newParent.setChildrenList(children);
        }, () -> { //Lambda expression
            /* success actions */
            Log.i("Success", "New parent object added to realm!");
            //realm.close(); // Not sure if this the correct place to close the realm instance
        }, error -> {
            /* failure actions */
            Log.e("Error", "Something went wrong! " + error);
        });
    }

    /*
    This method updates a parent's list of children.
     */
    public void updateChildrenList(RealmList<ChildSchema> newChildrenList){
        realm.executeTransactionAsync(realm ->{
            ParentSchema parent = getParentSchema();
            parent.setChildrenList(newChildrenList);
        });
    }

    /*
    This method returns a parent schema by ID.
    */
    public ParentSchema getParentSchema(){
        return realm.where(ParentSchema.class).equalTo("ID", ID).findFirst();
    }

    /*
    This method deletes a parent's list of children.
     */
    public void deleteParentChildren(){
        realm.executeTransactionAsync(realm -> {
            ParentSchema parent = getParentSchema();
            RealmList<ChildSchema> children = parent.getChildren();
            children.deleteAllFromRealm();
            children = null;
        });
    }

    /*
    This method deletes a parent schema.
     */
    public void deleteParentSchema(){
        realm.executeTransactionAsync(realm -> {
            ParentSchema parentSchema = getParentSchema();
            parentSchema.deleteFromRealm();
            parentSchema = null;
        });
    }
}
