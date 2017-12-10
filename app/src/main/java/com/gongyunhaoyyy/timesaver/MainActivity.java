package com.gongyunhaoyyy.timesaver;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Toast;

import com.gongyunhaoyyy.timesaver.appcontrol.AppManageActivity;

import org.litepal.crud.DataSupport;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import Adapter.TimeLineAdapter;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private ImageButton openDrawer;
    private DrawerLayout drawerLayout;
    private TimeLineAdapter timeLineAdapter;
    private LinearLayout timeLine,calender,toDo,deadLine,timeReport,sheQu,setting,appManage;
    private ImageButton add,search;
    private List<TaskClass> mList=new ArrayList<>(  );
    private RecyclerView recyclerView;
    GridLayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );
        paddingWindow();
        init();
        initList();
        openDrawer.setOnClickListener( this );
        timeLine.setOnClickListener( this );
        calender.setOnClickListener( this );
        DividerItemDecoration divider=new DividerItemDecoration( this,DividerItemDecoration.VERTICAL );
        divider.setDrawable( ContextCompat.getDrawable(this,R.drawable.custom_divider) );
        recyclerView.addItemDecoration( divider );
        appManage.setOnClickListener(this);
        add.setOnClickListener( this );


        layoutManager=new GridLayoutManager(MainActivity.this,1 );
        recyclerView.setLayoutManager( layoutManager );
        timeLineAdapter=new TimeLineAdapter( mList );
        recyclerView.setAdapter( timeLineAdapter );
        drawTimeLine();
    }

    private void drawTimeLine(){
        FrameLayout ll=(FrameLayout) findViewById(R.id.bg_tl_frame);
        final DrawView view=new DrawView(this,drawGetTime(),getTime());
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
        recyclerView= findViewById( R.id.timeLine_recycler );
        appManage= findViewById(R.id.AM);
        add= findViewById( R.id.ib_add );
        search= findViewById( R.id.ib_search );
    }

    private void initList(){
        List<TaskDataBase> notedb= DataSupport.findAll( TaskDataBase.class );
        for (TaskDataBase notedatabase:notedb){
            mList.add( new TaskClass(notedatabase.getId(),notedatabase.getContent(),notedatabase.getTime()) );
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
                Intent intent=new Intent( MainActivity.this,CalenderActivity.class );
                startActivity( intent );
                break;
            case R.id.AM:                   //moniter
                Intent intentToAM=new Intent( MainActivity.this,AppManageActivity.class );
                startActivity( intentToAM );
                break;
            case R.id.td_LL:                //To Do
                break;
            case R.id.dl_LL:                //DeadLine
                break;
            case R.id.tr_LL:                //TimeReport
                break;
            case R.id.ts_LL:                //TimeSaver社区
                break;
            case R.id.st_LL:                //Settings
                break;
            case R.id.ib_add:
                TaskDataBase task=new TaskDataBase();
                task.setContent( "六级模拟试卷" );
                task.setTime( getTime() );
                task.save();
                mList.add( new TaskClass(task.getId(),task.getContent(),task.getTime()) );
                timeLineAdapter.notifyDataSetChanged();
                break;
        }
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
