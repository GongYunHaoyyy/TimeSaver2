package com.gongyunhaoyyy.timesaver.appcontrol.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.gongyunhaoyyy.timesaver.R;
import com.gongyunhaoyyy.timesaver.appcontrol.Interface.RecyclerViewOnItemListener;
import com.gongyunhaoyyy.timesaver.appcontrol.bean.ThirdPartAppModel;

import java.util.List;

/**
 * Created by DELL on 2017/11/28.
 */

public class ThirdPartAppAdapter extends RecyclerView.Adapter<ThirdPartAppAdapter.ViewHolder> {
    private List<ThirdPartAppModel>list;
    private Context context;
    private RecyclerViewOnItemListener listener;
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(View.inflate(context, R.layout.third_part_app_item,null),listener);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
    holder.appName.setText(list.get(position).getAppName());
    holder.appImg.setImageDrawable(list.get(position).getAppIcon());
        Log.i("adapter","appName"+holder.appName.getText().toString());
    }
    public ThirdPartAppAdapter(Context context, List<ThirdPartAppModel>appList,RecyclerViewOnItemListener listener){
        this.context=context;
        this.list=appList;
        this.listener=listener;
    }
    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private ImageView appImg;
        private TextView appName;
        private RecyclerViewOnItemListener listener;
        public ViewHolder(View itemView,RecyclerViewOnItemListener listener){
            super(itemView);
            this.listener=listener;
            appImg=(ImageView)itemView.findViewById(R.id.third_partApp_img);
            appName=(TextView)itemView.findViewById(R.id.third_partApp_name);
         itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (listener!=null){
                listener.onItemClick(v,getPosition());
            }
        }
    }
}
