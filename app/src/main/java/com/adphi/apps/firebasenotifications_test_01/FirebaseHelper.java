package com.adphi.apps.firebasenotifications_test_01;

import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by Philippe-Adrien on 17/10/2017.
 */

public class FirebaseHelper {

    private static FirebaseDatabase mDatabase;

    /**
     * PersistenceEnabled must be called only once, at the first Instance's call.
     * Method to handle unique Firebase Database instance and set Persistance on first call.
     * @return the Firebase Instance.
     */
    public static FirebaseDatabase getDatabase(){
        if(mDatabase == null) {
            mDatabase = FirebaseDatabase.getInstance();
            mDatabase.setPersistenceEnabled(true);
        }
        return mDatabase;
    }
}
