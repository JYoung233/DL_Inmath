package com.inmath.dl_test.rdata;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.inmath.dl_test.AddNote;
import com.inmath.dl_test.R;
import com.inmath.dl_test.ViewNote;
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
        setContentView(R.layout.mainnote);
        mRecyclerView = (RecyclerView) findViewById(R.id.note_list);
        mToolbar = (Toolbar) findViewById(R.id.toolbar4);
        mToolbar.setTitle("Inmath");

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        madapter = new MyNoteAdapter(this);
        madapter.msetOnClickListener(new MyNoteAdapter.mClickListener() {
            @Override
            public void OnPicItemClick(View v, int pos) {
                Intent intent = new Intent(NoteMain.this,ViewNote.class);
                Bundle bundle=new Bundle();
                bundle.putInt("id", pos);//这里需要做一下测试
                intent.putExtras(bundle);
                startActivity(intent);

            }

            @Override
            public void OnPicItemLongClick(View v, final int pos, final int length) {
                AlertDialog.Builder builder = new AlertDialog.Builder(NoteMain.this);
                builder.setMessage("确认删除？");
                builder.setPositiveButton("是的", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        madapter.delete(pos,length);

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
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(NoteMain.this, AddNote.class);
                startActivity(intent);
                NoteMain.this.finish();
            }
        });
    }
}
