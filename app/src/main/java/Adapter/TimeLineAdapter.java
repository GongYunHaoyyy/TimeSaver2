package Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by acer on 2017/12/2.
 */

public class TimeLineAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}

//
//    private List<NoteClass> mNotelist;
//
//static class ViewHolder extends RecyclerView.ViewHolder {
//    View noteview;
//    TextView content1;
//    TextView content2;
//
//    public ViewHolder(View itemView) {
//        super( itemView );
//        noteview=itemView;
//        content1=(TextView)itemView.findViewById( R.id.content1 );
//        content2=(TextView)itemView.findViewById( R.id.content2 );
//    }
//}
//
//    public RecyclerNoteAdapter(List<NoteClass> noteList){
//        mNotelist=noteList;
//    }
//
//    @Override
//    public RecyclerNoteAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//        View view= LayoutInflater.from( parent.getContext() ).inflate( R.layout.note_item_recy,parent,false );
//        final ViewHolder viewHolder=new ViewHolder( view );
//        //        viewHolder.content1.setOnClickListener(  );
//        //        viewHolder.content2.setOnClickListener(  );
//        //        对子项监听
//        viewHolder.noteview.setOnClickListener( new View.OnClickListener( ) {
//            @Override
//            public void onClick(View v) {
//                int position=viewHolder.getAdapterPosition();
//                NoteClass noteClass=mNotelist.get( position );
//                mNotelist.remove( position );
//                notifyDataSetChanged();
//                DataSupport.delete( NoteContent.class,noteClass.getId() );
//            }
//        } );
//        return viewHolder;
//    }
//
//    @Override
//    public void onBindViewHolder(RecyclerNoteAdapter.ViewHolder holder, int position) {
//        NoteClass noteClass=mNotelist.get( position );
//        holder.content1.setText( noteClass.getContent() );
//        holder.content2.setText( noteClass.getTime() );
//    }
//
//    @Override
//    public int getItemCount() {
//        return mNotelist.size();
//    }

