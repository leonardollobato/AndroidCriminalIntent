package com.example.leonardollobato.criminalintent;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.leonardollobato.criminalintent.database.CrimeBaseHelper;
import com.example.leonardollobato.criminalintent.database.CrimeDbSchema;
import com.example.leonardollobato.criminalintent.database.CrimeDbSchema.CrimeTable;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by leonardollobato on 5/30/16.
 */
public class CrimeLab {
    private static CrimeLab sCrimeLab;

    private Context mApplicationContext;
    private SQLiteDatabase mDatabase;



    public static CrimeLab get(Context context){
        if(sCrimeLab == null)
            sCrimeLab = new CrimeLab(context);
        return sCrimeLab;
    }

    private CrimeLab(Context context){
        mApplicationContext = context.getApplicationContext();
        mDatabase = new CrimeBaseHelper(mApplicationContext)
            .getWritableDatabase();
    }

    public List<Crime> getCrimes(){
        return new ArrayList<>();
    }

    public void addCrime(Crime c){

    }

    public void removeCrime(Crime crime){
//       int index =  mCrimes.indexOf(crime);
//        mCrimes.remove(index);
    }


    public Crime getCrime(UUID id){
        return null;
    }

    private static ContentValues getContentValues(Crime crime){
        ContentValues values = new ContentValues();

        values.put(CrimeTable.Cols.UUID, crime.getId().toString());
        values.put(CrimeTable.Cols.TITLE, crime.getTitle());
        values.put(CrimeTable.Cols.DATE, crime.getDate().getTime());
        values.put(CrimeTable.Cols.TIME, crime.getTitle());
        values.put(CrimeTable.Cols.SOLVED, crime.isSolved() ? 1 : 0);

        return values;
    }
}
