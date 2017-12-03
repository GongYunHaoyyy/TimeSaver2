package com.gongyunhaoyyy.timesaver.appcontrol;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.NumberPicker;
import android.widget.TextView;

import com.gongyunhaoyyy.timesaver.R;
import com.gongyunhaoyyy.timesaver.appcontrol.bean.ControledAppModel;
import com.gongyunhaoyyy.timesaver.appcontrol.tools.ManagedAppSave;

import java.util.ArrayList;
import java.util.List;

import BaseClass.Base.BaseActivity;

public class ManageAppDetail extends BaseActivity implements View.OnClickListener,NumberPicker.OnValueChangeListener,NumberPicker.Formatter{
     Context mContext;

     private final static int FROM_MANAGED_ACTIVITY=0;
     private final static int FROM_THIRD_PART_APP_ACTIVITY=1;
     private ImageView mAppImg;
     private TextView mAppName;
     private TextView mRepetition_tv;
     private TextView mRemindWay_tv;
     private TextView mAppTimeSetting_tv;

     private int hour,minute;
     private ManagedAppSave managedAppSave;
     private List<ControledAppModel> list;
     private Dialog dialog;
     private int from;
     private android.support.v7.widget.Toolbar toolbar;
     Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext=this;
        managedAppSave=new ManagedAppSave(getApplication(),"AppManagedSp");
        if (managedAppSave!=null){
            list=managedAppSave.getDataList("appList");
        }else {
            list=new ArrayList<>();
        }
        initView();
        intent=getIntent();
       if (intent!=null){
           if (intent.getStringExtra("from").equals(ThirdPartAppActivity.TAG)){
               from=FROM_THIRD_PART_APP_ACTIVITY;
              byte[] bytes=intent.getByteArrayExtra("bitmap");
               Bitmap bitmap= BitmapFactory.decodeByteArray(bytes,0,bytes.length);
               mAppImg.setImageBitmap(bitmap);
               mAppName.setText(intent.getStringExtra("appName"));
           }else {
               from=FROM_MANAGED_ACTIVITY;
               byte[] bytes=intent.getByteArrayExtra("bitmap");
               Bitmap bitmap= BitmapFactory.decodeByteArray(bytes,0,bytes.length);
               mAppImg.setImageBitmap(bitmap);
               mAppName.setText(intent.getStringExtra("appName"));
               mRepetition_tv.setText(intent.getStringExtra("repeat"));
               mRemindWay_tv.setText(intent.getStringExtra("remind"));
               hour=intent.getIntExtra("setTime",0)/60;
               minute=intent.getIntExtra("setTime",0)%60;
               mAppTimeSetting_tv.setText(hour+" 时"+minute+" 分");
           }
       }
       toolbar=initToolBarRightTxt("应用详情", "保存", new OnRightClickListener() {
           @Override
           public void rightClick() {
               if (from==FROM_MANAGED_ACTIVITY){
                   for (int i=0;i<list.size();i++){
                       if (list.get(i).getmAppName().equals(mAppName.getText().toString())){
                           list.get(i).setmSetTime(hour*60+minute);
                           list.get(i).setmRemindWay(mRemindWay_tv.getText().toString());
                           list.get(i).setmRepeat(mRepetition_tv.getText().toString());
                           break;
                       }
                   }
               }else {
                   ControledAppModel app=new ControledAppModel(mAppName.getText().toString(),hour*60+minute,mRemindWay_tv.getText().toString(),mRepetition_tv.getText().toString());
                   list.add(app);
               }
               managedAppSave.setDataList("appList",list);
               finish();
           }
       });

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_manage_app_detail;
    }

    @Override
    protected void setListener() {

    }

    @Override
    protected void initData() {

    }

    protected void initView(){
        mAppImg=(ImageView)findViewById(R.id.app_detail_img);
        mAppName=(TextView)findViewById(R.id.app_datail_name);
        mRemindWay_tv=(TextView)findViewById(R.id.remind_way);
        mRepetition_tv=(TextView)findViewById(R.id.repetition);
        mAppTimeSetting_tv=(TextView)findViewById(R.id.setTime);
        mRemindWay_tv.setOnClickListener(this);
        mRepetition_tv.setOnClickListener(this);
        mAppTimeSetting_tv.setOnClickListener(this);
    }

    @Override
    protected void setData() {

    }

    @Override
    protected void getBundleExtras(Bundle bundle) {

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.remind_way:
                Log.i("ManageDetail",v.getId()+"被点击");
                ActionSheet.createBuilder(mContext)
                        .setCancelButtonTitle(
                                "取消")
                        .setOtherButtonTitles(
                                "震动",
                                "通知栏",
                                "震动,通知栏")
                        .setCancelableOnTouchOutside(true)
                        .setListener(new ActionSheet.ActionSheetListener() {

                            @Override
                            public void onOtherButtonClick(int index) {

                                switch (index) {
                                    case 0:
                                       mRemindWay_tv.setText("震动");
                                        break;
                                    case 1:
                                        mRemindWay_tv.setText("通知栏");
                                        break;
                                    case 2:
                                       mRemindWay_tv.setText("震动、通知栏");
                                        break;
                                    default:
                                        break;
                                }
                            }
                        }).show();
                break;
            case R.id.repetition:
                ActionSheet.createBuilder(mContext)
                        .setCancelButtonTitle(
                                "取消")
                        .setOtherButtonTitles(
                                "五分钟",
                                "十分钟",
                                "十五分")
                        .setCancelableOnTouchOutside(true)
                        .setListener(new ActionSheet.ActionSheetListener() {

                            @Override
                            public void onOtherButtonClick(int index) {

                                switch (index) {
                                    case 0:
                                        mRepetition_tv.setText("五分钟");
                                        break;
                                    case 1:
                                        mRepetition_tv.setText("十分钟");
                                        break;
                                    case 2:
                                        mRepetition_tv.setText("十五分");
                                        break;
                                    default:
                                        break;
                                }
                            }
                        }).show();
                break;
            case R.id.setTime:
                dialog= new Dialog(mContext, R.style.ActionSheetDialogStyle);
                //填充对话框布局
                LayoutInflater inflater = LayoutInflater.from(mContext);
                View view = inflater.inflate(R.layout.settime_dialog, null);
                //将布局设置给Dialog
                dialog.setContentView(view);
                //获得当前所在窗体
                NumberPicker hourPicker=view.findViewById(R.id.hourPicker);
                NumberPicker minutePicker=view.findViewById(R.id.minute_picker);
                TextView commit_time=(TextView)view.findViewById(R.id.commit_time);
                commit_time.setOnClickListener(this);
                hourPicker.setMaxValue(5);
                hourPicker.setMinValue(0);
                hourPicker.setOnValueChangedListener(this);
                minutePicker.setMaxValue(59);
                minutePicker.setMinValue(0);
                minutePicker.setFormatter(this);
                minutePicker.setOnValueChangedListener(this);
                Window dialogWindow = dialog.getWindow();
                //设置底部弹出
                dialogWindow.setGravity(Gravity.BOTTOM);
                //设置窗体属性
                WindowManager.LayoutParams lp = dialogWindow.getAttributes();
                //设置距离底部高度
                lp.y = 20;
                lp.width=WindowManager.LayoutParams.MATCH_PARENT;
                lp.height=WindowManager.LayoutParams.WRAP_CONTENT;
                dialogWindow.setAttributes(lp);
                dialog.show();
                break;
            case R.id.commit_time:
                mAppTimeSetting_tv.setText(hour+" 时"+minute+" 分");
                dialog.dismiss();
                break;
            default:
                break;
        }

    }
    public String format(int value){
        String tmpStr = String.valueOf(value);
        if (value < 10) {
            tmpStr = "0" + tmpStr;
        }
        return tmpStr;
    }

    @Override
    public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
        switch (picker.getId()){
            case R.id.hourPicker:
            hour=newVal;
            break;
            case R.id.minute_picker:
                minute=newVal;
        }
    }

    @Override
    protected String[] getNeedPermissions() {
        return new String[0];
    }
}
