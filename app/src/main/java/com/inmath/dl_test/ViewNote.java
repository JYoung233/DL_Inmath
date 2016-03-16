package com.inmath.dl_test;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Environment;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


import com.inmath.dl_test.rdata.DBopenHelper;
import com.inmath.dl_test.rdata.NoteMain;

import java.io.File;

/**
 * Created by asus on 2016/3/14.
 * 点击查看课堂笔记内容
 */
public class ViewNote extends AppCompatActivity{
    private Toolbar toolbar;
    private ImageView imageView;
    private TextView tvContent;
    private FloatingActionButton fab;
    private Cursor cursor;
    private DBopenHelper dh;
    private SQLiteDatabase db;
    private int id;
    private String title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.noteview);
        toolbar= (Toolbar) findViewById(R.id.toolbar5);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.mipmap.arrow);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ViewNote.this.finish();
            }
        });
        CollapsingToolbarLayout mcol= (CollapsingToolbarLayout) findViewById(R.id.toolbar_layout);

        imageView= (ImageView) findViewById(R.id.showpic);
        tvContent= (TextView) findViewById(R.id.showcontent);
        fab= (FloatingActionButton) findViewById(R.id.fab2);
        Bundle bundle=getIntent().getExtras();
        id=bundle.getInt("id");
        dh=new DBopenHelper(this);
        db=dh.getReadableDatabase();
        cursor=db.rawQuery("select * from note1 where _id=?",new String[]{String.valueOf(id)});
        if(cursor.moveToFirst()){
            title=cursor.getString(1);
            mcol.setTitle(title);
            mcol.setExpandedTitleColor(Color.WHITE);
            mcol.setCollapsedTitleTextColor(Color.WHITE);
            tvContent.setText(cursor.getString(2));
        }
        cursor.close();
        db.close();
        String path = Environment.getExternalStorageDirectory().getPath() +"/myImage2/"+title+".jpg";
       File file = new File(path);
        if(file.exists()){
            final Bitmap bm = BitmapFactory.decodeFile(path);
            imageView.setImageBitmap(bm);
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    LayoutInflater mInflater = LayoutInflater.from(ViewNote.this);
                    View view = mInflater.inflate(R.layout.note_bigimage, null);
                    final AlertDialog dialog = new AlertDialog.Builder(ViewNote.this).create();
                    ImageView im= (ImageView) view.findViewById(R.id.large_image);
                    im.setImageBitmap(bm);

                    dialog.setView(view);
                    dialog.show();
                    view.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            dialog.cancel();
                        }
                    });


                }
            });
         }

      fab.setOnClickListener(new View.OnClickListener() {
          @Override
            public void onClick(View v) {
                Intent intent=new Intent(ViewNote.this, NoteMain.class);
                startActivity(intent);
                ViewNote.this.finish();
           }
       });

    }
}
