package com.example.leonardollobato.criminalintent;

import java.util.Date;
import java.util.UUID;

/**
 * Created by leonardollobato on 5/29/16.
 */
public class Crime {
    private UUID mId;
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

    public UUID getId() {
        return mId;
    }

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

    public Crime() {
        this.mId = UUID.randomUUID();
        this.mDate = new Date();
        this.mTime = new Date();
    }
}
