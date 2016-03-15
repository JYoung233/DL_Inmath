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
        R.mipmap.a,R.mipmap.b,R.mipmap.c,R.mipmap.d,R.mipmap.e,R.mipmap.f,R.mipmap.g,R.mipmap.h,R.mipmap.i,R.mipmap.j};
    private NoteSource noteSource;

    public interface mClickListener{
        public void OnPicItemClick(View v,int pos);
        public void OnPicItemLongClick(View v,int pos,int length);
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
        c=noteSource.selectNote();
        if(c.moveToPosition(position)){
            holder.im.setImageResource(images[position%images.length]);
            holder.tvTitle.setText(c.getString(1));
            holder.tvContent.setText(c.getString(2));
        }if(c.isAfterLast()){
            noteSource.closeCur();
            noteSource.closeDB();
            c.close();

        }
        if(mlistener!=null){
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos=holder.getLayoutPosition();//为了确保当前位置正确
                    mlistener.OnPicItemClick(holder.itemView, pos);
                }
            });
            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    int pos=holder.getLayoutPosition();
                    mlistener.OnPicItemLongClick(holder.itemView,pos,c.getCount());
                    return false;
                }
            });

        }

    }
    public void delete(int position,int length){
        noteSource.DeleteNote(position);
        noteSource.MoreUpgrade(position,length);
        notifyItemRemoved(position);

    }
    public void add(){
        notifyItemInserted(getItemCount());
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
