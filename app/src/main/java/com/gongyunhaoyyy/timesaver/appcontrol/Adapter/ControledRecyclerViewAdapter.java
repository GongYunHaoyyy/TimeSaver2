package com.gongyunhaoyyy.timesaver.appcontrol.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.gongyunhaoyyy.timesaver.R;
import com.gongyunhaoyyy.timesaver.appcontrol.Interface.RecyclerViewOnItemListener;
import com.gongyunhaoyyy.timesaver.appcontrol.bean.ControledAppModel;

import java.util.List;

/**
 * Created by DELL on 2017/11/28.
 */

public class ControledRecyclerViewAdapter extends RecyclerView.Adapter<ControledRecyclerViewAdapter.ViewHolder>{
    private List<ControledAppModel> list;
    private Context context;
    private RecyclerViewOnItemListener listener;
   public ControledRecyclerViewAdapter(Context context){
       this.context=context;

   }
   public void setList(List<ControledAppModel> list){
       this.list=list;
   }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.managed_app_item,parent,false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Log.i("Adapter","bindView"+position);
        holder.appImg.setImageDrawable(list.get(position).getmAppImg());
        holder.appName.setText(list.get(position).getmAppName());
        holder.usedTv.setText(getTimeFromInt(list.get(position).getmUsedTime()));
        holder.setTv.setText(getTimeFromInt(list.get(position).getmSetTime()));
        holder.leftTv.setText(getTimeFromInt(list.get(position).getmSetTime()-list.get(position).getmUsedTime()));
    }
    protected String getTimeFromInt(int value){
        return value/60+" 小时"+value%60+" 分";
    }


    @Override
    public int getItemCount() {
        return list.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
       private ImageView appImg;
       private TextView appName;
       private TextView usedTv;
       private TextView setTv;
       private TextView leftTv;
       private RecyclerViewOnItemListener listener;
        public ViewHolder(View itemView) {
            super(itemView);
            appImg=(ImageView)itemView.findViewById(R.id.app_img);
            appName=(TextView)itemView.findViewById(R.id.app_name);
            usedTv=itemView.findViewById(R.id.used_time);
            setTv=itemView.findViewById(R.id.setting_useTime);
            leftTv=itemView.findViewById(R.id.leftTime);
        }
    }
}
