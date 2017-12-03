package com.gongyunhaoyyy.timesaver.appcontrol;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.usage.UsageStats;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.gongyunhaoyyy.timesaver.R;
import com.gongyunhaoyyy.timesaver.appcontrol.Adapter.ControledRecyclerViewAdapter;
import com.gongyunhaoyyy.timesaver.appcontrol.Adapter.RecycleViewDivider;
import com.gongyunhaoyyy.timesaver.appcontrol.bean.ControledAppModel;
import com.gongyunhaoyyy.timesaver.appcontrol.tools.ItemClickSupport;
import com.gongyunhaoyyy.timesaver.appcontrol.tools.ManagedAppSave;
import com.gongyunhaoyyy.timesaver.appcontrol.tools.UsageStatsManage;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import BaseClass.Base.BaseActivity;

public class AppManageActivity extends BaseActivity{
     public static String TAG="AppManageActivity";
     private ManagedAppSave managedAppSave;
     private ControledRecyclerViewAdapter adapter;
     private RecyclerView mRecyclerView;
     private List<ControledAppModel> mControledApplist;
     List<String> packageName;
     List<UsageStats> usageStats;
     Context mContext;
     List<ControledAppModel> updateList;
     Toolbar toolbar;
    @Override
    protected int getLayoutId() {
        return R.layout.activity_appmanage;
    }

    @Override
    protected void setListener() {

    }

    @SuppressLint("NewApi")
    protected void initView(){
        mContext=this;
        toolbar=findViewById(R.id.tool_bar);
        toolbar=initToolBar("软件管理");
        toolbar.setPopupTheme(R.style.OverflowMenuStyle);
        setSupportActionBar(toolbar);
        mRecyclerView=(RecyclerView)findViewById(R.id.managed_app_rv);
        mRecyclerView.addItemDecoration(new RecycleViewDivider(this,LinearLayoutManager.VERTICAL));
        updateList=new ArrayList<>();
        packageName=new ArrayList<>();
        LinearLayoutManager layoutManager=new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);
        adapter=new ControledRecyclerViewAdapter(this);
        mControledApplist=new ArrayList<>();
        setOnClick();
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()){
                    case R.id.toolbar_r_1:
                        initData();
                        break;
                    case R.id.toolbar_r_2:
                        Intent intent =new Intent(AppManageActivity.this,ThirdPartAppActivity.class);
                        startActivity(intent);
                        break;
                }
                return true;
            }
        });
    }

    @Override
    protected void setData() {

    }

    @Override
    protected void getBundleExtras(Bundle bundle) {

    }

    @SuppressLint("NewApi")
    protected void initRecyclerView(){
        //获取包管理
        PackageManager pm=getPackageManager();
        //获取已安装程序列表
        List<ApplicationInfo> applicationInfos=pm.getInstalledApplications(PackageManager.GET_UNINSTALLED_PACKAGES);
        Collections.sort(applicationInfos,new ApplicationInfo.DisplayNameComparator(pm));
        for (int i=0;i<mControledApplist.size();i++){

            for (ApplicationInfo app:applicationInfos){
                //是否为第三方应用
                if ((app.flags&ApplicationInfo.FLAG_SYSTEM)<=0||(app.flags & ApplicationInfo.FLAG_UPDATED_SYSTEM_APP)!= 0){
                    if (mControledApplist.get(i).getmAppName().equals(app.loadLabel(pm).toString())) {
                        mControledApplist.get(i).setmAppImg(app.loadIcon(pm));
                         packageName.add(app.packageName);
                         Log.i(TAG,"packageName"+packageName.get(i)+mControledApplist.get(i).getmAppName());

                    }

                }
            }
        }
        Log.i(TAG,"usageSize"+usageStats.size());
        for (int j=0;j<mControledApplist.size();j++){
            Log.i(TAG,"PACKAGENAME"+packageName.get(j).toString());
            for (UsageStats usageStat:usageStats){
                if (packageName.get(j).toString().equals(usageStat.getPackageName().toString())){
                    Log.i("TAG",packageName.get(j));
                    mControledApplist.get(j).setmUsedTime((int)(usageStat.getTotalTimeInForeground()/1000/60));
                    Log.i(TAG,usageStat.getPackageName()+mControledApplist.get(j).getmUsedTime()+"分");
                    Log.i(TAG,usageStat.getPackageName()+mControledApplist.get(j).getmSetTime()+"分");
                    Log.i(TAG,mControledApplist.get(j).getmAppName());

                }
            }

        }
        adapter.setList(mControledApplist);
        mRecyclerView.setAdapter(adapter);

    }
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    protected void initData(){
        usageStats = UsageStatsManage.getUsageStatsList(this);
        Log.i(TAG,usageStats.size()+"个程序正在运行");
        //未获得权限重新获取
        managedAppSave=new ManagedAppSave(getApplication(),"AppManagedSp");
        if (managedAppSave!=null){
            Log.i(TAG,"managedAppSave不为空");
            mControledApplist=managedAppSave.getDataList("appList");
            Log.i(TAG,"appListSize"+mControledApplist.size());
        }
        initRecyclerView();
    }
  protected void setOnClick(){
      ItemClickSupport.addTo(mRecyclerView).setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
          @Override
          public void onItemClicked(RecyclerView recyclerView, int position, View v) {
              Intent intent=new Intent(AppManageActivity.this,ManageAppDetail.class);
              Drawable drawable=mControledApplist.get(position).getmAppImg();
              final Bitmap bmp = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
              final Canvas canvas = new Canvas(bmp);
              drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
              drawable.draw(canvas);
              ByteArrayOutputStream baos=new ByteArrayOutputStream();
              bmp.compress(Bitmap.CompressFormat.PNG,100,baos);
              byte[] bytes=baos.toByteArray();
              intent.putExtra("bitmap",bytes);
              intent.putExtra("from",TAG);
              intent.putExtra("appName",mControledApplist.get(position).getmAppName());
              intent.putExtra("repeat",mControledApplist.get(position).getmRepeat());
              intent.putExtra("remind",mControledApplist.get(position).getmRemindWay());
              intent.putExtra("setTime",mControledApplist.get(position).getmSetTime());
              startActivity(intent);
          }
      });
      ItemClickSupport.addTo(mRecyclerView).setOnItemLongClickListener(new ItemClickSupport.OnItemLongClickListener() {
          @Override
          public boolean onItemLongClicked(final RecyclerView recyclerView, final int position, View v) {
            AlertDialog.Builder deleteDialog=new AlertDialog.Builder(mContext);
             deleteDialog.setTitle("确认删除？");
             deleteDialog.setPositiveButton("确认", new DialogInterface.OnClickListener() {
                 @Override
                 public void onClick(DialogInterface dialog, int which) {
                     dialog.dismiss();
                     Log.i("删除前",mControledApplist.size()+"");
                     mControledApplist.remove(position);
                     adapter.notifyDataSetChanged();
                     mControledApplist.size();
                     packageName.remove(position);
                     for (int i=0;i<mControledApplist.size();i++){
                         updateList.add(new ControledAppModel(mControledApplist.get(i).getmAppName(),mControledApplist.get(i).getmSetTime(),mControledApplist.get(i).getmRemindWay(),mControledApplist.get(i).getmRepeat()));
                     }
                     Log.i("TAG",updateList.size()+"");
                     managedAppSave.setDataList("appList",updateList);
                     updateList=new ArrayList<>();
                 }
             });
             deleteDialog.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                 @Override
                 public void onClick(DialogInterface dialog, int which) {
                     dialog.dismiss();
                 }
             });
             deleteDialog.show();
              return false;
          }
      });
  }

    @Override
    protected String[] getNeedPermissions() {
        return new String[]{};
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
      getMenuInflater().inflate(R.menu.toolbar,menu);
        return super.onCreateOptionsMenu(menu);
    }
}
