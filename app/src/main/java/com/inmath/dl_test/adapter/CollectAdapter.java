package com.inmath.dl_test.adapter;

import android.content.Context;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.inmath.dl_test.R;
import com.inmath.dl_test.model.ImageSource;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by asus on 2016/3/13.
 * 收藏夹的适配器：从数据库中读取图片名称，从assets文件夹中获取图片
 */
public class CollectAdapter extends RecyclerView.Adapter<CollectAdapter.MyHolder>{

    private ImageSource mSource;
    private Context mContext;
    private LayoutInflater mInflater;
    private AssetManager am;
    private List<Integer> mHeights;
    public interface mClickListener{
        public void OnPicItemClick(View v,int pos);
        public void OnPicItemLongClick(View v,int pos);
    }
    public mClickListener mlistener;
    public void msetOnClickListener(mClickListener listener){
        mlistener=listener;
    }

    public CollectAdapter(Context c) {
        mContext=c;
        mInflater=LayoutInflater.from(c);
        mSource=new ImageSource(c);
        Resources resources=c.getResources();
        am=resources.getAssets();
        mHeights=new ArrayList<>();
        for(int i=0;i<mSource.getLength();i++){
            mHeights.add((int) (100+Math.random()*300));
        }
    }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=mInflater.inflate(R.layout.item_pic, parent, false);
        MyHolder holder=new MyHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final MyHolder holder, final int position) {
    //为瀑布流设置随机高度
        ViewGroup.LayoutParams params=holder.itemView.getLayoutParams();
        params.height=mHeights.get(position);
        holder.itemView.setLayoutParams(params);
        //从assets中读取图片信息
        Cursor cur=mSource.getCursor();
        if(cur.moveToPosition(position)){
            String filename=cur.getString(0);
            InputStream is=null;
            try {
                is=am.open(filename);
                Bitmap bm= BitmapFactory.decodeStream(is);
                holder.im.setImageBitmap(bm);
                holder.im.setScaleType(ImageView.ScaleType.FIT_XY);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }if(cur.isAfterLast()){
            mSource.closeCur();
            mSource.closeDB();
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
                    mlistener.OnPicItemLongClick(holder.itemView,position);
                    return false;
                }
            });

        }

    }

    @Override
    public int getItemCount() {
        return mSource.getLength();
    }
    public void delete(int position){
      //怎么才能安全地删除数据库中的数据？？？
        notifyItemRemoved(position);
    }

    class MyHolder extends RecyclerView.ViewHolder{
        private ImageView im;

        public MyHolder(View itemView) {
            super(itemView);
            im= (ImageView) itemView.findViewById(R.id.im1);
        }
    }
}
