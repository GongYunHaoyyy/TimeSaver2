package Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.gongyunhaoyyy.timesaver.R;
import com.gongyunhaoyyy.timesaver.TaskClass;
import com.gongyunhaoyyy.timesaver.TaskDataBase;

import org.litepal.crud.DataSupport;

import java.util.List;

/**
 * Created by acer on 2017/12/2.
 */

public class TimeLineAdapter extends RecyclerView.Adapter<TimeLineAdapter.ViewHolder> {
    private List<TaskClass> mTaskList;

    static class ViewHolder extends RecyclerView.ViewHolder {
            View taskview;
            TextView timeTV,contentTV;

            public ViewHolder(View itemView) {
                super( itemView );
                taskview=itemView;
                timeTV=(TextView)itemView.findViewById( R.id.time_TV );
                contentTV=(TextView)itemView.findViewById( R.id.content_TV );
            }
        }

    public TimeLineAdapter(List<TaskClass> taskList){
        mTaskList=taskList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view= LayoutInflater.from( parent.getContext() ).inflate( R.layout.item_timeline,parent,false );
            final ViewHolder viewHolder=new ViewHolder( view );
//              viewHolder.timeTV.setOnClickListener(  );
//              viewHolder.contentTV.setOnClickListener(  );
//              对子项监听
            viewHolder.taskview.setOnClickListener( new View.OnClickListener( ) {
                @Override
                public void onClick(View v) {
                    int position=viewHolder.getAdapterPosition();
                    TaskClass noteClass=mTaskList.get( position );
                    mTaskList.remove( position );
                    notifyDataSetChanged();
                    DataSupport.delete( TaskDataBase.class,noteClass.getId() );
                }
            } );
            return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        TaskClass taskClass=mTaskList.get( position );
        holder.timeTV.setText( taskClass.getTime() );
        holder.contentTV.setText( taskClass.getContent() );
    }

    @Override
    public int getItemCount() {
        return mTaskList.size();
    }
}

