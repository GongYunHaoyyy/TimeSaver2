package com.gongyunhaoyyy.timesaver.appcontrol.tools;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.gongyunhaoyyy.timesaver.appcontrol.bean.ControledAppModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by DELL on 2017/11/29.
 */

public class ManagedAppSave {

        private SharedPreferences preferences;
        private SharedPreferences.Editor editor;

        public ManagedAppSave(Context mContext, String preferenceName) {
            preferences = mContext.getSharedPreferences(preferenceName, Context.MODE_PRIVATE);
            editor = preferences.edit();
        }

        /**
         * 保存List
         * @param tag
         * @param datalist
         */
        public <T> void setDataList(String tag, List<T> datalist) {
           if (datalist==null||datalist.size()<=0) {
               editor.clear().commit();
               return;
           }
            //转换成json数据，再保存
            Log.i("savedata","保存app数据");
            String strJson = JSON.toJSONString(datalist);
            editor.clear().commit();
            editor.putString(tag, strJson);
            editor.commit();

        }

        /**
         * 获取List
         * @param tag
         * @return
         */
        public  List<ControledAppModel> getDataList(String tag) {
            List<ControledAppModel> datalist=new ArrayList<ControledAppModel>();
            String strJson = preferences.getString(tag, null);
            if (null == strJson) {
                return datalist;
            }
            Log.i("getdata","获得数据");
            datalist = (List<ControledAppModel>) JSON.parseArray(strJson, ControledAppModel.class);
            return datalist;

        }

}
