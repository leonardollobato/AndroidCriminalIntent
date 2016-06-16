package com.example.leonardollobato.criminalintent;

import java.util.Date;
import java.util.UUID;

import io.realm.RealmObject;

/**
 * Created by leonardollobato on 5/29/16.
 */
public class Crime extends RealmObject {
    private long mId;
    private String mTitle;
    private Date mDate;
    private Date mTime;
    private boolean mSolved;

    public Date getTime() {
        return mTime;
    }

    public void setTime(Date time) {
        mTime = time;
    }

    public long getId() {
        return mId;
    }

    public void setId(long id) { this.mId = id;}

    public Date getDate() {
        return mDate;
    }

    public void setDate(Date date) {
        mDate = date;
    }

    public boolean isSolved() {
        return mSolved;
    }

    public void setSolved(boolean solved) {
        mSolved = solved;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public Crime(){
        this.mDate = new Date();
        this.mTime = new Date();
    }
}
