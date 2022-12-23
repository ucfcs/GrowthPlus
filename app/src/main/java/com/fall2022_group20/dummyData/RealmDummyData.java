package com.fall2022_group20.dummyData;

import io.realm.Realm;

public class RealmDummyData {
    private Realm realm;

    public RealmDummyData(){

        this.realm = Realm.getDefaultInstance();
    }

    public void generateDummyData(){

        ReportDummyData childReportOne = new ReportDummyData(String.valueOf(0), realm, "Pablo Martinez", 85);
        childReportOne.createChildReportDummyData();

        ReportDummyData childReportTwo = new ReportDummyData(String.valueOf(1), realm, "Leo Messi", 95);
        childReportTwo.createChildReportDummyData();

        ReportDummyData childReportThree = new ReportDummyData(String.valueOf(2), realm, "Serena Williams", 90);
        childReportThree.createChildReportDummyData();

        LessonDummyData multiplicationLesson = new LessonDummyData("multiplication", realm, 220);
        multiplicationLesson.createLessonDummyData();

        LessonDummyData additionLesson = new LessonDummyData("addition", realm, 100);
        additionLesson.createLessonDummyData();

        LessonDummyData subtractionLesson = new LessonDummyData("subtraction", realm, 100);
        subtractionLesson.createLessonDummyData();

        LessonDummyData divisionLesson = new LessonDummyData("division", realm, 100);
        divisionLesson.createLessonDummyData();

        GameDummyData gameOne = new GameDummyData(realm, 1000, "gameOne");
        gameOne.createGameDummyData();

        GameDummyData gameTwo = new GameDummyData(realm, 2000, "gameTwo");
        gameTwo.createGameDummyData();

        closeRealmInstance();

    }

    protected void closeRealmInstance() {

        realm.close();
    }
}
