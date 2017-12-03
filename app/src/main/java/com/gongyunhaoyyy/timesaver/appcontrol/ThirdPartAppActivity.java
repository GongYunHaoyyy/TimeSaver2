package com.gongyunhaoyyy.timesaver.appcontrol;

import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.gongyunhaoyyy.timesaver.R;
import com.gongyunhaoyyy.timesaver.appcontrol.Adapter.RecycleViewDivider;
import com.gongyunhaoyyy.timesaver.appcontrol.Adapter.ThirdPartAppAdapter;
import com.gongyunhaoyyy.timesaver.appcontrol.Interface.RecyclerViewOnItemListener;
import com.gongyunhaoyyy.timesaver.appcontrol.bean.ThirdPartAppModel;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import BaseClass.Base.BaseActivity;

public class ThirdPartAppActivity extends BaseActivity implements RecyclerViewOnItemListener {
    public static final int THIRD_APP_FILTER=2;
    private RecyclerView third_partApp_rv;
    private List<ThirdPartAppModel> appList=new ArrayList<>();
    private int filter=THIRD_APP_FILTER;
    public static String TAG="ThirdPartAppActivity";
    private RecyclerViewOnItemListener listener=this;
    private Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getIntent()!=null){
            filter=getIntent().getIntExtra("filter",2);
        }
         toolbar=initToolBar("应用列表");
        third_partApp_rv=(RecyclerView)findViewById(R.id.third_partApp_rv);
        LinearLayoutManager layoutManager=new LinearLayoutManager(this);
        third_partApp_rv.setLayoutManager(layoutManager);
        queryThirdPartApp(filter);
        ThirdPartAppAdapter adapter=new ThirdPartAppAdapter(this, appList, listener );
        third_partApp_rv.setAdapter(adapter);
        third_partApp_rv.addItemDecoration(new RecycleViewDivider(this,LinearLayoutManager.VERTICAL));
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_third_part_app;
    }

    @Override
    protected void setListener() {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {

    }

    @Override
    protected void setData() {

    }

    @Override
    protected void getBundleExtras(Bundle bundle) {

    }


    /**
     * 获得所有启动的应用信息
     */
    public void queryThirdPartApp(int filter){
        //获取包管理
        PackageManager pm=getPackageManager();
        //获取已安装程序列表
        List<ApplicationInfo> applicationInfos=pm.getInstalledApplications(PackageManager.GET_UNINSTALLED_PACKAGES);
        Collections.sort(applicationInfos,new ApplicationInfo.DisplayNameComparator(pm));
        Log.i(TAG,applicationInfos.size()+" appInfo");
        if (filter==THIRD_APP_FILTER){
            appList.clear();
            Log.i(TAG,"filter=THIRD_APP");
            for (ApplicationInfo app:applicationInfos){
                //非系统应用
                if ((app.flags&ApplicationInfo.FLAG_SYSTEM)<=0){
                    ThirdPartAppModel appModel=new ThirdPartAppModel(app.loadLabel(pm).toString(),app.loadIcon(pm));
                    appList.add(appModel);
                    Log.i(TAG,app.loadLabel(pm).toString());
                }else if ((app.flags & ApplicationInfo.FLAG_UPDATED_SYSTEM_APP) != 0){
                    ThirdPartAppModel appModel=new ThirdPartAppModel(app.loadLabel(pm).toString(),app.loadIcon(pm));
                    Log.i(TAG,app.loadLabel(pm).toString());
                    appList.add(appModel);
                }
            }

        }
    }

    @Override
    public void onItemClick(View view, int position) {
        Intent intent=new Intent(ThirdPartAppActivity.this,ManageAppDetail.class);
        Drawable drawable=appList.get(position).getAppIcon();
        final Bitmap bmp = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        final Canvas canvas = new Canvas(bmp);
        drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        drawable.draw(canvas);
        ByteArrayOutputStream baos=new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.PNG,100,baos);
        byte[] bytes=baos.toByteArray();
        intent.putExtra("bitmap",bytes);
        intent.putExtra("from",TAG);
        intent.putExtra("appName",appList.get(position).getAppName());
        startActivity(intent);
    }

    @Override
    protected String[] getNeedPermissions() {
        return new String[0];
    }
}
