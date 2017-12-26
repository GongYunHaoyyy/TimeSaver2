package com.gongyunhaoyyy.timesaver;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.ScrollView;
import android.widget.TextView;

import com.baidu.speech.EventListener;
import com.baidu.speech.EventManager;
import com.baidu.speech.EventManagerFactory;
import com.baidu.speech.asr.SpeechConstant;
import com.github.silvestrpredko.dotprogressbar.DotProgressBar;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.util.ArrayList;

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


    private Button audioButton;
    private Context mContext;
    private TextView result;
    private PopupWindow popWindow;
    private ScrollView scroll_toDo;
    private DotProgressBar dotProgressBar;
    private String Tag = "toDoActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_to_do);
        mContext = toDoActivity.this;
        audioButton = (Button)findViewById(R.id.audioButton);
        scroll_toDo = (ScrollView) findViewById(R.id.scroll_toDo);
        final View popupWindowView = LayoutInflater.from(mContext).inflate(R.layout.popupwindow,null,false);
        result = (TextView)popupWindowView.findViewById(R.id.listening_result);
        dotProgressBar = (DotProgressBar)popupWindowView.findViewById(R.id.dot_progress_bar);
        final EventManager asr = EventManagerFactory.create(this,"asr");
        EventListener yourListener = new EventListener() {
            @Override
            public void onEvent(final String name, String params, byte[] bytes, int offset, int length) {
                if(name.equals(SpeechConstant.CALLBACK_EVENT_ASR_READY)){
                    // 引擎就绪，可以说话，一般在收到此事件后通过UI通知用户可以说话了
                    Log.d(Tag,"引擎就绪，可以说话，一般在收到此事件后通过UI通知用户可以说话了");
                    result.setText("可以说话");
                }
                if(name.equals(SpeechConstant.CALLBACK_EVENT_ASR_FINISH)){
                    // 识别结束
                    Log.d(Tag,"识别结束");

                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            popWindow.dismiss();
                        }
                    },1000);
                }
                if(name.equals(SpeechConstant.CALLBACK_EVENT_ASR_PARTIAL)){
                    Log.d(Tag,params);
                    JsonParser jsonParser = new JsonParser();
                    JsonObject jsonObject = jsonParser.parse(params).getAsJsonObject();
                    String results_recognition = jsonObject.get("results_recognition").getAsString();
                    result.setText(results_recognition);
                }
            }
        };
        asr.registerListener(yourListener);
        audioButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {

                String permissions[] = {
                        Manifest.permission.RECORD_AUDIO,
                        Manifest.permission.ACCESS_NETWORK_STATE,
                        Manifest.permission.INTERNET,
                        Manifest.permission.READ_PHONE_STATE,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE
                };
                ArrayList<String> toApplyList = new ArrayList<String>();

                for (String perm :permissions){
                    if (PackageManager.PERMISSION_GRANTED != ContextCompat.checkSelfPermission(toDoActivity.this, perm)) {
                        toApplyList.add(perm);
                        //进入到这里代表没有权限.
                    }
                }
                if (toApplyList.isEmpty()){
                    popWindow = new PopupWindow(popupWindowView,
                            ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
                    popWindow.setAnimationStyle(R.anim.anim_pop);
                    popWindow.setTouchable(true);
                    popWindow.setTouchInterceptor(new View.OnTouchListener() {
                        @Override
                        public boolean onTouch(View view, MotionEvent motionEvent) {
                            asr.send(SpeechConstant.ASR_CANCEL,null,null,0,0);
                            return false;
                        }
                    });
                    popWindow.showAtLocation(scroll_toDo, Gravity.CENTER,0,0);
                    String json = "{\"accept-audio-data\":false,\"disable-punctuation\":false,\"accept-audio-volume\":true,\"pid\":1537}";
                    asr.send(SpeechConstant.ASR_START,json,null,0,0);
                }else{
                    String tmpList[] = new String[toApplyList.size()];
                    ActivityCompat.requestPermissions(toDoActivity.this, toApplyList.toArray(tmpList), 123);
                }


            }
        });
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
