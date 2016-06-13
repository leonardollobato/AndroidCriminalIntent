package com.example.leonardollobato.criminalintent.database;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.leonardollobato.criminalintent.database.CrimeDbSchema.CrimeTable;

/**
 * Created by leonardollobato on 6/12/16.
 */
public class CrimeBaseHelper extends SQLiteOpenHelper{

    private static final int VERSION = 1;
    private static final String DATABASE_NAME = "crimeBase.db";

    public CrimeBaseHelper(Context context){

        super(context, DATABASE_NAME, null, VERSION, new DatabaseErrorHandler() {
            @Override
            public void onCorruption(SQLiteDatabase dbObj) {
                Log.d("ON DATABASE", dbObj.getPath());
            }
        });
    }



    @Override
    public void onCreate(SQLiteDatabase db) {
        final StringBuilder sqlStatment = new StringBuilder("create table ");
        sqlStatment
                .append(CrimeTable.NAME)
                .append(" ( _id integer primary key autoincrement, ")
                .append(CrimeTable.Cols.UUID + ", ")
                .append(CrimeTable.Cols.TITLE + ", ")
                .append(CrimeTable.Cols.DATE + ", ")
                .append(CrimeTable.Cols.TIME + ", ")
                .append(CrimeTable.Cols.SOLVED + " ) ");

        db.execSQL(sqlStatment.toString());
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

}
