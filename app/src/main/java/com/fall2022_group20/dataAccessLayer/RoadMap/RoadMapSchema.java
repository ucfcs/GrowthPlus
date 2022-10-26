package com.fall2022_group20.dataAccessLayer.RoadMap;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class RoadMapSchema extends RealmObject {

    @PrimaryKey
    private Integer id;
    private String roadMapName;
    /* Can't create these fields as we don't have the schemas created yet
    * private RealmList<LessonSchema> lessons;
    * private TestSchema test;
    * */


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRoadMapName() {
        return roadMapName;
    }

    public void setRoadMapName(String roadMapName) {
        this.roadMapName = roadMapName;
    }
}
