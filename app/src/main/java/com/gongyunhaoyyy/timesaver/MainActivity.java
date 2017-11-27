package com.gongyunhaoyyy.timesaver;

import android.content.Intent;
import android.os.Build;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    ImageButton openDrawer;
    DrawerLayout drawerLayout;
    LinearLayout timeLine,calender,toDo,deadLine,timeReport,sheQu,setting;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );
        paddingWindow();
        init();
        openDrawer.setOnClickListener( this );
        timeLine.setOnClickListener( this );
        calender.setOnClickListener( this );
    }

    private void paddingWindow() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            WindowManager.LayoutParams localLayoutParams = getWindow().getAttributes();
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS | localLayoutParams.flags);
        }
    }

    private void init(){
        openDrawer=(ImageButton)findViewById( R.id.ib_opendrawer );
        drawerLayout=(DrawerLayout)findViewById( R.id.drawerLayout );
        timeLine=(LinearLayout) findViewById( R.id.tl_LL );
        calender=(LinearLayout) findViewById( R.id.cld_LL );
        toDo=(LinearLayout)findViewById( R.id.td_LL );
        deadLine=(LinearLayout)findViewById( R.id.dl_LL );
        timeReport=(LinearLayout)findViewById( R.id.tr_LL );
        sheQu=(LinearLayout)findViewById( R.id.ts_LL );
        setting=(LinearLayout)findViewById( R.id.st_LL );
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
        }
    }
}
