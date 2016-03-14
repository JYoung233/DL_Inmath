package com.inmath.dl_test.adapter;

import android.content.Context;
import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.inmath.dl_test.R;
import com.inmath.dl_test.model.NoteSource;



/**
 * Created by asus on 2016/3/14.
 * 课堂笔记的适配器
 */
public class MyNoteAdapter extends RecyclerView.Adapter<MyNoteAdapter.MyHolder>{
    private Context mContext;
    private LayoutInflater mInflater;
    private Cursor c;
    private int[] images=new int[]{
        R.mipmap.fen,R.mipmap.favourite};
    private NoteSource noteSource;

    public interface mClickListener{
        public void OnPicItemClick(View v,int pos);
        public void OnPicItemLongClick(View v,int pos);
    }
    public mClickListener mlistener;
    public void msetOnClickListener(mClickListener listener){
        mlistener=listener;
    }

    public MyNoteAdapter(Context mContext) {
        this.mContext = mContext;
        mInflater=LayoutInflater.from(mContext);
        noteSource=new NoteSource(mContext);
        c=noteSource.selectNote();

    }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=mInflater.inflate(R.layout.item_note,parent,false);
        MyHolder holder=new MyHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final MyHolder holder, final int position) {

        if(c.moveToPosition(position)){
            holder.im.setImageResource(images[position%images.length]);
            holder.tvTitle.setText(c.getString(1));
            holder.tvContent.setText(c.getString(2));
        }
        if(mlistener!=null){
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mlistener.OnPicItemClick(holder.itemView, position);
                }
            });
            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    mlistener.OnPicItemLongClick(holder.itemView, position);
                    return false;
                }
            });

        }

    }
    public void delete(int position){
        noteSource.DeleteNote(position);
        notifyItemRemoved(position);
    }


    @Override
    public int getItemCount() {
        return c.getCount();
    }

    class MyHolder extends RecyclerView.ViewHolder{
        private ImageView im;
        private TextView tvTitle,tvContent;

        public MyHolder(View itemView) {
            super(itemView);
            im= (ImageView) itemView.findViewById(R.id.note_im);
            tvTitle= (TextView) itemView.findViewById(R.id.note_title);
            tvContent= (TextView) itemView.findViewById(R.id.note_content);
        }
    }
}
