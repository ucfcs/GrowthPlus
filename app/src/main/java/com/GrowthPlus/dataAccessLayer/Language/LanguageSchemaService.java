package com.GrowthPlus.dataAccessLayer.Language;

import com.GrowthPlus.dataAccessLayer.child.ChildSchema;

import io.realm.Realm;

public class LanguageSchemaService {

    String languageId;
    private Realm realm;

    /*
      Constructor
    */
    public LanguageSchemaService(Realm realm, String langID){

        this.realm = realm;
        this.languageId = langID;
    }

    /*
    This method returns a child schema by ID.
     */
    public LanguageSchema getLanguageSchemaById(){
        return realm.where(LanguageSchema.class).equalTo("languageId", languageId).findFirst();
    }
}
