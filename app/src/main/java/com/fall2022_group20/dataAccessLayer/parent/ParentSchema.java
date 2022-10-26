package com.fall2022_group20.dataAccessLayer.parent;

import com.fall2022_group20.dataAccessLayer.child.ChildSchema;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class ParentSchema extends RealmObject {
    @PrimaryKey
    private String ID;
    private Integer PIN;
    private RealmList<ChildSchema> children;

    public String getID() {
        return ID;
    }

    public void setID(long ID) {
        this.ID = String.valueOf(ID);
    }

    public int getPIN() {
        return PIN;
    }

    public void setPIN(int PIN) {
        this.PIN = PIN;
    }

    public RealmList<ChildSchema> getChildren() {
        return children;
    }

    public void setChildrenList(RealmList<ChildSchema> children) {
        this.children = children;
    }
}

