package com.gongyunhaoyyy.timesaver.appcontrol.bean;

import android.graphics.drawable.Drawable;

import java.io.Serializable;

/**
 * Created by DELL on 2017/11/28.
 */

public class ControledAppModel implements Serializable{
    private String mAppName;
    private Drawable mAppImg;
    private int mUsedTime;
    private int mSetTime;
    private String mRemindWay;
    private String mRepeat;


    public ControledAppModel(String mAppName,int mSetTime,String mRemindWay,String mRepeat){
        this.mAppName=mAppName;
        this.mAppImg=mAppImg;
        this.mSetTime=mSetTime;
        this.mRemindWay=mRemindWay;
        this.mRepeat=mRepeat;
    }

    public String getmAppName() {
        return mAppName;
    }

    public void setmAppName(String mAppName) {
        this.mAppName = mAppName;
    }

    public Drawable getmAppImg() {
        return mAppImg;
    }

    public void setmAppImg(Drawable mAppImg) {
        this.mAppImg = mAppImg;
    }

    public int getmUsedTime() {
        return mUsedTime;
    }

    public void setmUsedTime(int mUsedTime) {
        this.mUsedTime = mUsedTime;
    }

    public int getmSetTime() {
        return mSetTime;
    }

    public void setmSetTime(int mSetTime) {
        this.mSetTime = mSetTime;
    }
    public String getmRemindWay() {
        return mRemindWay;
    }

    public String getmRepeat() {
        return mRepeat;
    }

    public void setmRemindWay(String mRemindWay) {
        this.mRemindWay = mRemindWay;
    }

    public void setmRepeat(String mRepeat) {
        this.mRepeat = mRepeat;
    }

    public ControledAppModel() {
    }
}
