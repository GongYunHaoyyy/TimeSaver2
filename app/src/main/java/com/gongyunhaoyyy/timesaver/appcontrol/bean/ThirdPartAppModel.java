package com.gongyunhaoyyy.timesaver.appcontrol.bean;

import android.graphics.drawable.Drawable;

import java.io.Serializable;

/**
 * Created by DELL on 2017/11/28.
 */

public class ThirdPartAppModel implements Serializable {
    private Drawable appIcon;
    private String appName;

    public ThirdPartAppModel(String appName,Drawable appIcon) {
        this.appIcon = appIcon;
        this.appName = appName;
    }

    public Drawable getAppIcon() {
        return appIcon;
    }

    public void setAppIcon(Drawable appIcon) {
        this.appIcon = appIcon;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }


}
