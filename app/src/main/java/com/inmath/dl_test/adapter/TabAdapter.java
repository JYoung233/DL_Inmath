package com.inmath.dl_test.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.inmath.dl_test.R;

/**
 * Created by asus on 2016/3/13.
 * tab2页面的适配器
 */
public class TabAdapter extends RecyclerView.Adapter<TabAdapter.MyHolder> {
    private String[] title=new String[]{"分类模拟题","历年考研真题","答题闯关","考研预测"};
    private int[] images=new int[]{R.mipmap.fen,R.mipmap.li,R.mipmap.da,R.mipmap.kao};
    private Context mContext;
    private LayoutInflater mInflater;

    public TabAdapter(Context mContext) {
        this.mContext = mContext;
        mInflater=LayoutInflater.from(mContext);
    }
    public interface mClickListener{
        public void mOnClickListener(View view,int pos);
    }
    public mClickListener mlistener;
    public void msetOnClickListener(mClickListener listener){
        mlistener=listener;
    }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=mInflater.inflate(R.layout.item_tab2,parent,false);
        MyHolder holder=new MyHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final MyHolder holder, final int position) {
      holder.im.setImageResource(images[position]);
        holder.tvtitle.setText(title[position]);
        if(mlistener!=null){
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mlistener.mOnClickListener(holder.itemView,position);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return images.length;
    }

    class MyHolder extends RecyclerView.ViewHolder{
        private ImageView im;
        private TextView tvtitle;

        public MyHolder(View itemView) {
            super(itemView);
            im= (ImageView) itemView.findViewById(R.id.tab2_im);
            tvtitle= (TextView) itemView.findViewById(R.id.tab2_title);
        }
    }
}
