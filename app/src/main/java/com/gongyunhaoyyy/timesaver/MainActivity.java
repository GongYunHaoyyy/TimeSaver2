package com.gongyunhaoyyy.timesaver;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import com.gongyunhaoyyy.timesaver.appcontrol.AppManageActivity;

import org.litepal.crud.DataSupport;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private ImageButton openDrawer;
    private DrawerLayout drawerLayout;
    private LinearLayout timeLine,calender,toDo,deadLine,timeReport,sheQu,setting,appManage;
    private ImageButton add,search;
    private List<TaskClass> mList=new ArrayList<>(  );
    private Intent intentcal,intenttodo,intentdl,intenttr,intentsq,intentset,intentam;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );
        init();
        initList();
        initIntents();
        SetClickListener();
        drawTimeLine();
    }

    @Override
    protected void onPostResume() {
        super.onPostResume( );
        drawTimeLine();
    }

    private void drawTimeLine(){
        FrameLayout ll=(FrameLayout) findViewById(R.id.bg_tl_frame);
        final DrawView view=new DrawView(this,drawGetTime(),getTime(),mList,mList.size());
        view.setMinimumHeight(100);
        view.setMinimumWidth(400);
        //通知view组件重绘
        view.invalidate();
        ll.addView(view);
    }

    private void paddingWindow() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            WindowManager.LayoutParams localLayoutParams = getWindow().getAttributes();
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS | localLayoutParams.flags);
        }
    }

    private void init(){
        openDrawer= findViewById( R.id.ib_opendrawer );
        drawerLayout= findViewById( R.id.drawerLayout );
        timeLine= findViewById( R.id.tl_LL );
        calender= findViewById( R.id.cld_LL );
        toDo= findViewById( R.id.td_LL );
        deadLine= findViewById( R.id.dl_LL );
        timeReport= findViewById( R.id.tr_LL );
        sheQu= findViewById( R.id.ts_LL );
        setting= findViewById( R.id.st_LL );
        appManage= findViewById(R.id.AM);
        add= findViewById( R.id.ib_add );
        search= findViewById( R.id.ib_search );
    }

    private void initList(){
        List<TaskDataBase> notedb= DataSupport.findAll( TaskDataBase.class );
        for (TaskDataBase notedatabase:notedb){
            mList.add( new TaskClass(notedatabase.getContent(),notedatabase.getStarttime(),notedatabase.getEndtime(),notedatabase.getStartY(),notedatabase.getEndY()) );
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ib_opendrawer:
                drawerLayout.openDrawer( Gravity.START );
                break;
            case R.id.tl_LL:                //TimeLine
                break;
            case R.id.cld_LL:               //Calender
                startActivity( intentcal );
                break;
            case R.id.AM:                   //moniter
                startActivity( intentam );
                break;
            case R.id.td_LL:                //To Do
                startActivity( intenttodo );
                break;
            case R.id.dl_LL:                //DeadLine
                startActivity( intentdl );
                break;
            case R.id.tr_LL:                //TimeReport
                startActivity( intenttr );
                break;
            case R.id.ts_LL:                //TimeSaver社区
                startActivity( intentsq );
                break;
            case R.id.st_LL:                //Settings
                startActivity( intentset );
                break;
            case R.id.ib_add:               //To Do
                startActivity( intenttodo );
                break;
        }
    }
    private void SetClickListener(){
        openDrawer.setOnClickListener( this );
        timeLine.setOnClickListener( this );
        calender.setOnClickListener( this );
        toDo.setOnClickListener( this );
        deadLine.setOnClickListener( this );
        timeReport.setOnClickListener( this );
        sheQu.setOnClickListener( this );
        setting.setOnClickListener( this );
        appManage.setOnClickListener(this);
        add.setOnClickListener( this );
    }

    private void initIntents(){
        intentcal=new Intent( MainActivity.this,CalenderActivity.class );
        intenttodo=new Intent( MainActivity.this,toDoActivity.class );
        intentdl=new Intent( MainActivity.this,deadlineActivity.class );
        intenttr=new Intent( MainActivity.this,timeReportActivity.class );
        intentsq=new Intent( MainActivity.this,timeSaverCommunityActivity.class );
        intentset=new Intent( MainActivity.this,SettingActivity.class );
        intentam=new Intent( MainActivity.this,AppManageActivity.class );
    }

    protected String getTime(){
        @SuppressLint("SimpleDateFormat") SimpleDateFormat sDateFormat = new SimpleDateFormat("kk:mm");
        String time = sDateFormat.format(new java.util.Date());
        return time;
    }
    protected double drawGetTime(){
        @SuppressLint("SimpleDateFormat") SimpleDateFormat sDateFormat = new SimpleDateFormat("kk,mm");
        String time = sDateFormat.format(new java.util.Date());
        String[] time2=time.split( "," );
//        用来实现dp,px的转换
//        如果这里是COMPLEX_UNIX_SP，就是讲sp转化为dp。
//        单位间的转换就用这个方法，可以将其封装成一个工具方法。
        double position = (double) TypedValue.applyDimension( TypedValue.COMPLEX_UNIT_DIP,
                (float) (Float.parseFloat( time2[0] )*45.5+Float.parseFloat( time2[1] )/60*45),
                this.getResources().getDisplayMetrics());
        return position;
    }
}
