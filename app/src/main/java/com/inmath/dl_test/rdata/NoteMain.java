package com.inmath.dl_test.rdata;

import android.app.Activity;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.inmath.dl_test.R;
import com.inmath.dl_test.adapter.MyNoteAdapter;

/**
 * Created by asus on 2016/3/14.
 *课堂笔记的主界面，使用cardview+recycylerview
 */
public class NoteMain extends Activity{
    private RecyclerView mRecyclerView;
    private Toolbar mToolbar;
    private MyNoteAdapter madapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.collectpic);
        mRecyclerView= (RecyclerView) findViewById(R.id.pic_list);
        mToolbar= (Toolbar) findViewById(R.id.toolbar2);
        mToolbar.setTitle("Inmath");
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        madapter=new MyNoteAdapter(this);
        madapter.msetOnClickListener(new MyNoteAdapter.mClickListener() {
            @Override
            public void OnPicItemClick(View v, int pos) {

            }

            @Override
            public void OnPicItemLongClick(View v, final int pos) {
                AlertDialog.Builder builder=new AlertDialog.Builder(NoteMain.this);
                builder.setMessage("确认删除？");
                builder.setPositiveButton("是的", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        madapter.delete(pos);
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
        mRecyclerView.setAdapter(madapter);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
    }
}
