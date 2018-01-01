package com.gongyunhaoyyy.timesaver;

import android.Manifest;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.baidu.speech.EventListener;
import com.baidu.speech.EventManager;
import com.baidu.speech.EventManagerFactory;
import com.baidu.speech.asr.SpeechConstant;
import com.github.silvestrpredko.dotprogressbar.DotProgressBar;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Calendar;

public class deadlineActivity extends AppCompatActivity implements View.OnClickListener{

    private Button btn_audio;
    private Button btn_hand_in;
    private Button btn_ok;
    private Button btn_back;
    private TextView tv_deadline;
    private TextView tv_title;
    private Context mContext;
    private TextView tv_hour1,tv_hour2;
    private TextView tv_result;
    private TextView tv_remind_time;
    private TextView tv_site;
    private EditText tv_edit;
    private TextView tv_calenders;
    private PopupWindow popWindow;
    private EventManager asr;
    private RelativeLayout relativeLayout_deadline;
    private View popupWindowView;
    private View popupWindowView_1;
    private PopupWindow popWindow_1;
    private DotProgressBar dotProgressBar;
    private String Tag = "deadlineActivity";
    private boolean flag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deadline);
        mContext = deadlineActivity.this;
        initView();
        SetClickListener();
        asr = EventManagerFactory.create(this,"asr");
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

                }

            }
        };
        asr.registerListener(yourListener);
    }

    public void SetClickListener(){
        btn_ok.setOnClickListener(this);
     tv_site.setOnClickListener(this);
     tv_remind_time.setOnClickListener(this);
     tv_calenders.setOnClickListener(this);
     btn_audio.setOnClickListener(this);
     tv_deadline.setOnClickListener(this);
     tv_hour2.setOnClickListener(this);
     tv_hour1.setOnClickListener(this);
     btn_hand_in.setOnClickListener(this);
     btn_back.setOnClickListener(this);
    }

    public void initView(){
        btn_ok = findViewById(R.id.save);
        btn_back = findViewById(R.id.btn_deadline_back);
        tv_site = findViewById(R.id.tv_site);
        tv_remind_time = findViewById(R.id.tv_remind_time);
        tv_calenders = findViewById(R.id.tv_calender);
        tv_hour1 = findViewById(R.id.hour1);
        tv_hour2 = findViewById(R.id.hour2);
        tv_deadline = findViewById(R.id.tv_deadline);
        popupWindowView = LayoutInflater.from(mContext).inflate(R.layout.popupwindow_pink,null,false);
        popupWindowView_1 = LayoutInflater.from(mContext).inflate(R.layout.popupwindow_edit_pink,null,false);
        btn_hand_in = popupWindowView_1.findViewById(R.id.btn_hand_in);
        tv_title = popupWindowView_1.findViewById(R.id.tv_title);
        tv_edit = popupWindowView_1.findViewById(R.id.tv_edit);
        tv_result = popupWindowView.findViewById(R.id.listening_result);
        dotProgressBar = popupWindowView.findViewById(R.id.dot_progress_bar);
        btn_audio = findViewById(R.id.btn_audio);
        relativeLayout_deadline = findViewById(R.id.relativeLayout_deadline);
    }


    @Override
    public void onClick(View view){
        switch (view.getId()){
            case R.id.save:
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
            case R.id.tv_remind_time:
                PopupMenu popupMenu = new PopupMenu(deadlineActivity.this,tv_remind_time);
                popupMenu.getMenuInflater().inflate(R.menu.menu_repetition,popupMenu.getMenu());
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        switch (menuItem.getItemId()){
                            case R.id.fifteen_minute:
                                Toast.makeText(deadlineActivity.this,"fifteen_minute",Toast.LENGTH_SHORT).show();
                                break;
                            case R.id.thirty_minute:
                                Toast.makeText(deadlineActivity.this,"thirty_minute",Toast.LENGTH_SHORT).show();
                                break;
                            case R.id.forty_five_minute:
                                Toast.makeText(deadlineActivity.this,"forty_five_minute",Toast.LENGTH_SHORT).show();
                                break;
                            case R.id.one_hour:
                                Toast.makeText(deadlineActivity.this,"one_hour",Toast.LENGTH_SHORT).show();
                                break;
                        }
                        return true;
                    }
                });
                popupMenu.show();
                break;
            case R.id.tv_deadline:
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
                popWindow_1.showAtLocation(relativeLayout_deadline, Gravity.CENTER,0,0);
                break;
            case R.id.btn_deadline_back:
                showBackDialog();
                break;
            case R.id.btn_audio:
                String permissions[] = {
                        Manifest.permission.RECORD_AUDIO,
                        Manifest.permission.ACCESS_NETWORK_STATE,
                        Manifest.permission.INTERNET,
                        Manifest.permission.READ_PHONE_STATE,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE
                };
                ArrayList<String> toApplyList = new ArrayList<String>();

                for (String perm :permissions){
                    if (PackageManager.PERMISSION_GRANTED != ContextCompat.checkSelfPermission(deadlineActivity.this, perm)) {
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
                    popWindow.showAtLocation(relativeLayout_deadline, Gravity.CENTER,0,0);
                    String json = "{\"accept-audio-data\":false,\"disable-punctuation\":false,\"accept-audio-volume\":true,\"pid\":1537}";
                    asr.send(SpeechConstant.ASR_START,json,null,0,0);
                }else{
                    String tmpList[] = new String[toApplyList.size()];
                    ActivityCompat.requestPermissions(deadlineActivity.this, toApplyList.toArray(tmpList), 123);
                }
                break;
            case R.id.tv_calender:
                Calendar calendar = Calendar.getInstance();
                new DatePickerDialog(deadlineActivity.this, new DatePickerDialog.OnDateSetListener(){
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth){

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
                new TimePickerDialog(deadlineActivity.this, new TimePickerDialog.OnTimeSetListener() {
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
                new TimePickerDialog(deadlineActivity.this, new TimePickerDialog.OnTimeSetListener() {
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
                popWindow_1.showAtLocation(relativeLayout_deadline, Gravity.CENTER,0,0);
                break;
            case R.id.btn_hand_in:
                if (flag){
                    tv_site.setText(tv_edit.getText().toString());
                }else{
                    tv_deadline.setText(tv_edit.getText().toString());
                }
                popWindow_1.dismiss();
                break;

        }
    }


    private void paddingWindow() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            WindowManager.LayoutParams localLayoutParams = getWindow().getAttributes();
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS | localLayoutParams.flags);
        }
    }

    public void showBackDialog(){
        AlertDialog.Builder dialog=new AlertDialog.Builder( deadlineActivity.this );
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

    @Override
    public void onBackPressed() {
        showBackDialog();
    }

}
