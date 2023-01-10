package com.fall2022_group20.realmImporter;

import android.content.res.Resources;
import android.util.Log;

import com.fall2022_group20.R;
import com.fall2022_group20.dataAccessLayer.Language.LanguageSchema;

import java.io.InputStream;

import io.realm.Realm;

public class RealmImporter {
    private Realm realm;
    public void importFromJson(Resources resources) {
        realm = Realm.getDefaultInstance();

        //transaction timer
        final TransactionTime transactionTime = new TransactionTime(System.currentTimeMillis());

        realm.executeTransactionAsync(realm1 -> {
            InputStream inputStream = resources.openRawResource(R.raw.languages);
            try {
                realm1.createOrUpdateAllFromJson(LanguageSchema.class, inputStream);
                transactionTime.setEnd(System.currentTimeMillis());
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                realm1.close();
            }
        });
        Log.d("Languages", "createAllFromJson Languages, completed in " + transactionTime.getDuration() + "ms");
    }
}

