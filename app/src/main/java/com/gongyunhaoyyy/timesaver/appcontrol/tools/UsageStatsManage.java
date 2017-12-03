package com.gongyunhaoyyy.timesaver.appcontrol.tools;

import android.annotation.SuppressLint;
import android.app.AppOpsManager;
import android.app.usage.UsageStats;
import android.app.usage.UsageStatsManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Build;
import android.provider.Settings;
import android.support.annotation.RequiresApi;
import android.util.Log;

import java.util.Calendar;
import java.util.List;

/**
 * Created by DELL on 2017/11/29.
 */

public class UsageStatsManage {

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public static List<UsageStats> getUsageStatsList(Context mContext){
        AppOpsManager appOps = (AppOpsManager) mContext
                .getSystemService(Context.APP_OPS_SERVICE);
        int mode = appOps.checkOpNoThrow("android:get_usage_stats",
                android.os.Process.myUid(), mContext.getPackageName());
        boolean granted = mode == AppOpsManager.MODE_ALLOWED;
        if (!granted){
            Log.i("UsageStats","前往seting");
            Intent intent=new Intent(Settings.ACTION_USAGE_ACCESS_SETTINGS);
            mContext.startActivity(intent);
        }

        UsageStatsManager usm = getUsageStatsManager(mContext);
        Calendar calendar = Calendar.getInstance();
        long endTime = calendar.getTimeInMillis();
        calendar.add(Calendar.HOUR_OF_DAY, -1);
        long startTime = calendar.getTimeInMillis();
        List<UsageStats> usageStatsList = usm.queryUsageStats(UsageStatsManager.INTERVAL_DAILY,startTime,endTime);
        Log.i("getUsageStatuslist",""+usageStatsList.size());
        return usageStatsList;
    }
    private static UsageStatsManager getUsageStatsManager(Context mContext) {
      @SuppressLint("WrongConstant") UsageStatsManager usm = (UsageStatsManager) mContext.getSystemService("usagestats");
        return usm;
    }
    @SuppressLint("NewApi")
    public static boolean hasModule(Context mContext) {
        PackageManager packageManager = mContext.getApplicationContext().getPackageManager();
        Intent intent = new Intent(Settings.ACTION_USAGE_ACCESS_SETTINGS);
        List<ResolveInfo> list = packageManager.queryIntentActivities(intent,
                PackageManager.MATCH_DEFAULT_ONLY);
        return list.size() > 0;
    }
}
