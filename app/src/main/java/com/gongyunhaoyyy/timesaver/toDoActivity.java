package com.gongyunhaoyyy.timesaver;

import android.Manifest;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
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
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
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
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class toDoActivity extends AppCompatActivity implements View.OnClickListener{
    private TextView tv_calenders,tv_hour1,tv_hour2;
    private Button btn_ok,btn_back;
    private Button btn_hand_in;
    private List<TaskClass> mList=new ArrayList<>(  );
    private Button btn_audio;
    private Context mContext;
    private TextView tv_result;
    private PopupWindow popWindow;
    private PopupWindow popWindow_1;
    private ScrollView scroll_toDo;
    private View popupWindowView;
    private View popupWindowView_1;
    private EventManager asr;
    private TextView tv_remind_time;
    private TextView tv_site;
    private TextView tv_title;
    private TextView tv_todo;
    private EditText tv_edit;
    private String Tag = "toDoActivity";
    private String answer;
    private Boolean flag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_to_do);
        mContext = toDoActivity.this;
        asr = EventManagerFactory.create(this,"asr");
        //注册百度语音的监听事件
        EventListener yourListener = new EventListener() {
            @Override
            public void onEvent(final String name, String params, byte[] bytes, int offset, int length) {
                if(name.equals(SpeechConstant.CALLBACK_EVENT_ASR_READY)){
                    // 引擎就绪，可以说话，一般在收到此事件后通过UI通知用户可以说话了
                    Log.d(Tag,"引擎就绪，可以说话，一般在收到此事件后通过UI通知用户可以说话了");
                    tv_result.setText("可以说话");
                }
                if(name.equals(SpeechConstant.CALLBACK_EVENT_ASR_FINISH)){
                    // 识别结束
                    Log.d(Tag,"识别结束");
                    tv_todo.setText(answer);
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
                    tv_result.setText(results_recognition);
                    answer = results_recognition;
                }
            }
        };
        asr.registerListener(yourListener);
       // paddingWindow();
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
        tv_site = findViewById(R.id.tv_site);
        tv_todo = findViewById(R.id.tv_todo);
        tv_remind_time = findViewById(R.id.tv_remind_time);
        popupWindowView = LayoutInflater.from(mContext).inflate(R.layout.popupwindow,null,false);
        popupWindowView_1 = LayoutInflater.from(mContext).inflate(R.layout.popupwindow_edit,null,false);
        btn_hand_in = popupWindowView_1.findViewById(R.id.btn_hand_in);
        tv_title = popupWindowView_1.findViewById(R.id.tv_title);
        tv_edit = popupWindowView_1.findViewById(R.id.tv_edit);
        tv_result = popupWindowView.findViewById(R.id.listening_result);
        btn_audio = findViewById(R.id.audioButton);
        scroll_toDo = findViewById(R.id.scroll_toDo);
        tv_calenders = findViewById( R.id.todo_calender );
        btn_back = findViewById( R.id.btn_todo_back );
        btn_ok = findViewById( R.id.btn_todo_ok );
        tv_hour1 = findViewById( R.id.hour1 );
        tv_hour2 = findViewById( R.id.hour2 );
    }

    public void SetClickListener(){
        tv_todo.setOnClickListener(this);
        btn_hand_in.setOnClickListener(this);
        tv_site.setOnClickListener(this);
        tv_remind_time.setOnClickListener(this);
        btn_audio.setOnClickListener(this);
        tv_calenders.setOnClickListener(this);
        tv_hour1.setOnClickListener(this);
        tv_hour2.setOnClickListener(this);
        btn_ok.setOnClickListener(this);
        btn_back.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_todo_ok:
                String todotime = tv_hour1.getText().toString()+"-"+tv_hour2.getText().toString();
                String[] tttime = todotime.split( "-" );
                String[] starttime = tttime[0].split( ":" );
                String[] endtime = tttime[1].split( ":" );
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
                Calendar calendar = Calendar.getInstance();
                new DatePickerDialog(toDoActivity.this, new DatePickerDialog.OnDateSetListener(){
                    @Override
                    public void onDateSet(DatePicker view,int year, int monthOfYear, int dayOfMonth){

                         String date = "";
                         date = date + year;
                         date = date + "—";
                         date = date + (monthOfYear+1);
                         date = date + "—";
                         date = date + dayOfMonth;
                         tv_calenders.setText(date);

                    }
                },calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),calendar.get(Calendar.DAY_OF_MONTH)).show();
                break;
            case R.id.hour1:
                Calendar calendar_1 = Calendar.getInstance();
                new TimePickerDialog(toDoActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int i, int i1) {

                         String time = "";
                         time = time + i;
                         time = time + ":";
                         time = time + i1;
                         tv_hour1.setText(time);


                    }
                },calendar_1.get(Calendar.HOUR_OF_DAY),calendar_1.get(Calendar.MINUTE),true).show();
                break;
            case R.id.hour2:
                Calendar calendar_2 = Calendar.getInstance();
                new TimePickerDialog(toDoActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int i, int i1) {

                        String time = "";
                        time = time + i;
                        time = time + ":";
                        time = time + i1;
                        tv_hour2.setText(time);

                    }
                },calendar_2.get(Calendar.HOUR_OF_DAY),calendar_2.get(Calendar.MINUTE),true).show();
                break;
            case R.id.audioButton:
                String permissions[] = {
                        Manifest.permission.RECORD_AUDIO,
                        Manifest.permission.ACCESS_NETWORK_STATE,
                        Manifest.permission.INTERNET,
                        Manifest.permission.READ_PHONE_STATE,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE
                };
                final ArrayList<String> toApplyList = new ArrayList<String>();

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
                break;
            case R.id.tv_remind_time:
                PopupMenu popupMenu = new PopupMenu(toDoActivity.this,tv_remind_time);
                popupMenu.getMenuInflater().inflate(R.menu.menu_repetition,popupMenu.getMenu());
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        switch (menuItem.getItemId()){
                            case R.id.fifteen_minute:
                                Toast.makeText(toDoActivity.this,"fifteen_minute",Toast.LENGTH_SHORT).show();
                                break;
                            case R.id.thirty_minute:
                                Toast.makeText(toDoActivity.this,"thirty_minute",Toast.LENGTH_SHORT).show();
                                break;
                            case R.id.forty_five_minute:
                                Toast.makeText(toDoActivity.this,"forty_five_minute",Toast.LENGTH_SHORT).show();
                                break;
                            case R.id.one_hour:
                                Toast.makeText(toDoActivity.this,"one_hour",Toast.LENGTH_SHORT).show();
                                break;
                        }
                        return true;
                    }
                });
                popupMenu.show();
                break;
            case R.id.tv_site:
                flag = true;
                popWindow_1 = new PopupWindow(popupWindowView_1,
                        ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
                popWindow_1.setAnimationStyle(R.anim.anim_pop);
                popWindow_1.setTouchable(true);
                popWindow_1.setTouchInterceptor(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View view, MotionEvent motionEvent) {
                        return false;
                    }
                });
                popWindow_1.showAtLocation(scroll_toDo, Gravity.CENTER,0,0);
                break;
            case R.id.btn_hand_in:
                if (flag){
                    tv_site.setText(tv_edit.getText().toString());
                }else{
                    tv_todo.setText(tv_edit.getText().toString());
                }
                popWindow_1.dismiss();
                break;
            case R.id.tv_todo:
                flag = false;
                tv_title.setText("设置你要做的事");
                popWindow_1 = new PopupWindow(popupWindowView_1,
                        ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
                popWindow_1.setAnimationStyle(R.anim.anim_pop);
                popWindow_1.setTouchable(true);
                popWindow_1.setTouchInterceptor(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View view, MotionEvent motionEvent) {
                        return false;
                    }
                });
                popWindow_1.showAtLocation(scroll_toDo, Gravity.CENTER,0,0);
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
