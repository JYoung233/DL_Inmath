package com.inmath.dl_test.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.inmath.dl_test.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by asus on 2016/3/16.
 * tab3部分的瀑布流的适配器
 */
public class MyTab3Adapter extends RecyclerView.Adapter<MyTab3Adapter.MyHolder> {
    private LayoutInflater mInflater;
    private Context mContext;
    private int image[]=new int[]{R.mipmap.amath,R.mipmap.bmath,R.mipmap.cmath,R.mipmap.dmath,
            R.mipmap.emath,R.mipmap.fmath,R.mipmap.gmatn,R.mipmap.hmath,R.mipmap.imath,R.mipmap.jmath

    };
    private List<Integer> mHeights;
    public interface mClickListener{
        public void mOnClickListener(View view,int pos);
    }
    public mClickListener mlistener;
    public void msetOnClickListener(mClickListener listener){
        mlistener=listener;
    }

    public MyTab3Adapter(Context mContext) {
        this.mContext=mContext;
        mInflater=LayoutInflater.from(mContext);
        mHeights=new ArrayList<>();
        for(int i=0;i<image.length;i++){
            mHeights.add((int) (100+Math.random()*300));
        }
    }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=mInflater.inflate(R.layout.item_tab3_straggle,parent,false);
        MyHolder holder=new MyHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final MyHolder holder, final int position) {
        ViewGroup.LayoutParams params=holder.itemView.getLayoutParams();
        params.height=mHeights.get(position);
        holder.itemView.setLayoutParams(params);
        holder.im.setImageResource(image[position]);
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
        return image.length;
    }

    class MyHolder extends RecyclerView.ViewHolder{
        private ImageView im;
        public MyHolder(View itemView) {
            super(itemView);
            im= (ImageView) itemView.findViewById(R.id.tab3_im);

        }
    }
}
