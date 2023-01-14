package com.fall2022_group20.dataAccessLayer.parent;

import com.fall2022_group20.dataAccessLayer.child.ChildSchema;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class ParentSchema extends RealmObject {
    @PrimaryKey
    private String parentId;
    private Integer PIN;
    private RealmList<ChildSchema> children;

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public Integer getPIN() {
        return PIN;
    }

    public void setPIN(Integer PIN) {
        this.PIN = PIN;
    }

    public RealmList<ChildSchema> getChildren() {
        return children;
    }

    public void setChildren(RealmList<ChildSchema> children) {
        this.children = children;
    }
}

