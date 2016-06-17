package com.example.leonardollobato.criminalintent;


import android.content.Context;
import java.util.List;
import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by leonardollobato on 5/30/16.
 */
public class CrimeLab {
    private static CrimeLab sCrimeLab;

    private Context mApplicationContext;

    private Realm mRealm;
    private RealmConfiguration mRealmConfig;



    public static CrimeLab get(Context context){
        if(sCrimeLab == null)
            sCrimeLab = new CrimeLab(context);
        return sCrimeLab;
    }

    private CrimeLab(Context context){

        mApplicationContext = context.getApplicationContext();
        mRealmConfig = new RealmConfiguration.Builder(mApplicationContext).build();
        mRealm = Realm.getInstance(mRealmConfig);
    }

    public List<Crime> getCrimes(){
        return mRealm.where(Crime.class).findAll();
    }

    public void addCrime(final Crime crime){

        mRealm.executeTransaction(new Realm.Transaction(){
            @Override
            public void execute(Realm realm) {

                Crime newCrime = realm.createObject(Crime.class);
                newCrime.setId(getNextKey());
                newCrime.setTitle(crime.getTitle());
                newCrime.setSolved(crime.isSolved());
                newCrime.setTime(crime.getTime());
                newCrime.setDate(crime.getDate());
            }
        });
    }

    public int getNextKey()
    {
        return mRealm.where(Crime.class).max("mId").intValue() + 1;
    }

    public void removeCrime(final Crime crime){

        mRealm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                final Crime result = realm
                            .where(Crime.class)
                            .equalTo("mId", crime.getId())
                            .findFirst();

                result.deleteFromRealm();
            }
        });
    }

    public void updateCrime(final Crime crime){

            mRealm.executeTransaction(new Realm.Transaction() {
                @Override
                public void execute(Realm realm) {
                    Crime updateCrime = realm
                                .where(Crime.class)
                                .equalTo("mId", crime.getId())
                                .findFirst();

                    updateCrime.setDate(crime.getDate());
                    updateCrime.setTime(crime.getTime());
                    updateCrime.setTitle(crime.getTitle());
                    updateCrime.setSolved(crime.isSolved());
                }
            });

    }

    public Crime getCrime(final long id){
        return mRealm.where(Crime.class).equalTo("mId", id).findFirst();
    }

}
