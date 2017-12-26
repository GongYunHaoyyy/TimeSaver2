package com.gongyunhaoyyy.timesaver;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.TypedValue;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class toDoActivity extends AppCompatActivity implements View.OnClickListener{
    private TextView calenders;
    private EditText hour1,hour2;
    private Button btn_ok,btn_back;
    private List<TaskClass> mList=new ArrayList<>(  );

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_to_do);
        paddingWindow();
        initView();
        SetClickListener();
    }

    private void paddingWindow() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            WindowManager.LayoutParams localLayoutParams = getWindow().getAttributes();
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS | localLayoutParams.flags);
        }
    }

    public void initView(){
        calenders=findViewById( R.id.todo_calender );
        btn_back=findViewById( R.id.btn_todo_back );
        btn_ok=findViewById( R.id.btn_todo_ok );
        hour1=findViewById( R.id.hour1 );
        hour2=findViewById( R.id.hour2 );
    }
    public void SetClickListener(){
        calenders.setOnClickListener( this );
        btn_ok.setOnClickListener( this );
        btn_back.setOnClickListener( this );
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_todo_ok:
                String todotime=hour1.getText().toString()+"-"+hour2.getText().toString();
                String[] tttime=todotime.split( "-" );
                String[] starttime=tttime[0].split( ":" );
                String[] endtime=tttime[1].split( ":" );
//                用来实现dp,px的转换
                double position1 = (double) TypedValue.applyDimension( TypedValue.COMPLEX_UNIT_DIP,
                        (float) (Float.parseFloat( starttime[0] )*45.5+Float.parseFloat( starttime[1] )/60*45),
                        this.getResources().getDisplayMetrics());
                double position2 = (double) TypedValue.applyDimension( TypedValue.COMPLEX_UNIT_DIP,
                        (float) (Float.parseFloat( endtime[0] )*45.5+Float.parseFloat( endtime[1] )/60*45),
                        this.getResources().getDisplayMetrics());
                TaskDataBase task=new TaskDataBase();
                task.setContent( "这是测试文字" );
                task.setStarttime( tttime[0] );
                task.setEndtime( tttime[1] );
                task.setStartY( position1 );
                task.setEndY( position2 );
                task.save();
                finish();
                break;
            case R.id.btn_todo_back:
                showBackDialog();
                break;
            case R.id.todo_calender:
                break;
        }
    }

    @Override
    public void onBackPressed() {
        showBackDialog();
    }

    public void showBackDialog(){
        AlertDialog.Builder dialog=new AlertDialog.Builder( toDoActivity.this );
        dialog.setTitle( "提示" );
        dialog.setMessage( "返回后当前输入的内容将被清除，确定要返回吗？" );
        dialog.setPositiveButton( "OK", new DialogInterface.OnClickListener( ) {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        } );
        dialog.setNegativeButton( "Cancel", new DialogInterface.OnClickListener( ) {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        } );
        dialog.setCancelable( false );
        dialog.show();
    }
}
