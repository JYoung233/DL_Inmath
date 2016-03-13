package com.inmath.dl_test;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.inmath.dl_test.adapter.CollectAdapter;

/**
 * Created by asus on 2016/3/13.
 * 显示收藏夹中的内容
 */
public class CollectPic extends Activity {
    private RecyclerView mRecyclerView;
    private Toolbar mToolbar;
    private CollectAdapter mAdpater;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.collectpic);
        mRecyclerView= (RecyclerView) findViewById(R.id.pic_list);
        mToolbar= (Toolbar) findViewById(R.id.toolbar2);
        mToolbar.setTitle("Inmath");
        mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        mAdpater=new CollectAdapter(this);
        mAdpater.msetOnClickListener(new CollectAdapter.mClickListener() {
            @Override
            public void OnPicItemClick(View v, int pos) {
                Intent intent=new Intent(CollectPic.this,Gallerypic.class);
                intent.putExtra("position", pos);
                CollectPic.this.startActivity(intent);
            }

            @Override
            public void OnPicItemLongClick(View v, final int pos) {
                //长按删除菜单，先出来对话框
                AlertDialog.Builder builder=new AlertDialog.Builder(CollectPic.this);
                builder.setMessage("确认删除？");
                builder.setPositiveButton("是的", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mAdpater.delete(pos);
                    }
                });
                builder.setNegativeButton("不，谢谢", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

                builder.create().show();
            }
        });
        mRecyclerView.setAdapter(mAdpater);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());



    }
}
